package com.remember.minio.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * @author wangjiahao
 * @date 2022/8/24 21:45
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.minio")
public class MinioProperties implements Serializable {
    private String endpoint;
    private String bucket;
    private String accessKey;
    private String secretKey;
}
