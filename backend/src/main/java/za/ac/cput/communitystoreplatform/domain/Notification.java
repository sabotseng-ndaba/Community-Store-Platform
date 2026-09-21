package za.ac.cput.communitystoreplatform.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Notification {
    @Id
    private int notificationId;
    private int userId;
    private String message;
    private LocalDateTime dateSent;
    private String status;

    protected Notification() {
    }

    private Notification(Builder builder) {
        this.notificationId = builder.notificationId;
        this.userId = builder.userId;
        this.message = builder.message;
        this.dateSent = builder.dateSent;
        this.status = builder.status;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public int getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateSent() {
        return dateSent;
    }

    public String getStatus() {
        return status;
    }

    public void sendNotification() {
        this.status = "SENT";
        this.dateSent = LocalDateTime.now();
    }

    public void markAsRead() {
        this.status = "READ";
    }

    public boolean isRead() {
        return "READ".equals(this.status);
    }

    public static class Builder {
        private int notificationId;
        private int userId;
        private String message;
        private LocalDateTime dateSent;
        private String status;

        public Builder setNotificationId(int notificationId) {
            this.notificationId = notificationId;
            return this;
        }

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setDateSent(LocalDateTime dateSent) {
            this.dateSent = dateSent;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder copy(Notification notification) {
            this.notificationId = notification.notificationId;
            this.userId = notification.userId;
            this.message = notification.message;
            this.dateSent = notification.dateSent;
            this.status = notification.status;
            return this;
        }

        public Notification build() {
            return new Notification(this);
        }
    }

    @Override
    public String toString() {
        return "==Notification Details==" +
                "\nNotification ID: " + notificationId +
                "\nUser ID: " + userId +
                "\nMessage: " + message +
                "\nDate Sent: " + dateSent +
                "\nStatus: " + status;
    }
}
