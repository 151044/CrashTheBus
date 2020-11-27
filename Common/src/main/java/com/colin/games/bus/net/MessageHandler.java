package com.colin.games.bus.net;

import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class MessageHandler {
    private MessageHandler(){
        throw new AssertionError();
    }
    private static Map<String, BiConsumer<ChannelHandlerContext,Message>> handlers = new HashMap<>();
    public static boolean hasHandler(String str){
        return handlers.containsKey(str);
    }
    public static void addHandler(String str, BiConsumer<ChannelHandlerContext,Message> cons){
        handlers.put(str,cons);
    }
    public static void handle(ChannelHandlerContext ctx, Message m){
        handlers.getOrDefault(m.getType(), (c,msg) -> {/*No-op*/}).accept(ctx,m);
    }
    public static void remove(String str){
        handlers.remove(str);
    }
}
