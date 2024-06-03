package project.medconnect.entitytest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import project.medconnect.entity.Staff;

class StaffTest {
    private Staff staff;

    @BeforeEach
    void setUp() {
        staff = new Staff("Maria", "Dolores", "mariadolores@ua.pt", "mdolores123");
    }

    @AfterEach
    void tearDown() {
        staff = null;
    }

    @Test
    void testGetFirstName() {
        assertEquals("Maria", staff.getFirstName());
    }

    @Test
    void testSetFirstName() {
        staff.setFirstName("John");
        assertEquals("John", staff.getFirstName());
    }

    @Test
    void testGetLastName() {
        assertEquals("Dolores", staff.getLastName());
    }

    @Test
    void testSetLastName() {
        staff.setLastName("Doe");
        assertEquals("Doe", staff.getLastName());
    }

    @Test
    void testGetEmail() {
        assertEquals("mariadolores@ua.pt", staff.getEmail());
    }

    @Test
    void testSetEmail() {
        staff.setEmail("johndoe@ua.pt");
        assertEquals("johndoe@ua.pt", staff.getEmail());
    }

    @Test
    void testGetPassword() {
        assertEquals("mdolores123", staff.getPassword());
    }

    @Test
    void testSetPassword() {
        staff.setPassword("johndoe123");
        assertEquals("johndoe123", staff.getPassword());
    }
}
