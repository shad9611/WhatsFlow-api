package com.whatsflow.mvp.modules.messages.application.ports;

import java.util.List;
import java.util.UUID;

import com.whatsflow.mvp.modules.messages.Message;

public interface MessageRepository {
    Message save(Message message);

    List<Message> findByConversation(UUID conversationId);
}