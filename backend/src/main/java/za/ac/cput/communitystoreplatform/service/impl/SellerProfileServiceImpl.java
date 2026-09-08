package za.ac.cput.communitystoreplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.communitystoreplatform.domain.SellerProfile;
import za.ac.cput.communitystoreplatform.repository.SellerProfileRepository;
import za.ac.cput.communitystoreplatform.service.ISellerProfileService;

import java.util.List;

@Service
public class SellerProfileServiceImpl implements ISellerProfileService {
    private final SellerProfileRepository repository;

    @Autowired
    public SellerProfileServiceImpl(SellerProfileRepository repository){
        this.repository = repository;
    }

    @Override
    public SellerProfile create(SellerProfile sellerProfile) {
        return repository.save(sellerProfile);
    }

    @Override
    public SellerProfile read(Integer sellerId) {
        return repository.findById(sellerId).orElse(null);
    }

    @Override
    public SellerProfile update(SellerProfile sellerProfile) {
        return repository.save(sellerProfile);
    }

    @Override
    public boolean delete(Integer sellerId) {
        if (repository.existsById(sellerId)) {
            repository.deleteById(sellerId);
            return true;
        }
        return false;
    }

    @Override
    public List<SellerProfile> getAll() {
        return repository.getAll();
    }

}
