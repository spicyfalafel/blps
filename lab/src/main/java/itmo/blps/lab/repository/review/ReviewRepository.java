package itmo.blps.lab.repository.review;

import itmo.blps.lab.dto.Review;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository {
    boolean save(Review review, Long medicationId);
}
