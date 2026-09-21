package za.ac.cput.communitystoreplatform.service;

import za.ac.cput.communitystoreplatform.domain.SellerProfile;
import java.util.List;

public interface ISellerProfileService {

    SellerProfile create(SellerProfile sellerProfile);

    SellerProfile update(SellerProfile sellerProfile);

    SellerProfile read(int sellerId);

    List<SellerProfile> getAll();

    SellerProfile findByStoreName(String storeName);

    List<SellerProfile> findByVerificationStatus(
            String verificationStatus);

    void delete(int sellerId);
}