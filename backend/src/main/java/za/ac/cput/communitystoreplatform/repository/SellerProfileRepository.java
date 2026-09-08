package za.ac.cput.communitystoreplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.communitystoreplatform.domain.SellerProfile;
import java.util.*;

public interface SellerProfileRepository extends JpaRepository<SellerProfile, Integer> {
    List<SellerProfile> getAll();
}
