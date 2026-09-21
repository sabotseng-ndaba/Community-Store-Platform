package za.ac.cput.communitystoreplatform.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.communitystoreplatform.domain.Cart;
import za.ac.cput.communitystoreplatform.domain.CartItem;
import za.ac.cput.communitystoreplatform.domain.Order;
import za.ac.cput.communitystoreplatform.factory.CartFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class CartControllerTest {

    private static final int CART_ID = 7111;

    private static Cart cart;

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    private String baseUrl() {
        return "http://localhost:" + port + "/CommunityStore/cart";
    }

    private String cartItemUrl() {
        return "http://localhost:" + port + "/CommunityStore/cartItem";
    }

    @BeforeAll
    public static void setUp() {
        cart = CartFactory.createCart(CART_ID, 511, BigDecimal.ZERO, LocalDateTime.now());
    }

    @Test
    void a_create() {
        String url = baseUrl() + "/create";
        Cart created = this.restTemplate.postForObject(url, cart, Cart.class);
        assertNotNull(created);
        assertEquals(CART_ID, created.getCartId());
        cart = created;
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        String url = baseUrl() + "/read/" + CART_ID;
        ResponseEntity<Cart> response = this.restTemplate.getForEntity(url, Cart.class);
        assertNotNull(response.getBody());
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }

    @Test
    void c_update() {
        Cart updated = new Cart.Builder().copy(cart)
                .setTotalAmount(BigDecimal.valueOf(99.98))
                .build();
        String url = baseUrl() + "/update";
        this.restTemplate.put(url, updated);

        ResponseEntity<Cart> response = this.restTemplate.getForEntity(baseUrl() + "/read/" + CART_ID, Cart.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, BigDecimal.valueOf(99.98).compareTo(response.getBody().getTotalAmount()));
        System.out.println("Updated: " + response.getBody());
    }

    @Test
    void d_getAll() {
        String url = baseUrl() + "/getAll";
        ResponseEntity<Cart[]> response = this.restTemplate.getForEntity(url, Cart[].class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length >= 1);
        System.out.println("Get All:");
        for (Cart c : response.getBody()) {
            System.out.println(c);
        }
    }

    @Test
    void e_checkout() {
        CartItem item = new CartItem.Builder()
                .setCartItemId(7211)
                .setCartId(CART_ID)
                .setProductId(99)
                .setQuantity(2)
                .setPrice(49.99)
                .build();
        CartItem createdItem =
                this.restTemplate.postForObject(cartItemUrl() + "/create", item, CartItem.class);
        assertNotNull(createdItem);

        Order order = this.restTemplate.postForObject(baseUrl() + "/checkout/" + CART_ID, null, Order.class);
        assertNotNull(order);
        assertTrue(order.getOrderId() > 0);
        assertEquals("PENDING", order.getOrderStatus());
        assertFalse(order.getOrderDetails().isEmpty());

        ResponseEntity<Cart> readAfterCheckout =
                this.restTemplate.getForEntity(baseUrl() + "/read/" + CART_ID, Cart.class);
        assertNotNull(readAfterCheckout.getBody());
        assertEquals(0, BigDecimal.ZERO.compareTo(readAfterCheckout.getBody().getTotalAmount()));

        System.out.println("Checkout order: " + order);
    }

    @Test
    void f_getByBuyer() {
        String url = baseUrl() + "/getByBuyer/511";
        ResponseEntity<Cart[]> response = this.restTemplate.getForEntity(url, Cart[].class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length >= 1);
    }

    @Test
    @Disabled
    void g_delete() {
        String url = baseUrl() + "/delete/" + CART_ID;
        this.restTemplate.delete(url);

        ResponseEntity<Cart> response = this.restTemplate.getForEntity(baseUrl() + "/read/" + CART_ID, Cart.class);
        assertNull(response.getBody());
        System.out.println("Cart deleted: true");
    }
}
