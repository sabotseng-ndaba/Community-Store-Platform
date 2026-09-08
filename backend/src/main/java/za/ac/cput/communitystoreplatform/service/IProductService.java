package za.ac.cput.communitystoreplatform.service;

import za.ac.cput.communitystoreplatform.domain.Product;

import java.util.List;

public interface IProductService extends IService<Product, Integer>{
    List<Product> getAll();
}
