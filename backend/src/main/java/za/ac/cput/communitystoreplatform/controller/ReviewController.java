package za.ac.cput.communitystoreplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.communitystoreplatform.domain.Review;
import za.ac.cput.communitystoreplatform.service.impl.ReviewServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewServiceImpl reviewService;

    @Autowired
    public ReviewController(ReviewServiceImpl reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping("/create")
    public Review create(@RequestBody Review review){
        return reviewService.create(review);
    }

    @GetMapping("/read/{reviewId}")
    public Review read(@PathVariable String reviewId){
        return reviewService.read(reviewId);
    }

    @PutMapping("/update")
    public Review update(@RequestBody Review review){
        return reviewService.update(review);
    }

    @DeleteMapping("/delete/{reviewId}")
    public boolean delete(@PathVariable String reviewId){
        return reviewService.delete(reviewId);
    }

    @GetMapping("/getAll")
    public List<Review> getAll(){
        return reviewService.getAll();
    }
}
