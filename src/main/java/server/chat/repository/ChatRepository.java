package server.chat.repository;

import server.chat.model.Chat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Integer> {
}
