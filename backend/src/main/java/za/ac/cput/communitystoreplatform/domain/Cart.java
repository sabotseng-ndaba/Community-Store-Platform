package za.ac.cput.communitystoreplatform.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Cart {
    @Id
    private int cartId;
    private int buyerId;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;

    @Transient
    private final List<CartItem> items = new ArrayList<>();

    protected Cart() {
    }

    private Cart(Builder builder) {
        this.cartId = builder.cartId;
        this.buyerId = builder.buyerId;
        this.totalAmount = builder.totalAmount;
        this.createdAt = builder.createdAt;
    }

    public int getCartId() {
        return cartId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(int productId, int quantity, double price) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        CartItem existingItem = findItemByProductId(productId);
        if (existingItem != null) {
            existingItem.updateQuantity(existingItem.getQuantity() + quantity);
        } else {
            items.add(new CartItem.Builder()
                    .setCartItemId(items.size() + 1)
                    .setCartId(this.cartId)
                    .setProductId(productId)
                    .setQuantity(quantity)
                    .setPrice(price)
                    .build());
        }
        recalculateTotal();
    }

    public void removeItem(int productId) {
        CartItem item = findItemByProductId(productId);
        if (item == null) {
            throw new IllegalArgumentException("Product not found in cart: " + productId);
        }
        items.remove(item);
        recalculateTotal();
    }

    private CartItem findItemByProductId(int productId) {
        for (CartItem item : items) {
            if (item.getProductId() == productId) {
                return item;
            }
        }
        return null;
    }

    private void recalculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : items) {
            total = total.add(BigDecimal.valueOf(item.getSubtotal()));
        }
        this.totalAmount = total;
    }

    public void clearCart() {
        items.clear();
        this.totalAmount = BigDecimal.ZERO;
    }

    public BigDecimal getTotal() {
        return totalAmount;
    }

    public Order checkout() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Cannot checkout an empty cart.");
        }
        Order order = new Order.Builder()
                .setBuyerId(this.buyerId)
                .setOrderDate(LocalDateTime.now())
                .setOrderStatus("PENDING")
                .setTotalAmount(this.totalAmount.doubleValue())
                .build();

        for (CartItem item : items) {
            order.addOrderDetail(new OrderItem.Builder()
                    .setProductId(item.getProductId())
                    .setQuantity(item.getQuantity())
                    .setPrice(item.getPrice())
                    .build());
        }
        clearCart();
        return order;
    }

    public static class Builder {
        private int cartId;
        private int buyerId;
        private BigDecimal totalAmount;
        private LocalDateTime createdAt;

        public Builder setCartId(int cartId) {
            this.cartId = cartId;
            return this;
        }

        public Builder setBuyerId(int buyerId) {
            this.buyerId = buyerId;
            return this;
        }

        public Builder setTotalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder copy(Cart cart) {
            this.cartId = cart.cartId;
            this.buyerId = cart.buyerId;
            this.totalAmount = cart.totalAmount;
            this.createdAt = cart.createdAt;
            return this;
        }

        public Cart build() {
            return new Cart(this);
        }
    }

    @Override
    public String toString() {
        return "==Cart Details==" +
                "\nCart ID: " + cartId +
                "\nBuyer ID: " + buyerId +
                "\nTotal Amount: " + totalAmount +
                "\nCreated At: " + createdAt;
    }
}
