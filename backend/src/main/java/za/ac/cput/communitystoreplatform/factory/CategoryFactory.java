package za.ac.cput.communitystoreplatform.factory;

import za.ac.cput.communitystoreplatform.domain.Category;

public class CategoryFactory {

    public static Category createCategory(
            int categoryId,
            String categoryName,
            String description) {

        return new Category.Builder()
                .setCategoryId(categoryId)
                .setCategoryName(categoryName)
                .setDescription(description)
                .build();
    }
}