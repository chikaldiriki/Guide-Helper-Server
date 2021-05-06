package server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.core.dto.OrderDTO;
import server.core.model.Order;
import server.core.repository.OrderRepository;
import server.mapper.Mapper;
import server.specifications.GenericSpecification;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /*private List<OrderDTO> orderToDTO(List<Order> tours) {
        return tours.stream()
                .map(tour -> new ModelMapper().map(tour, OrderDTO.class))
                .collect(Collectors.toList());
    }*/

    @Override
    public List<OrderDTO> getAllOrders() {
        return Mapper.mapList(orderRepository.findAll(), OrderDTO.class);
    }

    @Override
    public List<OrderDTO> getOrdersByUser(String userId) {
        GenericSpecification<Order> spec = new GenericSpecification<>("customerMail", "eq", userId);
        return Mapper.mapList(orderRepository.findAll(spec, Sort.by(Sort.Direction.ASC, "tourTime")), OrderDTO.class);
    }
}
