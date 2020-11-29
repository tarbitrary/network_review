package net.xcip.tarbitrary.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        final EchoServerHandler echoServerHandler = new EchoServerHandler();

        final EventLoopGroup bossExecutors = new NioEventLoopGroup(1);
        final EventLoopGroup workerExecutors = new NioEventLoopGroup();
        try {
            final ChannelFuture channelFuture = serverBootstrap.group(bossExecutors, workerExecutors).channel(NioServerSocketChannel.class).localAddress(8888).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    final InetSocketAddress inetSocketAddress = ch.remoteAddress();
                    System.out.println("client connected, " + inetSocketAddress.getAddress().toString() + ":" + inetSocketAddress.getPort());
                    ch.pipeline().addLast(echoServerHandler);
                }
            }).bind().sync();

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                workerExecutors.shutdownGracefully().sync();
                bossExecutors.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
