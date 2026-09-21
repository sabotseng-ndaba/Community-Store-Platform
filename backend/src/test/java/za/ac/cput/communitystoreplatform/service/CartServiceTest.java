package za.ac.cput.communitystoreplatform.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.communitystoreplatform.domain.Cart;
import za.ac.cput.communitystoreplatform.domain.CartItem;
import za.ac.cput.communitystoreplatform.domain.Order;
import za.ac.cput.communitystoreplatform.factory.CartFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CartServiceTest {

    @Autowired
    private ICartService cartService;

    @Autowired
    private ICartItemService cartItemService;

    private static final int CART_ID = 7101;

    Cart cart = CartFactory.createCart(CART_ID, 501, BigDecimal.ZERO, LocalDateTime.now());

    @Test
    void a_create() {
        Cart created = cartService.create(cart);
        assertNotNull(created);
        assertEquals(CART_ID, created.getCartId());
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        Cart read = cartService.read(CART_ID);
        assertNotNull(read);
        assertEquals(501, read.getBuyerId());
        System.out.println("Read: " + read);
    }

    @Test
    void c_update() {
        Cart updated = new Cart.Builder().copy(cart)
                .setTotalAmount(BigDecimal.valueOf(99.98))
                .build();
        Cart result = cartService.update(updated);
        assertNotNull(result);
        assertEquals(0, BigDecimal.valueOf(99.98).compareTo(result.getTotalAmount()));
        System.out.println("Updated: " + result);
    }

    @Test
    void d_getAll() {
        assertFalse(cartService.getAll().isEmpty());
        System.out.println("Get All: " + cartService.getAll());
    }

    @Test
    void e_checkout() {
        CartItem item = new CartItem.Builder()
                .setCartItemId(7201)
                .setCartId(CART_ID)
                .setProductId(88)
                .setQuantity(2)
                .setPrice(49.99)
                .build();
        cartItemService.create(item);

        Order order = cartService.checkout(CART_ID);

        assertNotNull(order);
        assertTrue(order.getOrderId() > 0);
        assertEquals("PENDING", order.getOrderStatus());
        assertEquals(1, order.getOrderDetails().size());

        Cart cleared = cartService.read(CART_ID);
        assertNotNull(cleared);
        assertEquals(0, BigDecimal.ZERO.compareTo(cleared.getTotalAmount()));
        assertTrue(cartItemService.getItemsByCart(CART_ID).isEmpty());

        System.out.println("Checkout order: " + order);
    }

    @Test
    void f_getCartsByBuyer() {
        assertFalse(cartService.getCartsByBuyer(501).isEmpty());
    }
}
