package za.ac.cput.communitystoreplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.communitystoreplatform.domain.Notification;
import za.ac.cput.communitystoreplatform.service.INotificationService;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final INotificationService service;

    @Autowired
    public NotificationController(INotificationService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Notification create(@RequestBody Notification notification) {
        return service.create(notification);
    }

    @GetMapping("/read/{notificationId}")
    public Notification read(@PathVariable int notificationId) {
        return service.read(notificationId);
    }

    @PutMapping("/update")
    public Notification update(@RequestBody Notification notification) {
        return service.update(notification);
    }

    @DeleteMapping("/delete/{notificationId}")
    public boolean delete(@PathVariable int notificationId) {
        return service.delete(notificationId);
    }

    @GetMapping("/getAll")
    public List<Notification> getAll() {
        return service.getAll();
    }

    @GetMapping("/getByUser/{userId}")
    public List<Notification> getByUser(@PathVariable int userId) {
        return service.getNotificationsByUser(userId);
    }
}
