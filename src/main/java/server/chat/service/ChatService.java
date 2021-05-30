package server.chat.service;

import io.micrometer.core.lang.Nullable;
import keywords.rake.Rake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import server.chat.dto.ChatDTO;
import server.chat.model.Chat;
import server.chat.model.Keyword;
import server.chat.repository.ChatRepository;
import server.chat.repository.KeywordRepository;
import server.core.dto.UserDTO;
import server.core.service.UserService;
import server.specifications.GenericSpecification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private MessagesService messagesService;

    @Autowired
    private UserService userService;

    @Nullable
    private Chat getChatByUsers(String firstUserMail, String secondUserMail) {
        GenericSpecification<Chat> specFirstUserToFirst =
                new GenericSpecification<>("firstUserMail", "eq", firstUserMail);
        GenericSpecification<Chat> specSecondUserToFirst =
                new GenericSpecification<>("firstUserMail", "eq", secondUserMail);
        GenericSpecification<Chat> specFirstUserToSecond =
                new GenericSpecification<>("secondUserMail", "eq", firstUserMail);
        GenericSpecification<Chat> specSecondUserToSecond =
                new GenericSpecification<>("secondUserMail", "eq", secondUserMail);
        Specification<Chat> firstSpec = Specification.where(specFirstUserToFirst).and(specSecondUserToSecond);
        Specification<Chat> reversedUsersSpec = Specification.where(specFirstUserToSecond).and(specSecondUserToFirst);

        List<Chat> chats = chatRepository.findAll(Specification.where(firstSpec).or(reversedUsersSpec));
        if (chats.isEmpty()) {
            return null;
        }
        return chats.get(0);
    }

    public long getChatId(String firstUserMail, String secondUserMail) {
        Chat chat = getChatByUsers(firstUserMail, secondUserMail);
        if (chat == null) {
            chat = new Chat().setFirstUserMail(firstUserMail).setSecondUserMail(secondUserMail);
            return chatRepository.save(chat).getId();
        }
        return chat.getId();
    }

    public List<ChatDTO> getDialogs(String userId) {
        GenericSpecification<Chat> specFirstUser =
                new GenericSpecification<>("firstUserMail", "eq", userId);
        GenericSpecification<Chat> specSecondUser =
                new GenericSpecification<>("secondUserMail", "eq", userId);

        return mapChatListToChatDTOList(chatRepository.findAll(Specification.where(specFirstUser).or(specSecondUser)));
    }

    public List<Keyword> getKeywords(String firstUserMail, String secondUserMail) {
        Chat chat = getChatByUsers(firstUserMail, secondUserMail);
        if (chat == null) {
            throw new IllegalArgumentException();
        }

        long chatId = chat.getId();

        int newNumberOfMessages = messagesService.countMessagesByChatId(chatId);
        if (newNumberOfMessages == 0) {
            return Collections.emptyList();
        }
        if (chat.getNumberOfMessages() == newNumberOfMessages) {
            GenericSpecification<Keyword> spec = new GenericSpecification<>("chatId", "eq", chatId);
            return keywordRepository.findAll(spec);
        }

        chatRepository.updateNumberOfMessages(chatId, newNumberOfMessages);

        keywordRepository.deleteByChatId(chatId);

        String chatText = messagesService.getChatText(chatId);

        Rake rake = new Rake();

        List<Keyword> keywords = rake.apply(chatText).stream()
                .map(word -> new Keyword(0, chatId, word))
                .collect(Collectors.toList());

        keywordRepository.saveAll(keywords);

        return keywords;
    }

    public void deleteChat(String firstUserId, String secondUserId) {
        chatRepository.delete(Objects.requireNonNull(getChatByUsers(firstUserId, secondUserId)));
    }

    private ChatDTO mapChatToChatDTO(Chat chat) {
        UserDTO firstUser = userService.getUser(chat.getFirstUserMail());
        UserDTO secondUser = userService.getUser(chat.getSecondUserMail());
        return new ChatDTO()
                .setFirstUserMail(firstUser.getUserMail())
                .setFirstUserName(firstUser.getName())
                .setFirstUserPhoto(firstUser.getAvatarUrl())
                .setSecondUserMail(secondUser.getUserMail())
                .setSecondUserName(secondUser.getName())
                .setSecondUserPhoto(secondUser.getAvatarUrl());
    }

    private List<ChatDTO> mapChatListToChatDTOList(List<Chat> chats) {
        List<ChatDTO> result = new ArrayList<>();

        for (Chat chat : chats) {
            result.add(mapChatToChatDTO(chat));
        }

        return result;
    }
}