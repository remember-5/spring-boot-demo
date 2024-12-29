## Introduction
spring-boot的部分demo，部分参考了http://www.iocoder.cn/


## 模块介绍

为了节省编译和加载时间，默认只开启了`demo-common`,需要用什么模块，就开启什么模块，reload maven 即可 

```shell
├── demo-admin # spring-boot-admin 监控模块
├── demo-common # 公共模块
├── demo-design-pattern # 设计模式部分代码
├── demo-dynamic-datasource # 动态数据源
├── demo-elasticsearch6 # es6 代码
├── demo-elasticsearch7 # es7 代码
├── demo-email # 发送邮件
├── demo-encrypt # 加密相关
├── demo-flowable # flowable
├── demo-interview # 面试题
├── demo-junit # junit测试用例
├── demo-jwt # jwt认证相关
├── demo-liquibase
├── demo-minio # minio 文件上传下载相关
├── demo-mongodb # mongodb测试用例
├── demo-mqtt # mqtt测试 
├── demo-mybatis-plus # mybatis-plus
├── demo-nacos # nacos配置中心
├── demo-oauth2 # oauth2相关代码
├── demo-office # office(excel/word/ppt)等操作
├── demo-package # 打包测试
├── demo-quartz # 定时任务
├── demo-rabbitmq # rabbitmq相关代码
├── demo-redis # redis 相关
├── demo-rocketmq # rocketmq相关
├── demo-security # spring security 相关
├── demo-sentinel # sentinel相关
├── demo-skywalking # skywalking
├── demo-spi # spi
├── demo-swagger # swagger2 knife4j-ui
├── demo-validation # 数据校验
├── demo-web # 普通web服务
├── demo-webflux # webflux相关 
├── demo-websocket # spring和netty-websocket结合使用代码
├── CHANGELOG.md # 更新日志
├── LICENSE # LICENSE
├── README.md # 介绍
├── pom.xml # pom
```

## FAQ
如果遇到Lombok失效，请升级Lombok的版本

## 打包
参考`demo-web`中的pom文件
```xml
<build>
        <finalName>demo</finalName>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${spring-boot.version}</version>
                <configuration> <!-- 这里添加启动路径 -->
                    <mainClass>com.remember.demo.web.SpringBootDemoWebApplication</mainClass>
                </configuration>
                <executions>
                    <execution>
                        <id>repackage</id>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```
# 感谢 JetBrains 免费的开源授权
<a href="https://www.jetbrains.com/?from=spring-boot-demo" target="_blank">
<img src="https://i.loli.net/2021/06/22/Kpd28P9GwhUlrck.png" height="200"/></a>
