package itmo.blps.lab.controller;

import itmo.blps.lab.entity.Medication;
import itmo.blps.lab.entity.MedicationIdTitle;
import itmo.blps.lab.repository.medication.MedicationCRUDRepository;
import itmo.blps.lab.repository.medication.MyMedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MedicationController {
    private MedicationCRUDRepository medicationCRUDRepository;
    private MyMedicationRepository myMedicationRepository;
    @Autowired
    public void setMyMedicationRepository(MyMedicationRepository medicationRepository) {
        this.myMedicationRepository = medicationRepository;
    }
    @Autowired
    public void setMedicationCRUDRepository(MedicationCRUDRepository medicationCRUDRepository) {
        this.medicationCRUDRepository = medicationCRUDRepository;
    }
    @GetMapping("/api/medication")
    public ResponseEntity<List<MedicationIdTitle>> allMedicationsTitleAndId() {
        List<MedicationIdTitle> lst = medicationCRUDRepository.findAllReturnTitleAndId();
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping(value = "/api/medication", params = "title")
    public ResponseEntity<List<Medication>> medicationsByTitle(@RequestParam String title) {
        List<Medication> lst = medicationCRUDRepository.findByTitleContainingIgnoreCase(title);
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }
    @GetMapping("/api/medication/{id}")
    public ResponseEntity<Medication> medicationById(@PathVariable Long id) {
        Medication m = myMedicationRepository.getMedicationByIdLimitReviews(id);
        if (m == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }
}
