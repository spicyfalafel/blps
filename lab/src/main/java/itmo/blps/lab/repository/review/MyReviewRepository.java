package itmo.blps.lab.repository.review;

import itmo.blps.lab.dto.Review;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MyReviewRepository implements ReviewRepository {

    private final JdbcTemplate jdbcTemplate;

    public MyReviewRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public boolean save(Review review, Long medicationId) {
        return jdbcTemplate.update("insert into review (approved, mark, name, email, review, medication_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?)",
                review.getApproved(),
                review.getMark(),
                review.getName(),
                review.getEmail(),
                review.getReview(),
                medicationId)
                == 1;
    }
}
