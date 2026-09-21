```java
        package za.ac.cput.communitystoreplatform.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import za.ac.cput.communitystoreplatform.domain.User;
import za.ac.cput.communitystoreplatform.repository.UserRepository;
import za.ac.cput.communitystoreplatform.service.impl.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository repository;

    private UserService service;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        service = new UserService(repository);

        user = new User.Builder()
                .setUserId("U001")
                .setFirstName("Lindo")
                .setLastName("Nanto")
                .build();
    }

    @Test
    void create() {
        when(repository.save(user))
                .thenReturn(user);

        User result = service.create(user);

        assertNotNull(result);
        assertEquals("U001", result.getUserId());
        assertEquals("Lindo", result.getFirstName());
        assertEquals("Nanto", result.getLastName());

        verify(repository).save(user);
    }

    @Test
    void update() {
        when(repository.save(user))
                .thenReturn(user);

        User result = service.update(user);

        assertNotNull(result);
        assertEquals("U001", result.getUserId());

        verify(repository).save(user);
    }

    @Test
    void read() {
        when(repository.findById("U001"))
                .thenReturn(Optional.of(user));

        User result = service.read("U001");

        assertNotNull(result);
        assertEquals("U001", result.getUserId());
        assertEquals("Lindo", result.getFirstName());
    }

    @Test
    void readNotFound() {
        when(repository.findById("U999"))
                .thenReturn(Optional.empty());

        User result = service.read("U999");

        assertNull(result);
    }

    @Test
    void getAll() {
        List<User> users = Arrays.asList(user);

        when(repository.findAll())
                .thenReturn(users);

        List<User> result = service.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(
                "Lindo",
                result.get(0).getFirstName()
        );

        verify(repository).findAll();
    }

    @Test
    void findByFirstName() {
        List<User> users = Arrays.asList(user);

        when(repository.findByFirstName("Lindo"))
                .thenReturn(users);

        List<User> result =
                service.findByFirstName("Lindo");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(
                "Lindo",
                result.get(0).getFirstName()
        );
    }

    @Test
    void findByLastName() {
        List<User> users = Arrays.asList(user);

        when(repository.findByLastName("Nanto"))
                .thenReturn(users);

        List<User> result =
                service.findByLastName("Nanto");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(
                "Nanto",
                result.get(0).getLastName()
        );
    }

    @Test
    void delete() {
        doNothing().when(repository)
                .deleteById("U001");

        service.delete("U001");

        verify(repository).deleteById("U001");
    }
}
```
