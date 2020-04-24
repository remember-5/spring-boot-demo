## 使用方式

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
