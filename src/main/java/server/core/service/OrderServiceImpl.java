package server.core.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.core.dto.OrderDTO;
import server.core.dto.TourDTO;
import server.core.model.Order;
import server.core.model.Tour;
import server.core.repository.OrderRepository;
import server.specifications.GenericSpecification;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    private List<OrderDTO> orderToDTO(List<Order> tours) {
        return tours.stream()
                .map(tour -> new ModelMapper().map(tour, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderToDTO(orderRepository.findAll());
    }

    @Override
    public List<OrderDTO> getOrdersByUser(String userId) {
        GenericSpecification<Order> spec = new GenericSpecification<>("customerMail", "eq", userId);
        return orderToDTO(orderRepository.findAll(spec, Sort.by(Sort.Direction.ASC, "tourTime")));
    }
}
