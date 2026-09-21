package za.ac.cput.communitystoreplatform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.communitystoreplatform.domain.Category;
import za.ac.cput.communitystoreplatform.service.ICategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final ICategoryService service;

    public CategoryController(ICategoryService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Category> create(
            @RequestBody Category category) {

        return new ResponseEntity<>(
                service.create(category),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Category> update(
            @RequestBody Category category) {

        return ResponseEntity.ok(service.update(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> read(
            @PathVariable int id) {

        Category category = service.read(id);

        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(category);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Category> findByName(
            @PathVariable String name) {

        Category category = service.findByCategoryName(name);

        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable int id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}