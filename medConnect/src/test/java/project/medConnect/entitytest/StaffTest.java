package project.medConnect.entitytest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.*;

import project.medConnect.entity.Staff;

@SuppressWarnings("deprecation")
public class StaffTest {
    private Staff staff;

    @BeforeEach
    public void setUp() {
        staff = new Staff("Maria", "Dolores", "mariadolores@ua.pt", "mdolores123");
    }

    @AfterEach
    public void tearDown() {
        staff = null;
    }

    @Test
    public void testGetFirstName() {
        assertEquals("Maria", staff.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        staff.setFirstName("John");
        assertEquals("John", staff.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Dolores", staff.getLastName());
    }

    @Test
    public void testSetLastName() {
        staff.setLastName("Doe");
        assertEquals("Doe", staff.getLastName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("mariadolores@ua.pt", staff.getEmail());
    }

    @Test
    public void testSetEmail() {
        staff.setEmail("johndoe@ua.pt");
        assertEquals("johndoe@ua.pt", staff.getEmail());
    }

    @Test
    public void testGetPassword() {
        assertEquals("mdolores123", staff.getPassword());
    }

    @Test
    public void testSetPassword() {
        staff.setPassword("johndoe123");
        assertEquals("johndoe123", staff.getPassword());
    }
}
