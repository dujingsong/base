# 基础架构-基础框架

-------------------------------------------------------------------------------

## 📚简介

为了更加方便和准确的开始一个项目，节省使用常用组件时查找资料的时间，更加聚焦于业务实现，将日常使用到的组件封装，统一依赖与使用行为。

-------------------------------------------------------------------------------

## 🛠️包含组件

基础架构-基础框架提供以下组件：

| 模块                                                                           | 介绍                     |
|------------------------------------------------------------------------------|------------------------|
| [base-auth-jwt](base-auth-jwt/README.md)                                     | 基础架构-基础框架-JWT实现封装      |
| [base-code-generator](base-code-generator/README.md)                         | 基础架构-基础框架-代码生成工具       |
| [base-common](base-common/README.md)                                         | 基础架构-基础框架-公用组件         |
| [base-data-structure](base-data-structure/README.md)                         | 基础架构-基础框架-常用数据结构类型     |
| [base-datasource-decorator-p6spy](base-datasource-decorator-p6spy/README.md) | 基础架构-基础框架-数据源sql打印     |
| [base-datasource-druid](base-datasource-druid/README.md)                     | 基础架构-基础框架-数据库连接池druid  |
| [base-dynamic-datasource](base-dynamic-datasource/README.md)                 | 基础架构-基础框架-动态数据源        |
| [base-elastic-job](base-elastic-job/README.md)                               | 基础架构-基础框架-分布式定时任务      |
| [base-lettuce](base-lettuce/README.md)                                       | 基础架构-基础框架-redis交互组件    |
| [base-log](base-log/README.md)                                               | 基础架构-基础框架-日志打印组件       |
| [base-mybatis-plus](base-mybatis-plus/README.md)                             | 基础架构-基础框架-mybatis-plus |
| [base-netty](base-netty/README.md)                                           | 基础架构-基础框架-netty        |
| [base-redisson](base-redisson/README.md)                                     | 基础架构-基础框架-redisson     |
| [base-rocketmq](base-rocketmq/README.md)                                     | 基础架构-基础框架-rocketmq     |
| [base-samples](base-samples/README.md)                                       | 基础架构-基础框架-示例工程         |
| [base-skeleton-dependency](base-skeleton-dependency/README.md)               | 基础架构-基础框架-骨架工程依赖       |
| [base-toolkit](base-toolkit/README.md)                                       | 基础架构-基础框架-常用工具类        |

可以根据需求引入各个模块。

-------------------------------------------------------------------------------

## 🎋版本历史

[版本历史](doc/VERSION.md)

### 更新项目的版本

~~~
1. 更新版本号：
mvn versions:set "-DnewVersion=release-0.0.2.1"

2. 回滚： 
mvn versions:revert

3. 提交： 
mvn versions:commit
~~~

### markdown中可直接黏贴使用的图标
[emojipedia](https://www.emojipedia.org/)