package za.ac.cput.communitystoreplatform.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.communitystoreplatform.domain.Payment;
import za.ac.cput.communitystoreplatform.factory.PaymentFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class PaymentServiceTest {

    @Autowired
    private IPaymentService paymentService;

    private static final int PAYMENT_ID = 7001;

    Payment payment = PaymentFactory.createPayment(
            PAYMENT_ID, 7301, "Card", "TXN-SVC-7001",
            1500.00, "PENDING", LocalDateTime.now()
    );

    @Test
    void a_create() {
        Payment created = paymentService.create(payment);
        assertNotNull(created);
        assertEquals(PAYMENT_ID, created.getPaymentId());
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        Payment read = paymentService.read(PAYMENT_ID);
        assertNotNull(read);
        assertEquals("Card", read.getPaymentMethod());
        System.out.println("Read: " + read);
    }

    @Test
    void c_update() {
        Payment updated = new Payment.Builder().copy(payment)
                .setPaymentStatus("COMPLETED")
                .build();
        Payment result = paymentService.update(updated);
        assertNotNull(result);
        assertEquals("COMPLETED", result.getPaymentStatus());
        System.out.println("Updated: " + result);
    }

    @Test
    void d_getAll() {
        assertFalse(paymentService.getAll().isEmpty());
        System.out.println("Get All: " + paymentService.getAll());
    }

    @Test
    void e_getPaymentsByOrder() {
        assertTrue(paymentService.getPaymentsByOrder(7301).size() >= 1);
    }

    @Test
    @Disabled
    void f_delete() {
        paymentService.delete(PAYMENT_ID);
        assertNull(paymentService.read(PAYMENT_ID));
        System.out.println("Payment deleted successfully");
    }
}
