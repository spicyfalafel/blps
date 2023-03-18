package itmo.blps.lab.controller;

import itmo.blps.lab.dto.Review;
import itmo.blps.lab.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.ResponseEntity.ok;

@RestController
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/api/medication/{medicationId}/review")
    @ResponseBody
    public ResponseEntity<String> review(@PathVariable Long medicationId, @RequestBody Review review) {
        reviewService.review(medicationId, review);
        return new ResponseEntity<>("approved", HttpStatus.OK);
    }

    @DeleteMapping("/api/review/{reviewId}")
    @ResponseBody
    public ResponseEntity<?> deleteReviewById(@PathVariable Long reviewId) {
        reviewService.deleteReviewById(reviewId);
        return ok("deleted review with id " + reviewId);
    }

    @GetMapping(value = "/api/review")
    @ResponseBody
    public ResponseEntity<?> getReviews(
            @RequestParam(value="approved", required = false) String approved) {
        return new ResponseEntity<>(reviewService.getReviews(approved), HttpStatus.OK);
    }
}
