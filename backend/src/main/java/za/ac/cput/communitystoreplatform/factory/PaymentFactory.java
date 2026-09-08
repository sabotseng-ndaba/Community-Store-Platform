package za.ac.cput.communitystoreplatform.factory;

import za.ac.cput.communitystoreplatform.domain.Payment;
import za.ac.cput.communitystoreplatform.util.Helper;

import java.time.LocalDateTime;

public class PaymentFactory {

    public static Payment createPayment(int paymentId, int orderId, String paymentMethod,
                                        String transactionReference, double amount,
                                        String paymentStatus, LocalDateTime paymentDate) {

        if (Helper.isValidInt(paymentId)
                && Helper.isValidInt(orderId)
                && !Helper.isNullOrEmpty(paymentMethod)
                && !Helper.isNullOrEmpty(transactionReference)
                && Helper.isPositive(amount)
                && !Helper.isNullOrEmpty(paymentStatus)
                && !Helper.isNull(paymentDate)) {

            return new Payment.Builder()
                    .setPaymentId(paymentId)
                    .setOrderId(orderId)
                    .setPaymentMethod(paymentMethod)
                    .setTransactionReference(transactionReference)
                    .setAmount(amount)
                    .setPaymentStatus(paymentStatus)
                    .setPaymentDate(paymentDate)
                    .build();
        }
        return null;
    }
}
