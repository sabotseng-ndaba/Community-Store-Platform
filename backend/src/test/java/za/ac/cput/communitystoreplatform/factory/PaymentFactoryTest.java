package za.ac.cput.communitystoreplatform.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.communitystoreplatform.domain.Payment;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PaymentFactoryTest {

    @Test
    void createPayment() {

        Payment payment = PaymentFactory.createPayment(
                7001,
                7301,
                "Card",
                "TXN-7001",
                1500.00,
                "Completed",
                LocalDateTime.now()
        );

        assertNotNull(payment);
        System.out.println(payment);
    }
}
