package com.tzxylao.java8.nio;

import com.google.common.base.Charsets;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class ServerNetty {
    private int port;

    public ServerNetty(int port) {
        this.port = port;
    }

    public void action() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ServerHandler());
                        }
                    });
            System.out.println("server open");
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            channelFuture.channel().close().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    private class ServerHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            //普通处理
            simpleRead(ctx, msg);

            //分隔符处理
            delimiterread(ctx, msg);
        }

        private void delimiterread(ChannelHandlerContext ctx, Object msg) {
            // 如果把msg直接转成字符串，必须在服务中心添加 socketChannel.pipeline().addLast(new StringDecoder());
            String reqStr = (String) msg;
            System.err.println("server 接收到请求信息是：" + reqStr);
            String respStr = new StringBuilder("来自服务器的响应").append(reqStr).append("$_").toString();

            // 返回给客户端响应
            // 和客户端链接中断即短连接，当信息返回给客户端后中断
            ctx.writeAndFlush(Unpooled.copiedBuffer(respStr.getBytes())).addListener(ChannelFutureListener.CLOSE);
        }

        private void simpleRead(ChannelHandlerContext ctx, Object msg) {
            ByteBuf bb = (ByteBuf) msg;
            byte[] reqByte = new byte[bb.readableBytes()];
            bb.readBytes(reqByte);
            String reqStr = new String(reqByte, Charsets.UTF_8);
            System.err.println("server receive client data : " + reqStr);
            String respStr = new StringBuilder("来自服务器的响应").append(reqStr).append("$_").toString();
            ctx.writeAndFlush(Unpooled.copiedBuffer(respStr.getBytes()));
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            System.err.println("服务端读取完毕");
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            System.err.println("server 读取异常");
            ctx.close();
        }
    }

    public static void main(String[] args) {
        new ServerNetty(8081).action();
    }
}