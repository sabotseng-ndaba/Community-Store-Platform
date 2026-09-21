package za.ac.cput.communitystoreplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.communitystoreplatform.domain.Review;
import java.util.*;

public interface ReviewRepository extends JpaRepository<Review, String> {
    List<Review> getAll();
}
