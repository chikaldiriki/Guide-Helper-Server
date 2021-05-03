package server.chat.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.chat.dto.ChatDTO;
import server.chat.dto.MessageDTO;
import server.chat.model.Keyword;
import server.chat.model.Message;
import server.chat.service.ChatService;
import server.chat.service.MessageService;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatService chatService;

    @GetMapping
    public void processMessage(@RequestBody MessageDTO messageDTO) {
        Message message = new ModelMapper().map(messageDTO, Message.class);
        int chatId = chatService
                .getChatId(message.getSenderMail(), message.getReceiverMail());
        messageService.save(message.setChatId(chatId));
    }

    // create chat if not exist
    @GetMapping("/messages/chat/{firstUserId}/{secondUserId}")
    public int getChatId(@PathVariable String firstUserId, @PathVariable String secondUserId) {
        return chatService.getChatId(firstUserId, secondUserId);
    }

    @GetMapping("/dialogs/{userId}")
    public List<ChatDTO> getDialogs(@PathVariable String userId) {
        return chatService.getDialogs(userId);
    }

    @GetMapping("/messages/{chatId}")
    public List<MessageDTO> getDialogMessages(@PathVariable int chatId) {
        return messageService.findChatMessages(chatId);
    }

    @GetMapping("/messages/chat/keywords/{firstUserId}/{secondUserId}")
    public List<Keyword> getKeyWords(@PathVariable String firstUserId, @PathVariable String secondUserId) {
        return chatService.getKeywords(firstUserId, secondUserId);
    }
}