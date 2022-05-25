## 写在前面
个人练习demo，很多来自此网站http://www.iocoder.cn/

把postgres 和 mysql 的 jpa & mybatisPlus使用写到一起，结合多数据源

## 模块介绍

| 模块 | 介绍 |  
| -- | -- |  
| spring-boot-demo-admin | spring-boot-admin模块
| spring-boot-demo-common | 公共模块
| spring-boot-demo-dynamic-datasource | springboot多数据demo
| spring-boot-demo-elasticsearch | springboot结合es
| spring-boot-demo-encrypt | 测试springboot结合加密
| spring-boot-demo-guava | google工具类guava的使用
| spring-boot-demo-interview | 面试练习
| spring-boot-demo-jpa | jpa联系
| spring-boot-demo-junit | junit单元测试案例
| spring-boot-demo-jwt | jwt token-demo
| spring-boot-demo-minio | minio文件上传demo
| spring-boot-demo-mybatis-plus | springboot结合mybatisplus并且配置多数据源和ps6py打印日志
| spring-boot-demo-postgresql | 结合postgres
| spring-boot-demo-rabbitmq | rabbitmq
| spring-boot-demo-redis | redis操作demo
| spring-boot-demo-rocketmq-consumer | rocketmq
| spring-boot-demo-rocketmq-provider | rocketmq
| spring-boot-demo-sentinel | 结合sentinel
| spring-boot-demo-spi | 整合spi
| spring-boot-demo-swagger | swagger2 knife4j-ui
| spring-boot-demo-validation | spring数据校验


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