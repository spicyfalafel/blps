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

	public static void main(String[] args) {
		SpringApplication.run(LabApplication.class, args);
	}
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(LabApplication.class);

	@Autowired
	private MedicationRepository medicationRepository;
	@Autowired
	private DatabaseSeeder dbSeeder;
	@Override
	public void run(String... arg0) throws Exception {

		LOGGER.info("@@ Inserting Data....");
		dbSeeder.insertData();

		LOGGER.info("@@ findAll() call...");
		medicationRepository.findAll()
				.forEach(person -> LOGGER.info(person.toString()));

		LOGGER.info("@@ findById() call...");
		Optional<Medication> optionalMedication = medicationRepository.findById(1L);
		optionalMedication.ifPresent(person -> LOGGER.info(person.toString()));

		LOGGER.info("@@ save() call...");
		Medication medication = new Medication("med11111", "med11111");
		Medication result = medicationRepository.save(medication);
		LOGGER.info(result.toString());

		LOGGER.info("@@ delete");
		optionalMedication.ifPresent(person -> medicationRepository.delete(person));

		LOGGER.info("@@ findAll() call...");
		medicationRepository.findAll()
				.forEach(person -> LOGGER.info(person.toString()));

		LOGGER.info("@@ findByFirstName() call...");
		medicationRepository.findByTitle("med11111")
				.forEach(med -> LOGGER.info(med.toString()));

	}
}
