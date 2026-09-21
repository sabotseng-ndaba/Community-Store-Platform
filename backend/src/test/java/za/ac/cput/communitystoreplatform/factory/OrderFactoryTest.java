package za.ac.cput.communitystoreplatform.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.communitystoreplatform.domain.Order;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderFactoryTest {

    @Test
    void createOrder() {

        Order order = OrderFactory.createOrder(
                7301,
                501,
                LocalDateTime.now(),
                "PENDING",
                1500.00,
                "12 Main Road, Cape Town"
        );

        assertNotNull(order);
        System.out.println(order);
    }
}
