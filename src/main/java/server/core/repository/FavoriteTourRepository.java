package server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import server.core.model.FavoriteTour;

import java.util.List;

@Repository
public interface FavoriteTourRepository extends JpaRepository<FavoriteTour, Long>, JpaSpecificationExecutor<FavoriteTour> {

    List<FavoriteTour> getFavoriteTourByTourIdAndUserMail(Long tourId, String userMail);
}
