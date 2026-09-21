package za.ac.cput.communitystoreplatform.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.communitystoreplatform.domain.CartItem;

import static org.junit.jupiter.api.Assertions.*;

class CartItemFactoryTest {

    @Test
    void createCartItem() {

        CartItem cartItem = CartItemFactory.createCartItem(
                7201,
                7101,
                88,
                2,
                49.99
        );

        assertNotNull(cartItem);
        System.out.println(cartItem);
    }
}
