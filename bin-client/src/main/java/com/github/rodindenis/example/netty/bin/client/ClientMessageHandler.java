package com.github.rodindenis.example.netty.bin.client;

import com.github.rodindenis.example.netty.bin.api.MessageParams;
import com.github.rodindenis.example.netty.bin.api.message.Message;
import com.github.rodindenis.example.netty.bin.api.message.MessageTypeEnum;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class ClientMessageHandler extends SimpleChannelInboundHandler<Message> {

    private static final InternalLogger logger = InternalLoggerFactory.getInstance(ClientMessageHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        var message = new Message();
        message.setMagicNumber(MessageParams.magicNumber);
        message.setVersion(MessageParams.version);
        message.setType(MessageTypeEnum.BASIC);
        message.setPayload("Hello");

        logger.info("Sending message {}", message);
        ctx.writeAndFlush(message);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) {

        logger.info("Received message {}", msg.toString());


        var message = new Message();
        message.setMagicNumber(MessageParams.magicNumber);
        message.setVersion(MessageParams.version);
        message.setType(MessageTypeEnum.BASIC);
        message.setPayload("Good bye");

        var channelFuture = ctx.writeAndFlush(message);
        channelFuture.addListener((ChannelFutureListener) future -> ctx.close());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
