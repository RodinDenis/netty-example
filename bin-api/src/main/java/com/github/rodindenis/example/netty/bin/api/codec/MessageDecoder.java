package com.github.rodindenis.example.netty.bin.api.codec;

import com.github.rodindenis.example.netty.bin.api.message.Message;
import com.github.rodindenis.example.netty.bin.api.message.MessageTypeEnum;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.nio.charset.Charset;
import java.util.List;

public class MessageDecoder extends ByteToMessageDecoder {

    private static final InternalLogger logger = InternalLoggerFactory.getInstance(MessageDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf buf, List<Object> list) {

        logger.info("Decoding message {}", buf.toString(Charset.defaultCharset()));

        var message = new Message();
        message.setMagicNumber(buf.readInt());
        message.setVersion(buf.readInt());
        message.setType(MessageTypeEnum.get(buf.readByte()));
        var payloadLength = buf.readInt();
        message.setPayload(buf.readCharSequence(payloadLength, Charset.defaultCharset()).toString());

        list.add(message);
    }
}
