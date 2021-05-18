package server.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.core.dto.OrderDTO;
import server.core.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/user_mail={userId}")
    public List<OrderDTO> getOrdersByUser(@PathVariable String userId) {
        return orderService.getOrdersByUser(userId);
    }

    @GetMapping("/all")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("")
    public void addOrder(OrderDTO orderDTO) {
        orderService.addOrder(orderDTO);
    }

    @DeleteMapping("/delete")
    public void deleteOrder(OrderDTO orderDTO) {
        orderService.deleteOrder(orderDTO);
    }
}
