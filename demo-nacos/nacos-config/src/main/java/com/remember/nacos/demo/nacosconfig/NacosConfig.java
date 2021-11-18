package com.remember.nacos.demo.nacosconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2021/10/22
 */
@Data
@Component
@ConfigurationProperties(prefix = "name")
public class NacosConfig {

    private String w;

}
