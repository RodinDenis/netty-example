package com.github.rodindenis.example.netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        var pipeline = socketChannel.pipeline();
        pipeline.addLast(new ClientHandler());
    }
}
