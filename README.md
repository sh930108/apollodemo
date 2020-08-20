# apollo demo
apolloJava客户端用法demo

##### 本demo 采用 官网springboot方式连接

获取配置方式
```
## 获取变量的方式有俩种 见FactoryConfiguration
#1. config方式
Config config = ConfigService.getConfig("values.yaml");
#2. @Value方式
@Value("${feign.client.url:yes}")
private String key2;

## feign 配置中心获取配置  demo中是用FakeController进行测试
FakeFeignClient
```

##### 热部署
```
## 添加listener方式
config.addChangeListener(new SimpleConfigChangeListener());
## 注解方式
@ApolloConfigChangeListener("values.yaml")

日志级别动态更新示例
org.xiying.apollodemo.apollodemo.listener.LogChangeListener
```


##### demo中 values.yaml配置
```
middleware:
  consul:
    host: localhost
    port: 8500

  postgresql:
    version: 11.4
    host: pg-653ccabb-stolon-proxy.92ece1701f3b49bfbd182dbefb5dfda8-dbaas.svc
    port: 5432
    dbname: yres
    username: postgres
    password: Hik12345
    driverclass: org.postgresql.Driver

feign:
    client:
        url: http://127.0.0.1:8080/test

logging:
    level:
        root: warn
```
#config variables begin


##### 更多demo
官方更多使用示例
https://github.com/ctripcorp/apollo-use-cases.git