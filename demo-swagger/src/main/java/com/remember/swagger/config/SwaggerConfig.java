package com.remember.swagger.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author wangjiahao
 * @date 2020/4/27
 */
@Data
@EnableSwagger2
@EnableKnife4j
@ConfigurationProperties(prefix = "swagger")
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    private String controller;
    private String title;
    private String serviceUrl;
    private Boolean enabled;
    private String description;
    private String version;
    private String author;
    private String email;
    private String blog;
    private String host;

    @Bean
    public Docket getDocket() {
        // TODO 新版swagger不能使用
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enabled)
                .host(host)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(controller))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .contact(new Contact(author, blog, email))
                .description(description)
                .version(version)
                .termsOfServiceUrl(serviceUrl)
                .licenseUrl(serviceUrl)
                .build();
    }


}
