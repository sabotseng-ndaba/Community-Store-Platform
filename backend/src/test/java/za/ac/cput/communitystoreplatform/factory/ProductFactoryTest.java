package za.ac.cput.communitystoreplatform.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.communitystoreplatform.domain.Product;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProductFactoryTest {

    @Test
    void createProduct() {

        Product product = ProductFactory.createProduct(
                1,
                "Laptop",
                "Second-hand laptop",
                5000.00,
                2,
                "Good",
                "SALE",
                true,
                "AVAILABLE",
                LocalDate.now(),
                LocalDate.now()
        );

        assertNotNull(product);
        assertEquals(1, product.getProductId());
        assertEquals("Laptop", product.getProductName());
        assertEquals(5000.00, product.getPrice());
    }
}