package project.medConnect.repositorytest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import project.medConnect.entity.Medic;
import project.medConnect.repository.MedicRepository;

@DataJpaTest
public class MedicRepTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MedicRepository medicRepository;

    @Test
    @DisplayName("Find All Medics")
    public void testFindAllMedics() {
        Medic medic1 = new Medic();
        medic1.setFirstName("John");
        medic1.setLastName("Doe");
        medic1.setEmail("johndoe@ua.pt");
        medic1.setPhoneNumber("912345678");
        medic1.setSpecialty("Cardiology");
        medic1.setServiceTime("9:00-17:00");

        Medic medic2 = new Medic();
        medic2.setFirstName("Jane");
        medic2.setLastName("Smith");
        medic2.setEmail("janesmith@ua.pt");
        medic2.setPhoneNumber("912345678");
        medic2.setSpecialty("Dermatology");
        medic2.setServiceTime("10:00-18:00");

        entityManager.persist(medic1);
        entityManager.persist(medic2);
        entityManager.flush(); 

        List<Medic> medics = medicRepository.findAll();

        assertNotNull(medics);
        assertEquals(2, medics.size());
    }

    @Test
    @DisplayName("Find Medic by Id")
    public void testFindMedicById() {
        Medic medic = new Medic();
        medic.setFirstName("John");
        medic.setLastName("Doe");
        medic.setEmail("johndoe@ua.pt");
        medic.setPhoneNumber("912345678");
        medic.setSpecialty("Cardiology");
        medic.setServiceTime("9:00-17:00");

        entityManager.persist(medic);
        entityManager.flush();

        Medic foundMedic = medicRepository.findById(medic.getMedicId()).get(); 
        
        assertNotNull(foundMedic);
        assertEquals(medic.getMedicId(), foundMedic.getMedicId());
        assertEquals(medic.getFirstName(), foundMedic.getFirstName());
        assertEquals(medic.getLastName(), foundMedic.getLastName());
        assertEquals(medic.getEmail(), foundMedic.getEmail());
        assertEquals(medic.getPhoneNumber(), foundMedic.getPhoneNumber());
        assertEquals(medic.getSpecialty(), foundMedic.getSpecialty());
        assertEquals(medic.getServiceTime(), foundMedic.getServiceTime());
    }

    @Test
    @DisplayName("Find Medic by Specialty")
    public void testFindMedicBySpecialty() {
        Medic medic1 = new Medic();
        medic1.setFirstName("John");
        medic1.setLastName("Doe");
        medic1.setEmail("johndoe@ua.pt");
        medic1.setPhoneNumber("912345678");
        medic1.setSpecialty("Cardiology");
        medic1.setServiceTime("9:00-17:00");

        Medic medic2 = new Medic();
        medic2.setFirstName("Jane");
        medic2.setLastName("Smith");
        medic2.setEmail("janesmith@ua.pt");
        medic2.setPhoneNumber("912345678");
        medic2.setSpecialty("Dermatology");
        medic2.setServiceTime("10:00-18:00");

        entityManager.persist(medic1);
        entityManager.persist(medic2);
        entityManager.flush();

        List<Medic> medics = medicRepository.findMedicBySpecialty("Cardiology");

        assertNotNull(medics);
        assertEquals(1, medics.size());
    }
}

