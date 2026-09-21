package za.ac.cput.communitystoreplatform.service.impl;

import org.springframework.stereotype.Service;
import za.ac.cput.communitystoreplatform.domain.User;
import za.ac.cput.communitystoreplatform.repository.UserRepository;
import za.ac.cput.communitystoreplatform.service.IUserService;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        return repository.save(user);
    }

    @Override
    public User read(String userId) {
        return repository.findById(userId).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        return repository.findByFirstName(firstName);
    }

    @Override
    public List<User> findByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }

    @Override
    public void delete(String userId) {
        repository.deleteById(userId);
    }
}
