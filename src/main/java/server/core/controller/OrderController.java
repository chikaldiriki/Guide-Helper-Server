package server.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import server.core.dto.OrderDTO;
import server.core.service.OrderService;

import java.time.LocalDateTime;
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

    @GetMapping("/coming/user_mail={userId}/time")
    public List<OrderDTO> getComingOrders(@PathVariable String userId,
                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                  LocalDateTime date) {
        return orderService.getComingOrders(userId, date);
    }

    @GetMapping("/test/foo")
    public String testQuery(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                        LocalDateTime date) {
        return "KEK: " + date.toString();
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
