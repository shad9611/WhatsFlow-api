package com.whatsflow.mvp.modules.channels.application.usecases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.whatsflow.mvp.modules.channels.ChannelType;
import com.whatsflow.mvp.modules.channels.application.dto.ReceiveIncomingMessageCommand;
import com.whatsflow.mvp.modules.channels.application.dto.ReceiveIncomingMessageResponse;
import com.whatsflow.mvp.modules.conversations.infraestructure.inmemory.InMemoryConversationRepository;
import com.whatsflow.mvp.modules.messages.infraestructure.inmemory.InMemoryMessageRepository;
import com.whatsflow.mvp.modules.rules.Rule;
import com.whatsflow.mvp.modules.rules.RuleMatcher;
import com.whatsflow.mvp.modules.rules.infraestructure.inmemory.InMemoryRuleRepository;

class ReceiveIncomingMessageUseCaseTest {

        private InMemoryConversationRepository conversationRepository;
        private InMemoryMessageRepository messageRepository;
        private InMemoryRuleRepository ruleRepository;
        private RuleMatcher ruleMatcher;

        private ReceiveIncomingMessageUseCase useCase;

        @BeforeEach
        void setUp() {

                conversationRepository = new InMemoryConversationRepository();

                messageRepository = new InMemoryMessageRepository();

                ruleRepository = new InMemoryRuleRepository();

                ruleMatcher = new RuleMatcher();

                useCase = new ReceiveIncomingMessageUseCase(
                                conversationRepository,
                                messageRepository,
                                ruleRepository,
                                ruleMatcher);
        }

        @Test
        void shouldReturnRuleResponseWhenMatchExists() {

                Rule greetingRule = Rule.builder()
                                .pattern("hola")
                                .response("Hola, ¿cómo puedo ayudarte?")
                                .active(true)
                                .build();

                ruleRepository.save(
                                greetingRule);

                ReceiveIncomingMessageCommand command = new ReceiveIncomingMessageCommand(
                                ChannelType.WHATSAPP,
                                "526671234567",
                                "hola", null);

                ReceiveIncomingMessageResponse response = useCase.execute(
                                command);

                assertEquals(
                                "Hola, ¿cómo puedo ayudarte?",
                                response.response());
        }

        @Test
        void shouldReturnFallbackResponseWhenNoRuleMatches() {
                ReceiveIncomingMessageCommand command = new ReceiveIncomingMessageCommand(
                                ChannelType.WHATSAPP,
                                "526671234567",
                                "mensaje desconocido",
                                null);

                ReceiveIncomingMessageResponse result = useCase.execute(command);

                assertEquals(
                                "Lo siento, no entendí tu mensaje.",
                                result.response());
        }

        @Test
        void shouldCreateConversationWhenNotExists() {

        }

        @Test
        void shouldReuseExistingConversation() {
        }

        @Test
        void shouldStoreInboundAndOutboundMessages() {
        }
}