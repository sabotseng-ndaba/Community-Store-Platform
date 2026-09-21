package za.ac.cput.communitystoreplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.communitystoreplatform.domain.Address;
import za.ac.cput.communitystoreplatform.service.impl.AddressServiceImpl;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressServiceImpl addressService;

    @Autowired
    public AddressController(AddressServiceImpl addressService){
        this.addressService = addressService;
    }

    @PostMapping("/create")
    public Address create(@RequestBody Address address){
        return addressService.create(address);
    }

    @GetMapping("/read/{addressId}")
    public Address read(@PathVariable Integer addressId){
        return addressService.read(addressId);
    }

    @PutMapping("/update")
    public Address update(@RequestBody Address address){
        return addressService.update(address);
    }

    @DeleteMapping("/delete/{addressId}")
    public boolean delete(@PathVariable Integer addressId){
        return addressService.delete(addressId);
    }
}
