package za.ac.cput.communitystoreplatform.controller;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import za.ac.cput.communitystoreplatform.domain.Order;
import za.ac.cput.communitystoreplatform.factory.OrderFactory;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderControllerTest {

    private static final int ORDER_ID = 7311;

    private static Order order;

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    private String baseUrl() {
        return "http://localhost:" + port + "/CommunityStore/order";
    }

    @BeforeAll
    public static void setUp() {
        order = OrderFactory.createOrder(
                ORDER_ID, 511, LocalDateTime.now(), "PENDING", 1500.00, "12 Main Road, Cape Town"
        );
    }

    @Test
    void a_create() {
        String url = baseUrl() + "/create";
        Order created = restTemplate.postForObject(url, order, Order.class);
        assertNotNull(created);
        assertEquals(ORDER_ID, created.getOrderId());
        order = created;
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        String url = baseUrl() + "/read/" + ORDER_ID;
        ResponseEntity<Order> response = restTemplate.getForEntity(url, Order.class);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getOrderDetails());
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }

    @Test
    void c_update() {
        Order updated = new Order.Builder().copy(order)
                .setOrderStatus("CONFIRMED")
                .build();
        String url = baseUrl() + "/update";
        restTemplate.put(url, updated);

        ResponseEntity<Order> response = this.restTemplate
                .getForEntity(baseUrl() + "/read/" + ORDER_ID, Order.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("CONFIRMED", response.getBody().getOrderStatus());
        System.out.println("Updated: " + response.getBody());
    }

    @Test
    void d_getAll() {
        String url = baseUrl() + "/getAll";
        ResponseEntity<Order[]> response = restTemplate.getForEntity(url, Order[].class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length >= 1);
        System.out.println("Get All:");
        for (Order o : response.getBody()) {
            System.out.println(o);
        }
    }

    @Test
    void e_getByBuyer() {
        String url = baseUrl() + "/getByBuyer/511";
        ResponseEntity<Order[]> response = restTemplate.getForEntity(url, Order[].class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length >= 1);
    }

    @Test
    @Disabled
    void f_delete() {
        String url = baseUrl() + "/delete/" + ORDER_ID;
        restTemplate.delete(url);

        ResponseEntity<Order> response = this.restTemplate
                .getForEntity(baseUrl() + "/read/" + ORDER_ID, Order.class);
        assertNull(response.getBody());
        System.out.println("Order deleted: true");
    }
}
