package server.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import server.chat.model.Chat;
import server.chat.repository.ChatRepository;
import server.specifications.GenericSpecification;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public int getChatId(String firstUserMail, String secondUserMail) {
        GenericSpecification<Chat> specFirstUser =
                new GenericSpecification<>("firstUserMail", "eq", firstUserMail);
        GenericSpecification<Chat> specSecondUser =
                new GenericSpecification<>("secondUserMail", "eq", secondUserMail);
        List<Chat> chats = chatRepository.findAll(Specification.where(specFirstUser).and(specSecondUser));
        if (chats.isEmpty()) {
            Chat chat = new Chat().setFirstUserMail(firstUserMail).setSecondUserMail(secondUserMail);
            return chatRepository.save(chat).getId();
        }
        return chats.get(0).getId();
    }
}