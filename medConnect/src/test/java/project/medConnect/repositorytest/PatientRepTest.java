package project.medConnect.repositorytest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import project.medConnect.entity.Patient;
import project.medConnect.repository.PatientRepository;

@DataJpaTest
@SuppressWarnings("deprecation")
public class PatientRepTest {

    @Autowired
    private TestEntityManager entityManager;


    @Autowired
    private PatientRepository patientRepository;

    @Test
    @DisplayName("Find All Patients")
    void testFindAllPatients() {
        Patient patient1 = new Patient();
        patient1.setFirstName("John");
        patient1.setLastName("Doe");
        patient1.setDateOfBirth( new Date(1999, 12, 12));
        patient1.setGender("Male");
        patient1.setCcNumber("123456787");
        patient1.setPhoneNumber("912345678");
        patient1.setEmail("johndoe@ua.pt");
        patient1.setPassword("john123");

        Patient patient2 = new Patient();
        patient2.setFirstName("Jane");
        patient2.setLastName("Smith");
        patient2.setDateOfBirth( new Date(2002, 1, 1));
        patient2.setGender("Female");
        patient2.setCcNumber("123456787");
        patient2.setPhoneNumber("912345678");
        patient2.setEmail("janesmith@ua.pt");
        patient2.setPassword("jane123");

        entityManager.persist(patient1);
        entityManager.persist(patient2);
        entityManager.flush();

        List<Patient> patients = patientRepository.findAll();

        assertNotNull(patients);
        assertEquals(2, patients.size());
    }

    @Test
    @DisplayName("Find Patient by Id")
    void testFindPatientById() {
        Patient patient = new Patient();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setDateOfBirth( new Date(1999, 12, 12));
        patient.setGender("Male");
        patient.setCcNumber("123456787");
        patient.setPhoneNumber("912345678");
        patient.setEmail("johndoe@ua.pt");
        patient.setPassword("john123");

        entityManager.persist(patient);
        entityManager.flush();

        Patient foundPatient = patientRepository.findById(patient.getPatientId()).get();

        assertNotNull(foundPatient);
        assertEquals(patient.getPatientId(), foundPatient.getPatientId());
        assertEquals(patient.getFirstName(), foundPatient.getFirstName());
        assertEquals(patient.getLastName(), foundPatient.getLastName());
        assertEquals(patient.getDateOfBirth(), foundPatient.getDateOfBirth());
        assertEquals(patient.getGender(), foundPatient.getGender());
        assertEquals(patient.getCcNumber(), foundPatient.getCcNumber());
        assertEquals(patient.getPhoneNumber(), foundPatient.getPhoneNumber());
        assertEquals(patient.getEmail(), foundPatient.getEmail());
        assertEquals(patient.getPassword(), foundPatient.getPassword());
    }    

    @Test
    @DisplayName("Find Patient by Email")
    void testFindPatientByEmail() {
        Patient patient = new Patient();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setDateOfBirth( new Date(1999, 12, 12));
        patient.setGender("Male");
        patient.setCcNumber("123456787");
        patient.setPhoneNumber("912345678");
        patient.setEmail("johndoe@ua.pt");
        patient.setPassword("john123");

        entityManager.persist(patient);
        entityManager.flush();

        Patient foundPatient = patientRepository.findPatientByEmail(patient.getEmail());

        assertNotNull(foundPatient);
        assertEquals(patient.getPatientId(), foundPatient.getPatientId());
        assertEquals(patient.getFirstName(), foundPatient.getFirstName());
        assertEquals(patient.getLastName(), foundPatient.getLastName());
        assertEquals(patient.getDateOfBirth(), foundPatient.getDateOfBirth());
        assertEquals(patient.getGender(), foundPatient.getGender());
        assertEquals(patient.getCcNumber(), foundPatient.getCcNumber());
        assertEquals(patient.getPhoneNumber(), foundPatient.getPhoneNumber());
        assertEquals(patient.getEmail(), foundPatient.getEmail());
        assertEquals(patient.getPassword(), foundPatient.getPassword());
    }

    @Test
    @DisplayName("Check Password")
    void testCheckPassword() {
        Patient patient = new Patient();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setDateOfBirth( new Date(1999, 12, 12));
        patient.setGender("Male");
        patient.setCcNumber("123456787");
        patient.setPhoneNumber("912345678");
        patient.setEmail("johndoe@ua.pt");
        patient.setPassword("john123");

        entityManager.persist(patient);
        entityManager.flush();

        Boolean response = patientRepository.checkPassword(patient.getEmail(), patient.getPassword());

        assertNotNull(response);
        assertEquals(true, response);
    }
}
