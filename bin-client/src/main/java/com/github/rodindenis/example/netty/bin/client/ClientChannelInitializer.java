package com.github.rodindenis.example.netty.bin.client;

import com.github.rodindenis.example.netty.bin.api.codec.MessageDecoder;
import com.github.rodindenis.example.netty.bin.api.codec.MessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        var pipeline = socketChannel.pipeline();
        pipeline.addLast(new MessageDecoder())
                .addLast(new MessageEncoder())
                .addLast(new ClientMessageHandler());
    }
}
