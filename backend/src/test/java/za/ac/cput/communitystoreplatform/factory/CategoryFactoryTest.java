package za.ac.cput.communitystoreplatform.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.communitystoreplatform.domain.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryFactoryTest {

    @Test
    void createCategory() {

        Category category = CategoryFactory.createCategory(
                1,
                "Electronics",
                "Electronic products"
        );

        assertNotNull(category);
        assertEquals(1, category.getCategoryId());
        assertEquals("Electronics", category.getCategoryName());
        assertEquals(
                "Electronic products",
                category.getDescription()
        );
    }
}