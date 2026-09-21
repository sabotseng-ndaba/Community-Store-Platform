package za.ac.cput.communitystoreplatform.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.communitystoreplatform.domain.OrderItem;
import za.ac.cput.communitystoreplatform.factory.OrderItemFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderItemServiceTest {

    @Autowired
    private IOrderItemService orderItemService;

    private static final int ORDER_ITEM_ID = 7402;

    OrderItem orderItem = OrderItemFactory.createOrderItem(ORDER_ITEM_ID, 7301, 88, 3, 25.00);

    @Test
    void a_create() {
        OrderItem created = orderItemService.create(orderItem);
        assertNotNull(created);
        assertEquals(ORDER_ITEM_ID, created.getOrderItemId());
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        OrderItem read = orderItemService.read(ORDER_ITEM_ID);
        assertNotNull(read);
        assertEquals(75.00, read.getSubtotal(), 0.0001);
        System.out.println("Read: " + read);
    }

    @Test
    void c_update() {
        OrderItem updated = new OrderItem.Builder().copy(orderItem)
                .setQuantity(4)
                .build();
        OrderItem result = orderItemService.update(updated);
        assertNotNull(result);
        assertEquals(100.00, result.getSubtotal(), 0.0001);
        System.out.println("Updated: " + result);
    }

    @Test
    void d_getAll() {
        assertFalse(orderItemService.getAll().isEmpty());
        System.out.println("Get All: " + orderItemService.getAll());
    }

    @Test
    void e_getItemsByOrder() {
        assertTrue(orderItemService.getItemsByOrder(7301).size() >= 1);
    }
}
