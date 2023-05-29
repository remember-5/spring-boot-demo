## 写在前面
spring-boot的部分demo，部分参考了http://www.iocoder.cn/

把postgres 和 mysql 的 jpa & mybatisPlus使用写到一起，结合多数据源

## 模块介绍

| 模块 | 介绍 |  
| -- | -- |  
| demo-admin | spring-boot-admin模块
| demo-common | 公共模块
| demo-dynamic-datasource | springboot多数据demo
| demo-elasticsearch6 | springboot结合es
| demo-elasticsearch7 | springboot结合es
| demo-encrypt | 测试springboot结合加密
| demo-interview | 面试练习
| demo-junit | junit单元测试案例
| demo-jwt | jwt token-demo
| demo-liquibase| liquibase使用教程
| demo-minio | minio文件上传demo
| demo-mybatis-plus | springboot结合mybatisplus并且配置多数据源和ps6py打印日志
| demo-postgresql | 结合postgres
| demo-rabbitmq | rabbitmq
| demo-redis | redis操作demo
| demo-rocketmq-consumer | rocketmq
| demo-rocketmq-provider | rocketmq
| demo-sentinel | 结合sentinel
| demo-spi | 整合spi
| demo-swagger | swagger2 knife4j-ui
| demo-validation | spring数据校验


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
