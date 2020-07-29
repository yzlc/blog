---
title: "quartz"
date: 2019-02-08T10:32:33+08:00
tags: [quartz]
categories: [笔记]
---

>[quartz](http://quartz-scheduler.org/)Java下的一种作业控制开源框架。
## 核心概念
1. Job 表示一个工作，要执行的具体内容。此接口中只有一个方法，如下：  
`void execute(JobExecutionContext context)`
2. JobDetail 表示一个具体的可执行的调度程序，Job 是这个可执行程调度程序所要执行的内容，另外 JobDetail 还包含了这个任务调度的方案和策略。 
3. Trigger 代表一个调度参数的配置，什么时候去调。 
4. Scheduler 代表一个调度容器，一个调度容器中可以注册多个 JobDetail 和 Trigger。当 Trigger 与 JobDetail 组合，就可以被 Scheduler 容器调度了。 

## 快速入门
1. 下载[最新稳定版本](http://quartz-scheduler.org/downloads/)
2. 解压安装包。将lib/目录下的quartz-xxx.jar（其中xxx是版本号）放在应用的classpath下
3. 在classpath下创建名为quartz.properties的配置文件。基本配置如下:
```properties
#调度程序的名称
org.quartz.scheduler.instanceName = MyScheduler
#线程池中有3个线程，这意味着最多可以同时运行3个job
org.quartz.threadPool.threadCount = 3
#quartz的所有数据，包括job和trigger的配置，都会存储在内存中
org.quartz.jobStore.class = org.quartz.simpl.RAMJobStore
```
4. 启动示例应用程序。下面的示例代码，获取scheduler实例对象，启动，然后关闭。  
QuartzTest.java
```java
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class QuartzTest {

  public static void main(String[] args) {

      try {
          // Grab the Scheduler instance from the Factory
          Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

          // and start it off
          scheduler.start();

          scheduler.shutdown();

      } catch (SchedulerException se) {
          se.printStackTrace();
      }
  }
}
```

## Quartz API
>Quartz API的关键接口是：
>- Scheduler - 与调度程序交互的主要API。
>- Job - 由希望由调度程序执行的组件实现的接口。
>- JobDetail - 用于定义作业的实例。
>- Trigger - 定义执行给定作业的计划的组件。
>- JobBuilder - 用于定义/构建JobDetail实例，用于定义作业的实例。
>- TriggerBuilder - 用于定义/构建触发器实例。
### Scheduler
- 生命周期,从SchedulerFactory创建它时开始，到Scheduler调用shutdown()方法时结束  
- Scheduler被创建后，可以增加、删除和列举Job和Trigger，以及执行其它与调度相关的操作（如暂停Trigger）
- Scheduler只有在调用start()方法后，才会真正地触发trigger（即执行job）
- 代码片段
```java
// Grab the Scheduler instance from the Factory
Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

// and start it off
scheduler.start();

scheduler.shutdown();
```

### Job和JobDetail
>job - 实现了Job接口的类，该接口只有一个方法  
>JobDetail - Job实例所包含的属性

- Job接口：
```java
package org.quartz;

public interface Job {

public void execute(JobExecutionContext context)
  throws JobExecutionException;
}
```
- 代码片段
```java
// define the job and tie it to our HelloJob class
JobDetail job = newJob(HelloJob.class)
  .withIdentity("myJob", "group1") // name "myJob", group "group1"
  .build();

// Trigger the job to run now, and then every 40 seconds
Trigger trigger = newTrigger()
  .withIdentity("myTrigger", "group1")
  .startNow()
  .withSchedule(simpleSchedule()
      .withIntervalInSeconds(40)
      .repeatForever())            
  .build();

// Tell quartz to schedule the job using our trigger
sched.scheduleJob(job, trigger);
```
- 定义作业类“HelloJob”：
```java
public class HelloJob implements Job {

    public HelloJob() {
    }
    
    public void execute(JobExecutionContext context)
      throws JobExecutionException
    {
      System.err.println("Hello!  HelloJob is executing.");
    }
}
```

### Triggers
>公共属性
>- jobKey属性：当trigger触发时被执行的job的身份
>- startTime属性：设置trigger第一次触发的时间
>- endTime属性：表示trigger失效的时间点
- Simple Trigger:
SimpleTrigger：在具体的时间点执行一次，或者在具体的时间点执行，并且以指定的间隔重复执行若干次。
- CronTrigger:基于日历的概念启动计划
    - Cron Expressions
        - 格式：`秒 分 时 日 月 星期 年`
        - 示例(每5分钟触发一次):`"0 0/5 - - - ?"`
    - 代码片段
    ```java
    trigger = newTrigger()
      .withIdentity("trigger3", "group1")
      .withSchedule(cronSchedule("0 0/2 8-17 - - ?"))
      .forJob("myJob", "group1")
      .build();
    ```

## Job Stores
>负责跟踪提供给调度程序的所有“工作数据”：jobs，triggers，日历等。
### RAMJobStore
- 优点:使用简单，所有数据保存在RAM中，性能高
- 缺点:当应用程序结束（或崩溃）时，所有调度信息都将丢失
- 配置:`org.quartz.jobStore.class = org.quartz.simpl.RAMJobStore`

### JDBC JobStore
- 优点:所有数据保存在数据库中
- 缺点:配置比RAMJobStore复杂，而且也不是那么快
- 使用:
    - 创建数据库表。在Quartz发行版的“docs / dbTables”目录中有SQL脚本[点击下载 quartz-1.7.2.sql](/files/note/quartz-1.7.2.sql)。
        - QRTZ_CALENDARS: 存储Calendar信息
        - QRTZ_CRON_TRIGGERS: 存储Cron表达式和时区信息 
        - QRTZ_FIRED_TRIGGERS: 存储与已触发的Trigger相关的状态信息，以及相联Job的执行信息
        - QRTZ_PAUSED_TRIGGER_GRPS 存储已暂停的 Trigger 组的信息 
        - QRTZ_SCHEDULER_STATE: 存储少量的有关Scheduler的状态信息，和别的Scheduler实例
        - QRTZ_LOCKS: 存储程序的悲观锁的信息
        - QRTZ_JOB_DETAILS: 存储已配置的Job的详细信息
        - QRTZ_JOB_LISTENERS: 存储已配置的JobListener的信息 
        - QRTZ_SIMPLE_TRIGGERS: 存储简单的Trigger，包括重复次数，间隔，以及已触的次数
        - QRTZ_BLOG_TRIGGERS: Trigger作为Blob类型存储
        - QRTZ_TRIGGER_LISTENERS: 存储已配置的TriggerListener的信息 
        - QRTZ_TRIGGERS: 存储已配置的Trigger的信息
    - 配置JobStoreTx管理事务
        `org.quartz.jobStore.class = org.quartz.impl.jdbcjobstore.JobStoreTX`
    - 配置DriverDelegate执行特定数据库可能需要的任何JDBC工作
        `org.quartz.jobStore.driverDelegateClass = org.quartz.impl.jdbcjobstore.StdJDBCDelegate`
    - 配置表前缀
        `org.quartz.jobStore.tablePrefix = QRTZ_`
    - 配置DataSource
        `org.quartz.jobStore.dataSource = myDS`

### TerracottaJobStore
提供了一种不需要使用数据的可伸缩，健壮的方案，而不使用数据库
- 配置:
```properties
org.quartz.jobStore.class = org.terracotta.quartz.TerracottaJobStore
org.quartz.jobStore.tcConfigUrl = localhost:9510
```