package basry.app;

import java.util.Date;

import basry.app.dao.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import basry.app.entities.Patient;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {
	@Autowired
    PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		patientRepository.save(new Patient(null, "Karim", new Date(), false, 50));
		patientRepository.save(new Patient(null, "Fatima", new Date(), false, 7));
		patientRepository.save(new Patient(null, "Bilal", new Date(), false, 20));
		patientRepository.save(new Patient(null, "Khadija", new Date(), false, 20));

		patientRepository.findAll().forEach(p -> {
			System.out.println(p.getName());
		});
	}

}
