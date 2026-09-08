package za.ac.cput.communitystoreplatform.factory;

import za.ac.cput.communitystoreplatform.domain.Category;
import za.ac.cput.communitystoreplatform.util.Helper;

public class CategoryFactory {
    public Category createCategory(int categoryId, String categoryName, String description){

        if(Helper.isValidInt(categoryId)
        && Helper.isNullOrEmpty(categoryName)
        && Helper.isNullOrEmpty(description)){
            return null;
        }

        return new Category.Builder()
                .setCategoryId(categoryId)
                .setCategoryName(categoryName)
                .setDescription(description)
                .build();
    }
}
