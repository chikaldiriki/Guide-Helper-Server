package server.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.chat.model.Keyword;

import java.util.List;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Integer>, JpaSpecificationExecutor<Keyword> {

    @Transactional
    void deleteByChatId(long chatId);

    List<Keyword> getKeywordsByWord(String word);
    List<Keyword> getKeywordsByChatId(long chatId);
}
