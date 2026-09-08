package za.ac.cput.communitystoreplatform.service;

import za.ac.cput.communitystoreplatform.domain.SellerProfile;
import java.util.*;

public interface ISellerProfileService extends IService<SellerProfile, Integer>{
    List<SellerProfile> getAll();
}
