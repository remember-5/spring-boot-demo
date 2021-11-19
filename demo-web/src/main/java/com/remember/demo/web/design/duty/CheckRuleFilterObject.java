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
@Order(43)
@Slf4j
@Component
public class CheckRuleFilterObject extends AbstractHandler{
    @Override
    void doFilter(HttpServletRequest request, HttpServletResponse response) {
        log.info("check rule");
    }
}
