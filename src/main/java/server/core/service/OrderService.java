package server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import server.core.dto.OrderDTO;
import server.core.model.Order;
import server.core.repository.OrderRepository;
import server.specifications.GenericSpecification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private Order mapDTOtoEntity(OrderDTO orderDTO) {
        return new Order().setCustomerMail(orderDTO.getCustomerMail())
                .setTourId(orderDTO.getTourId())
                .setTourTime(orderDTO.getTourTime());
    }

    private OrderDTO mapEntityToDTO(Order order) {
        return new OrderDTO(order.getCustomerMail(), order.getTourId(), order.getTourTime());
    }

    private List<OrderDTO> mapListEntityToDTO(List<Order> orders) {
        return orders.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrders() {
        return mapListEntityToDTO(orderRepository.findAll());
    }

    public List<OrderDTO> getOrdersByUser(String userId) {
        GenericSpecification<Order> spec = new GenericSpecification<>("customerMail", "eq", userId);
        return mapListEntityToDTO(orderRepository.findAll(spec, Sort.by(Sort.Direction.ASC, "tourTime")));
    }

    public List<OrderDTO> getComingOrders(String userId, LocalDateTime currentDate) {
        GenericSpecification<Order> userSpec = new GenericSpecification<>("customerMail", "eq", userId);
        GenericSpecification<Order> dateSpec = new GenericSpecification<>("tourTime", "gt", currentDate);
        return mapListEntityToDTO(orderRepository.findAll(Specification.where(userSpec).and(dateSpec)));
    }

    public void deleteOrder(String customerMail, Long tourId, String timeString) {
        System.out.println(customerMail);
        System.out.println(tourId);
        System.out.println(LocalDateTime.parse(timeString));
        orderRepository.deleteOrdersByCustomerMailAndTourIdAndTourTime(customerMail, tourId,
                LocalDateTime.parse(timeString));
    }

    public void addOrder(OrderDTO orderDTO) {
        orderRepository.save(mapDTOtoEntity(orderDTO));
    }
}
