package server.chat.service;

import io.micrometer.core.lang.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import server.chat.dto.ChatDTO;
import server.chat.model.Chat;
import server.chat.model.Keyword;
import server.chat.repository.ChatRepository;
import server.chat.repository.KeywordRepository;
import server.specifications.GenericSpecification;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    private List<ChatDTO> chatToDTO(List<Chat> messages) {
        return messages.stream()
                .map(chat -> new ModelMapper().map(chat, ChatDTO.class))
                .collect(Collectors.toList());
    }

    @Nullable
    private Chat getChatByUsers(String firstUserMail, String secondUserMail) {
        GenericSpecification<Chat> specFirstUser =
                new GenericSpecification<>("firstUserMail", "eq", firstUserMail);
        GenericSpecification<Chat> specSecondUser =
                new GenericSpecification<>("secondUserMail", "eq", secondUserMail);
        List<Chat> chats = chatRepository.findAll(Specification.where(specFirstUser).and(specSecondUser));
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

    public List<ChatDTO> getDialogs(String userId) {
        GenericSpecification<Chat> specFirstUser =
                new GenericSpecification<>("firstUserMail", "eq", userId);
        GenericSpecification<Chat> specSecondUser =
                new GenericSpecification<>("secondUserMail", "eq", userId);

        return chatToDTO(chatRepository.findAll(Specification.where(specFirstUser).or(specSecondUser)));
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