package za.ac.cput.communitystoreplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.communitystoreplatform.domain.FraudAlert;
import java.util.*;

public interface FraudAlertRepository extends JpaRepository<FraudAlert, Integer> {
    List<FraudAlert> getAll();
}
