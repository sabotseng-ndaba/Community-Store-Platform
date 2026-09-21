package za.ac.cput.communitystoreplatform.factory;

import za.ac.cput.communitystoreplatform.domain.Notification;
import za.ac.cput.communitystoreplatform.util.Helper;

import java.time.LocalDateTime;

public class NotificationFactory {

    public static Notification createNotification(int notificationId, int userId, String message,
                                                  LocalDateTime dateSent, String status) {

        if (Helper.isValidInt(notificationId)
                && Helper.isValidInt(userId)
                && !Helper.isNullOrEmpty(message)
                && !Helper.isNull(dateSent)
                && !Helper.isNullOrEmpty(status)) {
            return new Notification.Builder()
                    .setNotificationId(notificationId)
                    .setUserId(userId)
                    .setMessage(message)
                    .setDateSent(dateSent)
                    .setStatus(status)
                    .build();
        }
        return null;
    }
}
