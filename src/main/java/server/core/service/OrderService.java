package server.service;

import org.springframework.stereotype.Service;
import server.dto.OrderDTO;

import java.util.List;

@Service
public interface OrderService {
    List<OrderDTO> getAllOrders();
}
