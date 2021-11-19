package com.remember.demo.web.design.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author wangjiahao
 * @date 2021/11/19
 */
@Component("ChainPatternDemo")
public class ChainPatternDemo {

    /**
     * 自动注入各个责任链的对象
     */
    @Autowired
    private List<AbstractHandler> abstractHandlerList;

    private AbstractHandler abstractHandler;

    public AbstractHandler getAbstractHandler() {
        return abstractHandler;
    }

    public void setAbstractHandler(AbstractHandler abstractHandler) {
        this.abstractHandler = abstractHandler;
    }

    /**
     * Spring 注入后自动执行，责任链的对象链接起来
     */
    @PostConstruct
    public void initializeChainFilter() {
        for (int i = 0; i < abstractHandlerList.size(); i++) {
            if (i == 0) {
                abstractHandler = abstractHandlerList.get(0);
            } else {
                AbstractHandler currentHandler = abstractHandlerList.get(i - 1);
                AbstractHandler nextHandler = abstractHandlerList.get(i);
                currentHandler.setNextHandler(nextHandler);
            }
        }
    }

    public HttpServletResponse exec(HttpServletRequest request, HttpServletResponse response) {
        abstractHandler.filter(request, response);
        return response;
    }


}
