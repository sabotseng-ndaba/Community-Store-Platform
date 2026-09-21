package za.ac.cput.communitystoreplatform.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.communitystoreplatform.domain.CartItem;
import za.ac.cput.communitystoreplatform.factory.CartItemFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class CartItemControllerTest {

    private static final int CART_ITEM_ID = 7212;

    private static CartItem cartItem;

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    private String baseUrl() {
        return "http://localhost:" + port + "/CommunityStore/cartItem";
    }

    @BeforeAll
    public static void setUp() {
        cartItem = CartItemFactory.createCartItem(CART_ITEM_ID, 7112, 88, 3, 25.00);
    }

    @Test
    void a_create() {
        String url = baseUrl() + "/create";
        CartItem created = restTemplate.postForObject(url, cartItem, CartItem.class);
        assertNotNull(created);
        assertEquals(CART_ITEM_ID, created.getCartItemId());
        cartItem = created;
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        String url = baseUrl() + "/read/" + CART_ITEM_ID;
        ResponseEntity<CartItem> response = restTemplate.getForEntity(url, CartItem.class);
        assertNotNull(response.getBody());
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }

    @Test
    void c_update() {
        CartItem updated = new CartItem.Builder().copy(cartItem)
                .setQuantity(4)
                .build();
        String url = baseUrl() + "/update";
        restTemplate.put(url, updated);

        ResponseEntity<CartItem> response = this.restTemplate
                .getForEntity(baseUrl() + "/read/" + CART_ITEM_ID, CartItem.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(100.00, response.getBody().getSubtotal(), 0.0001);
        System.out.println("Updated: " + response.getBody());
    }

    @Test
    void d_getAll() {
        String url = baseUrl() + "/getAll";
        ResponseEntity<CartItem[]> response = restTemplate.getForEntity(url, CartItem[].class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length >= 1);
        System.out.println("Get All:");
        for (CartItem ci : response.getBody()) {
            System.out.println(ci);
        }
    }

    @Test
    void e_getByCart() {
        String url = baseUrl() + "/getByCart/7112";
        ResponseEntity<CartItem[]> response = restTemplate.getForEntity(url, CartItem[].class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length >= 1);
    }

    @Test
    @Disabled
    void f_delete() {
        String url = baseUrl() + "/delete/" + CART_ITEM_ID;
        restTemplate.delete(url);

        ResponseEntity<CartItem> response = this.restTemplate
                .getForEntity(baseUrl() + "/read/" + CART_ITEM_ID, CartItem.class);
        assertNull(response.getBody());
        System.out.println("Cart item deleted: true");
    }
}
