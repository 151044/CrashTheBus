package com.colin.games.bus.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.net.InetAddress;

public class Client {
    private InetAddress addr;
    private int port;
    private Channel chan;
    private ChannelFuture future;

}
