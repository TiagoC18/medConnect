package project.medConnect.entitytest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.*;

import project.medConnect.entity.Medic;

class MedicTest {
    private Medic medic;

    @BeforeEach
    void setUp() {
        medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
    }

    @AfterEach
    void tearDown() {
        medic = null;
    }

    @Test
    void testGetFirstName() {
        assertEquals("John", medic.getFirstName());
    }

    @Test
    void testSetFirstName() {
        medic.setFirstName("Jane");
        assertEquals("Jane", medic.getFirstName());
    }

    @Test
    void testGetLastName() {
        assertEquals("Doe", medic.getLastName());
    }

    @Test
    void testSetLastName() {
        medic.setLastName("Smith");
        assertEquals("Smith", medic.getLastName());
    }

    @Test
    void testGetEmail() {
        assertEquals("johndoe@ua.pt", medic.getEmail());
    }

    @Test
    void testSetEmail() {
        medic.setEmail("janesmith@ua.pt");
        assertEquals("janesmith@ua.pt", medic.getEmail());
    }

    @Test
    void testGetPhoneNumber() {
        assertEquals("912345678", medic.getPhoneNumber());
    }

    @Test
    void testSetPhoneNumber() {
        medic.setPhoneNumber("987654321");
        assertEquals("987654321", medic.getPhoneNumber());
    }

    @Test
    void testGetSpecialty() {
        assertEquals("Cardiology", medic.getSpecialty());
    }

    @Test
    void testSetSpecialty() {
        medic.setSpecialty("Dermatology");
        assertEquals("Dermatology", medic.getSpecialty());
    }

    @Test
    void testGetServiceTime() {
        assertEquals(Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"), medic.getServiceTime());
    }

    @Test
    void testSetServiceTime() {
        medic.setServiceTime(Arrays.asList("10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h", "18h"));
        assertEquals(Arrays.asList("10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h", "18h"), medic.getServiceTime());
    }
}
