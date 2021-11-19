package com.remember.demo.web.design.duty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangjiahao
 * @date 2021/11/19
 */
@Order(2)
@Slf4j
@Component
public class CheckSecurityFilterObject extends AbstractHandler{
    @Override
    void doFilter(HttpServletRequest request, HttpServletResponse response) {
        log.info("安全调用检查");
    }
}
