package server.core.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import server.core.dto.OrderDTO;
import server.core.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Order Controller", description = "Контроллер заказов")
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "Получить список всех заказов пользователя по его id")
    @GetMapping("/user_mail={userId}")
    public List<OrderDTO> getOrdersByUser(@PathVariable String userId) {
        return orderService.getOrdersByUser(userId);
    }

    @Operation(summary = "Получить все список всех заказов")
    @GetMapping("/all")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Operation(
            summary = "Получить список всех заказов пользователя на экскурсии, с датой начала не раньше time",
            description = "Время в формате YYYY-MM-DD'T'hh:mm:ss, например : 2007-12-03T10:15:30"
    )
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

    @Operation(summary = "Добавить заказ")
    @PostMapping("")
    public void addOrder(OrderDTO orderDTO) {
        orderService.addOrder(orderDTO);
    }

    @Operation(summary = "Удалить заказ")
    @DeleteMapping("/delete")
    public void deleteOrder(OrderDTO orderDTO) {
        orderService.deleteOrder(orderDTO);
    }
}
