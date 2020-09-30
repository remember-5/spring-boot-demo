package com.remember.sentinel.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.SentinelWebInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.SentinelWebTotalInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcConfig;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcTotalConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wangjiahao
 * @date 2020/4/7
 */
@Configuration
public class SpringMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Add Sentinel Interceptors
//        addSentinelWebTotalInterceptor(registry);
        addSentinelWebInterceptors(registry);
    }

    private void addSentinelWebInterceptors(InterceptorRegistry registry) {
        // <1.1> 创建 SentinelWebMvcConfig 对象
        SentinelWebMvcConfig config = new SentinelWebMvcConfig();
        // <1.2> 是否包含请求方法。即基于 URL 创建的资源，是否包含 Method。
        config.setHttpMethodSpecify(true);
        // <1.3> 设置 BlockException 处理器。
        // config.setBlockExceptionHandler(new DefaultBlockExceptionHandler());

        /// <2> 添加 SentinelWebInterceptor 拦截器
        registry.addInterceptor(new SentinelWebInterceptor(config)).addPathPatterns("/**");
    }


    private void addSentinelWebTotalInterceptor(InterceptorRegistry registry) {
        // <1> 创建 SentinelWebMvcTotalConfig 对象
        SentinelWebMvcTotalConfig config = new SentinelWebMvcTotalConfig();
        // <2> 添加 SentinelWebTotalInterceptor 拦截器
        registry.addInterceptor(new SentinelWebTotalInterceptor(config)).addPathPatterns("/**");


    }

}
