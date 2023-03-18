package itmo.blps.lab.services;

import ch.qos.logback.classic.Logger;
import itmo.blps.lab.LabApplication;
import itmo.blps.lab.dto.Medication;
import itmo.blps.lab.exception.NoEntityWithSuchIdException;
import itmo.blps.lab.repository.medication.MedicationCRUDRepository;
import itmo.blps.lab.repository.medication.MyMedicationRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MedicationService {

    private final MedicationCRUDRepository medicationCRUDRepository;
    private final MyMedicationRepository myMedicationRepository;

    @Autowired
    public MedicationService(MedicationCRUDRepository medicationCRUDRepository, MyMedicationRepository myMedicationRepository) {
        this.medicationCRUDRepository = medicationCRUDRepository;
        this.myMedicationRepository = myMedicationRepository;
    }

    public Medication getMedicationByIdLimitReviews(Long id) {
        Medication m = myMedicationRepository.getMedicationByIdLimitReviews(id);
        if (m == null) throw new NoEntityWithSuchIdException("no such medication with id " + id);
        return m;
    }

    public List<?> allMedicationsTitleAndId(Pageable pageable) {
        return this.medicationCRUDRepository.findAllReturnTitleAndId(pageable.getPageSize(), pageable.getOffset());
    }

    public List<Medication> medicationsByTitleContains(Pageable pageable, String title) {
        return medicationCRUDRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    public List<Medication> medicationsByTitleStarting(Pageable pageable, String titleStarting) {
        return medicationCRUDRepository.findByTitleStartingWithIgnoreCase(titleStarting, pageable);
    }

    public Medication createMedication(Medication medication) {
        return medicationCRUDRepository.save(medication);
    }

    public void deleteMedication(Long id) {
        if (medicationCRUDRepository.existsById(id))
            medicationCRUDRepository.deleteById(id);
        else throw new NoEntityWithSuchIdException("no such medication with id " + id);
    }

    public void updateMedication(Long id, Medication medication) {
        if (medicationCRUDRepository.existsById(id)) {
            medicationCRUDRepository.save(medication);
        } else {
            throw new NoSuchElementException();
        }
    }
}
