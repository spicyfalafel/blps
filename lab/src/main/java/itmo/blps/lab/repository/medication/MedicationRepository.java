package itmo.blps.lab.repository.medication;

import itmo.blps.lab.dto.Medication;
import org.springframework.stereotype.Repository;


@Repository
public interface MedicationRepository {
    Medication getMedicationByIdLimitReviews(Long medicationId);
}
