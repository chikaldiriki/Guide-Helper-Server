package server.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.chat.model.Message;
import server.chat.model.MessageStatus;
import server.chat.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {

    public Message save(Message message) {
        message.setStatus(MessageStatus.RECEIVED);
        // save message to DB
        return message;
    }

    public long countNewMessages(String dialogId) {
        // get count of unreaded messages in dialog
        return 0;
    }

    public List<Message> findChatMessages(String dialogId) {
        // find all messages from dialog
        return null;
    }

    public void updateStatus(String dialogId, MessageStatus status) {
        // update status for last sended message OR for last unreaded messages
    }
}