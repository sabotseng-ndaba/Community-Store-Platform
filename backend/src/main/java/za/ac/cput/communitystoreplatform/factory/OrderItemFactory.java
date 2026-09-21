package za.ac.cput.communitystoreplatform.factory;

import za.ac.cput.communitystoreplatform.domain.OrderItem;
import za.ac.cput.communitystoreplatform.util.Helper;

public class OrderItemFactory {

    public static OrderItem createOrderItem(int orderItemId, int orderId, int productId,
                                            int quantity, double price) {

        if (Helper.isValidInt(orderItemId)
                && Helper.isValidInt(orderId)
                && Helper.isValidInt(productId)
                && Helper.isValidInt(quantity)
                && Helper.isPositive(price)) {
            return new OrderItem.Builder()
                    .setOrderItemId(orderItemId)
                    .setOrderId(orderId)
                    .setProductId(productId)
                    .setQuantity(quantity)
                    .setPrice(price)
                    .build();
        }
        return null;
    }
}
