package za.ac.cput.communitystoreplatform.factory;

import za.ac.cput.communitystoreplatform.domain.Order;
import za.ac.cput.communitystoreplatform.util.Helper;

import java.time.LocalDateTime;

public class OrderFactory {

    public static Order createOrder(int orderId, int buyerId, LocalDateTime orderDate,
                                    String orderStatus, double totalAmount, String shippingAddress) {

        if (Helper.isValidInt(orderId)
                && Helper.isValidInt(buyerId)
                && !Helper.isNull(orderDate)
                && !Helper.isNullOrEmpty(orderStatus)
                && Helper.isPositive(totalAmount)
                && !Helper.isNullOrEmpty(shippingAddress)) {
            return new Order.Builder()
                    .setOrderId(orderId)
                    .setBuyerId(buyerId)
                    .setOrderDate(orderDate)
                    .setOrderStatus(orderStatus)
                    .setTotalAmount(totalAmount)
                    .setShippingAddress(shippingAddress)
                    .build();
        }
        return null;
    }
}
