# mybatis-generator
生成mybatis映射文件、实体、dao


## 工程相关
```
java -jar mybatis-generator-core-1.3.5.jar -configfile generatorConfig.xml -overwrite
Main-Class: org.mybatis.generator.api.ShellRunner -configfile generatorConfig.xml -overwrite
工程中远行: src/Shellrunner的main 增加args -configfile generatorConfig.xml -overwrite

```
* 配置
    * 数据库驱动配置 conf/generatorConfig.xml  classPathEntry节点location属性修改为相应驱动jar mysql/oracle
    * 数据连接修改conf/generatorConfig.xml  jdbcConnection节点修改相应数据库的连接串 mysql/oracle

* 运行工程
    * org.mybatis.generator.api.ShellRunner -configfile generatorConfig.xml -overwrite

* jar 包运行
    * java -jar mybatis-generator-core-1.3.5.jar -configfile generatorConfig.xml -overwrite


## 功能相关
* mybatis3生成文件各种类
  *  org.mybatis.generator.codegen.mybatis3.xmlmapper.elements
```
mybatis3生成文件各种类
org.mybatis.generator.codegen.mybatis3.xmlmapper.elements

BaseRecordGenerator.java   entity实体类

DefaultCommentGenerator.java  注释  javaFormatter 　java模型 DefaultCommentGenerator.addModelClassComment()
InnerClass.java  getFormattedContent
   实体类注释 
	
根据字段生成select by example
SelectByExampleElementGenerator

DatabaseIntrospector.calculateIntrospectedTables(); 计算表的各种信息
DatabaseIntrospector.calculatePrimaryKey计算主键
```  

* 解析generatorConfig.xml文件 MyBatisGeneratorConfigurationParser.parseConfiguration parseContext

* 生成entity类 TopLevelClass.java
* 生成字段类 Field.java

## 修改记录
* 增加生成JPA功能，修改配置文件 config.properties Jpa.flag=true
* 增加Jpa 字段配置，是否需要在字段上增加@column　：Jpa.field.column.flag
* 增加是否增加lombok.Getter/Setter注解,增加注解，则不需要生成geeter setter方法: getset.annotation=true

20180811 增加打包后目录
## 工程打包后目录
```shell
.
├── src                             # 生成mybatis代码源码目录
├── clear.cmd                       # windows下清理生成代码目录脚本,清理 src、mapper
├── generator.bat                   # windows下根据配置文件生成数据库表对应实体和映射文件脚本
├── generatorConfig.xml             # 数据库驱动、数据库连接url、用户名、密码、表...配置信息
├── mybatis-generator-core-1.3.5.jar # 工程编译后jar
├── mysql-connector-java-5.1.30.jar #　mysql驱动
└── ojdbc6-11.2.0.3.jar             #  oracle驱动
```
### 配置
    * 数据库驱动配置 conf/generatorConfig.xml  classPathEntry节点location属性修改为相应驱动jar mysql/oracle
    * 数据连接修改conf/generatorConfig.xml  jdbcConnection节点修改相应数据库的连接串 mysql/oracle
    
### 参数配置
* config.properties  mybatis-generator-core-1.3.5.jar/config.properties
    * 可以设置 mybatis DAO 接口是否需要注解@Component，如果不需要，则“＝”后的内容删除即可
    * 是否生成支持jpa方法: Jpa.flag=true  
    * Jpa　字段是否需要加上@column注释 Jpa.field.column.flag=false
    * 是否支持lombook注解，如果需要，则不会生成getter setter方法 :  getset.annotation=true