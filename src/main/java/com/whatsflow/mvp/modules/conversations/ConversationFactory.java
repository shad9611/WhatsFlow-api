package com.whatsflow.mvp.modules.conversations;

import java.time.LocalDateTime;
import java.util.UUID;

import com.whatsflow.mvp.modules.channels.ChannelType;

public final class ConversationFactory {
    private ConversationFactory() {
    }

    public static Conversation create(String externalUserId, ChannelType channel) {
        LocalDateTime now = LocalDateTime.now();

        return Conversation.builder()
                .id(UUID.randomUUID())
                .externalUserId(externalUserId)
                .channel(channel)
                .status(ConversationStatus.PENDING)
                .createdAt(now)
                .build();
    }
}
