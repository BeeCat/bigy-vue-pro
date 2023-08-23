package cn.iocoder.yudao.server.framework;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient {
    private  final int port;

    private final String host;

    public EchoClient(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void start() throws InterruptedException {
       try {
           EventLoopGroup group = new NioEventLoopGroup();
           Bootstrap bootstrap = new Bootstrap();
           bootstrap.group(group)
                   .channel(NioSocketChannel.class)
                   .remoteAddress(new InetSocketAddress(host, port))
                   .handler(new EchoClientHandler())
           ;
           ChannelFuture sync = bootstrap.connect().sync();
       } finally {

       }
    }

}
