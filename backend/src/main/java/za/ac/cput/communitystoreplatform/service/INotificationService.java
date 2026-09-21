package za.ac.cput.communitystoreplatform.service;

import za.ac.cput.communitystoreplatform.domain.Notification;

import java.util.List;

public interface INotificationService extends IService<Notification, Integer> {

    List<Notification> getAll();

    List<Notification> getNotificationsByUser(int userId);
}
