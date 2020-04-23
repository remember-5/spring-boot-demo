package com.remember.encrypt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bobby
 * @date 2019/4/9
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "rsa.encrypt")
public class SecretKeyConfig {

    private String privateKey;

    private String publicKey;

    private boolean open = true;

    private boolean showLog = false;

}
