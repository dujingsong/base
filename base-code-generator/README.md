# 代码生成模块

## 代码生成模块，采用mybatis-plus-generator依赖实现

### usage

引入依赖

~~~
<dependency>
    <groupId>cn.imadc.application</groupId>
    <artifactId>base-code-generator</artifactId>
</dependency>
~~~

生成基础的包层次
controller、service、serviceImpl、entity、dto

已经预置基础的增删改查接口


用法
~~~
import cn.imadc.application.base.codegenerator.BaseFastAutoGeneratorFaced;

// mysql相关信息：连接url、用户名、密码
static String URL = "jdbc:mysql://host:3306/db_name";
static String DB_USER_NAME = "数据库用户名";
static String DB_PASSWORD = "数据库密码";

// 代码生成后存放的本地磁盘位置
static String OUTPUT_DIR = "D://codeGenerate";

// 指定表名
static String TABLE_NAME = "table_name";

// 代码所在的包名（注意这里不要填写最后一级报名）
static String PACKAGE_NAME = "cn.imadc.base.project.module";
// 代码所在的包名，最后一级包名
static String MODULE_NAME = "biz";
// 注释上的作者
static String AUTHOR = "user";

public static void main(String[] args) {
    BaseFastAutoGeneratorFaced.generate(URL, DB_USER_NAME, DB_PASSWORD, OUTPUT_DIR, TABLE_NAME, PACKAGE_NAME,
            MODULE_NAME, AUTHOR);
}
~~~
