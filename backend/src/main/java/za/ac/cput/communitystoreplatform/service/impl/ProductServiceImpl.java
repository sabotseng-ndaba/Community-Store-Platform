package za.ac.cput.communitystoreplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.communitystoreplatform.domain.Product;
import za.ac.cput.communitystoreplatform.repository.ProductRepository;
import za.ac.cput.communitystoreplatform.service.IProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository){
        this.repository = repository;
    }

    @Override
    public Product create(Product product) {
        return repository.save(product);
    }

    @Override
    public Product read(Integer productId) {
        return repository.findById(productId).orElse(null);
    }

    @Override
    public Product update(Product product) {
        return repository.save(product);
    }

    @Override
    public boolean delete(Integer productId) {
        repository.deleteById(productId);
        return true;
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }
}
