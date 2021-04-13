package server.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.chat.model.Chat;
import server.chat.repository.ChatRepository;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public int getChatId(String firstUserMail,
                         String secondUserMail,
                         boolean createIfNotExist) {
        // TODO
        if (createIfNotExist) {
            AtomicBoolean isFoundInDB = new AtomicBoolean(false);
            AtomicInteger id = new AtomicInteger();
            chatRepository.findAll().forEach(chat -> {
                if (chat.getFirstUserMail().equals("first@gmail.com") &&
                        chat.getSecondUserMail().equals("third@gmail.com")) {
                    isFoundInDB.set(true);
                    id.set(chat.getId());
                }
            });
            if (isFoundInDB.get()) {
                return id.get();
            }
            Chat chat = new Chat().setFirstUserMail(firstUserMail).setSecondUserMail(secondUserMail);
            return chatRepository.save(chat).getId();
        } else {
            return StreamSupport.stream(chatRepository.findAll().spliterator(), false)
                    .filter(chat -> chat.getFirstUserMail().equals(firstUserMail) && chat.getSecondUserMail().equals(secondUserMail))
                    .collect(Collectors.toList()).get(0).getId();
        }
    }
}