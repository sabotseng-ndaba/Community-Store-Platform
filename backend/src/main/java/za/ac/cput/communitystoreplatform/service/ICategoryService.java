package za.ac.cput.communitystoreplatform.service;

import za.ac.cput.communitystoreplatform.domain.Category;

import java.util.List;

public interface ICategoryService extends IService<Category,Integer>{
    List<Category> getAll();
}
