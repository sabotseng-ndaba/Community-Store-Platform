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
import za.ac.cput.communitystoreplatform.domain.OrderItem;
import za.ac.cput.communitystoreplatform.service.IOrderItemService;

import java.util.List;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {

    private final IOrderItemService service;

    @Autowired
    public OrderItemController(IOrderItemService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public OrderItem create(@RequestBody OrderItem orderItem) {
        return service.create(orderItem);
    }

    @GetMapping("/read/{orderItemId}")
    public OrderItem read(@PathVariable int orderItemId) {
        return service.read(orderItemId);
    }

    @PutMapping("/update")
    public OrderItem update(@RequestBody OrderItem orderItem) {
        return service.update(orderItem);
    }

    @DeleteMapping("/delete/{orderItemId}")
    public boolean delete(@PathVariable int orderItemId) {
        return service.delete(orderItemId);
    }

    @GetMapping("/getAll")
    public List<OrderItem> getAll() {
        return service.getAll();
    }

    @GetMapping("/getByOrder/{orderId}")
    public List<OrderItem> getByOrder(@PathVariable int orderId) {
        return service.getItemsByOrder(orderId);
    }
}
