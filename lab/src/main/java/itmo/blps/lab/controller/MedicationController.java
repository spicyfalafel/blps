package itmo.blps.lab.controller;

import itmo.blps.lab.entity.Medication;
import itmo.blps.lab.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MedicationController {
    private MedicationRepository medicationRepository;
    @Autowired
    public void setMedicationRepository(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }
    @GetMapping("/api/medication")
    public List<Medication> allMedicationsTitleAndId() {
        return medicationRepository.findAllReturnTitleAndId();
    }
    @GetMapping(value = "/api/medication", params = "title")
    public List<Medication> medicationsByTitle(@RequestParam String title) {
        return medicationRepository.findByTitle(title);
    }
    @GetMapping("/api/medication/:id")
    public Medication medicationById(@PathVariable Long id) {
        Optional<Medication> m = medicationRepository.findById(id);
        return m.orElse(null);
    }
}
