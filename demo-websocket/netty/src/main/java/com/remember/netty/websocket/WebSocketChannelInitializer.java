package com.remember.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2022/6/27 17:01
 */
@Component
@RequiredArgsConstructor
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final WebSocketHandler webSocketHandler;
    /**
     * webSocket协议名
     */
    private static final String WEBSOCKET_PROTOCOL = "WebSocket";
    /**
     * webSocket路径
     */
    @Value("${web-socket.netty.path}")
    private String webSocketPath;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 流水线管理通道中的处理程序（Handler），用来处理业务
        // webSocket协议本身是基于http协议的，所以这边也要使用http编解码器
        ch.pipeline().addLast(new HttpServerCodec());
        ch.pipeline().addLast(new ObjectEncoder());
        // 以块的方式来写的处理器
        ch.pipeline().addLast(new ChunkedWriteHandler());
        /*
        说明：
        1、http数据在传输过程中是分段的，HttpObjectAggregator可以将多个段聚合
        2、这就是为什么，当浏览器发送大量数据时，就会发送多次http请求
         */
        ch.pipeline().addLast(new HttpObjectAggregator(8192));

        // 自定义鉴权
        ch.pipeline().addLast(new AuthHandler());

        //针对客户端，若10s内无读事件则触发心跳处理方法HeartBeatHandler#userEventTriggered
        ch.pipeline().addLast(new IdleStateHandler(10, 0, 0));
        //自定义空闲状态检测(自定义心跳检测handler)
        ch.pipeline().addLast(new HeartBeatHandler());
        /*
        说明：
        1、对应webSocket，它的数据是以帧（frame）的形式传递
        2、浏览器请求时 ws://localhost:58080/xxx 表示请求的uri
        3、核心功能是将http协议升级为ws协议，保持长连接
        */
        ch.pipeline().addLast(new WebSocketServerProtocolHandler(webSocketPath, WEBSOCKET_PROTOCOL, true, 65536 * 10));
        // 自定义的handler，处理业务逻辑
        ch.pipeline().addLast(webSocketHandler);
    }
}
