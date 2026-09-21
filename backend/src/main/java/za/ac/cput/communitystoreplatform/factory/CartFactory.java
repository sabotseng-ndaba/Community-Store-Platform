package za.ac.cput.communitystoreplatform.factory;

import za.ac.cput.communitystoreplatform.domain.Cart;
import za.ac.cput.communitystoreplatform.util.Helper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CartFactory {

    public static Cart createCart(int cartId, int buyerId, BigDecimal totalAmount, LocalDateTime createdAt) {

        if (Helper.isValidInt(cartId)
                && Helper.isValidInt(buyerId)
                && !Helper.isNull(totalAmount)
                && !Helper.isNull(createdAt)) {
            return new Cart.Builder()
                    .setCartId(cartId)
                    .setBuyerId(buyerId)
                    .setTotalAmount(totalAmount)
                    .setCreatedAt(createdAt)
                    .build();
        }
        return null;
    }
}
