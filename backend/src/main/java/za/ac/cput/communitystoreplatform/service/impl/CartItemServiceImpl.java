package za.ac.cput.communitystoreplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.communitystoreplatform.domain.CartItem;
import za.ac.cput.communitystoreplatform.repository.CartItemRepository;
import za.ac.cput.communitystoreplatform.service.ICartItemService;

import java.util.List;

@Service
public class CartItemServiceImpl implements ICartItemService {

    private final CartItemRepository repository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public CartItem create(CartItem cartItem) {
        return this.repository.save(cartItem);
    }

    @Override
    public CartItem read(Integer cartItemId) {
        return this.repository.findById(cartItemId).orElse(null);
    }

    @Override
    public CartItem update(CartItem cartItem) {
        if (this.repository.existsById(cartItem.getCartItemId())) {
            return this.repository.save(cartItem);
        }
        return null;
    }

    @Override
    public boolean delete(Integer cartItemId) {
        this.repository.deleteById(cartItemId);
        return true;
    }

    @Override
    public List<CartItem> getAll() {
        return this.repository.findAll();
    }

    @Override
    public List<CartItem> getItemsByCart(int cartId) {
        return this.repository.findByCartId(cartId);
    }
}
