package za.ac.cput.communitystoreplatform.factory;

import za.ac.cput.communitystoreplatform.domain.CartItem;
import za.ac.cput.communitystoreplatform.util.Helper;

public class CartItemFactory {

    public static CartItem createCartItem(int cartItemId, int cartId, int productId,
                                          int quantity, double price) {

        if (Helper.isValidInt(cartItemId)
                && Helper.isValidInt(cartId)
                && Helper.isValidInt(productId)
                && Helper.isValidInt(quantity)
                && Helper.isPositive(price)) {
            return new CartItem.Builder()
                    .setCartItemId(cartItemId)
                    .setCartId(cartId)
                    .setProductId(productId)
                    .setQuantity(quantity)
                    .setPrice(price)
                    .build();
        }
        return null;
    }
}
