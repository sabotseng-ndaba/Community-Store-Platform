package za.ac.cput.communitystoreplatform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.communitystoreplatform.domain.SellerProfile;
import za.ac.cput.communitystoreplatform.service.ISellerProfileService;

import java.util.List;

@RestController
@RequestMapping("/seller-profiles")
public class SellerProfileController {

    private final ISellerProfileService service;

    public SellerProfileController(
            ISellerProfileService service) {

        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<SellerProfile> create(
            @RequestBody SellerProfile sellerProfile) {

        return new ResponseEntity<>(
                service.create(sellerProfile),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update")
    public ResponseEntity<SellerProfile> update(
            @RequestBody SellerProfile sellerProfile) {

        return ResponseEntity.ok(
                service.update(sellerProfile));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerProfile> read(
            @PathVariable int id) {

        SellerProfile sellerProfile = service.read(id);

        if (sellerProfile == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(sellerProfile);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SellerProfile>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/store/{storeName}")
    public ResponseEntity<SellerProfile> findByStoreName(
            @PathVariable String storeName) {

        SellerProfile sellerProfile =
                service.findByStoreName(storeName);

        if (sellerProfile == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(sellerProfile);
    }

    @GetMapping("/verification/{status}")
    public ResponseEntity<List<SellerProfile>>
    findByVerificationStatus(@PathVariable String status) {

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