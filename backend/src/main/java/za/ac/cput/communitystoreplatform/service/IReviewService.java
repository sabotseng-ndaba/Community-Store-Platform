package za.ac.cput.communitystoreplatform.service;

import za.ac.cput.communitystoreplatform.domain.Review;
import java.util.*;

public interface IReviewService extends IService<Review,String>{
    List<Review> getAll();

}
