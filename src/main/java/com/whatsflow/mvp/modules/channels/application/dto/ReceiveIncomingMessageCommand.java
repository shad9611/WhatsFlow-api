package com.whatsflow.mvp.modules.channels.application.dto;

import java.time.LocalDateTime;

import com.whatsflow.mvp.modules.channels.ChannelType;

public record ReceiveIncomingMessageCommand(
                ChannelType channel,
                String externalUserId,
                String content,
                LocalDateTime receivedAt) {
}