package com.marvisx.reviewms.review;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {
    //ResponseEntity<String> createJob(Job requestMap);

    ResponseEntity<List<Review>> getAllReview(Long id);

    ResponseEntity<String> createReview(Long companyId, Review requestMap);

    ResponseEntity<Review> getReview( Long id);

    ResponseEntity<String> updateReview( Long id, Review review);

    ResponseEntity<String> deleteReview( Long id);

}
