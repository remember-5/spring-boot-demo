package com.remember.minio.config;

import com.remember.minio.properties.MinioProperties;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;

/**
 * @author wangjiahao
 * @date 2022/8/24 21:46
 */
@RequiredArgsConstructor
public class MinioConfiguration {

    private final MinioProperties minioProperties;

    @Bean
    @ConditionalOnExpression(value = "T(org.apache.commons.lang3.StringUtils).isNotEmpty('${spring.minio.host}')")
    public MinioClient getMinioClient() {
        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }
}
