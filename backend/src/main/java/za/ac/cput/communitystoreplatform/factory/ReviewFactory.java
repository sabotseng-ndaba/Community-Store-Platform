package za.ac.cput.communitystoreplatform.factory;

import za.ac.cput.communitystoreplatform.domain.Product;
import za.ac.cput.communitystoreplatform.domain.Review;
import za.ac.cput.communitystoreplatform.domain.User;
import za.ac.cput.communitystoreplatform.util.Helper;

import java.time.LocalDate;

public class ReviewFactory {
    public static Review createReview(String reviewId, Product product,  User user, double ratings,
                                      String comment, LocalDate reviewDate){

        if(Helper.isNullOrEmpty(reviewId)
        && Helper.isNull(product)
        && Helper.isNull(user)
        && Helper.isPositive(ratings)
        && Helper.isNullOrEmpty(comment)
        ){
            return null;
        }

        return new Review.Builder()
                .setReviewId(reviewId)
                .setProduct(product)
                .setUser(user)
                .setRatings(ratings)
                .setComment(comment)
                .setReviewDate(reviewDate)
                .build();
    }
}
