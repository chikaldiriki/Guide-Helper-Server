package server.chat.service;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatService {

    public Optional<Integer> getChatId(String firstUserMail,
                                      String secondUserMail,
                                      boolean createIfNotExist) {
        return null;
    }
}