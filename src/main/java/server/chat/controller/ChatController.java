package server.chat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.chat.dto.ChatDTO;
import server.chat.service.ChatService;

import java.util.List;

@Tag(name = "Chat Controller", description = "Контроллер чата и ключевых слов")
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    // create chat if not exist
    @Operation(
            summary = "Получить id чата по id пользователей",
            description = "Если чата не существует, то чат создаётся и возвращается его id"
    )
    @GetMapping("/{firstUserId}/{secondUserId}")
    public long getChatId(@PathVariable String firstUserId, @PathVariable String secondUserId) {
        return chatService.getChatId(firstUserId, secondUserId);
    }

    @Operation(summary = "Получить список всех чатов пользователя по его id")
    @GetMapping("/dialogs/{userId}")
    public List<ChatDTO> getDialogs(@PathVariable String userId) {
        return chatService.getDialogs(userId);
    }

    @Operation(summary = "Получить cписок ключевых слов чата по id собеседников")
    @GetMapping("/keywords/{firstUserId}/{secondUserId}")
    public List<String> getKeyWords(@PathVariable String firstUserId, @PathVariable String secondUserId) {
        return chatService.getKeywords(firstUserId, secondUserId);
    }

    @Operation(summary = "Получить список чатов, содержащих некое ключевое слово")
    @GetMapping("/keywords/word={word}")
    public List<ChatDTO> getChatsByKeyword(@PathVariable String word) {
        return chatService.getChatsByKeyword(word);
    }

    @GetMapping("/keywords/new/user_mail={userMail}")
    public List<String> getNewPopularKeywords(@PathVariable String userMail) {
        return chatService.getNewPopularKeywords(userMail);
    }

    @GetMapping("/keywords/DB/user_mail={userMail}")
    public List<String> getPopularKeywordsFromDB(@PathVariable String userMail) {
        return chatService.getPopularKeywordsFromDB(userMail);
    }

    @Operation(summary = "Удалить чат по id собеседников")
    @DeleteMapping("/delete/{firstUserId}/{secondUserId}")
    public void deleteChat(@PathVariable String firstUserId, @PathVariable String secondUserId) {
        chatService.deleteChat(firstUserId, secondUserId);
    }

}