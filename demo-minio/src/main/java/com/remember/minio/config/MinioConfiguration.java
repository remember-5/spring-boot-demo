package com.remember.minio.config;

import com.remember.minio.properties.MinioProperties;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangjiahao
 * @date 2022/8/24 21:46
 */
@Configuration
@RequiredArgsConstructor
public class MinioConfiguration {

    private final MinioProperties minioProperties;

    @Bean
//    @ConditionalOnExpression(value = "T(org.apache.commons.lang3.StringUtils).is('${spring.minio.enable}')")
    @ConditionalOnProperty(prefix = "spring.minio", value = "enabled", matchIfMissing = true)
    public MinioClient getMinioClient() {
        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }
}
