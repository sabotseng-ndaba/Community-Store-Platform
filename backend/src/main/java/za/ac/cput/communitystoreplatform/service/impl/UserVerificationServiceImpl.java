package za.ac.cput.communitystoreplatform.service.impl;

import org.springframework.stereotype.Service;
import za.ac.cput.communitystoreplatform.domain.UserVerification;
import za.ac.cput.communitystoreplatform.repository.UserVerificationRepository;
import za.ac.cput.communitystoreplatform.service.IUserVerificationService;

import java.util.List;

@Service
public class UserVerificationService
        implements IUserVerificationService {

    private final UserVerificationRepository repository;

    public UserVerificationService(
            UserVerificationRepository repository) {

        this.repository = repository;
    }

    @Override
    public UserVerification create(
            UserVerification userVerification) {

        return repository.save(userVerification);
    }

    @Override
    public UserVerification update(
            UserVerification userVerification) {

        return repository.save(userVerification);
    }

    @Override
    public UserVerification read(int verificationId) {

        return repository.findById(verificationId).orElse(null);
    }

    @Override
    public List<UserVerification> getAll() {
        return repository.findAll();
    }

    @Override
    public List<UserVerification> findByVerificationType(
            String verificationType) {

        return repository.findByVerificationType(
                verificationType);
    }

    @Override
    public List<UserVerification> findByVerificationStatus(
            String verificationStatus) {

        return repository.findByVerificationStatus(
                verificationStatus);
    }

    @Override
    public void delete(int verificationId) {
        repository.deleteById(verificationId);
    }
}