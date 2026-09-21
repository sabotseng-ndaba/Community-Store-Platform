package za.ac.cput.communitystoreplatform.factory;

import za.ac.cput.communitystoreplatform.domain.Contact;
import za.ac.cput.communitystoreplatform.domain.Role;
import za.ac.cput.communitystoreplatform.domain.User;

public class UserFactory {

    public static User createUser(
            String userId,
            String firstName,
            String lastName,
            Role role,
            Contact contact) {

        return new User.Builder()
                .setUserId(userId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setRole(role)
                .setContact(contact)
                .build();
    }
}
