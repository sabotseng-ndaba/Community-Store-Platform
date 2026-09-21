package za.ac.cput.communitystoreplatform.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.communitystoreplatform.domain.CartItem;
import za.ac.cput.communitystoreplatform.factory.CartItemFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CartItemServiceTest {

    @Autowired
    private ICartItemService cartItemService;

    private static final int CART_ITEM_ID = 7202;

    CartItem cartItem = CartItemFactory.createCartItem(CART_ITEM_ID, 7102, 88, 3, 25.00);

    @Test
    void a_create() {
        CartItem created = cartItemService.create(cartItem);
        assertNotNull(created);
        assertEquals(CART_ITEM_ID, created.getCartItemId());
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        CartItem read = cartItemService.read(CART_ITEM_ID);
        assertNotNull(read);
        assertEquals(75.00, read.getSubtotal(), 0.0001);
        System.out.println("Read: " + read);
    }

    @Test
    void c_update() {
        CartItem updated = new CartItem.Builder().copy(cartItem)
                .setQuantity(5)
                .build();
        CartItem result = cartItemService.update(updated);
        assertNotNull(result);
        assertEquals(125.00, result.getSubtotal(), 0.0001);
        System.out.println("Updated: " + result);
    }

    @Test
    void d_getAll() {
        assertFalse(cartItemService.getAll().isEmpty());
        System.out.println("Get All: " + cartItemService.getAll());
    }

    @Test
    void e_getItemsByCart() {
        assertTrue(cartItemService.getItemsByCart(7102).size() >= 1);
    }
}
