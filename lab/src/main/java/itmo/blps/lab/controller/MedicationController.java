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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Medication>> medicationsByTitleContains(@RequestParam Optional<String> title) {
        if (title.isPresent()){
            List<Medication> lst = medicationCRUDRepository.findByTitleContainingIgnoreCase(title.get());
            return new ResponseEntity<>(lst, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/api/medication", params = "titleStarting")
    public ResponseEntity<List<Medication>> medicationsByTitleStarting(@RequestParam Optional<String> titleStarting) {
        if (titleStarting.isPresent()){
            List<Medication> lst = medicationCRUDRepository.findByTitleStartingWithIgnoreCase(titleStarting.get());
            return new ResponseEntity<>(lst, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }
    @GetMapping("/api/medication/{id}")
    public ResponseEntity<Medication> medicationById(@PathVariable Long id) {
        Medication m = myMedicationRepository.getMedicationByIdLimitReviews(id);
        if (m == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }
}
