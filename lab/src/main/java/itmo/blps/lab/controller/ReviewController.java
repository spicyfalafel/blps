package itmo.blps.lab.controller;

import itmo.blps.lab.entity.Medication;
import itmo.blps.lab.entity.Review;
import itmo.blps.lab.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;
    @PostMapping("/api/review")
    public String reviewToReview(@RequestBody Review review) {
        review.setApproved(false);
        reviewRepository.save(review);
        return "Sent to review. Wait for email.";
    }
    @GetMapping("/api/review/approve/:id")
    public Review approveReview(@PathVariable Long id) {
        return reviewRepository.approveReview(id);
    }
}
