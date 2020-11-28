package com.colin.games.bus.client;

import com.colin.games.bus.net.MessageDecoder;
import com.colin.games.bus.net.MessageEncoder;
import com.colin.games.bus.net.MessageReceiver;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetAddress;

public class Client {
    private InetAddress addr;
    private int port;
    private Channel chan;
    private ChannelFuture future;
    private boolean isConnected = false;
    private boolean isDead = false;
    private boolean isStarted = false;
    private Thread t;
    public Client(InetAddress addr, int port){
        this.addr = addr;
        this.port = port;
    }

    public void start(){
        t = new Thread(() -> {
            Bootstrap boot = new Bootstrap();
            boot.group(new NioEventLoopGroup())
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new LineBasedFrameDecoder(3000));
                            p.addLast(new StringDecoder(CharsetUtil.UTF_8));
                            p.addLast(new MessageDecoder());
                            p.addLast(new MessageReceiver());
                            p.addLast(new StringEncoder());
                            p.addLast(new MessageEncoder());
                        }
                    });
            try{
                future = boot.connect(addr,port);
                future.sync();
                chan = future.channel();
                chan.closeFuture().sync();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public boolean isStarted() {
        return isStarted;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public boolean isDead() {
        return isDead;
    }

    public Channel getChannel() {
        return chan;
    }

    public ChannelFuture channelFuture() {
        return future;
    }
}
