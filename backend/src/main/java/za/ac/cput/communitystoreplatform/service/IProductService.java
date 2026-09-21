package za.ac.cput.communitystoreplatform.service;

import za.ac.cput.communitystoreplatform.domain.Product;
import java.util.List;

public interface IProductService {

    Product create(Product product);

    Product update(Product product);

    Product read(int productId);

    List<Product> getAll();

    List<Product> findByProductName(String productName);

    List<Product> findByStatus(String status);

    void delete(int productId);
}