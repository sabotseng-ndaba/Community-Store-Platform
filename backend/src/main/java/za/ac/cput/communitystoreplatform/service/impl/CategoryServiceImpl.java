package za.ac.cput.communitystoreplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.communitystoreplatform.domain.Category;
import za.ac.cput.communitystoreplatform.repository.CategoryRepository;
import za.ac.cput.communitystoreplatform.service.ICategoryService;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {
    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository){
        this.repository = repository;
    }

    @Override
    public Category create(Category category) {
        return repository.save(category);
    }

    @Override
    public Category read(Integer categoryId) {
        return repository.findById(categoryId).orElse(null);
    }

    @Override
    public Category update(Category category) {
        return repository.save(category);
    }

    @Override
    public boolean delete(Integer categoryId) {
            repository.deleteById(categoryId);
            return true;
    }

    @Override
    public List<Category> getAll() {
        return repository.getAll();
    }

}
