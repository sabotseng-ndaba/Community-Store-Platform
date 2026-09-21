package za.ac.cput.communitystoreplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.communitystoreplatform.domain.UserVerification;

import java.util.List;

@Repository
public interface UserVerificationRepository
        extends JpaRepository<UserVerification, Integer> {

    List<UserVerification> findByVerificationType(
            String verificationType);

    List<UserVerification> findByVerificationStatus(
            String verificationStatus);
}