/**
 * Copyright [2022] [remember5]
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.remember.netty.mq.websocket.handler;

import cn.hutool.core.text.CharSequenceUtil;
import com.remember.netty.mq.constant.RabbitRoutingKeyConstants;
import com.remember.netty.mq.manager.NettyChannelManager;
import com.remember.netty.mq.properties.WebSocketProperties;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 鉴权handler
 *
 * @author wangjiahao
 * @date 2022/12/13 17:31
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    private static final String AUTHORIZATION = "Authorization";

    private final RedisTemplate<String, Object> redisTemplate;
    private final WebSocketProperties webSocketProperties;

    public AuthHandler(@Nullable @Lazy RedisTemplate<String, Object> redisTemplate, WebSocketProperties webSocketProperties) {
        this.redisTemplate = redisTemplate;
        this.webSocketProperties = webSocketProperties;
    }

    /**
     * 一旦连接，第一个被执行
     *
     * @param ctx 上下文
     * @throws Exception 异常
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        log.info("[handlerAdded] 逻辑处理器被添加：handlerAdded() {}", ctx.channel().id().asLongText());
        NettyChannelManager.getChannelGroup().add(ctx.channel());
    }


    /**
     * 注册
     *
     * @param ctx 上下文
     * @throws Exception 异常
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("[channelRegistered] channel 绑定到线程(NioEventLoop)：channelRegistered()");
        super.channelRegistered(ctx);
    }

    /**
     * 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
     *
     * @param ctx 上下文
     * @throws Exception 异常
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("[channelActive] channel 准备就绪：channelActive()");
        SocketChannel channel = (SocketChannel) ctx.channel();
        log.info("链接报告开始");
        log.info("链接报告信息：本客户端链接到服务端。channelId：{}", channel.id());
        log.info("链接报告IP: {}", channel.localAddress().getHostString());
        log.info("链接报告Port: {}", channel.localAddress().getPort());
        log.info("链接报告完毕");
        String str = "通知服务端链接建立成功" + " " + new Date() + " " + channel.localAddress().getHostString() + "\r\n";
        ctx.writeAndFlush(str);
    }


    /**
     * 读取消息
     *
     * @param ctx 上下文
     * @param msg 消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        log.info("[channelRead] channel 有数据可读：channelRead()");
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest httpRequest = (FullHttpRequest) msg;
            // 根据请求头的 AUTHORIZATION 进行鉴权操作
            final String uri = httpRequest.uri();
            final Map<String, String> params = getParams(uri);
            final String userId = params.get("userId");
            if (CharSequenceUtil.isEmpty(userId)) {
                ctx.channel().writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.UNAUTHORIZED));
                ctx.channel().close();
                return;
            }

            // 重定向uri，否则会导致连接阻塞异常
            if (uri.contains("?")) {
                String newUri = uri.substring(0, uri.indexOf("?"));
                httpRequest.setUri(newUri);
            }

            // 鉴权成功，添加channel用户组
            NettyChannelManager.getChannelGroup().add(ctx.channel());

            // 将用户ID作为自定义属性加入到channel中，方便随时channel中获取用户ID
            AttributeKey<String> key = AttributeKey.valueOf("userId");
            ctx.channel().attr(key).setIfAbsent(userId);
            NettyChannelManager.getUserChannelMap().put(userId, ctx.channel());

            // Save redis.
            Objects.requireNonNull(redisTemplate).opsForSet().add(RabbitRoutingKeyConstants.WS_CLIENT, userId);

            // 鉴权完成删除这个handler
            ctx.pipeline().remove(this);
            // 对事件进行传播，知道完成WebSocket连接。
            ctx.fireChannelRead(msg);
        } else {
            ctx.channel().close();
        }
    }


    /**
     * 消息读取完成
     *
     * @param ctx 上下文
     * @throws Exception 异常
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("[channelReadComplete] channel 某次数据读完：channelReadComplete()");
        super.channelReadComplete(ctx);
    }

    /**
     * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
     *
     * @param ctx 上下文
     * @throws Exception 异常
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("[channelInactive] channel 被关闭：channelInactive()");
        log.info("断开链接 {}", ctx.channel().localAddress().toString());
        removeRedisUserId(ctx);
    }

    /**
     * 注销
     *
     * @param ctx 上下文
     * @throws Exception 异常
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("[channelUnregistered] channel 取消线程(NioEventLoop) 的绑定: channelUnregistered()");
        removeRedisUserId(ctx);
    }

    /**
     * 发生异常 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
     *
     * @param ctx   /
     * @param cause /
     * @throws Exception /
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("[exceptionCaught] 异常：{}", cause.getMessage());
        cause.printStackTrace();
        removeRedisUserId(ctx);
    }


    public void removeRedisUserId(ChannelHandlerContext ctx) {
        AttributeKey<String> key = AttributeKey.valueOf("userId");
        String userId = ctx.channel().attr(key).get();
        ctx.channel().close();
    }


    /**
     * 获取uri中的参数
     *
     * @param uri uri
     * @return 参数map
     */
    public static Map<String, String> getParams(String uri) {
        // /ws?name=123  /?name=123
        if (null == uri || uri.isEmpty()) {
            return Collections.emptyMap();
        }
        if (uri.startsWith("/")) {
            uri = uri.substring(1);
        }
        if (uri.contains("?")) {
            uri = uri.substring(uri.indexOf("?"));
        }

        uri = uri.replace("/", "").replace("?", "");
        HashMap<String, String> result = new HashMap<>();

        if (uri.contains("&")) {
            final String[] split = uri.split("&");
            for (String s : split) {
                result.put(s.split("=")[0], s.split("=")[1]);
            }
        } else if (uri.contains("=")) {
            result.put(uri.split("=")[0], uri.split("=")[1]);
        }

        return result;

    }

}
