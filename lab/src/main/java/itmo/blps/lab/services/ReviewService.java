package itmo.blps.lab.services;

import ch.qos.logback.classic.Logger;
import itmo.blps.lab.LabApplication;
import itmo.blps.lab.dto.Review;
import itmo.blps.lab.exception.BooleanParamParseException;
import itmo.blps.lab.exception.NoEntityWithSuchIdException;
import itmo.blps.lab.exception.ReviewIsNotApprovedException;
import itmo.blps.lab.repository.review.MyReviewRepository;
import itmo.blps.lab.repository.review.ReviewCRUDRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final MyReviewRepository myReviewRepository;
    private final ReviewCRUDRepository reviewCRUDRepository;

    public ReviewService(MyReviewRepository myReviewRepository, ReviewCRUDRepository reviewCRUDRepository) {
        this.myReviewRepository = myReviewRepository;
        this.reviewCRUDRepository = reviewCRUDRepository;
    }

    private boolean markIsValid(Long mark){
        return mark != null && mark <= 5 && mark >= 1 && (mark != 1);
    }
    private boolean reviewReview(Review review){
        if (review == null || review.getReview() == null ||
                review.getEmail() == null || review.getName() == null) return false;
        return markIsValid(review.getMark()) && !(review.getReview().toLowerCase().matches("сша|россия|украина"));
    }

    public Iterable<Review> getReviews(String approved) {
        if (approved !=null) {
            if (approved.equals(Boolean.TRUE.toString())) {
                return reviewCRUDRepository.findAllByApprovedTrue();
            } else if (approved.equals(Boolean.FALSE.toString())) {
                return reviewCRUDRepository.findAllByApprovedFalse();
            } else throw new BooleanParamParseException("approved is not 'true' or 'false'");
        }
        return reviewCRUDRepository.findAll();
    }

    public void review(Long medicationId, Review review) {
        boolean appr = reviewReview(review);
        review.setApproved(appr);
        if (appr) {
            try {
                this.myReviewRepository.save(review, medicationId);
            } catch (Exception e)  {
                throw new NoEntityWithSuchIdException("can't find medication with such id " + medicationId);
            }
        } else {
            throw new ReviewIsNotApprovedException();
        }
    }

    public void deleteReviewById(Long reviewId) {
        if (reviewCRUDRepository.existsById(reviewId)) {
            reviewCRUDRepository.deleteById(reviewId);
        } else {
            throw new NoEntityWithSuchIdException("no such review with id " + reviewId);
        }
    }
}
