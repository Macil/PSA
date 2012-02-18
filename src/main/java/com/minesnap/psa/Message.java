package com.minesnap.psa;

public class Message {
    private final String name;
    private final String content;
    private final int chance;
    private final boolean spout;

    public Message(String name, String content) {
        this(name, content, 100);
    }

    public Message(String name, String content, int chance) {
        this(name, content, 100, false);
    }

    public Message(String name, String content, int chance, boolean spout) {
        this.name = name;
        this.content = content;
        this.chance = chance;
        this.spout = spout;
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

    public boolean getSpout() {
        return spout;
    }
}
