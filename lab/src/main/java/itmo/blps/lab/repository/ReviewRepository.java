package itmo.blps.lab.repository;

import itmo.blps.lab.entity.Medication;
import itmo.blps.lab.entity.Review;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findByEmail(String title);

    @Query("update review set approved = true where review_id = :id")
    Review approveReview(Long id);
}
