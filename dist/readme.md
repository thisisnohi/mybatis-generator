# mybatis-generator
生成mybatis映射文件、实体、dao

20180811 增加打包后目录
## 工程打包后目录
```shell
.
├── src                             # 生成mybatis代码源码目录
├── clear.cmd                       # windows下清理生成代码目录脚本,清理 src、mapper
├── generator.bat                   # windows下根据配置文件生成数据库表对应实体和映射文件脚本
├── config.properties               # 生成内容配置: jpa、注解等
├── generatorConfig.xml             # 数据库驱动、数据库连接url、用户名、密码、表...配置信息
├── mybatis-generator-core-1.3.5.jar # 工程编译后jar
├── mysql-connector-java-5.1.30.jar #　mysql驱动
└── ojdbc6-11.2.0.3.jar             #  oracle驱动
```
### 配置
    * 数据库驱动配置 conf/generatorConfig.xml  classPathEntry节点location属性修改为相应驱动jar mysql/oracle
    * 数据连接修改conf/generatorConfig.xml  jdbcConnection节点修改相应数据库的连接串 mysql/oracle
    * RenameSqlMapperPlugin 修改mapper.xml的文件名
    * RenameJavaMapperPlugin 修改dao文件的文件名
### 参数配置
* config.properties  mybatis-generator-core-1.3.5.jar/config.properties
    * 可以设置 mybatis DAO 接口是否需要注解@Component，如果不需要，则“＝”后的内容删除即可
    * 是否生成支持jpa方法: Jpa.flag=true  
    * Jpa　字段是否需要加上@column注释 Jpa.field.column.flag=false
    * 是否支持lombook注解，如果需要，则不会生成getter setter方法 :  getset.annotation=true
    * 20190731 config.properties 允许在jar外层配置，优先加载