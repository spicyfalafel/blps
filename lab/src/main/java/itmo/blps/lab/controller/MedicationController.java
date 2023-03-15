package itmo.blps.lab.controller;

import itmo.blps.lab.entity.Medication;
import itmo.blps.lab.entity.MedicationIdTitle;
import itmo.blps.lab.repository.medication.MedicationCRUDRepository;
import itmo.blps.lab.repository.medication.MyMedicationRepository;
import itmo.blps.lab.services.MedicationManager;
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
    private final MedicationCRUDRepository medicationCRUDRepository;
    private final MyMedicationRepository myMedicationRepository;
    private final MedicationManager medicationManager;

    @Autowired
    public MedicationController(MedicationCRUDRepository medicationCRUDRepository,
                                MyMedicationRepository myMedicationRepository, MedicationManager medicationManager) {
        this.medicationCRUDRepository = medicationCRUDRepository;
        this.myMedicationRepository = myMedicationRepository;
        this.medicationManager = medicationManager;
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
    @PostMapping("/api/medication")
    @ResponseBody
    public ResponseEntity<Medication> createMedication(@RequestBody Medication medication) {
        if (medicationManager.validateMedication(medication)){
            Medication m = medicationCRUDRepository.save(medication);
            return new ResponseEntity<>(m, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/api/medication/{id}")
    @ResponseBody
    public ResponseEntity<Medication> deleteMedication(@PathVariable Long id) {
        if (medicationCRUDRepository.existsById(id)) {
            medicationCRUDRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/medication/{id}")
    @ResponseBody
    public ResponseEntity<Medication> updateMedication(@PathVariable Long id, @RequestBody Medication medication) {
        if (medicationCRUDRepository.existsById(id)) {
            medicationCRUDRepository.save(medication);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
