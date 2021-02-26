---
title: "springIOC"
date: 2020-12-07T22:08:33+08:00
tags: [spring]
categories: [read]
---

参考
- [Spring分析](https://developer.ibm.com/zh/articles/j-lo-spring-principle/)
- [请别再问Spring Bean的生命周期了！](https://www.jianshu.com/p/1dec08d290c1)

思想：把对象创建和调用交给spring管理，降低耦合

实现：配置解析->工厂->创建对象(反射)->DI(注入属性)

## beans（演员）
创建

![](/images/read/spring/image003.png)

- BeanFactory：IOC容器基本实现，懒加载
- ListableBeanFactory：Bean是可列表的
- HierarchicalBeanFactory：Bean有继承关系
- AutowireCapableBeanFactory：Bean的自动装配规则
- DefaultListableBeanFactory：默认实现

定义

![](/images/read/spring/image004.png)

解析

![](/images/read/spring/image005.png)

## context（舞台）
>给Spring提供运行时的环境（Ioc容器），用以保存各个对象的状态。整合了大部分功能

Context类图

![](/images/read/spring/image006.png)

- ResourceLoader：访问外部资源
- ConfigurableApplicationContext：可动态添加或修改已有的配置信息
    - AbstractRefreshableApplicationContext（常用）

作用
- 标识应用环境
- 创建Bean对象
- 保存对象关系表
- 捕获各种事件

## core（道具）
>定义了资源的访问方式

Resource类图

![](/images/read/spring/image007.png)

Context和Resource类关系图

![](/images/read/spring/image008.png)

## ioc
`AbstractApplicationContext`
```java
public void refresh() throws BeansException, IllegalStateException {
    synchronized (this.startupShutdownMonitor) {
        // Prepare this context for refreshing.
        prepareRefresh();

        // Tell the subclass to refresh the internal bean factory.
        ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();//创建BeanFactory

        // Prepare the bean factory for use in this context.
        prepareBeanFactory(beanFactory);//配置BeanFactory：添加一些Spring本身需要的一些工具类

        try {
            // Allows post-processing of the bean factory in context subclasses.
            postProcessBeanFactory(beanFactory);

            // Invoke factory processors registered as beans in the context.
            invokeBeanFactoryPostProcessors(beanFactory);//对已经构建的 BeanFactory 的配置做修改（实现BeanFactoryPostProcessor接口）

            // Register bean processors that intercept bean creation.
            registerBeanPostProcessors(beanFactory);//对以后再创建 Bean 的实例对象时添加一些自定义的操作（实现BeanPostProcessor接口）

            // Initialize message source for this context.
            initMessageSource();

            // Initialize event multicaster for this context.
            initApplicationEventMulticaster();//初始化监听事件

            // Initialize other special beans in specific context subclasses.
            onRefresh();

            // Check for listener beans and register them.
            registerListeners();//对系统的其他监听者的注册（实现ApplicationListener接口）

            // Instantiate all remaining (non-lazy-init) singletons.
            finishBeanFactoryInitialization(beanFactory);//创建 Bean 实例并构建 Bean 的关系网

            // Last step: publish corresponding event.
            finishRefresh();
        } catch (BeansException ex) {

            // Destroy already created singletons to avoid dangling resources.
            destroyBeans();

            // Reset 'active' flag.
            cancelRefresh(ex);

            // Propagate exception to caller.
            throw ex;
        } finally {
            // Reset common introspection caches in Spring's core, since we
            // might not ever need metadata for singleton beans anymore...
            resetCommonCaches();
        }
    }
}
```
### 构建BeanFactory
`AbstractRefreshableApplicationContext`
```java
protected final void refreshBeanFactory() throws BeansException {
    if (hasBeanFactory()) {
        destroyBeans();
        closeBeanFactory();
    }
    try {
        DefaultListableBeanFactory beanFactory = createBeanFactory();//BeanFactory的原始对象
        beanFactory.setSerializationId(getId());
        customizeBeanFactory(beanFactory);
        loadBeanDefinitions(beanFactory);//加载、解析Bean的定义
        this.beanFactory = beanFactory;
    } catch (IOException ex) {
        throw new ApplicationContextException("I/O error parsing bean definition source for " + getDisplayName(), ex);
    }
}
```

`DefaultListableBeanFactory`类图

![](/images/read/spring/image009.png)

### Bean的实例化
`DefaultListableBeanFactory`
```java
public void preInstantiateSingletons() throws BeansException {
    // Iterate over a copy to allow for init methods which in turn register new bean definitions.
    // While this may not be part of the regular factory bootstrap, it does otherwise work fine.
    List<String> beanNames = new ArrayList<>(this.beanDefinitionNames);

    // Trigger initialization of all non-lazy singleton beans...
    for (String beanName : beanNames) {
        RootBeanDefinition bd = getMergedLocalBeanDefinition(beanName);
        if (!bd.isAbstract() && bd.isSingleton() && !bd.isLazyInit()) {
            if (isFactoryBean(beanName)) {
                Object bean = getBean(FACTORY_BEAN_PREFIX + beanName);
                if (bean instanceof FactoryBean) {
                    FactoryBean<?> factory = (FactoryBean<?>) bean;
                    boolean isEagerInit;
                    if (System.getSecurityManager() != null && factory instanceof SmartFactoryBean) {
                        isEagerInit = AccessController.doPrivileged(
                                (PrivilegedAction<Boolean>) ((SmartFactoryBean<?>) factory)::isEagerInit,
                                getAccessControlContext());
                    } else {
                        isEagerInit = (factory instanceof SmartFactoryBean &&
                                ((SmartFactoryBean<?>) factory).isEagerInit());
                    }
                    if (isEagerInit) {
                        getBean(beanName);
                    }
                }
            } else {
                getBean(beanName);//创建实例
            }
        }
    }
}
```

`AbstractAutowireCapableBeanFactory`
```java
protected Object doCreateBean(String beanName, RootBeanDefinition mbd, @Nullable Object[] args) throws BeanCreationException {
    // Instantiate the bean.
    BeanWrapper instanceWrapper = null;
    if (instanceWrapper == null) {
        instanceWrapper = createBeanInstance(beanName, mbd, args);//实例化阶段
    }

    // Initialize the bean instance.
    Object exposedObject = bean;
    try {
        populateBean(beanName, mbd, instanceWrapper);//属性赋值阶段
        exposedObject = initializeBean(beanName, exposedObject, mbd);//初始化阶段
    }
}
```

## 扩展点
### 第一大类：影响多个Bean的接口
#### `InstantiationAwareBeanPostProcessor`
>作用于实例化阶段的前后

```java
@Override
protected Object createBean(String beanName, RootBeanDefinition mbd, @Nullable Object[] args) throws BeanCreationException {
    try {
        // Give BeanPostProcessors a chance to return a proxy instead of the target bean instance.
        Object bean = resolveBeforeInstantiation(beanName, mbdToUse);// postProcessBeforeInstantiation方法调用点
        if (bean != null) {
            return bean;
        }
    }
    
    try {   
        Object beanInstance = doCreateBean(beanName, mbdToUse, args);
        if (logger.isTraceEnabled()) {
            logger.trace("Finished creating instance of bean '" + beanName + "'");
        }
        return beanInstance;
    }
}

protected void populateBean(String beanName, RootBeanDefinition mbd, @Nullable BeanWrapper bw) {
   // Give any InstantiationAwareBeanPostProcessors the opportunity to modify the
   // state of the bean before properties are set. This can be used, for example,
   // to support styles of field injection.
   boolean continueWithPropertyPopulation = true;
   if (!mbd.isSynthetic() && hasInstantiationAwareBeanPostProcessors()) {
      for (BeanPostProcessor bp : getBeanPostProcessors()) {
         if (bp instanceof InstantiationAwareBeanPostProcessor) {
            InstantiationAwareBeanPostProcessor ibp = (InstantiationAwareBeanPostProcessor) bp;
            if (!ibp.postProcessAfterInstantiation(bw.getWrappedInstance(), beanName)) {// postProcessAfterInstantiation方法调用点
               continueWithPropertyPopulation = false;
               break;
            }
         }
      }
   }
}

```

#### `BeanPostProcessor`
>作用于初始化阶段的前后

执行顺序：PriorityOrdered > Ordered


### 第二大类：只调用一次的接口
#### Aware
>让我们能够拿到Spring容器中的一些资源

Aware Group1
- BeanNameAware
- BeanClassLoaderAware
- BeanFactoryAware

Aware Group2
- EnvironmentAware
- EmbeddedValueResolverAware：实现该接口能够获取Spring EL解析器，用户的自定义注解需要支持spel表达式的时候使用
- ApplicationContextAware(ResourceLoaderAware\ApplicationEventPublisherAware\MessageSourceAware)

```java
// 初始化阶段
protected Object initializeBean(final String beanName, final Object bean, @Nullable RootBeanDefinition mbd) {
    invokeAwareMethods(beanName, bean);// Group1中的三个Bean开头的Aware

    Object wrappedBean = bean;
    
    // 这里调用的是Group2中的几个Aware，
    // 而实质上这里就是前面所说的BeanPostProcessor的调用点！
    // 这里是通过BeanPostProcessor（ApplicationContextAwareProcessor）实现的。
    wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
    
    invokeInitMethods(beanName, wrappedBean, mbd);// InitializingBean调用点
    
    wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);// BeanPostProcessor的另一个调用点

    return wrappedBean;
}
```

#### 生命周期接口
`InitializingBean`：对应生命周期的初始化阶段

`DisposableBean`：对应生命周期的销毁阶段，以ConfigurableApplicationContext#close()方法作为入口