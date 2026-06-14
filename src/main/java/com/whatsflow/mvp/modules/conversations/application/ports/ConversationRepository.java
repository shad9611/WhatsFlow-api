package com.whatsflow.mvp.modules.conversations.application.ports;

import java.util.Optional;
import java.util.UUID;

import com.whatsflow.mvp.modules.channels.ChannelType;
import com.whatsflow.mvp.modules.conversations.Conversation;

public interface ConversationRepository {
    Conversation save(Conversation conversation);

    Optional<Conversation> findById(UUID id);

    Optional<Conversation> findByExternalUserIdAndChannel(
            String externalUserId,
            ChannelType channel);
}