package za.ac.cput.communitystoreplatform.factory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import za.ac.cput.communitystoreplatform.domain.Cart;

class CartFactoryTest {

    @Test
    void createCart() {

        Cart cart = CartFactory.createCart(
                7101,
                501,
                new BigDecimal("0.00"),
                LocalDateTime.now()
        );

        assertNotNull(cart);
        System.out.println(cart);
    }
}
