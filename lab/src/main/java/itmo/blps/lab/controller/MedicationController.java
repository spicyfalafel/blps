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
    @Autowired
    private MedicationRepository medicationRepository;
    @GetMapping("/api/medication")
    public List<Medication> medicationsByTitle(@RequestParam String title) {
        if (title == null)
            return (List<Medication>) medicationRepository.findAll();
        return medicationRepository.findByTitle(title);
    }
    @GetMapping("/api/medication/:id")
    public Medication medicationById(@PathVariable Long id) {
        Optional<Medication> m = medicationRepository.findById(id);
        return m.orElse(null);
    }

//    @PostMapping("/api/medication")
//    public Medication addmedication(@RequestBody Medication medication) {
//        return medicationRepository.save(medication);
//    }
}
