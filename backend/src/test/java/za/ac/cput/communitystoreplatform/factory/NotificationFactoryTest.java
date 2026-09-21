package za.ac.cput.communitystoreplatform.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.communitystoreplatform.domain.Notification;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class NotificationFactoryTest {

    @Test
    void createNotification() {

        Notification notification = NotificationFactory.createNotification(
                7501,
                501,
                "Your order has been shipped",
                LocalDateTime.now(),
                "SENT"
        );

        assertNotNull(notification);
        System.out.println(notification);
    }
}
