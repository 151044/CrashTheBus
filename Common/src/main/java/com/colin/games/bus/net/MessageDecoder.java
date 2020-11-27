package com.colin.games.bus.net;

import com.colin.swing.Environment;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class MessageDecoder extends MessageToMessageDecoder<String> {
    @Override
    protected void decode(ChannelHandlerContext ctx, String str, List<Object> out) throws Exception {
        String[] arr = str.split(Environment.getMessageSeparator());
        if(arr.length != 2){
            //throw new MalformedMessageException();
        }
        out.add(new Message(arr[0],arr[1]));
    }
}
