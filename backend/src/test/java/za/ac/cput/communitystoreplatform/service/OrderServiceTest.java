package za.ac.cput.communitystoreplatform.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.communitystoreplatform.domain.Order;
import za.ac.cput.communitystoreplatform.factory.OrderFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderServiceTest {

    @Autowired
    private IOrderService orderService;

    private static final int ORDER_ID = 7301;

    Order order = OrderFactory.createOrder(
            ORDER_ID, 501, LocalDateTime.now(), "PENDING", 1500.00, "12 Main Road, Cape Town"
    );

    @Test
    void a_create() {
        Order created = orderService.create(order);
        assertNotNull(created);
        assertEquals(ORDER_ID, created.getOrderId());
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        Order read = orderService.read(ORDER_ID);
        assertNotNull(read);
        assertNotNull(read.getOrderDetails());
        System.out.println("Read: " + read);
    }

    @Test
    void c_update() {
        Order updated = new Order.Builder().copy(order)
                .setOrderStatus("CONFIRMED")
                .build();
        Order result = orderService.update(updated);
        assertNotNull(result);
        assertEquals("CONFIRMED", result.getOrderStatus());
        System.out.println("Updated: " + result);
    }

    @Test
    void d_getAll() {
        assertFalse(orderService.getAll().isEmpty());
        System.out.println("Get All: " + orderService.getAll());
    }

    @Test
    void e_getOrdersByBuyer() {
        assertTrue(orderService.getOrdersByBuyer(501).size() >= 1);
    }
}
