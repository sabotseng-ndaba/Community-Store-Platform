package za.ac.cput.communitystoreplatform.factory;

import za.ac.cput.communitystoreplatform.domain.SellerProfile;

import java.time.LocalDate;

public class SellerProfileFactory {

    public static SellerProfile createSellerProfile(
            int sellerId,
            String storeName,
            String storeDescription,
            String businessRegistrationNo,
            String verificationStatus,
            LocalDate createdAt) {

        return new SellerProfile.Builder()
                .setSellerId(sellerId)
                .setStoreName(storeName)
                .setStoreDescription(storeDescription)
                .setBusinessRegistrationNo(businessRegistrationNo)
                .setVerificationStatus(verificationStatus)
                .setCreatedAt(createdAt)
                .build();
    }
}