package com.whatsflow.mvp.modules.conversations.infraestructure.inmemory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.whatsflow.mvp.modules.channels.ChannelType;
import com.whatsflow.mvp.modules.conversations.Conversation;
import com.whatsflow.mvp.modules.conversations.application.ports.ConversationRepository;

public class InMemoryConversationRepository
        implements ConversationRepository {

    private final Map<UUID, Conversation> conversations = new HashMap<>();

    @Override
    public Conversation save(
            Conversation conversation) {

        conversations.put(
                conversation.getId(),
                conversation);

        return conversation;
    }

    @Override
    public Optional<Conversation> findByExternalUserIdAndChannel(
            String externalUserId,
            ChannelType channel) {

        return conversations.values()
                .stream()
                .filter(conversation -> conversation.getExternalUserId()
                        .equals(externalUserId)
                        &&
                        conversation.getChannel()
                                .equals(channel))
                .findFirst();
    }

    @Override
    public Optional<Conversation> findById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}