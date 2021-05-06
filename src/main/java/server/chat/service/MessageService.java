package server.chat.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.chat.dto.MessageDTO;
import server.chat.model.Message;
import server.chat.repository.MessageRepository;
import server.mapper.Mapper;
import server.specifications.GenericSpecification;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public MessageDTO save(Message messageDTO) {
        Message message = new ModelMapper().map(messageDTO, Message.class);
        Message savedMessage = messageRepository.save(message);
        return Mapper.map(savedMessage, MessageDTO.class);
    }

    public List<MessageDTO> findChatMessages(int chatId) {
        GenericSpecification<Message> spec = new GenericSpecification<>("chatId", "eq", chatId);
        return Mapper.mapList(messageRepository.findAll(spec, Sort.by(Sort.Direction.ASC, "dispatchTime")), MessageDTO.class);
    }

    public void updateStatus(String chatId) {
        // update status for last sended message OR for last unreaded messages
    }
}