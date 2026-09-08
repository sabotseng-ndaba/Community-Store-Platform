package za.ac.cput.communitystoreplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.communitystoreplatform.domain.UserVerification;
import za.ac.cput.communitystoreplatform.repository.UserVerificationRepository;
import za.ac.cput.communitystoreplatform.service.IUserVerificationService;

@Service
public class UserVerificationServiceImpl implements IUserVerificationService {
    private final UserVerificationRepository repository;

    @Autowired
    public UserVerificationServiceImpl(UserVerificationRepository repository){
        this.repository = repository;
    }
    @Override
    public UserVerification create(UserVerification userVerification) {
        return repository.save(userVerification);
    }

    @Override
    public UserVerification read(Integer sellerId) {
        return repository.findById(sellerId).orElse(null);
    }

    @Override
    public UserVerification update(UserVerification userVerification) {
        return repository.save(userVerification);
    }

    @Override
    public boolean delete(Integer sellerId) {
        if(repository.existsById(sellerId)){
            repository.deleteById(sellerId);
            return true;
        }
        return false;
    }
}
