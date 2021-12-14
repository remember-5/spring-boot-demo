package com.remember.websocket.cluster.service;

/**
 * @author wangjiahao
 * @date 2021/12/14
 */
public interface IRedisSessionService {

    void add(String name, String wsSessionId);
    boolean del(String name);
    String get(String name);

}
