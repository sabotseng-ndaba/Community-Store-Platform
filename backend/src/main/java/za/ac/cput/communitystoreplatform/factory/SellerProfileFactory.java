package za.ac.cput.communitystoreplatform.factory;

import za.ac.cput.communitystoreplatform.domain.SellerProfile;
import za.ac.cput.communitystoreplatform.util.Helper;

import java.time.LocalDate;

public class SellerProfileFactory {
    public SellerProfile createSellerProfile(int sellerId,String storeName,String storeDescription,
                                             String businessRegistrationNo, String verificationStatus,
                                             LocalDate createdAt){

        if(Helper.isNullOrEmpty(storeDescription)
        && Helper.isValidInt(sellerId)
        && Helper.isNullOrEmpty(storeName)
        && Helper.isNullOrEmpty(verificationStatus)
        && Helper.isNullOrEmpty(businessRegistrationNo)){
            return null;
        }

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
