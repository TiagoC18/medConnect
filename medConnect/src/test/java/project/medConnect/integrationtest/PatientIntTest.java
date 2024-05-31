package project.medConnect.integrationtest;

import project.medConnect.entity.Patient;
import project.medConnect.repository.PatientRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "../../application.properties")
class PatientIntTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    public void setUp() {
        patientRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        patientRepository.deleteAll();
    }

    @Test
    @DisplayName("Test Find All Patients")
    void testFindAllPatients() {
        Patient patient1 = new Patient("David", "Silva", new Date(1999, 07, 10) , "Male", "123456789", "123456789", "davidsilva@ua.pt", "david123");
        Patient patient2 = new Patient("John", "Doe", new Date(1999, 03, 27) , "Male", "123456789", "123456789", "johndoe@ua.pt","jonh123");

        patientRepository.save(patient1);
        patientRepository.save(patient2);

        restTemplate.getForEntity("/api/patients", Patient[].class);

        assertThat(patientRepository.findAll()).hasSize(2).contains(patient1, patient2);
    }

    @Test
    @DisplayName("Test Find Patient By Id")
    void testFindPatientById() {
        Patient patient1 = new Patient("David", "Silva", new Date(1999, 07, 10) , "Male", "123456789", "123456789", "davidsilva@ua.pt", "david123");
        Patient patient2 = new Patient("John", "Doe", new Date(1999, 03, 27) , "Male", "123456789", "123456789", "johndoe@ua.pt","jonh123");

        patientRepository.save(patient1);
        patientRepository.save(patient2);

        restTemplate.getForEntity("/api/patients/" + patient1.getPatientId(), Patient.class);

        Patient patient = restTemplate.getForObject("/api/patients/" + patient1.getPatientId(), Patient.class);

        assertThat(patient).isEqualTo(patient1);
    }

    @Test
    @DisplayName("Test Save Patient")
    void testSavePatient() {
        Patient patient1 = new Patient("David", "Silva", new Date(1999, 07, 10) , "Male", "123456789", "123456789", "davidsilva@ua.pt", "david123");
        Patient patient2 = new Patient("John", "Doe", new Date(1999, 03, 27) , "Male", "123456789", "123456789", "johndoe@ua.pt","jonh123");

        patientRepository.save(patient1);
        patientRepository.save(patient2);

        restTemplate.postForObject("/api/patients", patient1, Patient.class);
        restTemplate.postForObject("/api/patients", patient2, Patient.class);

        assertThat(patientRepository.findAll()).hasSize(2).contains(patient1, patient2);

    }

    @Test
    @DisplayName("Get patient by email")
    void testGetPatientByEmail() {
        Patient patient1 = new Patient("David", "Silva", new Date(1999, 07, 10) , "Male", "123456789", "123456789", "davidsilva@ua.pt", "david123");
        Patient patient2 = new Patient("John", "Doe", new Date(1999, 03, 27) , "Male", "123456789", "123456789", "johndoe@ua.pt","jonh123");

        patientRepository.save(patient1);
        patientRepository.save(patient2);

        restTemplate.getForEntity("/api/patients/byEmail/" + patient1.getEmail(), Patient.class);

        Patient patient = restTemplate.getForObject("/api/patients/byEmail/" + patient1.getEmail(), Patient.class);

        assertThat(patient).isEqualTo(patient1);
    }

    
}
