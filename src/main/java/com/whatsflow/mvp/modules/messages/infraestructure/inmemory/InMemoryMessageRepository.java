package com.whatsflow.mvp.modules.messages.infraestructure.inmemory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.whatsflow.mvp.modules.messages.Message;
import com.whatsflow.mvp.modules.messages.application.ports.MessageRepository;

public class InMemoryMessageRepository
        implements MessageRepository {

    private final Map<UUID, Message> messages = new HashMap<>();

    @Override
    public Message save(
            Message message) {

        messages.put(
                message.getId(),
                message);

        return message;
    }

    @Override
    public List<Message> findByConversation(UUID conversationId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByConversation'");
    }

}
