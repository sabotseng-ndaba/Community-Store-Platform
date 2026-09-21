package za.ac.cput.communitystoreplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.communitystoreplatform.domain.SellerProfile;

import java.util.Optional;

@Repository
public interface SellerProfileRepository
        extends JpaRepository<SellerProfile, Integer> {

    Optional<SellerProfile> findByStoreName(String storeName);

    Optional<SellerProfile> findByBusinessRegistrationNo(
            String businessRegistrationNo);

    java.util.List<SellerProfile> findByVerificationStatus(
            String verificationStatus);
}