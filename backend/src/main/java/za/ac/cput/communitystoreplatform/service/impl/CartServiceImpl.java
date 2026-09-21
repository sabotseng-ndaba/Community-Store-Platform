package za.ac.cput.communitystoreplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.communitystoreplatform.domain.Cart;
import za.ac.cput.communitystoreplatform.domain.CartItem;
import za.ac.cput.communitystoreplatform.domain.Order;
import za.ac.cput.communitystoreplatform.domain.OrderItem;
import za.ac.cput.communitystoreplatform.repository.CartItemRepository;
import za.ac.cput.communitystoreplatform.repository.CartRepository;
import za.ac.cput.communitystoreplatform.repository.OrderItemRepository;
import za.ac.cput.communitystoreplatform.repository.OrderRepository;
import za.ac.cput.communitystoreplatform.service.ICartService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    private final CartRepository repository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public CartServiceImpl(CartRepository repository,
                           CartItemRepository cartItemRepository,
                           OrderRepository orderRepository,
                           OrderItemRepository orderItemRepository) {
        this.repository = repository;
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Cart create(Cart cart) {
        return this.repository.save(cart);
    }

    @Override
    public Cart read(Integer cartId) {
        return this.repository.findById(cartId).orElse(null);
    }

    @Override
    public Cart update(Cart cart) {
        if (this.repository.existsById(cart.getCartId())) {
            return this.repository.save(cart);
        }
        return null;
    }

    @Override
    public boolean delete(Integer cartId) {
        this.repository.deleteById(cartId);
        return true;
    }

    @Override
    public List<Cart> getAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Cart> getCartsByBuyer(int buyerId) {
        return this.repository.findByBuyerId(buyerId);
    }

    @Override
    @Transactional
    public Order checkout(int cartId) {
        Cart cart = this.repository.findById(cartId).orElse(null);
        if (cart == null) {
            throw new IllegalArgumentException("Cart not found: " + cartId);
        }
        List<CartItem> items = this.cartItemRepository.findByCartId(cartId);
        if (items.isEmpty()) {
            throw new IllegalStateException("Cannot checkout an empty cart.");
        }

        double total = items.stream().mapToDouble(CartItem::getSubtotal).sum();
        Order order = this.orderRepository.save(new Order.Builder()
                .setOrderId(nextOrderId())
                .setBuyerId(cart.getBuyerId())
                .setOrderDate(LocalDateTime.now())
                .setOrderStatus("PENDING")
                .setTotalAmount(total)
                .build());

        int orderItemId = nextOrderItemId();
        for (CartItem item : items) {
            this.orderItemRepository.save(new OrderItem.Builder()
                    .setOrderItemId(orderItemId++)
                    .setOrderId(order.getOrderId())
                    .setProductId(item.getProductId())
                    .setQuantity(item.getQuantity())
                    .setPrice(item.getPrice())
                    .build());
        }

        this.cartItemRepository.deleteAll(items);

        this.repository.save(new Cart.Builder()
                .copy(cart)
                .setTotalAmount(BigDecimal.ZERO)
                .build());

        order.setOrderDetails(this.orderItemRepository.findByOrderId(order.getOrderId()));
        return order;
    }

    private int nextOrderId() {
        Order last = this.orderRepository.findTopByOrderByOrderIdDesc();
        return (last != null ? last.getOrderId() : 0) + 1;
    }

    private int nextOrderItemId() {
        OrderItem last = this.orderItemRepository.findTopByOrderByOrderItemIdDesc();
        return (last != null ? last.getOrderItemId() : 0) + 1;
    }
}
