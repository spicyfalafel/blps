package itmo.blps.lab.services;

import ch.qos.logback.classic.Logger;
import itmo.blps.lab.LabApplication;
import itmo.blps.lab.entity.Medication;
import itmo.blps.lab.entity.Review;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MedicationManager {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(LabApplication.class);

    public boolean validateMedication(Medication medication){
        return !(medication == null || medication.getTitle() == null || medication.getMedicationId() != null|| medication.getReviews() != null);
    }
}
