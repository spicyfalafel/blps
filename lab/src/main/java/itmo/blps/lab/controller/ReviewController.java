package itmo.blps.lab.controller;

import itmo.blps.lab.entity.Review;
import itmo.blps.lab.repository.review.MyReviewRepository;
import itmo.blps.lab.repository.review.ReviewCRUDRepository;
import itmo.blps.lab.services.ReviewManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReviewController {
    private final MyReviewRepository myReviewRepository;
    private final ReviewManager reviewManager;
    private final ReviewCRUDRepository reviewCRUDRepository;

    @Autowired
    public ReviewController(MyReviewRepository myReviewRepository, ReviewManager reviewManager, ReviewCRUDRepository reviewCRUDRepository) {
        this.myReviewRepository = myReviewRepository;
        this.reviewManager = reviewManager;
        this.reviewCRUDRepository = reviewCRUDRepository;
    }

    @PostMapping("/api/medication/{medicationId}/review")
    @ResponseBody
    public ResponseEntity<String> review(@PathVariable Long medicationId, @RequestBody Review review) {
        boolean appr = reviewManager.reviewReview(review);
        review.setApproved(appr);
        if (appr) {
            try {
                this.myReviewRepository.save(review, medicationId);
            } catch (Exception e)  {
                return new ResponseEntity<>("can't find medication with such id", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("review is not approved", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/review/{reviewId}")
    @ResponseBody
    public ResponseEntity<?> deleteReviewById(@PathVariable Long reviewId) {
        if (reviewCRUDRepository.existsById(reviewId)) {
            reviewCRUDRepository.deleteById(reviewId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/api/review")
    @ResponseBody
    public ResponseEntity<?> getNotApprovedReviews(@RequestParam Optional<Boolean> approved) {
        if (approved.isPresent()){
            List<Review> reviews;
            if (approved.get()){
                 reviews = reviewCRUDRepository.findAllByApprovedTrue();
            } else {
                 reviews = reviewCRUDRepository.findAllByApprovedFalse();
            }
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(reviewCRUDRepository.findAll(), HttpStatus.OK);
        }
    }
}
