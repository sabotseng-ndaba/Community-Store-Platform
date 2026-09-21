package za.ac.cput.communitystoreplatform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.communitystoreplatform.domain.UserVerification;
import za.ac.cput.communitystoreplatform.service.IUserVerificationService;

import java.util.List;

@RestController
@RequestMapping("/user-verifications")
public class UserVerificationController {

    private final IUserVerificationService service;

    public UserVerificationController(
            IUserVerificationService service) {

        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<UserVerification> create(
            @RequestBody UserVerification verification) {

        return new ResponseEntity<>(
                service.create(verification),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update")
    public ResponseEntity<UserVerification> update(
            @RequestBody UserVerification verification) {

        return ResponseEntity.ok(
                service.update(verification));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserVerification> read(
            @PathVariable int id) {

        UserVerification verification =
                service.read(id);

        if (verification == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(verification);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserVerification>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<UserVerification>>
    findByType(@PathVariable String type) {

        return ResponseEntity.ok(
                service.findByVerificationType(type));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<UserVerification>>
    findByStatus(@PathVariable String status) {

        return ResponseEntity.ok(
                service.findByVerificationStatus(status));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable int id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}