package za.ac.cput.communitystoreplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.communitystoreplatform.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);
}
