package za.ac.cput.communitystoreplatform.service;

import za.ac.cput.communitystoreplatform.domain.User;
import java.util.List;

public interface IUserService {

    User create(User user);

    User update(User user);

    User read(String userId);

    List<User> getAll();

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    void delete(String userId);
}