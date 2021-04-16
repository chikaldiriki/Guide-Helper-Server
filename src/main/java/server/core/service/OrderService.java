package server.core.service;

import org.springframework.stereotype.Service;
import server.core.dto.OrderDTO;

import java.util.List;

@Service
public interface OrderService {
    List<OrderDTO> getAllOrders();

    List<OrderDTO> getOrdersByUser(String userId);
}
