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
import server.mapper.Mapper;
import server.specifications.GenericSpecification;

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

    private final Rake rake = new Rake();

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

        return Mapper.mapList(chatRepository.findAll(Specification.where(specFirstUser).or(specSecondUser)), ChatDTO.class);
    }

    public List<Keyword> getKeywords(String firstUserMail, String secondUserMail) {
        //TODO: language detecting
        Chat chat = getChatByUsers(firstUserMail, secondUserMail);
        if (chat == null) {
            throw new IllegalArgumentException();
        }

        long chatId = chat.getId();

        int newNumberOfMessages = messagesService.countMessagesByChatId(chatId);
        if (chat.getNumberOfMessages() == newNumberOfMessages) {
            GenericSpecification<Keyword> spec = new GenericSpecification<>("chatId", "eq", chatId);
            return keywordRepository.findAll(spec);
        }

        chatRepository.updateNumberOfMessages(chatId, newNumberOfMessages);

        keywordRepository.deleteByChatId(chatId);

        List<Keyword> keywords = rake.apply(messagesService.getChatText(chatId)).stream()
                .map(word -> new Keyword(0, chatId, word))
                .collect(Collectors.toList());

        keywordRepository.saveAll(keywords);

        return keywords;
    }

    public void deleteChat(String firstUserId, String secondUserId) {
        chatRepository.delete(Objects.requireNonNull(getChatByUsers(firstUserId, secondUserId)));
    }
}