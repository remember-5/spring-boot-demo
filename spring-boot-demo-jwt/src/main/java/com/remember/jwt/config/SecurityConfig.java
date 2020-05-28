package com.remember.jwt.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author wangjiahao
 * @date 2020/5/28
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/**/**").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .httpBasic();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and()
//                // 禁用 CSRF
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
//                // 指定路径下的资源需要验证了的用户才能访问
//                .antMatchers("/api/**").authenticated()
//                .antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
//                // 其他都放行了
//                .anyRequest().permitAll()
//                .and()
//                //添加自定义Filter
//                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
//                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
//                // 不需要session（不创建会话）
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                // 授权异常处理
//                .exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint())
//                .accessDeniedHandler(new JWTAccessDeniedHandler());
//
//    }
}
