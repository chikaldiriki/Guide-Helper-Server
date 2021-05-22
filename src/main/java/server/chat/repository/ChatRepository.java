package server.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import server.chat.model.Chat;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer>, JpaSpecificationExecutor<Chat> {

    @Modifying
    @Query("update Chat chat set chat.numberOfMessages = :numberOfMessages where chat.id = :id")
    void updateNumberOfMessages(@Param(value = "id") long id, @Param(value = "numberOfMessages") int numberOfMessages);
}
