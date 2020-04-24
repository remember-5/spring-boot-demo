## 使用方式

```添加依赖

<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-configuration-processor</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<scope>provided</scope>
		</dependency>

		<dependency>
			<groupId>commons-codec</groupId>
			<artifactId>commons-codec</artifactId>
		</dependency>

		<dependency>
			<groupId>commons-io</groupId>
			<artifactId>commons-io</artifactId>
			<version>2.6</version>
		</dependency>


```



注解可以添加到方法上或者类上

```java
@AESDecryptBody
@DecryptBody
@DESDecryptBody
@RSADecryptBody

@AESEncryptBody
@DESEncryptBody
@EncryptBody
@MD5EncryptBody
@RSAEncryptBody
@SHAEncryptBody
```



完整配置请参考：
```
encrypt:
   enable: false
   aes-config:
      key: xx
   des-config:
      key: xxx
   rsa-config:
      private-key: xx
      public-key: xxx
      open: true 
      show-log: true 
      max-encrypt-block: 117
      max-decrypt-block: 256
```
