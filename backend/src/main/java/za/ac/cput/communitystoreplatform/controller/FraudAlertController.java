package za.ac.cput.communitystoreplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.communitystoreplatform.domain.FraudAlert;
import za.ac.cput.communitystoreplatform.repository.FraudAlertRepository;
import za.ac.cput.communitystoreplatform.service.impl.FraudAlertServiceImpl;

import java.util.*;

@RestController
@RequestMapping("/fraudAlerts")
public class FraudAlertController {
    private final FraudAlertServiceImpl alertService;

    @Autowired
    public FraudAlertController(FraudAlertServiceImpl alertService){
        this.alertService = alertService;
    }

    @PostMapping("/create")
    public FraudAlert create(@RequestBody FraudAlert alert){
        return alertService.create(alert);
    }

    @GetMapping("/read/{alertId}")
    public FraudAlert read(@PathVariable Integer alertId){
        return alertService.read(alertId);
    }

    @PutMapping("/update")
    public FraudAlert update(@RequestBody FraudAlert alert){
        return alertService.update(alert);
    }
}
