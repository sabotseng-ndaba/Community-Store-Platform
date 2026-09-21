package za.ac.cput.communitystoreplatform.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    private String reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private double ratings;
    private String comment;

    @Column(name = "review_date")
    private LocalDate reviewDate;

    protected Review(){}
    public Review(Builder builder){
        this.reviewId = builder.reviewId;
        this.product = builder.product;
        this.user = builder.user;
        this.ratings = builder.ratings;
        this.comment = builder.comment;
        this.reviewDate = builder.reviewDate;
    }

    public String getReviewId(){ return reviewId;}
    public Product getProduct(){ return this.product;}
    public User getUser(){ return this.user;}
    public double getRatings(){ return ratings;}
    public String getComment(){ return comment;}
    public LocalDate getReviewDate(){ return reviewDate;}


    public String toString() {
        return "Review{" +
                "reviewId='" + reviewId + '\'' +
                ", product=" + product +
                ", user=" + user +
                ", ratings=" + ratings +
                ", comment=" + comment +
                ", reviewDate=" + reviewDate +
                '}';
    }

    public static class Builder{
        private String reviewId;
        private Product product;
        private User user;
        private double ratings;
        private String comment;
        private LocalDate reviewDate;

        public Builder copy(Review review){
            this.reviewId = review.reviewId;
            this.product = review.product;
            this.user = review.user;
            this.ratings = review.ratings;
            this.comment = review.comment;
            this.reviewDate = review.reviewDate;
            return this;
        }

        public Builder setReviewId(String reviewId) {
            this.reviewId = reviewId;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setRatings(double ratings) {
            this.ratings = ratings;
            return this;
        }

        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder setReviewDate(LocalDate reviewDate) {
            this.reviewDate = reviewDate;
            return this;
        }

        public Review build(){
            return new Review(this);
        }
    }
}
