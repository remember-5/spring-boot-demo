package com.remember.websocket.cluster.config;

import com.remember.websocket.cluster.service.IRedisSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

import java.security.Principal;

/**
 * @author wangjiahao
 * @date 2021/12/14
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthWebSocketHandlerDecoratorFactory implements WebSocketHandlerDecoratorFactory {

    private final IRedisSessionService redisSessionService;

    @Override
    public WebSocketHandler decorate(WebSocketHandler webSocketHandler) {
        return new WebSocketHandlerDecorator(webSocketHandler) {
            @Override
            public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
                // 客户端与服务器端建立连接后，此处记录谁上线了
                Principal principal = session.getPrincipal();
                if(principal != null){
                    String username = principal.getName();
                    log.info("websocket online: " + username + " session " + session.getId());
                    redisSessionService.add(username, session.getId());
                }
                super.afterConnectionEstablished(session);
            }

            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
                // 客户端与服务器端断开连接后，此处记录谁下线了
                Principal principal = session.getPrincipal();
                if(principal != null){
                    String username = session.getPrincipal().getName();
                    log.info("websocket offline: " + username);
                    redisSessionService.del(username);
                }
                super.afterConnectionClosed(session, closeStatus);
            }
        };
    }
}
