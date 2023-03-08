package itmo.blps.lab.repository.medication;

import itmo.blps.lab.entity.Medication;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicationRepository {
    Medication getMedicationByIdLimitReviews(Long medicationId);
}
