package server.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.chat.model.Message;
import server.chat.repository.MessageRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public Message save(Message message) {
        //message.setStatus(false);
        return messageRepository.save(message);
    }

    public long countNewMessages(int chatId) {
        return findChatMessages(chatId)
                .stream()
                .dropWhile(Message::isStatus)
                .count();
    }

    public List<Message> findChatMessages(int chatId) {
        return StreamSupport.stream(messageRepository.findAll().spliterator(), true)
                .filter(message -> message.getChatId() == chatId)
                .sorted(Comparator.comparing(message -> message.getDispatchTime().getTime()))
                .collect(Collectors.toList());
    }

    public void updateStatus(String chatId) {
        // update status for last sended message OR for last unreaded messages
    }
}