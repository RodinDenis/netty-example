package com.github.rodindenis.example.netty.bin.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Main {
    public static void main(String[] args) {

        var eventLoopGroup = new NioEventLoopGroup(4);

        var server = new ServerBootstrap();
        server.group(eventLoopGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ServerChannelInitializer());

        try {
            var channelFuture = server.bind(9090).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();

            try {
                eventLoopGroup.terminationFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
