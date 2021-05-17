package server.chat.service;

import io.micrometer.core.lang.Nullable;
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

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @Nullable
    private Chat getChatByUsers(String firstUserMail, String secondUserMail) {
        GenericSpecification<Chat> specFirstUserAsFirst =
                new GenericSpecification<>("firstUserMail", "eq", firstUserMail);
        GenericSpecification<Chat> specSecondUserAsSecond =
                new GenericSpecification<>("secondUserMail", "eq", secondUserMail);

        GenericSpecification<Chat> specFirstUserAsSecond =
                new GenericSpecification<>("secondUserMail", "eq", firstUserMail);
        GenericSpecification<Chat> specSecondUserAsFirst =
                new GenericSpecification<>("firstUserMail", "eq", secondUserMail);

        List<Chat> chats = chatRepository.findAll(Specification.where(specFirstUserAsFirst).and(specSecondUserAsSecond));
        chats.addAll(chatRepository.findAll(Specification.where(specFirstUserAsSecond).and(specSecondUserAsFirst)));

        if (chats.isEmpty()) {
            return null;
        }
        return chats.get(0);
    }

    public int getChatId(String firstUserMail, String secondUserMail) {
        Chat chat = getChatByUsers(firstUserMail, secondUserMail);
        if (chat == null) {
            chat = new Chat().setFirstUserMail(firstUserMail).setSecondUserMail(secondUserMail);
            return chatRepository.save(chat).getId();
        }
        return chat.getId();
    }

    public List<ChatDTO> getDialogs(String userMail) {
        GenericSpecification<Chat> specFirstUser =
                new GenericSpecification<>("firstUserMail", "eq", userMail);
        GenericSpecification<Chat> specSecondUser =
                new GenericSpecification<>("secondUserMail", "eq", userMail);

        return Mapper.mapList(chatRepository.findAll(Specification.where(specFirstUser).or(specSecondUser)), ChatDTO.class);
    }

    public List<Keyword> getKeywords(String firstUserMail, String secondUserMail) {
        Chat chat = getChatByUsers(firstUserMail, secondUserMail);
        if (chat == null) {
            throw new IllegalArgumentException();
        }

        if (chat.isUpToDate()) {
            GenericSpecification<Keyword> spec = new GenericSpecification<>("chatId", "eq", chat.getId());
            return keywordRepository.findAll(spec);
        }

        //TODO: python server call

        return null;
    }
}