package za.ac.cput.communitystoreplatform.service.impl;

import org.springframework.stereotype.Service;
import za.ac.cput.communitystoreplatform.domain.SellerProfile;
import za.ac.cput.communitystoreplatform.repository.SellerProfileRepository;
import za.ac.cput.communitystoreplatform.service.ISellerProfileService;

import java.util.List;

@Service
public class SellerProfileService implements ISellerProfileService {

    private final SellerProfileRepository repository;

    public SellerProfileService(
            SellerProfileRepository repository) {

        this.repository = repository;
    }

    @Override
    public SellerProfile create(SellerProfile sellerProfile) {
        return repository.save(sellerProfile);
    }

    @Override
    public SellerProfile update(SellerProfile sellerProfile) {
        return repository.save(sellerProfile);
    }

    @Override
    public SellerProfile read(int sellerId) {
        return repository.findById(sellerId).orElse(null);
    }

    @Override
    public List<SellerProfile> getAll() {
        return repository.findAll();
    }

    @Override
    public SellerProfile findByStoreName(String storeName) {
        return repository.findByStoreName(storeName).orElse(null);
    }

    @Override
    public List<SellerProfile> findByVerificationStatus(
            String verificationStatus) {

        return repository.findByVerificationStatus(
                verificationStatus);
    }

    @Override
    public void delete(int sellerId) {
        repository.deleteById(sellerId);
    }
}