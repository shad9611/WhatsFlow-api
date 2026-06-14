package com.whatsflow.mvp.modules.messages;

import java.time.LocalDateTime;
import java.util.UUID;

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
public class Message {

    private UUID id;
    private UUID conversationId;
    private String content;
    private MessageDirection direction;
    private LocalDateTime timestamp;

}