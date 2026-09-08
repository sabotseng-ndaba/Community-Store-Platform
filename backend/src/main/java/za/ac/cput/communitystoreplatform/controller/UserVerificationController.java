package za.ac.cput.communitystoreplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.communitystoreplatform.domain.UserVerification;
import za.ac.cput.communitystoreplatform.service.impl.UserVerificationServiceImpl;

@RestController
@RequestMapping("/userVerification")
public class UserVerificationController {
    private final UserVerificationServiceImpl service;

    @Autowired
    public UserVerificationController(UserVerificationServiceImpl service){
        this.service = service;
    }

    @PostMapping("/create")
    public UserVerification create(@RequestBody UserVerification userVerification){
        return service.create(userVerification);
    }

    @GetMapping("/read/{verificationId")
    public UserVerification read(@PathVariable Integer verificationId){
        return service.read(verificationId);
    }

    @PutMapping("/update")
    public UserVerification update(@RequestBody UserVerification userVerification){
        return service.update(userVerification);
    }

    @DeleteMapping("/delete/{verificationId}")
    public boolean delete(@PathVariable Integer verificationId){
        return service.delete(verificationId);
    }
}
