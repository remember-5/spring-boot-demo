package com.remember.websocket.cluster.domain;

import java.security.Principal;

/**
 * @author fly
 * @description
 * @date 2021/12/14 16:19
 */
public class MyPrincipal implements Principal {

    private String loginName;

    public MyPrincipal(String loginName){
        this.loginName = loginName;
    }

    @Override
    public String getName() {
        return loginName;
    }

}
