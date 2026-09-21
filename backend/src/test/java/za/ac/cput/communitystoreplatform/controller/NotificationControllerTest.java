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
import za.ac.cput.communitystoreplatform.domain.Notification;
import za.ac.cput.communitystoreplatform.factory.NotificationFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class NotificationControllerTest {

    private static final int NOTIFICATION_ID = 7511;

    private static Notification notification;

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    private String baseUrl() {
        return "http://localhost:" + port + "/CommunityStore/notification";
    }

    @BeforeAll
    public static void setUp() {
        notification = NotificationFactory.createNotification(
                NOTIFICATION_ID, 511, "Your order has been shipped", LocalDateTime.now(), "PENDING"
        );
    }

    @Test
    void a_create() {
        String url = baseUrl() + "/create";
        Notification created = restTemplate.postForObject(url, notification, Notification.class);
        assertNotNull(created);
        assertEquals(NOTIFICATION_ID, created.getNotificationId());
        assertEquals("SENT", created.getStatus());
        notification = created;
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        String url = baseUrl() + "/read/" + NOTIFICATION_ID;
        ResponseEntity<Notification> response = restTemplate.getForEntity(url, Notification.class);
        assertNotNull(response.getBody());
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }

    @Test
    void c_update() {
        Notification updated = new Notification.Builder().copy(notification)
                .setStatus("READ")
                .build();
        String url = baseUrl() + "/update";
        restTemplate.put(url, updated);

        ResponseEntity<Notification> response = this.restTemplate
                .getForEntity(baseUrl() + "/read/" + NOTIFICATION_ID, Notification.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isRead());
        System.out.println("Updated: " + response.getBody());
    }

    @Test
    void d_getAll() {
        String url = baseUrl() + "/getAll";
        ResponseEntity<Notification[]> response = restTemplate.getForEntity(url, Notification[].class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length >= 1);
        System.out.println("Get All:");
        for (Notification n : response.getBody()) {
            System.out.println(n);
        }
    }

    @Test
    void e_getByUser() {
        String url = baseUrl() + "/getByUser/511";
        ResponseEntity<Notification[]> response = restTemplate.getForEntity(url, Notification[].class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length >= 1);
    }

    @Test
    @Disabled
    void f_delete() {
        String url = baseUrl() + "/delete/" + NOTIFICATION_ID;
        restTemplate.delete(url);

        ResponseEntity<Notification> response = this.restTemplate
                .getForEntity(baseUrl() + "/read/" + NOTIFICATION_ID, Notification.class);
        assertNull(response.getBody());
        System.out.println("Notification deleted: true");
    }
}
