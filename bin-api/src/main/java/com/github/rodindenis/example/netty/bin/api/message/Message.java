package com.github.rodindenis.example.netty.bin.api.message;

import java.util.Objects;

public class Message {
    private int magicNumber;
    private int version;
    private MessageTypeEnum type;
    private String payload;

    public Message() {
    }

    public Message(int magicNumber, int protocolVersion, MessageTypeEnum type, String payload) {
        this.magicNumber = magicNumber;
        this.version = protocolVersion;
        this.type = type;
        this.payload = payload;
    }

    public MessageTypeEnum getType() {
        return type;
    }

    public void setType(MessageTypeEnum type) {
        this.type = type;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return magicNumber == message.magicNumber && version == message.version && payload.equals(message.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(magicNumber, version, payload);
    }

    @Override
    public String toString() {
        return "Message{" +
                "magicNumber=" + magicNumber +
                ", version=" + version +
                ", type=" + type +
                ", payload='" + payload + '\'' +
                '}';
    }
}
