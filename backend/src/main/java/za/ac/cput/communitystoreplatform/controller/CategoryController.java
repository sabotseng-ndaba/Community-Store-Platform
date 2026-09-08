package za.ac.cput.communitystoreplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.communitystoreplatform.domain.Category;
import za.ac.cput.communitystoreplatform.service.impl.CategoryServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public Category create(@RequestBody Category category){
        return categoryService.create(category);
    }

    @GetMapping("/read/{categoryId")
    public Category read(@PathVariable Integer categoryId){
        return categoryService.read(categoryId);
    }

    @PutMapping("/update")
    public Category update(@RequestBody Category category){
        return categoryService.update(category);
    }

    @DeleteMapping("/delete/{categoryId}")
    public boolean delete(@PathVariable Integer categoryId){
        return categoryService.delete(categoryId);
    }

    @GetMapping("/getAll")
    public List<Category> getAll(){
        return categoryService.getAll();
    }
}
