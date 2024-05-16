package project.medConnect.entitytest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.*;

import project.medConnect.entity.Patient;

@SuppressWarnings("deprecation")
public class PatientTest {
    private Patient patient;

    @BeforeEach
    public void setUp() {
        patient = new Patient("David", "Silva", new Date(1999, 07, 10) , "Male", "123456789", "123456789", "davidsilva@ua.pt");
    }

    @AfterEach
    public void tearDown() {
        patient = null;
    }
    
    @Test
    public void testGetFirstName() {
        assertEquals("David", patient.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        patient.setFirstName("John");
        assertEquals("John", patient.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Silva", patient.getLastName());
    }

    @Test
    public void testSetLastName() {
        patient.setLastName("Doe");
        assertEquals("Doe", patient.getLastName());
    }

    @Test
    public void testGetDateOfBirth() {
        assertEquals(new Date(1999, 07, 10), patient.getDateOfBirth());
    }

    @Test
    public void testSetDateOfBirth() {
        patient.setDateOfBirth(new Date(1999, 07, 11));
        assertEquals(new Date(1999, 07, 11), patient.getDateOfBirth());
    }

    @Test
    public void testGetGender() {
        assertEquals("Male", patient.getGender());
    }

    @Test
    public void testSetGender() {
        patient.setGender("Female");
        assertEquals("Female", patient.getGender());
    }

    @Test
    public void testGetCcNumber() {
        assertEquals("123456789", patient.getCcNumber());
    }

    @Test
    public void testSetCcNumber() {
        patient.setCcNumber("987654321");
        assertEquals("987654321", patient.getCcNumber());
    }

    @Test
    public void testGetPhoneNumber() {
        assertEquals("123456789", patient.getPhoneNumber());
    }

    @Test
    public void testSetPhoneNumber() {
        patient.setPhoneNumber("987654321");
        assertEquals("987654321", patient.getPhoneNumber());
    }

    @Test
    public void testGetEmail() {
        assertEquals("davidsilva@ua.pt", patient.getEmail());
    }

    @Test
    public void testSetEmail() {
        patient.setEmail("johndoe@ua.pt");
        assertEquals("johndoe@ua.pt", patient.getEmail());
    }

}