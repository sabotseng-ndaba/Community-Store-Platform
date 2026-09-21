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
import za.ac.cput.communitystoreplatform.domain.OrderItem;
import za.ac.cput.communitystoreplatform.factory.OrderItemFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderItemControllerTest {

    private static final int ORDER_ITEM_ID = 7412;

    private static OrderItem orderItem;

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    private String baseUrl() {
        return "http://localhost:" + port + "/CommunityStore/orderItem";
    }

    @BeforeAll
    public static void setUp() {
        orderItem = OrderItemFactory.createOrderItem(ORDER_ITEM_ID, 7311, 88, 3, 25.00);
    }

    @Test
    void a_create() {
        String url = baseUrl() + "/create";
        OrderItem created = restTemplate.postForObject(url, orderItem, OrderItem.class);
        assertNotNull(created);
        assertEquals(ORDER_ITEM_ID, created.getOrderItemId());
        orderItem = created;
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        String url = baseUrl() + "/read/" + ORDER_ITEM_ID;
        ResponseEntity<OrderItem> response = restTemplate.getForEntity(url, OrderItem.class);
        assertNotNull(response.getBody());
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }

    @Test
    void c_update() {
        OrderItem updated = new OrderItem.Builder().copy(orderItem)
                .setQuantity(5)
                .build();
        String url = baseUrl() + "/update";
        restTemplate.put(url, updated);

        ResponseEntity<OrderItem> response = this.restTemplate
                .getForEntity(baseUrl() + "/read/" + ORDER_ITEM_ID, OrderItem.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(125.00, response.getBody().getSubtotal(), 0.0001);
        System.out.println("Updated: " + response.getBody());
    }

    @Test
    void d_getAll() {
        String url = baseUrl() + "/getAll";
        ResponseEntity<OrderItem[]> response = restTemplate.getForEntity(url, OrderItem[].class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length >= 1);
        System.out.println("Get All:");
        for (OrderItem oi : response.getBody()) {
            System.out.println(oi);
        }
    }

    @Test
    void e_getByOrder() {
        String url = baseUrl() + "/getByOrder/7311";
        ResponseEntity<OrderItem[]> response = restTemplate.getForEntity(url, OrderItem[].class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length >= 1);
    }

    @Test
    @Disabled
    void f_delete() {
        String url = baseUrl() + "/delete/" + ORDER_ITEM_ID;
        restTemplate.delete(url);

        ResponseEntity<OrderItem> response = this.restTemplate
                .getForEntity(baseUrl() + "/read/" + ORDER_ITEM_ID, OrderItem.class);
        assertNull(response.getBody());
        System.out.println("Order item deleted: true");
    }
}
