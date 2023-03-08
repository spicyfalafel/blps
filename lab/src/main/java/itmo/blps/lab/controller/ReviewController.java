package itmo.blps.lab.controller;

import itmo.blps.lab.entity.Medication;
import itmo.blps.lab.entity.Review;
import itmo.blps.lab.repository.ReviewRepository;
import itmo.blps.lab.services.ReviewManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewManager reviewManager;
    @PostMapping("/api/review")
    public boolean review(@RequestBody Review review) {
        review.setApproved(false);
        reviewRepository.save(review);
        boolean appr = reviewManager.reviewReview(review);
        if (appr) {
            review.setApproved(appr);
            reviewRepository.save(review);
        } else {
            reviewRepository.delete(review);
        }
        return appr;
    }
}
