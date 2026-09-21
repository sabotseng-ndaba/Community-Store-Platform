package za.ac.cput.communitystoreplatform.service;

import za.ac.cput.communitystoreplatform.domain.FraudAlert;
import java.util.*;

public interface IFraudAlertService extends IService<FraudAlert ,Integer>{
    List<FraudAlert> getAll();
}
