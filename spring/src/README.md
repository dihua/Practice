# spring 启动过程

## 入口： web.xml

<!--Spring上下文监听器 ContextLoaderListener-->

```xml
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>

ContextLoaderListener 实现 ServletContextListener接口，
随 web 应用的启动而启动，只初始化一次，随 web 应用的停止而销毁。
在web应用启动的时候会调用 contextInitialized 方法，停止的时候会调用 contextDestroyed 方法。
```



## **从ContextLoaderListener出发**

```java
ContextLoaderListener.java
[org.springframework.web.context.ContextLoaderListener]
public class ContextLoaderListener extends ContextLoader implements ServletContextListener {
	...

    public void contextInitialized(ServletContextEvent event) {
        this.initWebApplicationContext(event.getServletContext());
    }

    public void contextDestroyed(ServletContextEvent event) {...}
}


ContextLoader.java
[org.springframework.web.context.ContextLoader]
public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
     ...
            
     this.configureAndRefreshWebApplicationContext(cwac, servletContext);
     ...
}
protected void configureAndRefreshWebApplicationContext(ConfigurableWebApplicationContext wac, ServletContext sc) {
     ...
     wac.refresh();
}  




AbstractApplicationContext.java
[org.springframework.context.support.AbstractApplicationContext]
    //构建IOC整个过程 refresh()
public void refresh() throws BeansException, IllegalStateException {
    	//startupShutdownMonitor 锁 刷新和销毁监视器
        synchronized(this.startupShutdownMonitor) {
            //准备工作 准备上下文环境，设置启动日期、活动标志和属性初始化
            this.prepa准备上下文环境reRefresh();
            //***很重要*** 
            //1 创建一个新的 BeanFactory、读取和解析 bean 定义。
            ConfigurableListableBeanFactory beanFactory = this.obtainFreshBeanFactory();
            //配置beanFactory上下文特征
            this.prepareBeanFactory(beanFactory);

            try {
                //允许子类对 BeanFactory 进行后续处理，默认实现为空，留给子类实现
                this.postProcessBeanFactory(beanFactory);
                //2 实例化和调用所有 BeanFactoryPostProcessor，包括其子类 BeanDefinitionRegistryPostProcessor
                this.invokeBeanFactoryPostProcessors(beanFactory);
                //3 注册所有的 BeanPostProcessor，将所有实现了 BeanPostProcessor 接口的类加载到 BeanFactory 中。
                this.registerBeanPostProcessors(beanFactory);
                //初始化消息资源 MessageSource。
                this.initMessageSource();
                //初始化应用的事件广播器 ApplicationEventMulticaster
                this.initApplicationEventMulticaster();
                //默认实现为空。为模板方法，提供给子类扩展实现，可以重写以添加特定于上下文的刷新工作
                this.onRefresh();
                //注册监听器
                this.registerListeners();
                //4 实例化所有剩余的非懒加载单例 bean
                this.finishBeanFactoryInitialization(beanFactory);
                //完成此上下文的刷新，主要是推送上下文刷新完毕事件（ContextRefreshedEvent ）到监听器
                this.finishRefresh();
            } catch (BeansException var9) {
                if (this.logger.isWarnEnabled()) {
                    this.logger.warn("Exception encountered during context initialization - cancelling refresh attempt: " + var9);
                }
                this.destroyBeans();
                this.cancelRefresh(var9);
                throw var9;
            } finally {
                this.resetCommonCaches();
            }

        }
    }
    
```

## **obtainFreshBeanFactory( )**

1. 解析所有Spring配置文件（通常我们会放在 resources 目录下）

2. 将所有 Spring 配置文件中的 bean 定义封装成 BeanDefinition，加载到 BeanFactory 中

      其中有三个缓存

   * （1）**beanDefinitionNames**:  bean的**beanName**的集合

     ```java
     private volatile List<String> beanDefinitionNames = new ArrayList(256);
     ```

   * （2）**beanDefinitionMap**: bean的**beanName** 和 **beanDefinition** 的映射

     ```java
     private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap(256);
     ```

   * （3）**aliasMap**: bean的**beanName** 和 **别名** 的映射

     ```java
     private final Map<String, String> aliasMap = new ConcurrentHashMap(16);
     ```

     

## **prepareBeanFactory(beanFactory) **

配置 beanFactory 的标准上下文特征，例如上下文的 ClassLoader、后置处理器等。

1. 注册3个默认环境 bean：**environment、systemProperties 、systemEnvironment**

   ```java
   if (!beanFactory.containsLocalBean("environment")) {
        beanFactory.registerSingleton("environment", this.getEnvironment());
   }
   
   if (!beanFactory.containsLocalBean("systemProperties")) {
        beanFactory.registerSingleton("systemProperties", this.getEnvironment().getSystemProperties());
   }
   
   if (!beanFactory.containsLocalBean("systemEnvironment")) {
        beanFactory.registerSingleton("systemEnvironment", this.getEnvironment().getSystemEnvironment());
   }
   ```

2. 注册 2 个 后置处理器：**ApplicationContextAwareProcessor ApplicationListenerDetector**

```java
beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(this));
```

