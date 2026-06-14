package com.whatsflow.mvp.modules.channels.application.usecases;

import java.util.List;
import java.util.Optional;

import com.whatsflow.mvp.modules.channels.ChannelType;
import com.whatsflow.mvp.modules.channels.application.dto.ReceiveIncomingMessageCommand;
import com.whatsflow.mvp.modules.channels.application.dto.ReceiveIncomingMessageResponse;
import com.whatsflow.mvp.modules.conversations.Conversation;
import com.whatsflow.mvp.modules.conversations.ConversationFactory;
import com.whatsflow.mvp.modules.conversations.application.ports.ConversationRepository;
import com.whatsflow.mvp.modules.messages.Message;
import com.whatsflow.mvp.modules.messages.MessageFactory;
import com.whatsflow.mvp.modules.messages.application.ports.MessageRepository;
import com.whatsflow.mvp.modules.rules.Rule;
import com.whatsflow.mvp.modules.rules.RuleMatcher;
import com.whatsflow.mvp.modules.rules.application.ports.RuleRepository;

public class ReceiveIncomingMessageUseCase {

        private static final String DEFAULT_FALLBACK_RESPONSE = "Lo siento, no entendí tu mensaje.";
        private final ConversationRepository conversationRepository;
        private final MessageRepository messageRepository;
        private final RuleRepository ruleRepository;
        private final RuleMatcher ruleMatcher;

        public ReceiveIncomingMessageUseCase(
                        ConversationRepository conversationRepository,
                        MessageRepository messageRepository,
                        RuleRepository ruleRepository,
                        RuleMatcher ruleMatcher) {
                this.conversationRepository = conversationRepository;
                this.messageRepository = messageRepository;
                this.ruleRepository = ruleRepository;
                this.ruleMatcher = ruleMatcher;
        }

        private Conversation findOrCreateConversation(
                        String externalUserId,
                        ChannelType channel) {
                return conversationRepository.findByExternalUserIdAndChannel(externalUserId, channel)
                                .orElseGet(() -> conversationRepository
                                                .save(ConversationFactory.create(externalUserId, channel)));
        }

        public ReceiveIncomingMessageResponse execute(
                        ReceiveIncomingMessageCommand incomingMessage) {

                Conversation conversation = findOrCreateConversation(
                                incomingMessage.externalUserId(),
                                incomingMessage.channel());

                Message inboundMessage = MessageFactory.createInbound(
                                conversation.getId(),
                                incomingMessage.content());

                messageRepository.save(inboundMessage);

                List<Rule> activeRules = ruleRepository.findActiveRules();

                Optional<Rule> matchingRule = ruleMatcher.findMatch(
                                incomingMessage.content(),
                                activeRules);

                String replyMessage = matchingRule
                                .map(Rule::getResponse)
                                .orElse(DEFAULT_FALLBACK_RESPONSE);

                Message outboundMessage = MessageFactory.createOutbound(
                                conversation.getId(),
                                replyMessage);

                messageRepository.save(outboundMessage);

                return new ReceiveIncomingMessageResponse(replyMessage);
        }

}