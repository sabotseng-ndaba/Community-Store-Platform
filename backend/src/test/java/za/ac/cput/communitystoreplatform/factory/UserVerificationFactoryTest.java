package za.ac.cput.communitystoreplatform.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.communitystoreplatform.domain.UserVerification;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class UserVerificationFactoryTest {

    @Test
    void createUserVerification() {

        UserVerification verification =
                UserVerificationFactory.createUserVerification(
                        1,
                        "ID_DOCUMENT",
                        "VERIFIED",
                        "id-document.pdf",
                        LocalDate.now()
                );

        assertNotNull(verification);
        assertEquals(
                1,
                verification.getVerificationId()
        );
        assertEquals(
                "ID_DOCUMENT",
                verification.getVerificationType()
        );
        assertEquals(
                "VERIFIED",
                verification.getVerificationStatus()
        );
    }
}