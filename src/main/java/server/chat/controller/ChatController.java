package server.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import server.chat.model.Message;
import server.chat.service.MessageService;
import server.chat.service.ChatService;

import java.util.List;
import java.util.Optional;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ChatService chatService;

    @MessageMapping("/chat")
    public void processMessage(@Payload Message message) {
        Optional<Integer> chatId = chatService
                .getChatId(message.getSenderMail(), message.getReceiverMail(), true);

        message.setChatId(chatId.get());

        messagingTemplate.convertAndSendToUser(
                message.getReceiverMail(), "/messages", messageService.save(message));
    }

    @GetMapping("/messages/{chatId}")
    public List<Message> getDialogMessages(@PathVariable String chatId) {
        return messageService.findChatMessages(chatId);
    }

    @GetMapping("/messages/{chatId}/count")
    public Long countNewMessages(@PathVariable String chatId) {
        return messageService.countNewMessages(chatId);
    }
}