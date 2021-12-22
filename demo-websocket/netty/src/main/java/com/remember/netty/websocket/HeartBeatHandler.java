package com.remember.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 若连续2次无读事件，则关闭这个客户端channel
 * @author sixiaojie
 * @date 2020-08-21-16:14
 */
@Slf4j
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    private int lossConnectCount = 0;
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
//            if (event.state()== IdleState.READER_IDLE){
//                lossConnectCount ++;
//                if (lossConnectCount > 2){
//                    ctx.channel().close();
//                }
//            }
        }else {
            super.userEventTriggered(ctx,evt);
        }
    }
}
