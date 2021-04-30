package com.remember.resource.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author wangjiahao
 * @date 2021/2/4
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                // 设置login 无需权限访问
                .antMatchers("/login").permitAll()
                .antMatchers("/callback").permitAll()
                // 设置其他请求，需要认证后访问
                .anyRequest().authenticated();
    }
}
