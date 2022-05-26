package com.remember.websocket.cluster.config;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

/**
 * @author wangjiahao
 * @date 2021/12/14
 */
@Component
@RequiredArgsConstructor
public class WebSocketRabbitMQMessageBrokerConfigurer extends AbstractWebSocketMessageBrokerConfigurer {

    private final MyPrincipalHandshakeHandler myDefaultHandshakeHandler;
    private final AuthHandshakeInterceptor sessionAuthHandshakeInterceptor;
    private final AuthWebSocketHandlerDecoratorFactory myWebSocketHandlerDecoratorFactory;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /*
         * 注册 Stomp的端点
         *
         * addEndpoint：添加STOMP协议的端点。这个HTTP URL是供WebSocket或SockJS客户端访问的地址
         * withSockJS：指定端点使用SockJS协议
         */
        registry.addEndpoint("/ws/stomp/websocket")
                .addInterceptors(sessionAuthHandshakeInterceptor)
                .setHandshakeHandler(myDefaultHandshakeHandler)
                .withSockJS();
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*
         * 配置消息代理
         * 使用RabbitMQ做为消息代理，替换默认的Simple Broker
         */
        registry
                // 指服务端接收地址的前缀，意思就是说客户端给服务端发消息的地址的前缀
                .setApplicationDestinationPrefixes("/mq")
                // "STOMP broker relay"处理所有消息将消息发送到外部的消息代理
                .enableStompBrokerRelay("/exchange","/topic","/queue","/amq/queue")
                .setVirtualHost("/websocket-test")
                .setRelayHost("127.0.0.1")
                .setClientLogin("guest")
                .setClientPasscode("guest")
                .setSystemLogin("guest")
                .setSystemPasscode("guest")
                .setSystemHeartbeatSendInterval(5000)
                .setSystemHeartbeatReceiveInterval(4000);
    }

    /**
     * 这时实际spring weboscket集群的新增的配置，用于获取建立websocket时获取对应的sessionid值
     * @param registration
     */
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.addDecoratorFactory(myWebSocketHandlerDecoratorFactory);
        super.configureWebSocketTransport(registration);
    }
}
