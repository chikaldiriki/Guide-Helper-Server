package server.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import server.chat.model.Keyword;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Integer>, JpaSpecificationExecutor<Keyword> {
}
