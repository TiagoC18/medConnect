package project.medConnect.repositorytest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import project.medConnect.entity.Staff;
import project.medConnect.repository.StaffRepository;

@DataJpaTest
class StaffRepTest {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StaffRepository staffRepository;

    @Test
    @DisplayName("Find All Staff")
    void testFindAllStaff() {
        Staff staff1 = new Staff();
        staff1.setFirstName("Maria");
        staff1.setLastName("Dolores");
        staff1.setEmail("mariadolores@ua.pt");
        staff1.setPassword("mdolores123");

        Staff staff2 = new Staff();
        staff2.setFirstName("Alberto");
        staff2.setLastName("Rodrigues");
        staff2.setEmail("albertrodas@ua.pt");
        staff2.setPassword("souomelhor");

        entityManager.persist(staff1);
        entityManager.persist(staff2);
        entityManager.flush();

        List<Staff> staff = staffRepository.findAll();

        assertNotNull(staff);
        assertEquals(2, staff.size());
    }

    @Test
    @DisplayName("Check Password")
    void testCheckPassword() {
        Staff staff = new Staff();
        staff.setFirstName("Maria");
        staff.setLastName("Dolores");
        staff.setEmail("mariadolores@ua.pt");
        staff.setPassword("mdolores123");

        entityManager.persist(staff);
        entityManager.flush();

        boolean result = staffRepository.checkPassword(staff.getEmail(), staff.getPassword());

        assertTrue(result);
        assertEquals(true, result);
    }
}
