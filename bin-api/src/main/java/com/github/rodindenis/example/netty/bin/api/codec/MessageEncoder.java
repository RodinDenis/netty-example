package com.github.rodindenis.example.netty.bin.api.codec;

import com.github.rodindenis.example.netty.bin.api.exception.GeneralMessageProcessingException;
import com.github.rodindenis.example.netty.bin.api.message.Message;
import com.github.rodindenis.example.netty.bin.api.message.MessageTypeEnum;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.nio.charset.Charset;

public class MessageEncoder extends MessageToByteEncoder<Message> {

    private static final InternalLogger logger = InternalLoggerFactory.getInstance(MessageEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, Message message, ByteBuf buf) {
        logger.info("Encoding message {}", message.toString());

        if (message.getType() != MessageTypeEnum.BASIC) {
            throw new GeneralMessageProcessingException("Unsupported message type: " + message.getType());
        }

//        buf.writeInt(MessageParams.magicNumber);
//        buf.writeInt(MessageParams.version);
        buf.writeInt(message.getMagicNumber());
        buf.writeInt(message.getVersion());
        buf.writeByte(message.getType().getType());
        buf.writeInt(message.getPayload().length());
        buf.writeCharSequence(message.getPayload(), Charset.defaultCharset());
    }
}
