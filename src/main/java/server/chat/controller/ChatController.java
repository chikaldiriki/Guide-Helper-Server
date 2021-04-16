package server.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import server.chat.model.Message;
import server.chat.service.ChatService;
import server.chat.service.MessageService;

import java.util.List;

@RestController
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    private final MessageService messageService;

    private final ChatService chatService;

    @Autowired
    public ChatController(SimpMessagingTemplate messagingTemplate,
                          MessageService messageService,
                          ChatService chatService) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
        this.chatService = chatService;
    }

    @MessageMapping("chat")
    public void processMessage(@Payload Message message) {
        int chatId = chatService
                .getChatId(message.getSenderMail(), message.getReceiverMail());
        System.out.println(chatId);
        Message savedMessage = messageService.save(message.setChatId(chatId));
        messagingTemplate.convertAndSend("/queue/messages/228", savedMessage);
    }

    @GetMapping("/messages/chat/{firstUserId}/{secondUserId}")
    public int getChatId(@PathVariable String firstUserId, @PathVariable String secondUserId) {
        return chatService.getChatId(firstUserId, secondUserId);
    }

    @GetMapping("/messages/{chatId}")
    public List<Message> getDialogMessages(@PathVariable int chatId) {
        return messageService.findChatMessages(chatId);
    }

    @GetMapping("/messages/{chatId}/count")
    public Long countNewMessages(@PathVariable int chatId) {
        return messageService.countNewMessages(chatId);
    }
}