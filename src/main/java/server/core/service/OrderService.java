package server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import server.core.dto.OrderDTO;
import server.core.model.Order;
import server.core.repository.OrderRepository;
import server.mapper.Mapper;
import server.specifications.GenericSpecification;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderDTO> getAllOrders() {
        return Mapper.mapList(orderRepository.findAll(), OrderDTO.class);
    }

    public List<OrderDTO> getOrdersByUser(String userId) {
        GenericSpecification<Order> spec = new GenericSpecification<>("customerMail", "eq", userId);
        return Mapper.mapList(orderRepository.findAll(spec, Sort.by(Sort.Direction.ASC, "tourTime")), OrderDTO.class);
    }

    public List<OrderDTO> getComingOrders(String userId, LocalDateTime currentDate) {
        GenericSpecification<Order> userSpec = new GenericSpecification<>("customerMail", "eq", userId);
        GenericSpecification<Order> dateSpec = new GenericSpecification<>("tourTime", "gt", currentDate);
        return Mapper.mapList(orderRepository.findAll(Specification.where(userSpec).and(dateSpec)), OrderDTO.class);
    }

    public void deleteOrder(OrderDTO orderDTO) {
        orderRepository.delete(Mapper.map(orderDTO, Order.class));
    }

    public void addOrder(OrderDTO orderDTO) {
        orderRepository.save(Mapper.map(orderDTO, Order.class));
    }
}
