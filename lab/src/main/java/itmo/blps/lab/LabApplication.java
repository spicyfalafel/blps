package itmo.blps.lab;

import ch.qos.logback.classic.Logger;
import itmo.blps.lab.dto.Medication;
import itmo.blps.lab.repository.medication.MedicationCRUDRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.SpringVersion;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class LabApplication implements CommandLineRunner {
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(LabApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(LabApplication.class, args);
	}
	@Autowired
	private MedicationCRUDRepository medicationRepository;
	@Override
	public void run(String... arg0) throws Exception {
		LOGGER.info("spring version is " + SpringVersion.getVersion());
		LOGGER.info("@@ save() call...");
		Medication medication = new Medication("Акт уголь", "Если болит живот",
				"6 таблеток в день", "Если болит живот", "черный язык",
				false, "держать в прохладном месте без солнца не давать детям");
		medicationRepository.save(medication);
	}
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("itmo.blps.lab")).build();
	}
}
