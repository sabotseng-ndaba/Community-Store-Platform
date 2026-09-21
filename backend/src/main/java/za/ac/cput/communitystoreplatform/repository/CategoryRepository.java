package za.ac.cput.communitystoreplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.communitystoreplatform.domain.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository
        extends JpaRepository<Category, Integer> {

    Optional<Category> findByCategoryName(String categoryName);
}