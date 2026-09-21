package za.ac.cput.communitystoreplatform.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    private int paymentId;
    private int orderId;
    private String paymentMethod;
    private String transactionReference;
    private double amount;
    private String paymentStatus;
    private LocalDateTime paymentDate;

    protected Payment() {
    }

    private Payment(Builder builder) {
        this.paymentId = builder.paymentId;
        this.orderId = builder.orderId;
        this.paymentMethod = builder.paymentMethod;
        this.transactionReference = builder.transactionReference;
        this.amount = builder.amount;
        this.paymentStatus = builder.paymentStatus;
        this.paymentDate = builder.paymentDate;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public boolean markAsPaid() {
        if ("PENDING".equals(this.paymentStatus)) {
            this.paymentStatus = "COMPLETED";
            return true;
        }
        return false;
    }

    public boolean markAsFailed() {
        if ("PENDING".equals(this.paymentStatus)) {
            this.paymentStatus = "FAILED";
            return true;
        }
        return false;
    }

    public boolean refund() {
        if ("COMPLETED".equals(this.paymentStatus)) {
            this.paymentStatus = "REFUNDED";
            return true;
        }
        return false;
    }

    public void updateStatus(String status) {
        if (status != null && !status.isEmpty()) {
            this.paymentStatus = status;
        }
    }

    public boolean isPaid() {
        return "COMPLETED".equals(this.paymentStatus);
    }

    public static class Builder {
        private int paymentId;
        private int orderId;
        private String paymentMethod;
        private String transactionReference;
        private double amount;
        private String paymentStatus;
        private LocalDateTime paymentDate;

        public Builder setPaymentId(int paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder setOrderId(int orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder setTransactionReference(String transactionReference) {
            this.transactionReference = transactionReference;
            return this;
        }

        public Builder setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public Builder setPaymentDate(LocalDateTime paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public Builder copy(Payment payment) {
            this.paymentId = payment.paymentId;
            this.orderId = payment.orderId;
            this.paymentMethod = payment.paymentMethod;
            this.transactionReference = payment.transactionReference;
            this.amount = payment.amount;
            this.paymentStatus = payment.paymentStatus;
            this.paymentDate = payment.paymentDate;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }

    @Override
    public String toString() {
        return "==Payment Details==" +
                "\nPayment ID: " + paymentId +
                "\nOrder ID: " + orderId +
                "\nPayment Method: " + paymentMethod +
                "\nTransaction Reference: " + transactionReference +
                "\nAmount: " + amount +
                "\nPayment Status: " + paymentStatus +
                "\nPayment Date: " + paymentDate;
    }
}
