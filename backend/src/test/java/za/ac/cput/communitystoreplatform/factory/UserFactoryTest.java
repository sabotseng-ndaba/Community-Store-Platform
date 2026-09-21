package za.ac.cput.communitystoreplatform.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.communitystoreplatform.domain.User;

import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {

    @Test
    void createUser() {

        User user = UserFactory.createUser(
                "U001",
                "Lindo",
                "Nanto",
                null,
                null
        );

        assertNotNull(user);
        assertEquals("U001", user.getUserId());
        assertEquals("Lindo", user.getFirstName());
        assertEquals("Nanto", user.getLastName());
        assertNull(user.getRole());
        assertNull(user.getContact());
    }
}
