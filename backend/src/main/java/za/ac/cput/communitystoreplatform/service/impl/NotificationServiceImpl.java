package za.ac.cput.communitystoreplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.communitystoreplatform.domain.Notification;
import za.ac.cput.communitystoreplatform.repository.NotificationRepository;
import za.ac.cput.communitystoreplatform.service.INotificationService;

import java.util.List;

@Service
public class NotificationServiceImpl implements INotificationService {

    private final NotificationRepository repository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Notification create(Notification notification) {
        notification.sendNotification();
        return this.repository.save(notification);
    }

    @Override
    public Notification read(Integer notificationId) {
        return this.repository.findById(notificationId).orElse(null);
    }

    @Override
    public Notification update(Notification notification) {
        if (this.repository.existsById(notification.getNotificationId())) {
            return this.repository.save(notification);
        }
        return null;
    }

    @Override
    public boolean delete(Integer notificationId) {
        this.repository.deleteById(notificationId);
        return true;
    }

    @Override
    public List<Notification> getAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Notification> getNotificationsByUser(int userId) {
        return this.repository.findByUserId(userId);
    }
}
