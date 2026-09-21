package za.ac.cput.communitystoreplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.communitystoreplatform.domain.Order;
import za.ac.cput.communitystoreplatform.service.IOrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final IOrderService service;

    @Autowired
    public OrderController(IOrderService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Order create(@RequestBody Order order) {
        return service.create(order);
    }

    @GetMapping("/read/{orderId}")
    public Order read(@PathVariable int orderId) {
        return service.read(orderId);
    }

    @PutMapping("/update")
    public Order update(@RequestBody Order order) {
        return service.update(order);
    }

    @DeleteMapping("/delete/{orderId}")
    public boolean delete(@PathVariable int orderId) {
        return service.delete(orderId);
    }

    @GetMapping("/getAll")
    public List<Order> getAll() {
        return service.getAll();
    }

    @GetMapping("/getByBuyer/{buyerId}")
    public List<Order> getByBuyer(@PathVariable int buyerId) {
        return service.getOrdersByBuyer(buyerId);
    }
}
