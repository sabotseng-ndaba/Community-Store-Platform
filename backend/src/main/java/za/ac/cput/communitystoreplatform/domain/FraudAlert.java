package za.ac.cput.communitystoreplatform.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraudAlert")
public class FraudAlert {
    @Id
    private int alertId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = true)
    private Order order;

    private String alertType;
    private String description;
    private String severity;
    private String status;
    private LocalDateTime createdAt;

    protected FraudAlert(){}

    public FraudAlert(Builder builder){
        this.alertId = builder.alertId;
        this.user = builder.user;
        this.order = builder.order;
        this.alertType = builder.alertType;
        this.description = builder.description;
        this.severity = builder.severity;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
    }

    public Integer getAlertId(){ return alertId;}
    public User getUser(){ return user;}
    public Order getOrder(){ return order;}
    public String getAlertType(){ return alertType;}
    public String getDescription(){ return description;}
    public String getSeverity(){ return severity;}
    public String getStatus(){ return status;}
    public LocalDateTime getCreatedAt(){ return createdAt;}

    @Override
    public String toString() {
        return "FraudAlert{" +
                "alertId=" + alertId +
                ", user=" + user +
                ", order=" + order +
                ", alertType='" + alertType + '\'' +
                ", description='" + description + '\'' +
                ", severity='" + severity + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public static class Builder{
        private int alertId;
        private User user;
        private Order order;
        private String alertType;
        private String description;
        private String severity;
        private String status;
        private LocalDateTime createdAt;

        public Builder copy(FraudAlert alert){
            this.alertId = alert.alertId;
            this.user = alert.user;
            this.order = alert.order;
            this.alertType = alert.alertType;
            this.description = alert.description;
            this.severity = alert.severity;
            this.status = alert.status;
            this.createdAt = alert.createdAt;
            return this;
        }

        public Builder setAlertId(int alertId) {
            this.alertId = alertId;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder setAlertType(String alertType) {
            this.alertType = alertType;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setSeverity(String severity) {
            this.severity = severity;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }


        public FraudAlert build(){
            return new FraudAlert(this);
        }
    }
}
