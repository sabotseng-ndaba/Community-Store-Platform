package za.ac.cput.communitystoreplatform.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.communitystoreplatform.domain.Notification;
import za.ac.cput.communitystoreplatform.factory.NotificationFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class NotificationServiceTest {

    @Autowired
    private INotificationService notificationService;

    private static final int NOTIFICATION_ID = 7501;

    Notification notification = NotificationFactory.createNotification(
            NOTIFICATION_ID, 501, "Your order has been shipped", LocalDateTime.now(), "PENDING"
    );

    @Test
    void a_create() {
        Notification created = notificationService.create(notification);
        assertNotNull(created);
        assertEquals("SENT", created.getStatus());
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        Notification read = notificationService.read(NOTIFICATION_ID);
        assertNotNull(read);
        assertEquals("Your order has been shipped", read.getMessage());
        System.out.println("Read: " + read);
    }

    @Test
    void c_update() {
        Notification updated = new Notification.Builder().copy(notification)
                .setStatus("READ")
                .build();
        Notification result = notificationService.update(updated);
        assertNotNull(result);
        assertEquals("READ", result.getStatus());
        assertTrue(result.isRead());
        System.out.println("Updated: " + result);
    }

    @Test
    void d_getAll() {
        assertFalse(notificationService.getAll().isEmpty());
        System.out.println("Get All: " + notificationService.getAll());
    }

    @Test
    void e_getNotificationsByUser() {
        assertTrue(notificationService.getNotificationsByUser(501).size() >= 1);
    }
}
