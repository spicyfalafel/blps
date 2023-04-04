package itmo.blps.lab.repository.medication;

import itmo.blps.lab.dto.Medication;
import itmo.blps.lab.dto.Review;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Component
public class MedicationExtractor implements ResultSetExtractor<Medication> {
    @Override
    public Medication extractData(ResultSet rs) throws SQLException, DataAccessException {
        Medication medication = new Medication();
        boolean med = false;
        Set<Review> reviews = new HashSet<>();
        while (rs.next()) {
            if (!med ) {
                medication.setMedicationId(rs.getLong("id"));
                medication.setByRecipe(rs.getBoolean("by_recipe"));
                medication.setDosage(rs.getString("dosage"));
                medication.setDescription(rs.getString("description"));
                medication.setTitle(rs.getString("title"));
                medication.setSideEffects(rs.getString("side_effects"));
                medication.setStorageConditions(rs.getString("storage_conditions"));
                medication.setReasonToUse(rs.getString("reason_to_use"));
                med = true;
            }
            Review r = new Review();

            r.setMark(rs.getLong("mark"));
            r.setName(rs.getString("name"));
            r.setEmail(rs.getString("email"));
            r.setReview(rs.getString("review"));
            r.setApproved(rs.getBoolean("approved"));
            r.setReviewId(rs.getLong("review_id"));
            if ((r.getApproved() != null && r.getApproved()) || r.getName() != null ||
                    r.getReview() != null || r.getEmail() != null ) {
                        reviews.add(r);
            }
        }
        medication.setReviews(reviews);
        if (!med) return null;
        return medication;
    }
}
