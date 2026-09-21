package za.ac.cput.communitystoreplatform.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import za.ac.cput.communitystoreplatform.domain.UserVerification;
import za.ac.cput.communitystoreplatform.repository.UserVerificationRepository;
import za.ac.cput.communitystoreplatform.service.impl.UserVerificationService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserVerificationServiceTest {

    @Mock
    private UserVerificationRepository repository;

    private UserVerificationService service;
    private UserVerification verification;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        service = new UserVerificationService(repository);

        verification = new UserVerification.Builder()
                .setVerificationId(1)
                .setVerificationType("ID_DOCUMENT")
                .setVerificationStatus("VERIFIED")
                .setVerificationDocument("id-document.pdf")
                .setVerifiedAt(LocalDate.now())
                .build();
    }

    @Test
    void create() {
        when(repository.save(verification))
                .thenReturn(verification);

        UserVerification result =
                service.create(verification);

        assertNotNull(result);
        assertEquals(
                "ID_DOCUMENT",
                result.getVerificationType()
        );

        verify(repository).save(verification);
    }

    @Test
    void update() {
        when(repository.save(verification))
                .thenReturn(verification);

        UserVerification result =
                service.update(verification);

        assertNotNull(result);
        assertEquals(
                1,
                result.getVerificationId()
        );

        verify(repository).save(verification);
    }

    @Test
    void read() {
        when(repository.findById(1))
                .thenReturn(Optional.of(verification));

        UserVerification result =
                service.read(1);

        assertNotNull(result);
        assertEquals(
                1,
                result.getVerificationId()
        );
    }

    @Test
    void readNotFound() {
        when(repository.findById(99))
                .thenReturn(Optional.empty());

        UserVerification result =
                service.read(99);

        assertNull(result);
    }

    @Test
    void getAll() {
        List<UserVerification> verifications =
                Arrays.asList(verification);

        when(repository.findAll())
                .thenReturn(verifications);

        List<UserVerification> result =
                service.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(repository).findAll();
    }

    @Test
    void findByVerificationType() {
        List<UserVerification> verifications =
                Arrays.asList(verification);

        when(repository.findByVerificationType("ID_DOCUMENT"))
                .thenReturn(verifications);

        List<UserVerification> result =
                service.findByVerificationType("ID_DOCUMENT");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(
                "ID_DOCUMENT",
                result.get(0).getVerificationType()
        );
    }

    @Test
    void findByVerificationStatus() {
        List<UserVerification> verifications =
                Arrays.asList(verification);

        when(repository.findByVerificationStatus("VERIFIED"))
                .thenReturn(verifications);

        List<UserVerification> result =
                service.findByVerificationStatus("VERIFIED");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(
                "VERIFIED",
                result.get(0).getVerificationStatus()
        );
    }

    @Test
    void delete() {
        doNothing().when(repository).deleteById(1);

        service.delete(1);

        verify(repository).deleteById(1);
    }
}