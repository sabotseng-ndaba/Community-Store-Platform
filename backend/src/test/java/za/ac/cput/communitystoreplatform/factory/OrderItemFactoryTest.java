package za.ac.cput.communitystoreplatform.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.communitystoreplatform.domain.OrderItem;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemFactoryTest {

    @Test
    void createOrderItem() {

        OrderItem orderItem = OrderItemFactory.createOrderItem(
                7401,
                7301,
                88,
                3,
                25.00
        );

        assertNotNull(orderItem);
        System.out.println(orderItem);
    }
}
