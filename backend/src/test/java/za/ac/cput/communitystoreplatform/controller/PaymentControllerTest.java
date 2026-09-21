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
import za.ac.cput.communitystoreplatform.domain.Payment;
import za.ac.cput.communitystoreplatform.factory.PaymentFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class PaymentControllerTest {

    private static final int PAYMENT_ID = 7002;

    private static Payment payment;

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    private String baseUrl() {
        return "http://localhost:" + port + "/CommunityStore/payment";
    }

    @BeforeAll
    public static void setUp() {
        payment = PaymentFactory.createPayment(
                PAYMENT_ID, 7311, "Card", "TXN-CTRL-7002",
                1500.00, "PENDING", LocalDateTime.now()
        );
    }

    @Test
    void a_create() {
        String url = baseUrl() + "/create";
        Payment created = this.restTemplate.postForObject(url, payment, Payment.class);
        assertNotNull(created);
        assertEquals(PAYMENT_ID, created.getPaymentId());
        payment = created;
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        String url = baseUrl() + "/read/" + PAYMENT_ID;
        ResponseEntity<Payment> response = this.restTemplate.getForEntity(url, Payment.class);
        assertNotNull(response.getBody());
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }

    @Test
    void c_update() {
        Payment updated = new Payment.Builder().copy(payment)
                .setPaymentStatus("COMPLETED")
                .build();
        String url = baseUrl() + "/update";
        this.restTemplate.put(url, updated);

        ResponseEntity<Payment> response = this.restTemplate.getForEntity(baseUrl() + "/read/" + PAYMENT_ID, Payment.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("COMPLETED", response.getBody().getPaymentStatus());
        System.out.println("Updated: " + response.getBody());
    }

    @Test
    void d_getAll() {
        String url = baseUrl() + "/getAll";
        ResponseEntity<Payment[]> response = this.restTemplate.getForEntity(url, Payment[].class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length >= 1);
        System.out.println("Get All:");
        for (Payment p : response.getBody()) {
            System.out.println(p);
        }
    }

    @Test
    void e_getByOrder() {
        String url = baseUrl() + "/getByOrder/7311";
        ResponseEntity<Payment[]> response = this.restTemplate.getForEntity(url, Payment[].class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length >= 1);
    }

    @Test
    @Disabled
    void f_delete() {
        String url = baseUrl() + "/delete/" + PAYMENT_ID;
        this.restTemplate.delete(url);

        ResponseEntity<Payment> response = this.restTemplate.getForEntity(baseUrl() + "/read/" + PAYMENT_ID, Payment.class);
        assertNull(response.getBody());
        System.out.println("Payment deleted: true");
    }
}
