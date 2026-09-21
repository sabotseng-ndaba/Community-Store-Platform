package za.ac.cput.communitystoreplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.communitystoreplatform.domain.FraudAlert;
import za.ac.cput.communitystoreplatform.repository.FraudAlertRepository;
import za.ac.cput.communitystoreplatform.service.IFraudAlertService;
import java.util.List;

@Service
public class FraudAlertServiceImpl implements IFraudAlertService {
    private final FraudAlertRepository repository;

    @Autowired
    public FraudAlertServiceImpl(FraudAlertRepository repository){
        this.repository = repository;
    }


    @Override
    public FraudAlert create(FraudAlert alert) {
        return repository.save(alert);
    }

    @Override
    public FraudAlert read(Integer alertId) {
        return repository.findById(alertId).orElse(null);
    }

    @Override
    public FraudAlert update(FraudAlert alert) {
        return repository.save(alert);
    }

    @Override
    public boolean delete(Integer alertId) {
        if(repository.existsById(alertId)){
            repository.deleteById(alertId);
            return true;
        }
        return false;
    }

    @Override
    public List<FraudAlert> getAll() {
        return repository.getAll();
    }

}
