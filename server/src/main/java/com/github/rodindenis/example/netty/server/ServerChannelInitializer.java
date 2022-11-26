package com.github.rodindenis.example.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        var pipeline = socketChannel.pipeline();
        pipeline.addLast(new ServerHandler());
    }
}
