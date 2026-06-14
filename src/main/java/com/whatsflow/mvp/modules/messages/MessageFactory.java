package com.whatsflow.mvp.modules.messages;

import java.time.LocalDateTime;
import java.util.UUID;

public final class MessageFactory {

    private MessageFactory() {
    }

    public static Message createInbound(
            UUID conversationId,
            String content) {

        return Message.builder()
                .id(UUID.randomUUID())
                .conversationId(conversationId)
                .content(content)
                .direction(MessageDirection.INBOUND)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static Message createOutbound(
            UUID conversationId,
            String content) {

        return Message.builder()
                .id(UUID.randomUUID())
                .conversationId(conversationId)
                .content(content)
                .direction(MessageDirection.OUTBOUND)
                .timestamp(LocalDateTime.now())
                .build();
    }
}