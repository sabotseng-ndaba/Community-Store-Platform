package za.ac.cput.communitystoreplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.communitystoreplatform.domain.Review;
import za.ac.cput.communitystoreplatform.repository.ReviewRepository;
import za.ac.cput.communitystoreplatform.service.IReviewService;
import java.util.*;

@Service
public class ReviewServiceImpl implements IReviewService {
    private final ReviewRepository repository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository repository){
        this.repository = repository;
    }

    @Override
    public Review create(Review review) {
        return repository.save(review);
    }

    @Override
    public Review read(String reviewId) {
        return repository.findById(reviewId).orElse(null);
    }

    @Override
    public Review update(Review review) {
        return repository.save(review);
    }

    @Override
    public boolean delete(String reviewId) {
        if(repository.existsById(reviewId)){
            repository.deleteById(reviewId);
            return true;
        }
        return false;
    }

    @Override
    public List<Review> getAll() {
        return repository.getAll();
    }

}
