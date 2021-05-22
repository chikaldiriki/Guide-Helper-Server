package server.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.chat.dto.ChatDTO;
import server.chat.model.Keyword;
import server.chat.service.ChatService;
import server.chat.service.MessagesService;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private MessagesService messagesService;

    // create chat if not exist
    @GetMapping("/{firstUserId}/{secondUserId}")
    public long getChatId(@PathVariable String firstUserId, @PathVariable String secondUserId) {
        return chatService.getChatId(firstUserId, secondUserId);
    }

    @GetMapping("/dialogs/{userId}")
    public List<ChatDTO> getDialogs(@PathVariable String userId) {
        return chatService.getDialogs(userId);
    }


    @GetMapping("/keywords/{firstUserId}/{secondUserId}")
    public List<Keyword> getKeyWords(@PathVariable String firstUserId, @PathVariable String secondUserId) {
        return chatService.getKeywords(firstUserId, secondUserId);
    }

    @DeleteMapping("/delete/{firstUserId}/{secondUserId}")
    public void deleteChat(@PathVariable String firstUserId, @PathVariable String secondUserId) {
        chatService.deleteChat(firstUserId, secondUserId);
    }

    @GetMapping("test/{chatId}")
    public String testQuery(@PathVariable long chatId) {
        return messagesService.getChatText(chatId);
    }

}