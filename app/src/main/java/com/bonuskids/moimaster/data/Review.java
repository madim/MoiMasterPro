package com.bonuskids.moimaster.data;

/**
 * Created by madi on 4/8/17.
 */

public class Review {

    private int rating;
    private Service service;
    private String review;
    private String reviewerAndDate;

    public Review() {

    }

    public Review(int rating, Service service, String review, String reviewerAndDate) {
        this.rating = rating;
        this.service = service;
        this.review = review;
        this.reviewerAndDate = reviewerAndDate;
    }

    public int getRating() {
        return rating;
    }

    public Service getService() {
        return service;
    }

    public String getReview() {
        return review;
    }

    public String getReviewerAndDate() {
        return reviewerAndDate;
    }
}
