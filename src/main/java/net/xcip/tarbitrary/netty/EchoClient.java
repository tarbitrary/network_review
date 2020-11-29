package net.xcip.tarbitrary.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        final NioEventLoopGroup eventExecutors = new NioEventLoopGroup(1);

        try {
            final ChannelFuture localhost = bootstrap.group(eventExecutors).channel(NioSocketChannel.class).remoteAddress("localhost", Integer.parseInt("8888")).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new EchoClientHandler());
                }
            }).connect().sync();

            localhost.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                eventExecutors.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
