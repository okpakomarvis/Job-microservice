package com.marvisx.reviewms.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @GetMapping
    ResponseEntity<List<Review>> getAllReview(@RequestParam Long companyId){
        try{
            return reviewService.getAllReview(companyId) ;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return  new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping
    ResponseEntity<String> createJob(@RequestParam Long companyId, @RequestBody Review requestMap){
        try{
            if(requestMap !=null){
                //requestMap.setId(id++);
                return reviewService.createReview(companyId, requestMap);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/{id}")
    ResponseEntity<Review> getReview( @PathVariable Long id){
        try{
            if(!Objects.isNull(id)) {
                return reviewService.getReview( id);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @PutMapping("/update/{id}")
    ResponseEntity<String> updateReview( @PathVariable Long id, @RequestBody Review review){
        try{
            return reviewService.updateReview( id, review) ;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteReview( @PathVariable Long id){
        try{
            return reviewService.deleteReview( id) ;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
