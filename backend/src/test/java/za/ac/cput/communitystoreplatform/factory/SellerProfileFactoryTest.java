package za.ac.cput.communitystoreplatform.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.communitystoreplatform.domain.SellerProfile;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SellerProfileFactoryTest {

    @Test
    void createSellerProfile() {

        SellerProfile seller =
                SellerProfileFactory.createSellerProfile(
                        1,
                        "Lindo's Store",
                        "Thrift clothing store",
                        "BR12345",
                        "VERIFIED",
                        LocalDate.now()
                );

        assertNotNull(seller);
        assertEquals(1, seller.getSellerId());
        assertEquals(
                "Lindo's Store",
                seller.getStoreName()
        );
        assertEquals(
                "VERIFIED",
                seller.getVerificationStatus()
        );
    }
}