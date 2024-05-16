package project.medConnect.entitytest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import project.medConnect.entity.Medic;

public class MedicTest {
    private Medic medic;

    @BeforeEach
    public void setUp() {
        medic = new Medic(1L, "John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", "9:00-17:00");
    }

    @AfterEach
    public void tearDown() {
        medic = null;
    }

    @Test
    public void testGetMedicId() {
        assertEquals(1L, medic.getMedicId());
    }

    @Test
    public void testSetMedicId() {
        medic.setMedicId(2L);
        assertEquals(2L, medic.getMedicId());
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
        assertEquals("9:00-17:00", medic.getServiceTime());
    }

    @Test
    public void testSetServiceTime() {
        medic.setServiceTime("10:00-18:00");
        assertEquals("10:00-18:00", medic.getServiceTime());
    }

}
