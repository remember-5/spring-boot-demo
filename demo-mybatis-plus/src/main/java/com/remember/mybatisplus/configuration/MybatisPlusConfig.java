package com.remember.mybatisplus.configuration;


import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.remember.mybatisplus.interceptor.EncryptCustomInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置注入
 *
 * @author wangjiahao
 * @date 2020/3/25
 */
@Configuration
@MapperScan(basePackages = {"com.remember.mybatisplus.mapper"})
public class MybatisPlusConfig {

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new EncryptCustomInterceptor());
        // 如果配置多个插件,切记分页最后添加
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        // 如果有多数据源可以不配具体类型 否则都建议配上具体的DbType
        // interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
