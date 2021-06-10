package server.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;
import server.core.model.Order;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
    @Transactional
    void deleteOrdersByCustomerMailAndTourIdAndTourTime(String customerMail, Long tourId, LocalDateTime tourTime);
}
