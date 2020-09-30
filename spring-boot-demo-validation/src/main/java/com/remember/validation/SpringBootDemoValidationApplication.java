package com.remember.validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 添加 @EnableAspectJAutoProxy 注解，重点是配置 exposeProxy = true ，因为我们希望 Spring AOP 能将当前代理对象设置到 AopContext 中。
 * 具体用途，我们会在下文看到。想要提前看的胖友，可以看看 《Spring AOP 通过获取代理对象实现事务切换》 文章。
 *
 * @author wangjiahao
 * @date
 * @see "http://www.voidcn.com/article/p-zddcuyii-bpt.html"
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class SpringBootDemoValidationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoValidationApplication.class, args);
    }

}
