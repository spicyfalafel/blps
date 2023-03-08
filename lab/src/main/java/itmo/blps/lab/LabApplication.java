package itmo.blps.lab;

import ch.qos.logback.classic.Logger;
import itmo.blps.lab.entity.Medication;
import itmo.blps.lab.repository.MedicationRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;
@SpringBootApplication
public class LabApplication implements CommandLineRunner {
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(LabApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(LabApplication.class, args);
	}
	@Autowired
	private MedicationRepository medicationRepository;
	@Override
	public void run(String... arg0) throws Exception {
		LOGGER.info("@@ save() call...");
		Medication medication = new Medication("med11111", "med11111");
		Medication result = medicationRepository.save(medication);
		LOGGER.info(result.toString());

		LOGGER.info("@@ findAll() call...");
		medicationRepository.findAll()
				.forEach(person -> LOGGER.info(person.toString()));

	}
}
