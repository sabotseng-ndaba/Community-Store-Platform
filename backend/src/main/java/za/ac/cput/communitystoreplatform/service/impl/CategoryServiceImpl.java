package za.ac.cput.communitystoreplatform.service.impl;

import org.springframework.stereotype.Service;
import za.ac.cput.communitystoreplatform.domain.Category;
import za.ac.cput.communitystoreplatform.repository.CategoryRepository;
import za.ac.cput.communitystoreplatform.service.ICategoryService;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category create(Category category) {
        return repository.save(category);
    }

    @Override
    public Category update(Category category) {
        return repository.save(category);
    }

    @Override
    public Category read(int categoryId) {
        return repository.findById(categoryId).orElse(null);
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public Category findByCategoryName(String categoryName) {
        return repository.findByCategoryName(categoryName).orElse(null);
    }

    @Override
    public void delete(int categoryId) {
        repository.deleteById(categoryId);
    }
}