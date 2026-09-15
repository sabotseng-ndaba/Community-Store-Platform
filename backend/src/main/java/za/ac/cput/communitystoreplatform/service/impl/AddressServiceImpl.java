package za.ac.cput.communitystoreplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.communitystoreplatform.domain.Address;
import za.ac.cput.communitystoreplatform.repository.AddressRepository;
import za.ac.cput.communitystoreplatform.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {
    private final AddressRepository repository;

    @Autowired
    public AddressServiceImpl(AddressRepository repository){
        this.repository = repository;
    }
    @Override
    public Address create(Address address) {
        return repository.save(address);
    }

    @Override
    public Address read(Integer addressId) {
        return repository.findById(addressId).orElse(null);
    }

    @Override
    public Address update(Address address) {
        return repository.save(address);
    }

    @Override
    public boolean delete(Integer addressId) {
        if(repository.existsById(addressId)){
            repository.deleteById(addressId);
            return true;
        }
        return false;
    }
}
