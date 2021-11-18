package com.remember.websocket.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * stomp模式请参考 http://www.mydlq.club/article/86/
 * websocket服务端,多例的，一次websocket连接对应一个实例
 * `@ServerEndpoint 注解的值为URI, 映射客户端输入的URL来连接到WebSocket服务器端`
 * @author wangjiahao
 *
 * @date 2021/10/20
 */
@Slf4j
@Component
@ServerEndpoint("/websocket/{name}")
public class WebSocketServer {


    /**
     * 用来记录当前在线连接数，设计成线程安全的
     */
    private static AtomicInteger onlineCount = new AtomicInteger();
    /**
     * 保存 uri对应的连接服务 {uri:WebSocketServer}，设计成线程安全的
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketServerMAP = new ConcurrentHashMap<>();
    /**
     * 于某个客户端的连接会话
     */
    private Session session;
    /**
     * 客户端消息发送者
     */
    private String name;
    /**
     * 连接uri
     */
    private String uri;

    /**
     * 连接建立成功时触发，绑定参数
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     * @param name    /
     * @param toName  /
     * @throws IOException /
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("name") String name) throws IOException {
        log.info("开始连接: {}", name);
        this.session = session;
        this.name = name;
        this.uri = session.getRequestURI().toString();
        WebSocketServer webSocketServer = webSocketServerMAP.get(uri);
        // 如果已经有了，就T下线
        if (webSocketServer != null) {
            log.info("{} 重复连接被挤下线了", uri);
            webSocketServer.session.getBasicRemote().sendText("uri" + "重复连接被挤下线了");
            webSocketServer.session.close();
        }
        webSocketServerMAP.put(uri, this);
        addOnlineCount();
        log.info("在线人数: {}", getOnlineCount());

    }

    /**
     * 连接关闭时触发，注意不能向客户端发送消息了
     */
    @OnClose
    public void onClose() {
        // 删除uri对应的服务
        webSocketServerMAP.remove(uri);
        // 在线人数减一
        reduceOnlineCount();
    }


    /**
     * 收到客户端消息后触发
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("收到消息: {}", message);
    }

    /**
     * 通信发生错误时触发
     *
     * @param session /
     * @param error   /
     */
    @OnError
    public void onError(Session session, Throwable error) {
        try {
            log.info("{}:通信发生错误，连接关闭", name);
            webSocketServerMAP.remove(uri);
        } catch (Exception e) {

        }
    }

    /**
     * 获取在线连接数
     *
     * @return /
     */
    public static int getOnlineCount() {
        return onlineCount.get();
    }


    /**
     * 原子性操作，在线连接数加一
     */
    public static void addOnlineCount() {
        onlineCount.getAndIncrement();
    }

    /**
     * 原子性操作，在线连接数减一
     */
    public static void reduceOnlineCount() {
        onlineCount.getAndDecrement();
    }


}
