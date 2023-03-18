package itmo.blps.lab.repository.review;

import itmo.blps.lab.dto.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewCRUDRepository extends CrudRepository<Review, Long> {
    List<Review> findAllByApprovedFalse();
    List<Review> findAllByApprovedTrue();
}
