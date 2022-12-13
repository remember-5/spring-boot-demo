package com.remember.netty.websocket;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.remember.netty.config.NettyProperties;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * TextWebSocketFrame类型， 表示一个文本帧
 *
 * @author sixiaojie
 * @date 2020-03-28-13:47
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static final String AUTHORIZATION = "Authorization";
    private int lossConnectCount = 0;

    /**
     * 一旦连接，第一个被执行
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerAdded 被调用" + ctx.channel().id().asLongText());
        // 添加到channelGroup 通道组
        NettyProperties.getChannelGroup().add(ctx.channel());
    }

    /**
     * 读取数据
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        log.info("服务器收到消息：{}", msg.text());

        // 获取用户ID
        JSONObject jsonObject = JSONUtil.parseObj(msg.text());
        String uid = jsonObject.getStr("uid");

        // 将用户ID作为自定义属性加入到channel中，方便随时channel中获取用户ID
        AttributeKey<String> key = AttributeKey.valueOf("userId");
        ctx.channel().attr(key).setIfAbsent(uid);

        // 关联channel
        NettyProperties.getUserChannelMap().put(uid, ctx.channel());

        // 回复消息
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器连接成功！"));
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerRemoved 被调用" + ctx.channel().id().asLongText());
        // 删除通道
        NettyProperties.getChannelGroup().remove(ctx.channel());
        removeUserId(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("异常：{}", cause.getMessage());
        // 删除通道
        NettyProperties.getChannelGroup().remove(ctx.channel());
        removeUserId(ctx);
        ctx.close();
    }

    /**
     * 删除用户与channel的对应关系
     *
     * @param ctx
     */
    private void removeUserId(ChannelHandlerContext ctx) {
        AttributeKey<String> key = AttributeKey.valueOf("userId");
        String userId = ctx.channel().attr(key).get();
        NettyProperties.getUserChannelMap().remove(userId);
    }




    /**
     * 用于用户鉴权 鉴权头部
     *
     * @param ctx /
     * @param msg /
     * @throws Exception /
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest msg1 = (FullHttpRequest) msg;
            //根据请求头的 AUTHORIZATION 进行鉴权操作
            String authentication = msg1.headers().get(AUTHORIZATION);
            log.info("鉴权操作");
            if (StrUtil.isEmpty(authentication)) {
                refuseChannel(ctx);
                return;
            }
            //查询数据库是否存在该用户
//            TestUser testUser = userMapper.selectById(authToken);
//            if (testUser == null) {
//                refuseChannel(ctx);
//            }

            //鉴权成功，添加channel用户组
            NettyProperties.getChannelGroup().add(ctx.channel());
            NettyProperties.getUserChannelMap().put(UUID.randomUUID().toString(), ctx.channel());
        }
        ctx.fireChannelRead(msg);
    }

    /**
     * 返回错误信息，关闭通道
     *
     * @param ctx
     */
    private void refuseChannel(ChannelHandlerContext ctx) {
        ctx.channel().writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.UNAUTHORIZED));
        ctx.channel().close();
    }

    /**
     * 自定义空闲状态检测(自定义心跳检测handler)
     * 若连续2次无读事件，则关闭这个客户端channel
     *
     * @author wangjiahao
     * @date 2020-08-21-16:14
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state()== IdleState.READER_IDLE){
                lossConnectCount ++;
                if (lossConnectCount > 2){
                    ctx.channel().close();
                }
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }


}
