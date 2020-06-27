## sentinel使用说明
1. 下载sentinel的安装包
    windows:
    - 打开git bash > wget https://github.com/alibaba/Sentinel/releases/download/1.7.1/sentinel-dashboard-1.7.1.jar
    linux & Mac OS 
    - 打开终端 wget https://github.com/alibaba/Sentinel/releases/download/1.7.1/sentinel-dashboard-1.7.1.jar
    
2. 启动sentinel管理界面
```sh
nohup java -jar sentinel-dashboard-1.7-server.port=7070 &
```
这就是一个java项目，jar包启动，server.port=7070 更改服务端口

3. 打开浏览器输入`127.0.0.1:7070` 即可访问服务
使用默认的账号「sentinel / sentinel」进行登陆，进入 Sentinel 首页。


4. 引入依赖，新增配置
引入一下依赖

```xml
<!-- Sentinel 核心库 -->
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-core</artifactId>
    <version>1.7.1</version>
</dependency>

<!-- Sentinel 接入控制台 -->
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-transport-simple-http</artifactId>
    <version>1.7.1</version>
</dependency>

<!-- Sentinel 对 SpringMVC 的支持 -->
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-spring-webmvc-adapter</artifactId>
    <version>1.7.1</version>
</dependency>

<!-- Sentinel 对【热点参数限流】的支持 -->
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-parameter-flow-control</artifactId>
    <version>1.7.1</version>
</dependency>
<!-- Sentinel 对 Spring AOP 的拓展 -->
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-annotation-aspectj</artifactId>
    <version>1.7.1</version>
</dependency>
```

在项目的resource目录下创建`sentinel.properties`文件，并添加一下配置：
````
csp.sentinel.dashboard.server=127.0.0.1:7070

springboot 2.3.0+ 以后取消了默认依赖，需要手动导入

```
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
```

