package com.remember.minio.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author wangjiahao
 * @date 2020/5/31
 */
@Data
@ConfigurationProperties(prefix = "spring.minio")
public class MinIOConfig {

    private String endpoint;
    private String bucket;
    private String accessKey;
    private String secretKey;


    @Bean(name = "minioClient")
    public MinioClient minioClient() throws InvalidPortException, InvalidEndpointException {
        return new MinioClient(this.endpoint, this.accessKey, this.secretKey);
    }


}
