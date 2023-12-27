package com.remember5.jwt.config;

import com.remember5.jwt.handler.BaseException;
import com.remember5.jwt.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangjiahao
 * @date 2020/5/28
 */
@Slf4j
@Configuration
public class JwtInterceptor extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.debug("经过了拦截器");
        //无论如何都放行。具体能不能操作还是在具体的操作中去判断。
        //拦截器只是负责把头请求头中包含token的令牌进行一个解析验证。
        String header = httpServletRequest.getHeader("Authorization");

        if (header != null && !"".equals(header)) {
            //如果有包含有Authorization头信息，就对其进行解析
            if (header.startsWith("Bearer ")) {
                //得到token
                String token = header.substring(7);
                //对令牌进行验证
                try {
                    Claims claims = JwtUtils.parseJWT(token);
                    String accountNum = (String) claims.get("accountNum");
                    if (StringUtils.isEmpty(accountNum)) {
                        throw new RuntimeException("令牌不正确！");
                    }
                } catch (Exception e) {
                    throw new RuntimeException("令牌不正确！");
                }
            }
        } else {
            throw new BaseException(401, "请求未认证！");
        }

    }
}
