package za.ac.cput.communitystoreplatform.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import za.ac.cput.communitystoreplatform.domain.SellerProfile;
import za.ac.cput.communitystoreplatform.repository.SellerProfileRepository;
import za.ac.cput.communitystoreplatform.service.impl.SellerProfileService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SellerProfileServiceTest {

    @Mock
    private SellerProfileRepository repository;

    private SellerProfileService service;
    private SellerProfile sellerProfile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        service = new SellerProfileService(repository);

        sellerProfile = new SellerProfile.Builder()
                .setSellerId(1)
                .setStoreName("Lindo's Store")
                .setStoreDescription("Thrift clothing store")
                .setBusinessRegistrationNo("BR12345")
                .setVerificationStatus("VERIFIED")
                .setCreatedAt(LocalDate.now())
                .build();
    }

    @Test
    void create() {
        when(repository.save(sellerProfile))
                .thenReturn(sellerProfile);

        SellerProfile result =
                service.create(sellerProfile);

        assertNotNull(result);
        assertEquals("Lindo's Store",
                result.getStoreName());

        verify(repository).save(sellerProfile);
    }

    @Test
    void update() {
        when(repository.save(sellerProfile))
                .thenReturn(sellerProfile);

        SellerProfile result =
                service.update(sellerProfile);

        assertNotNull(result);
        assertEquals(1, result.getSellerId());

        verify(repository).save(sellerProfile);
    }

    @Test
    void read() {
        when(repository.findById(1))
                .thenReturn(Optional.of(sellerProfile));

        SellerProfile result = service.read(1);

        assertNotNull(result);
        assertEquals(1, result.getSellerId());
    }

    @Test
    void readNotFound() {
        when(repository.findById(99))
                .thenReturn(Optional.empty());

        SellerProfile result = service.read(99);

        assertNull(result);
    }

    @Test
    void getAll() {
        List<SellerProfile> sellers =
                Arrays.asList(sellerProfile);

        when(repository.findAll()).thenReturn(sellers);

        List<SellerProfile> result =
                service.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(
                "Lindo's Store",
                result.get(0).getStoreName()
        );

        verify(repository).findAll();
    }

    @Test
    void findByStoreName() {
        when(repository.findByStoreName("Lindo's Store"))
                .thenReturn(Optional.of(sellerProfile));

        SellerProfile result =
                service.findByStoreName("Lindo's Store");

        assertNotNull(result);
        assertEquals(
                "Lindo's Store",
                result.getStoreName()
        );
    }

    @Test
    void findByStoreNameNotFound() {
        when(repository.findByStoreName("Unknown Store"))
                .thenReturn(Optional.empty());

        SellerProfile result =
                service.findByStoreName("Unknown Store");

        assertNull(result);
    }

    @Test
    void findByVerificationStatus() {
        List<SellerProfile> sellers =
                Arrays.asList(sellerProfile);

        when(repository.findByVerificationStatus("VERIFIED"))
                .thenReturn(sellers);

        List<SellerProfile> result =
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