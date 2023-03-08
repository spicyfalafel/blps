package itmo.blps.lab.services;

import itmo.blps.lab.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewManager {
    public boolean reviewReview(Review review){
        if (review == null || review.getReview() == null) return false;
        return (review.getMark() != 1) && (review.getReview().toLowerCase().matches("сша|россия|украина"));
    }
}
