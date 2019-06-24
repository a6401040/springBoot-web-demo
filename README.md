# springBoot-web-demo
mybatisPlus集成对mybatis进行补全

>Spring Boot基础学习工程myself （包含mysql主从同步，读写分离，mybatis，mybatisPlus，HTTP-RESTFUL解析...持续更新中） 编码基于 阿里巴巴编码规范编码
可以配置一主一从（默认）和一主多从:

- [mybatisPlus-api2doc（推荐）](https://github.com/a6401040/springBoot-web-demo/tree/mybatisPlus-api2doc): 在mybatisPlus集成的基础上，再集成了api2doc（替代swagger），完善restful doc
- [mybatisPlus](https://github.com/a6401040/springBoot-web-demo/tree/mybatisPlus): 使用了多数据源的 RESTful API 接口，mybatis读写分离，支持mybatisPlus下操作多数据源读写分离配置，自定义规则
- [master](https://github.com/a6401040/springBoot-web-demo): springBoot+mybatis原始工程 使用了多数据源的 RESTful API 接口，mybatis读写分离

>注：简单说明一下为什么不使用dynamic-datasource-spring-boot-starter这个动态切换数据源的工程
因为目前最新版本不支持分布式事务，我们需要自己去定义一系列的功能实现
为以后集成多数据源情况下的分布式事务做的一个基础版本

