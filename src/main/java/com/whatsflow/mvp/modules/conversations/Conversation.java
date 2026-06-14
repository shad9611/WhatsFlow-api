package com.whatsflow.mvp.modules.conversations;

import java.time.LocalDateTime;
import java.util.UUID;

import com.whatsflow.mvp.modules.channels.ChannelType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Conversation {

    private UUID id;
    private String externalUserId;
    private ConversationStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ChannelType channel;

    public void registerActivity() {
        this.updatedAt = LocalDateTime.now();
    }
}