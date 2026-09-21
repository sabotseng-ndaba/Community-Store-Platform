package za.ac.cput.communitystoreplatform.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    private int orderId;
    private int buyerId;
    private LocalDateTime orderDate;
    private String orderStatus;
    private double totalAmount;
    private String shippingAddress;

    @Transient
    private List<OrderItem> orderItems = new ArrayList<>();

    protected Order() {
    }

    private Order(Builder builder) {
        this.orderId = builder.orderId;
        this.buyerId = builder.buyerId;
        this.orderDate = builder.orderDate;
        this.orderStatus = builder.orderStatus;
        this.totalAmount = builder.totalAmount;
        this.shippingAddress = builder.shippingAddress;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void confirmOrder() {
        this.orderStatus = "CONFIRMED";
    }

    public void cancelOrder() {
        this.orderStatus = "CANCELLED";
    }

    public void setOrderDetails(List<OrderItem> orderItems) {
        if (orderItems != null) {
            this.orderItems = orderItems;
        }
    }

    public void addOrderDetail(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }

    public List<OrderItem> getOrderDetails() {
        return this.orderItems;
    }

    public static class Builder {
        private int orderId;
        private int buyerId;
        private LocalDateTime orderDate;
        private String orderStatus;
        private double totalAmount;
        private String shippingAddress;

        public Builder setOrderId(int orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setBuyerId(int buyerId) {
            this.buyerId = buyerId;
            return this;
        }

        public Builder setOrderDate(LocalDateTime orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Builder setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder setShippingAddress(String shippingAddress) {
            this.shippingAddress = shippingAddress;
            return this;
        }

        public Builder copy(Order order) {
            this.orderId = order.orderId;
            this.buyerId = order.buyerId;
            this.orderDate = order.orderDate;
            this.orderStatus = order.orderStatus;
            this.totalAmount = order.totalAmount;
            this.shippingAddress = order.shippingAddress;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    @Override
    public String toString() {
        return "==Order Details==" +
                "\nOrder ID: " + orderId +
                "\nBuyer ID: " + buyerId +
                "\nOrder Date: " + orderDate +
                "\nOrder Status: " + orderStatus +
                "\nTotal Amount: " + totalAmount +
                "\nShipping Address: " + shippingAddress;
    }
}
