package za.ac.cput.communitystoreplatform.service;

import za.ac.cput.communitystoreplatform.domain.Category;
import java.util.List;

public interface ICategoryService {

    Category create(Category category);

    Category update(Category category);

    Category read(int categoryId);

    List<Category> getAll();

    Category findByCategoryName(String categoryName);

    void delete(int categoryId);
}