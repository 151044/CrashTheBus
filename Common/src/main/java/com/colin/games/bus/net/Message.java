package com.colin.games.bus.net;

import com.colin.swing.Environment;

public class Message {
    private final String type;
    private final String content;

    public Message(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + Environment.getMessageSeparator() + content;
    }
}
