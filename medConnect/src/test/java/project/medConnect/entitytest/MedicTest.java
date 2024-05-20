package project.medConnect.entitytest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.*;

import project.medConnect.entity.Medic;

public class MedicTest {
    private Medic medic;

    @BeforeEach
    public void setUp() {
        medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
    }

    @AfterEach
    public void tearDown() {
        medic = null;
    }

    @Test
    public void testGetFirstName() {
        assertEquals("John", medic.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        medic.setFirstName("Jane");
        assertEquals("Jane", medic.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Doe", medic.getLastName());
    }

    @Test
    public void testSetLastName() {
        medic.setLastName("Smith");
        assertEquals("Smith", medic.getLastName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("johndoe@ua.pt", medic.getEmail());
    }

    @Test
    public void testSetEmail() {
        medic.setEmail("janesmith@ua.pt");
        assertEquals("janesmith@ua.pt", medic.getEmail());
    }

    @Test
    public void testGetPhoneNumber() {
        assertEquals("912345678", medic.getPhoneNumber());
    }

    @Test
    public void testSetPhoneNumber() {
        medic.setPhoneNumber("987654321");
        assertEquals("987654321", medic.getPhoneNumber());
    }

    @Test
    public void testGetSpecialty() {
        assertEquals("Cardiology", medic.getSpecialty());
    }

    @Test
    public void testSetSpecialty() {
        medic.setSpecialty("Dermatology");
        assertEquals("Dermatology", medic.getSpecialty());
    }

    @Test
    public void testGetServiceTime() {
        assertEquals(Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"), medic.getServiceTime());
    }

    @Test
    public void testSetServiceTime() {
        medic.setServiceTime(Arrays.asList("10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h", "18h"));
        assertEquals(Arrays.asList("10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h", "18h"), medic.getServiceTime());
    }

}
