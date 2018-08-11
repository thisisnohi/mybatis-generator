# mybatis-generator
生成mybatis映射文件、实体、dao


## 工程相关
```
java -jar mybatis-generator-core-1.3.5.jar -configfile generatorConfig.xml -overwrite
Main-Class: org.mybatis.generator.api.ShellRunner -configfile generatorConfig.xml -overwrite
工程中远行: src/Shellrunner的main 增加args -configfile generatorConfig.xml -overwrite

```

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