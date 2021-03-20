package server.core.repository;

import server.core.model.Tour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends CrudRepository<Tour, Integer> {

}
