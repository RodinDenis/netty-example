package com.github.rodindenis.example.netty.bin.api.message;

import com.github.rodindenis.example.netty.bin.api.exception.GeneralMessageProcessingException;

public enum MessageTypeEnum {

    BASIC((byte) 1);

    private byte type;

    MessageTypeEnum(byte type) {
        this.type = type;
    }

    public static MessageTypeEnum get(byte type) {
        for (MessageTypeEnum value : values()) {
            if (value.type == type) {
                return value;
            }
        }
        throw new GeneralMessageProcessingException("Unsupported message type: " + type);
    }

    public int getType() {
        return type;
    }
}
