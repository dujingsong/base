# 日志打印模块

## 日志打印组件，采用spring-boot-starter-logging作为依赖实现

### usage

引入依赖
~~~
<dependency>
    <groupId>cn.imadc.application</groupId>
    <artifactId>base-log</artifactId>
</dependency>
~~~


### 💡 原理

#### 🏷️ 装配原理
**spring-boot-starter**中引入了**spring-boot-starter-logging**依赖，**spring-boot-starter-logging**中引入了
**logback-classic**以及**slf4j-api**。

也就是说SpringBoot默认采用的是**slf4j**门面 + **logback**作为日志打印的实现

**spring-boot-starter-logging**中使用了**log4j-to-slf4j**和**jul-to-slf4j**兼容了log4j以及jul日志打印门面。

**spring-boot**下**spring.factories**文件中含有下面的装配类
~~~
org.springframework.boot.logging.logback.LogbackLoggingSystem.Factory
~~~
根据是否含有**ch.qos.logback.classic.LoggerContext**这个类来判断是否装配LogBack工厂。
~~~
@Order(Ordered.LOWEST_PRECEDENCE)
public static class Factory implements LoggingSystemFactory {

    private static final boolean PRESENT = ClassUtils.isPresent("ch.qos.logback.classic.LoggerContext",
            Factory.class.getClassLoader());

    @Override
    public LoggingSystem getLoggingSystem(ClassLoader classLoader) {
        if (PRESENT) {
            return new LogbackLoggingSystem(classLoader);
        }
        return null;
    }

}
~~~

#### 🏷️ 配置文件

装配时会获取classpath下的xml格式的配置文件，首先加载如下的配置文件
~~~
 { "logback-test.groovy", "logback-test.xml", "logback.groovy", "logback.xml" }
~~~
然后尝试加载可以被spring先行控制的配置文件，使用这种方式可以使用spring的profile功能。
~~~
 { "logback-test-spring.groovy", "logback-test-spring.xml", "logback-spring.groovy", "logback-spring.xml" }
~~~

所以可以通过在classpath下添加**logback-spring.xml**配置文件来控制日志打印细则。

#### 🏷️ 日志打印细则
**logback-spring.xml**配置细则：

https://logback.qos.ch/manual/configuration.html

##### 📍 输出到控制台
~~~
<configuration>

  <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <!-- encoders are assigned the type
         ch.qos.logback.classic.encoder.PatternLayoutEncoder by default -->
    <encoder>
      <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
    </encoder>
  </appender>

  <root level="debug">
    <appender-ref ref="STDOUT" />
  </root>
</configuration>
~~~

root节点中控制了日志打印的级别，可以是：
~~~
TRACE, DEBUG, INFO, WARN, ERROR, FATAL, OFF
~~~
优先级依次递增，例如设置了**ERROR**，那么TRACE, DEBUG, INFO, WARN级别的日志不会打印。
不过也可以针对某些包进行特殊设置：
~~~
<configuration>

  <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <!-- encoders are assigned the type
         ch.qos.logback.classic.encoder.PatternLayoutEncoder by default -->
    <encoder>
      <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
    </encoder>
  </appender>

  <logger name="chapters.configuration" level="INFO"/>

  <!-- Strictly speaking, the level attribute is not necessary since -->
  <!-- the level of the root level is set to DEBUG by default.       -->
  <root level="DEBUG">
    <appender-ref ref="STDOUT" />
  </root>

</configuration>
~~~
chapters.configuration下的日志将会以INFO级别输出。

##### 📍 输出到文件

https://logback.qos.ch/manual/appenders.html

~~~
<configuration>

  <appender name="FILE" class="ch.qos.logback.core.FileAppender">
    <file>myApp.log</file>

    <encoder>
      <pattern>%date %level [%thread] %logger{10} [%file:%line] %msg%n</pattern>
    </encoder>
  </appender>

  <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
      <pattern>%msg%n</pattern>
    </encoder>
  </appender>

  <root level="debug">
    <appender-ref ref="FILE" />
    <appender-ref ref="STDOUT" />
  </root>
</configuration>
~~~
日志不仅会输出到控制台还会输出到文件**myApp.log**

##### 📍 输出到文件 + 按照日期打包文件
~~~
<configuration>
  <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>logFile.log</file>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <!-- daily rollover -->
      <fileNamePattern>logFile.%d{yyyy-MM-dd}.log</fileNamePattern>

      <!-- keep 30 days' worth of history capped at 3GB total size -->
      <maxHistory>30</maxHistory>
      <totalSizeCap>3GB</totalSizeCap>

    </rollingPolicy>

    <encoder>
      <pattern>%-4relative [%thread] %-5level %logger{35} - %msg%n</pattern>
    </encoder>
  </appender> 

  <root level="DEBUG">
    <appender-ref ref="FILE" />
  </root>
</configuration>
~~~
上面的配置代表每天生成一个文件，保留30天，所有文件大小不超过30GB。

##### 📍 输出到文件 + 按照日期 + 限制单个文件大小打包文件
~~~
<configuration>
  <appender name="ROLLING" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>mylog.txt</file>
    <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
      <!-- rollover daily -->
      <fileNamePattern>mylog-%d{yyyy-MM-dd}.%i.txt</fileNamePattern>
       <!-- each file should be at most 100MB, keep 60 days worth of history, but at most 20GB -->
       <maxFileSize>100MB</maxFileSize>    
       <maxHistory>60</maxHistory>
       <totalSizeCap>20GB</totalSizeCap>
    </rollingPolicy>
    <encoder>
      <pattern>%msg%n</pattern>
    </encoder>
  </appender>


  <root level="DEBUG">
    <appender-ref ref="ROLLING" />
  </root>

</configuration>
~~~
上面的配置代表每天生成一个文件且每天的文件按照100mb切割成多个文件存储，保留60天，所有文件大小不超过20GB。

##### 📍 配置文件中使用占位符
~~~
<appender name="FILE" class="ch.qos.logback.core.RollingFileAppender">
    <file>${log.home}/myApp.log</file>

    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <!-- daily rollover -->
        <fileNamePattern>logFile.%d{yyyy-MM-dd}.log</fileNamePattern>

        <!-- keep 30 days' worth of history capped at 3GB total size -->
        <maxHistory>30</maxHistory>
        <totalSizeCap>3GB</totalSizeCap>

    </rollingPolicy>

    <encoder>
        <pattern>%date %level [%thread] %logger{10} [%file:%line] %msg%n</pattern>
    </encoder>
</appender>

~~~
其中${log.home}可以通过启动参数进行赋值，可以在不同的环境启动脚本中控制日志文件存储的位置。
~~~
-Dlog.home=/data/logs
~~~