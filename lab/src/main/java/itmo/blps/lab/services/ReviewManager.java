package itmo.blps.lab.services;

import ch.qos.logback.classic.Logger;
import itmo.blps.lab.LabApplication;
import itmo.blps.lab.entity.Review;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ReviewManager {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(LabApplication.class);

    public boolean markIsValid(Long mark){
        return mark != null && mark <= 5 && mark >= 1 && (mark != 1);
    }
    public boolean reviewReview(Review review){
        if (review == null || review.getReview() == null ||
                review.getEmail() == null || review.getName() == null) return false;
        return markIsValid(review.getMark()) && !(review.getReview().toLowerCase().matches("сша|россия|украина"));
    }
}
