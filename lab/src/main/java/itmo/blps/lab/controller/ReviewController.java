package itmo.blps.lab.controller;

import itmo.blps.lab.entity.Review;
import itmo.blps.lab.entity.ReviewAnswer;
import itmo.blps.lab.repository.review.MyReviewRepository;
import itmo.blps.lab.services.ReviewManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {
    private MyReviewRepository myReviewRepository;
    private ReviewManager reviewManager;
    @Autowired
    public void setMyReviewRepository(MyReviewRepository myReviewRepository) {
        this.myReviewRepository = myReviewRepository;
    }

    @Autowired
    public void setReviewManager(ReviewManager reviewManager) {
        this.reviewManager = reviewManager;
    }

    @PostMapping("/api/medication/{medicationId}/review")
    @ResponseBody
    public ResponseEntity<ReviewAnswer> review(@PathVariable Long medicationId, @RequestBody Review review) {
        boolean appr = reviewManager.reviewReview(review);
        review.setApproved(appr);
        if (appr) {
            try {
                this.myReviewRepository.save(review, medicationId);
            } catch (Exception e)  {
                return new ResponseEntity<>(new ReviewAnswer(false, "can't find medication with such id"),
                        HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ReviewAnswer(true, "ok"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ReviewAnswer(false, "review is not approved"), HttpStatus.BAD_REQUEST);
        }
    }
}