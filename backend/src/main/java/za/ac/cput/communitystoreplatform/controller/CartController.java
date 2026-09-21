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
import za.ac.cput.communitystoreplatform.domain.Cart;
import za.ac.cput.communitystoreplatform.domain.Order;
import za.ac.cput.communitystoreplatform.service.ICartService;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final ICartService service;

    @Autowired
    public CartController(ICartService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Cart create(@RequestBody Cart cart) {
        return service.create(cart);
    }

    @GetMapping("/read/{cartId}")
    public Cart read(@PathVariable int cartId) {
        return service.read(cartId);
    }

    @PutMapping("/update")
    public Cart update(@RequestBody Cart cart) {
        return service.update(cart);
    }

    @DeleteMapping("/delete/{cartId}")
    public boolean delete(@PathVariable int cartId) {
        return service.delete(cartId);
    }

    @GetMapping("/getAll")
    public List<Cart> getAll() {
        return service.getAll();
    }

    @GetMapping("/getByBuyer/{buyerId}")
    public List<Cart> getByBuyer(@PathVariable int buyerId) {
        return service.getCartsByBuyer(buyerId);
    }

    @PostMapping("/checkout/{cartId}")
    public Order checkout(@PathVariable int cartId) {
        return service.checkout(cartId);
    }
}
