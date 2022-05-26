package com.remember.demo.web.design.duty;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 责任链
 *
 * @author wangjiahao
 * @date 2021/11/19
 */
public abstract class AbstractHandler {

    /**
     * 责任链中的下一个对象
     */
    private AbstractHandler nextHandler;

    /**
     * 设置责任链中的下一个对象
     *
     * @param nextHandler /
     */
    public void setNextHandler(AbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public AbstractHandler getNextHandler() {
        return nextHandler;
    }

    public void filter(HttpServletRequest request, HttpServletResponse response) {
        doFilter(request, response);
        if (getNextHandler() != null) {
            getNextHandler().filter(request, response);
        }
    }

    abstract void doFilter(HttpServletRequest request, HttpServletResponse response);


}
