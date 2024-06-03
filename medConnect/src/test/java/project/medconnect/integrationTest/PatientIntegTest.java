package project.medconnect.integrationTest;

import project.medconnect.entity.Appointment;
import project.medconnect.entity.Medic;
import project.medconnect.entity.Patient;
import project.medconnect.repository.AppointmentRepository;
import project.medconnect.repository.PatientRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
public class PatientIntegTest {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentIntegTest.class);

    @LocalServerPort
    private int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    public void setUp() {
        appointmentRepository.deleteAll();
        patientRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        appointmentRepository.deleteAll();
        patientRepository.deleteAll();
    }

    @Test
    @DisplayName("Test for getting all patients")
    public void testGetAllPatients() {
        Patient patient1 = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "david123");
        Patient patient2 = new Patient("John", "Doe", new Date(1999, 3, 27), "Male", "123456789", "123456789", "johndoe@ua.pt","jonh123");

        patientRepository.save(patient1);
        patientRepository.save(patient2);
        

        ResponseEntity<Patient[]> responseEntity = restTemplate.getForEntity("/api/patient", Patient[].class);
        Patient[] patients = responseEntity.getBody();

        assertThat(patients)
            .isNotNull()
            .hasSize(2);
        assertThat(patients).extracting(Patient::getFirstName).contains(patient1.getFirstName(), patient2.getFirstName());
        assertThat(patients).extracting(Patient::getLastName).contains(patient1.getLastName(), patient2.getLastName());
        assertThat(patients).extracting(Patient::getEmail).contains(patient1.getEmail(), patient2.getEmail());
        assertThat(patients).extracting(Patient::getPassword).contains(patient1.getPassword(), patient2.getPassword());
        assertThat(patients).extracting(Patient::getGender).contains(patient1.getGender(), patient2.getGender());
        assertThat(patients).extracting(Patient::getPhoneNumber).contains(patient1.getPhoneNumber(), patient2.getPhoneNumber());
        assertThat(patients).extracting(Patient::getDateOfBirth).contains(patient1.getDateOfBirth(), patient2.getDateOfBirth());
    }

    @Test
    @DisplayName("Test for getting patient by id")
    public void testGetPatientById() {
        Patient patient1 = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "david123");
        Patient patient2 = new Patient("John", "Doe", new Date(1999, 3, 27), "Male", "123456789", "123456789", "johndoe@ua.pt","jonh123");

        patientRepository.save(patient1);
        patientRepository.save(patient2);

        ResponseEntity<Patient> responseEntity = restTemplate.getForEntity("/api/patient/" + patient1.getPatientId(), Patient.class);
        Patient patient = responseEntity.getBody();

        assertThat(patient)
            .isNotNull();
        assertThat(patient.getFirstName()).isEqualTo(patient1.getFirstName());
        assertThat(patient.getLastName()).isEqualTo(patient1.getLastName());
        assertThat(patient.getEmail()).isEqualTo(patient1.getEmail());
        assertThat(patient.getPassword()).isEqualTo(patient1.getPassword());
        assertThat(patient.getGender()).isEqualTo(patient1.getGender());
        assertThat(patient.getPhoneNumber()).isEqualTo(patient1.getPhoneNumber());
        assertThat(patient.getDateOfBirth()).isEqualTo(patient1.getDateOfBirth());

    }

    @Test
    @DisplayName("Test for getting patient by email")
    public void testGetPatientByEmail() {
        Patient patient1 = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "david123");
        Patient patient2 = new Patient("John", "Doe", new Date(1999, 3, 27), "Male", "123456789", "123456789", "johndoe@ua.pt","jonh123");

        patientRepository.save(patient1);
        patientRepository.save(patient2);

        ResponseEntity<Patient> responseEntity = restTemplate.getForEntity("/api/patient/byEmail/" + patient1.getEmail(), Patient.class);
        Patient patient = responseEntity.getBody();

        assertThat(patient)
            .isNotNull();
        assertThat(patient.getFirstName()).isEqualTo(patient1.getFirstName());
        assertThat(patient.getLastName()).isEqualTo(patient1.getLastName());
        assertThat(patient.getEmail()).isEqualTo(patient1.getEmail());
        assertThat(patient.getPassword()).isEqualTo(patient1.getPassword());
        assertThat(patient.getGender()).isEqualTo(patient1.getGender());
        assertThat(patient.getPhoneNumber()).isEqualTo(patient1.getPhoneNumber());
        assertThat(patient.getDateOfBirth()).isEqualTo(patient1.getDateOfBirth());

    }

    @Test
    @DisplayName("Test for checking password")
    public void testCheckPassword() {
        Patient patient1 = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "david123");
        Patient patient2 = new Patient("John", "Doe", new Date(1999, 3, 27), "Male", "123456789", "123456789", "johndoe@ua.pt","jonh123");

        patientRepository.save(patient1);
        patientRepository.save(patient2);

        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity("/api/patient/checkPassword?email=" + patient1.getEmail() + "&password=" + patient1.getPassword(), null, Boolean.class);
        Boolean result = responseEntity.getBody();

        assertThat(result)
            .isTrue();

    }
}

