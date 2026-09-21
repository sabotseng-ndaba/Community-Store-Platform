package za.ac.cput.communitystoreplatform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.communitystoreplatform.domain.Product;
import za.ac.cput.communitystoreplatform.service.IProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProductService service;

    public ProductController(IProductService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return new ResponseEntity<>(
                service.create(product),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Product> update(@RequestBody Product product) {
        return ResponseEntity.ok(service.update(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> read(@PathVariable int id) {

        Product product = service.read(id);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(
            @RequestParam String name) {

        return ResponseEntity.ok(
                service.findByProductName(name)
        );
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Product>> findByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                service.findByStatus(status)
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}