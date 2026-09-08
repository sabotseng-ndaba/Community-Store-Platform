package za.ac.cput.communitystoreplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.communitystoreplatform.domain.SellerProfile;
import za.ac.cput.communitystoreplatform.service.impl.SellerProfileServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/sellerProfile")
public class SellerProfileController {
    private final SellerProfileServiceImpl sellerService;

    @Autowired
    public SellerProfileController(SellerProfileServiceImpl sellerService){
        this.sellerService = sellerService;
    }

    @PostMapping("/create")
    public SellerProfile create(@RequestBody SellerProfile sellerProfile){
        return sellerService.create(sellerProfile);
    }

    @GetMapping("/read/{sellerId}")
    public SellerProfile read(@PathVariable Integer sellerId){
        return sellerService.read(sellerId);
    }

    @PutMapping("/update")
    public SellerProfile update(@RequestBody SellerProfile sellerProfile){
        return sellerService.update(sellerProfile);
    }

    @DeleteMapping("/delete/{sellerId}")
    public boolean delete(@PathVariable Integer sellerId){
        return sellerService.delete(sellerId);
    }

    @GetMapping("/getAll")
    public List<SellerProfile> getAll(){
        return sellerService.getAll();
    }
}
