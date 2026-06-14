# WhatsFlow API

> Rule-Based Conversational Automation Engine built with Java, Clean Architecture, and Domain-Driven Design.

WhatsFlow API is a backend application designed to manage multi-channel conversations and automate responses through configurable business rules.

The system receives incoming messages from external channels, stores conversation history, evaluates active rules, generates automated responses, and persists all interactions while keeping business logic isolated from infrastructure concerns.

---

## Features

- Multi-channel conversation support
- Conversation lifecycle management
- Incoming and outgoing message persistence
- Rule-based automated responses
- Active rule filtering
- Extensible domain-driven architecture
- In-memory repositories for testing
- Clean Architecture implementation
- Hexagonal Architecture principles
- Testable and framework-independent business logic

---

## Architecture

This project follows the principles of:

- Clean Architecture
- Domain-Driven Design (DDD)
- Hexagonal Architecture (Ports & Adapters)

### High-Level Flow

```text
Incoming Message
        │
        ▼
ReceiveIncomingMessageUseCase
        │
        ▼
Find or Create Conversation
        │
        ▼
Persist Inbound Message
        │
        ▼
Rule Matching
        │
        ▼
Generate Reply
        │
        ▼
Persist Outbound Message
        │
        ▼
Return Response
```

---

## Project Structure

```text
src
│
├── modules
│   │
│   ├── channels
│   │   ├── application
│   │   └── domain
│   │
│   ├── conversations
│   │   ├── application
│   │   └── domain
│   │
│   ├── messages
│   │   ├── application
│   │   └── domain
│   │
│   └── rules
│       ├── application
│       └── domain
│
└── shared
```

---

## Core Domain

### Conversation

Represents a conversation between a user and a communication channel.

Responsibilities:

- Maintain conversation identity
- Associate messages with a user
- Group related interactions

---

### Message

Represents a message exchanged within a conversation.

Supported message types:

- INBOUND
- OUTBOUND

Responsibilities:

- Store message content
- Associate messages with conversations
- Track communication history

---

### Rule

Represents a business rule capable of generating an automated response.

Responsibilities:

- Define matching criteria
- Provide automated responses
- Enable configurable conversation behavior

Example:

```text
Keyword: pricing
Response: Our plans start at $9.99/month
```

---

## Main Use Case

### ReceiveIncomingMessageUseCase

This use case orchestrates the complete message processing workflow.

#### Responsibilities

1. Find an existing conversation
2. Create a conversation if it does not exist
3. Store the inbound message
4. Load active rules
5. Match the message against rules
6. Generate a response
7. Store the outbound message
8. Return the generated response

#### Example

```java
ReceiveIncomingMessageCommand command =
        new ReceiveIncomingMessageCommand(
                "user-123",
                ChannelType.WHATSAPP,
                "pricing");

ReceiveIncomingMessageResponse response =
        useCase.execute(command);
```

---

## Rule Matching

The application uses a dedicated RuleMatcher service responsible for determining whether a message satisfies any active rule.

### Example

Incoming message:

```text
What are your pricing plans?
```

Rule:

```text
keyword = pricing
```

Generated response:

```text
Our plans start at $9.99/month
```

### Fallback Response

When no active rule matches the incoming message:

```text
Lo siento, no entendí tu mensaje.
```

---

## Testing

The project includes in-memory implementations of repositories to simplify testing and avoid infrastructure dependencies.

### Available Test Repositories

```text
InMemoryConversationRepository
InMemoryMessageRepository
InMemoryRuleRepository
```

### Benefits

- Fast execution
- Deterministic results
- No database setup required
- Easy unit testing

Example:

```java
var conversationRepository =
        new InMemoryConversationRepository();

var messageRepository =
        new InMemoryMessageRepository();

var ruleRepository =
        new InMemoryRuleRepository();

var ruleMatcher = new RuleMatcher();

var useCase = new ReceiveIncomingMessageUseCase(
        conversationRepository,
        messageRepository,
        ruleRepository,
        ruleMatcher);
```

---

## Example Processing Flow

```text
User sends:
"pricing"

        │
        ▼

Conversation Lookup

        │
        ▼

Store INBOUND Message

        │
        ▼

Load Active Rules

        │
        ▼

Rule Matching

        │
        ▼

Generate Response

        │
        ▼

Store OUTBOUND Message

        │
        ▼

Return Response
```

---

## Technologies

- Java 21
- Maven
- JUnit 5
- Clean Architecture
- Domain-Driven Design (DDD)
- Hexagonal Architecture

---

## Future Roadmap

### Channels

- WhatsApp
- Telegram
- Instagram
- Messenger
- Web Chat

### Rule Engine

- Regular Expressions
- Rule Priorities
- Context-Aware Responses
- Intent Detection

### Persistence

- PostgreSQL
- MongoDB

### AI Integrations

- OpenAI
- Claude
- Gemini

### Analytics

- Conversation Metrics
- Response Performance
- Rule Effectiveness Reports

---

## Design Goals

The primary goal of WhatsFlow API is to provide a maintainable and extensible foundation for conversational automation systems while keeping business rules independent from infrastructure and external services.

---

## License

This project is licensed under the MIT License.
