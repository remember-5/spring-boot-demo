package com.remember.netty.websocket;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.remember.netty.config.NettyConfig;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import lombok.extern.slf4j.Slf4j;

/**
 * 连接的时候，鉴权头部
 *
 * @author wangjiahao
 * @date 2022/6/27 17:06
 */
@Slf4j
public class AuthHandler extends ChannelInboundHandlerAdapter {

    private static final String AUTHORIZATION = "Authorization";

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
            NettyConfig.getChannelGroup().add(ctx.channel());
            NettyConfig.getUserChannelMap().put(UUID.randomUUID().toString(), ctx.channel());
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

}
