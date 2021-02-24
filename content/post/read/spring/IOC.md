---
title: "springIOC"
date: 2020-12-07T22:08:33+08:00
tags: [spring]
categories: [read]
---

参考：[Spring分析](https://developer.ibm.com/zh/articles/j-lo-spring-principle/)

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
        }

        catch (BeansException ex) {
            if (logger.isWarnEnabled()) {
                logger.warn("Exception encountered during context initialization - " +
                        "cancelling refresh attempt: " + ex);
            }

            // Destroy already created singletons to avoid dangling resources.
            destroyBeans();

            // Reset 'active' flag.
            cancelRefresh(ex);

            // Propagate exception to caller.
            throw ex;
        }

        finally {
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
    }
    catch (IOException ex) {
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
    if (logger.isTraceEnabled()) {
        logger.trace("Pre-instantiating singletons in " + this);
    }

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
                    }
                    else {
                        isEagerInit = (factory instanceof SmartFactoryBean &&
                                ((SmartFactoryBean<?>) factory).isEagerInit());
                    }
                    if (isEagerInit) {
                        getBean(beanName);
                    }
                }
            }
            else {
                getBean(beanName);//创建实例
            }
        }
    }

    // Trigger post-initialization callback for all applicable beans...
    for (String beanName : beanNames) {
        Object singletonInstance = getSingleton(beanName);
        if (singletonInstance instanceof SmartInitializingSingleton) {
            SmartInitializingSingleton smartSingleton = (SmartInitializingSingleton) singletonInstance;
            if (System.getSecurityManager() != null) {
                AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
                    smartSingleton.afterSingletonsInstantiated();
                    return null;
                }, getAccessControlContext());
            }
            else {
                smartSingleton.afterSingletonsInstantiated();
            }
        }
    }
}
```
