package cn.imadc.application.base.samples.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelId;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RedisClient {

    Map<ChannelId, Channel> channelMap = new ConcurrentHashMap<>();

    String host;    //   目标主机
    int port;       //   目标主机端口

    public RedisClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new RedisClientInitializer(channelMap));

            Channel channel = bootstrap.connect(host, port).sync().channel();
            channelMap.put(channel.id(), channel);
            send(channel, "auth Xsbankredis");

            for (int i = 0; i < 10; i++) {
                send(channel, "info");
            }

        } finally {
            group.shutdownGracefully();
        }
    }

    private void send(Channel channel, String cmd) throws InterruptedException {
        ChannelFuture lastWriteFuture = null;
        lastWriteFuture = channel.writeAndFlush(cmd);
        lastWriteFuture.addListener(new GenericFutureListener<ChannelFuture>() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    System.err.print("write failed: ");
                    future.cause().printStackTrace(System.err);
                }
            }
        });
        lastWriteFuture.sync();
    }

    public static void main(String[] args) throws Exception {
        RedisClient client = new RedisClient("192.168.137.200", 6379);
        client.start();
    }
}
