package itmo.blps.lab.repository.review;

import itmo.blps.lab.entity.Review;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository {
    boolean save(Review review, Long medicationId);
}
