# mapstruct

在`pom.xml`中添加以下配置

```xml

<dependencys>
    <!--mapStruct依赖-->
    <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct</artifactId>
        <version>${mapstruct.version}</version>
    </dependency>
    <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct-processor</artifactId>
        <version>${mapstruct.version}</version>
        <scope>provided</scope>
    </dependency>
</dependencys>
```

创建一个用户的基本信息`UserInfo.class`
















