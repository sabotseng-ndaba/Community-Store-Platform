package za.ac.cput.communitystoreplatform.factory;

import za.ac.cput.communitystoreplatform.domain.FraudAlert;
import za.ac.cput.communitystoreplatform.domain.Order;
import za.ac.cput.communitystoreplatform.domain.User;
import java.time.LocalDateTime;

public class FraudAlertFactory {
    public static FraudAlert createFraudAlert(int alertId, User user, Order order,
                                              String alertType, String description,
                                              String severity, String status,
                                              LocalDateTime createdAt){



        return new FraudAlert.Builder()
                .setAlertId(alertId)
                .setUser(user)
                .setOrder(order)
                .setAlertType(alertType)
                .setDescription(description)
                .setSeverity(severity)
                .setStatus(status)
                .setCreatedAt(createdAt)
                .build();
    }

}
