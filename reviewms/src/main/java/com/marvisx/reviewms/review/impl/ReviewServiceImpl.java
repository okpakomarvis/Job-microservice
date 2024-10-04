package com.marvisx.reviewms.review.impl;

import com.marvisx.reviewms.review.Review;
import com.marvisx.reviewms.review.ReviewRepository;
import com.marvisx.reviewms.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    //@Autowired
    //private CompanyService companyService;
    @Override
    public ResponseEntity<List<Review>> getAllReview(Long id) {
        try{
            return new ResponseEntity<>(reviewRepository.findByCompanyId(id), HttpStatus.OK);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> createReview(Long companyId, Review requestMap) {
        try {
            //Company company = companyService.getCompany(companyId).getBody();
            if(companyId !=null && requestMap !=null){
                requestMap.setCompanyId(companyId);
                //company.setReviews(requestMap.getReviews());
                //company.setReviews(requestMap.getReviews());

                reviewRepository.save(requestMap);
                return new ResponseEntity<>("Review Created Successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("Company not found", HttpStatus.OK);

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Review> getReview( Long id) {
            try{
                Review review = reviewRepository.findById(id).orElse(null);

                if(review !=null){
                    return new ResponseEntity<>(review, HttpStatus.OK);
                }
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    @Override
    public ResponseEntity<String> updateReview( Long id, Review updateReview) {
        try{
            //Company company= companyService.getCompany(companyId).getBody();
            Review review = reviewRepository.findById(id).orElse(null);
            if(review!=null ){
                Review newReview = new Review();
                System.out.println("{update} "+updateReview.toString());
                newReview.setTitle(updateReview.getTitle());
                newReview.setId(id);
                newReview.setCompanyId(updateReview.getCompanyId());
                newReview.setDescription(updateReview.getDescription());
                newReview.setRating(updateReview.getRating());
                reviewRepository.saveAndFlush(newReview);
                return new ResponseEntity<>("review updated successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> deleteReview( Long id) {
        try{
            Review review = reviewRepository.findById(id).orElse(null);
           // Company company= companyService.getCompany(companyId).getBody();
            if(review!=null ){
               reviewRepository.delete(review);
                return new ResponseEntity<>("review deleted successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
