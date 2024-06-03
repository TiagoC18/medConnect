package project.medconnect.entitytest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.*;

import project.medconnect.entity.Patient;

class PatientTest {
    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");
    }

    @AfterEach
    void tearDown() {
        patient = null;
    }
    
    @Test
    void testGetFirstName() {
        assertEquals("David", patient.getFirstName());
    }

    @Test
    void testSetFirstName() {
        patient.setFirstName("John");
        assertEquals("John", patient.getFirstName());
    }

    @Test
    void testGetLastName() {
        assertEquals("Silva", patient.getLastName());
    }

    @Test
    void testSetLastName() {
        patient.setLastName("Doe");
        assertEquals("Doe", patient.getLastName());
    }

    @Test
    void testGetDateOfBirth() {
        assertEquals(new Date(1999, 7, 10), patient.getDateOfBirth());
    }

    @Test
    void testSetDateOfBirth() {
        patient.setDateOfBirth(new Date(1999, 7, 11));
        assertEquals(new Date(1999, 7, 11), patient.getDateOfBirth());
    }

    @Test
    void testGetGender() {
        assertEquals("Male", patient.getGender());
    }

    @Test
    void testSetGender() {
        patient.setGender("Female");
        assertEquals("Female", patient.getGender());
    }

    @Test
    void testGetCcNumber() {
        assertEquals("123456789", patient.getCcNumber());
    }

    @Test
    void testSetCcNumber() {
        patient.setCcNumber("987654321");
        assertEquals("987654321", patient.getCcNumber());
    }

    @Test
    void testGetPhoneNumber() {
        assertEquals("123456789", patient.getPhoneNumber());
    }

    @Test
    void testSetPhoneNumber() {
        patient.setPhoneNumber("987654321");
        assertEquals("987654321", patient.getPhoneNumber());
    }

    @Test
    void testGetEmail() {
        assertEquals("davidsilva@ua.pt", patient.getEmail());
    }

    @Test
    void testSetEmail() {
        patient.setEmail("johndoe@ua.pt");
        assertEquals("johndoe@ua.pt", patient.getEmail());
    }

    @Test
    void testGetPassword() {
        assertEquals("password", patient.getPassword());
    }

    @Test
    void testSetPassword() {
        patient.setPassword("john123");
        assertEquals("john123", patient.getPassword());
    }
}
