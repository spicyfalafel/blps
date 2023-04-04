package itmo.blps.lab.controller;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.annotation.JsonView;
import itmo.blps.lab.LabApplication;
import itmo.blps.lab.dto.Medication;
import itmo.blps.lab.services.MedicationService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@Validated
public class MedicationController {
    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(LabApplication.class);
    @GetMapping(value = "/api/medication")
    @JsonView(Medication.TitleAndId.class)
    public ResponseEntity<?> medicationsByTitle(
            @RequestParam(required = false, name = "title") String title,
            @RequestParam(required = false, name = "titleStarting") String titleStarting,
            @RequestParam(required = false, name = "page", defaultValue = "1") @Min(1) @Max(1000) Integer page,
            @RequestParam(required = false, name = "size", defaultValue = "5") @Min(1) @Max(1000) Integer size) {
        Pageable pageable = PageRequest.of(page-1, size);
        LOGGER.info("page " + page);
        LOGGER.info("size " + page);
        if (title != null) {
            return ok(medicationService.medicationsByTitleContains(pageable, title));
        } else if (titleStarting != null) {
            return ok(medicationService.medicationsByTitleStarting(pageable, titleStarting));
        } else  {
            List<?> medications = medicationService.allMedicationsTitleAndId(pageable);
            if (medications.isEmpty()) {
                return new ResponseEntity<>("zero medications at page " + page, HttpStatus.NOT_FOUND);
            } else {
                return ok(medications);
            }
        }

    }

    @GetMapping("/api/medication/{id}")
    public ResponseEntity<?> medicationById(@PathVariable Long id) {
        return ok(medicationService.getMedicationByIdLimitReviews(id));
    }
    @PostMapping(value = "/api/medication", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> createMedication(@Validated(Medication.New.class) @RequestBody Medication medication) {
        return ok(medicationService.createMedication(medication));
    }

    @DeleteMapping(value = "/api/medication/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
        return new ResponseEntity<>("deleted medication with id " + id, HttpStatus.OK);
    }

    @PutMapping("/api/medication/{id}")
    @ResponseBody
    public ResponseEntity<String> updateMedication(@PathVariable Long id,
                                                       @Validated(Medication.Exist.class) @RequestBody Medication medication) {
        medicationService.updateMedication(id, medication);
        return new ResponseEntity<>("saved", HttpStatus.OK);
    }
}
