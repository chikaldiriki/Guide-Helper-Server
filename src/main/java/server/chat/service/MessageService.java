package server.chat.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.chat.dto.MessageDTO;
import server.chat.model.Message;
import server.chat.repository.MessageRepository;
import server.specifications.GenericSpecification;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    private List<MessageDTO> messageToDTO(List<Message> messages) {
        return messages.stream()
                .map(message -> new ModelMapper().map(message, MessageDTO.class))
                .collect(Collectors.toList());
    }

    public Message save(Message message) {
        //message.setStatus(false);
        return messageRepository.save(message);
    }

    public List<MessageDTO> findChatMessages(int chatId) {
        GenericSpecification<Message> spec = new GenericSpecification<>("chatId", "eq", chatId);
        return messageToDTO(messageRepository.findAll(spec, Sort.by(Sort.Direction.ASC, "dispatchTime")));
    }

    public void updateStatus(String chatId) {
        // update status for last sended message OR for last unreaded messages
    }
}