package za.ac.cput.communitystoreplatform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.communitystoreplatform.domain.User;
import za.ac.cput.communitystoreplatform.service.IUserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User createdUser = userService.create(user);
        return ResponseEntity.ok(createdUser);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    // READ BY ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> getById(@PathVariable String userId) {

        User user = userService.read(userId);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    // FIND BY FIRST NAME
    @GetMapping("/firstName/{firstName}")
    public ResponseEntity<List<User>> findByFirstName(
            @PathVariable String firstName) {

        return ResponseEntity.ok(
                userService.findByFirstName(firstName)
        );
    }

    // FIND BY LAST NAME
    @GetMapping("/lastName/{lastName}")
    public ResponseEntity<List<User>> findByLastName(
            @PathVariable String lastName) {

        return ResponseEntity.ok(
                userService.findByLastName(lastName)
        );
    }

    // UPDATE
    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        User updatedUser = userService.update(user);
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable String userId) {

        userService.delete(userId);

        return ResponseEntity.ok().build();
    }
}
