package com.github.rodindenis.example.netty.bin.server;

import com.github.rodindenis.example.netty.bin.api.MessageParams;
import com.github.rodindenis.example.netty.bin.api.exception.GeneralMessageProcessingException;
import com.github.rodindenis.example.netty.bin.api.message.Message;
import com.github.rodindenis.example.netty.bin.api.message.MessageTypeEnum;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class ServerMessageHandler extends SimpleChannelInboundHandler<Message> {

    private static final InternalLogger logger = InternalLoggerFactory.getInstance(ServerMessageHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) {

        logger.info("Received message {}", msg.toString());

        checkMessage(msg);

        var message = new Message();
        message.setMagicNumber(MessageParams.magicNumber);
        message.setVersion(MessageParams.version);
        message.setType(MessageTypeEnum.BASIC);
        message.setPayload("Ok");

        if ("Good bye".equals(msg.getPayload())) {

            var channelFuture = ctx.writeAndFlush(message);
            channelFuture.addListener((ChannelFutureListener) future -> ctx.close());
        } else {
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    private void checkMessage(Message msg) {
        if (MessageParams.magicNumber != msg.getMagicNumber()) {
            throw new GeneralMessageProcessingException("Wrong magic number: {}" + msg.getMagicNumber());
        } else if (MessageParams.version != msg.getVersion()) {
            throw new GeneralMessageProcessingException("Wrong version: {}" + msg.getVersion());
        } else if (MessageTypeEnum.BASIC != msg.getType()) {
            throw new GeneralMessageProcessingException("Wrong message type: {}" + msg.getType());
        }
    }
}
