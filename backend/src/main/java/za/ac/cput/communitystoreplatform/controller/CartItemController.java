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
import za.ac.cput.communitystoreplatform.domain.CartItem;
import za.ac.cput.communitystoreplatform.service.ICartItemService;

import java.util.List;

@RestController
@RequestMapping("/cartItem")
public class CartItemController {

    private final ICartItemService service;

    @Autowired
    public CartItemController(ICartItemService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public CartItem create(@RequestBody CartItem cartItem) {
        return service.create(cartItem);
    }

    @GetMapping("/read/{cartItemId}")
    public CartItem read(@PathVariable int cartItemId) {
        return service.read(cartItemId);
    }

    @PutMapping("/update")
    public CartItem update(@RequestBody CartItem cartItem) {
        return service.update(cartItem);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public boolean delete(@PathVariable int cartItemId) {
        return service.delete(cartItemId);
    }

    @GetMapping("/getAll")
    public List<CartItem> getAll() {
        return service.getAll();
    }

    @GetMapping("/getByCart/{cartId}")
    public List<CartItem> getByCart(@PathVariable int cartId) {
        return service.getItemsByCart(cartId);
    }
}
