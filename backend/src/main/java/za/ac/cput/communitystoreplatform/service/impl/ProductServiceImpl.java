package za.ac.cput.communitystoreplatform.service.impl;

import org.springframework.stereotype.Service;
import za.ac.cput.communitystoreplatform.domain.Product;
import za.ac.cput.communitystoreplatform.repository.ProductRepository;
import za.ac.cput.communitystoreplatform.service.IProductService;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product create(Product product) {
        return repository.save(product);
    }

    @Override
    public Product update(Product product) {
        return repository.save(product);
    }

    @Override
    public Product read(int productId) {
        return repository.findById(productId).orElse(null);
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Product> findByProductName(String productName) {
        return repository.findByProductNameContainingIgnoreCase(productName);
    }

    @Override
    public List<Product> findByStatus(String status) {
        return repository.findByStatus(status);
    }

    @Override
    public void delete(int productId) {
        repository.deleteById(productId);
    }
}