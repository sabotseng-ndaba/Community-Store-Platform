package za.ac.cput.communitystoreplatform.service;

import za.ac.cput.communitystoreplatform.domain.UserVerification;
import java.util.List;

public interface IUserVerificationService {

    UserVerification create(
            UserVerification userVerification);

    UserVerification update(
            UserVerification userVerification);

    UserVerification read(int verificationId);

    List<UserVerification> getAll();

    List<UserVerification> findByVerificationType(
            String verificationType);

    List<UserVerification> findByVerificationStatus(
            String verificationStatus);

    void delete(int verificationId);
}