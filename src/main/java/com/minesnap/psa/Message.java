package com.minesnap.psa;

public class Message {
    private final String name;
    private final String content;
    private final int chance;

    public Message(String name, String content) {
        this(name, content, 100);
    }

    public Message(String name, String content, int chance) {
        this.name = name;
        this.content = content;
        this.chance = chance;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public int getChance() {
        return chance;
    }
}
