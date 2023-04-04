package itmo.blps.lab.repository.medication;

import itmo.blps.lab.dto.Medication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MyMedicationRepository implements MedicationRepository {

    private final JdbcTemplate jdbcTemplate;

    public MyMedicationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final int MAX_REVIEWS_LIMIT = 3;
    private final String SQL_GET_MEDICATION_BY_ID_LIMIT_REVIEWS_5 = "select " +
            "m.medication_id as id, title, description, dosage, reason_to_use, " +
            "side_effects, by_recipe, storage_conditions, review_id, approved, mark, name, email, review " +
            "from medication m left join review r on r.medication_id = m.medication_id " +
            "where m.medication_id = ? order by r.review_id desc limit " + MAX_REVIEWS_LIMIT;
    @Override
    public Medication getMedicationByIdLimitReviews(Long medicationId) {
         return jdbcTemplate.query(SQL_GET_MEDICATION_BY_ID_LIMIT_REVIEWS_5,
                new Long[]{medicationId},  new MedicationExtractor());
    }
}
