package project.medconnect.integrationTest;

import project.medconnect.entity.Appointment;
import project.medconnect.entity.Medic;
import project.medconnect.repository.AppointmentRepository;
import project.medconnect.repository.MedicRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
public class MedicIntegTest {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentIntegTest.class);

    @LocalServerPort
    private int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    public void setUp() {
        appointmentRepository.deleteAll();
        medicRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        appointmentRepository.deleteAll();
        medicRepository.deleteAll();
    }

    @Test
    @DisplayName("Test for getting all medics")
    public void testGetAllMedics() {
        Medic medic1 = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Medic medic2 = new Medic("Jane", "Smith", "janesmith@ua.pt", "912345678", "Dermatology", Arrays.asList("10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h", "18h"));
        Medic medic3 = new Medic("David", "Silva", "davidsilva@ua.pt", "912345678", "Dermatology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        
        medicRepository.save(medic1);
        medicRepository.save(medic2);
        medicRepository.save(medic3);

        ResponseEntity<Medic[]> responseEntity = restTemplate.getForEntity("/api/medic", Medic[].class);
        Medic[] medics = responseEntity.getBody();

        assertThat(medics)
            .isNotNull()
            .hasSize(3);
        assertThat(medics).extracting(Medic::getFirstName).contains(medic1.getFirstName(), medic2.getFirstName(), medic3.getFirstName());
        assertThat(medics).extracting(Medic::getLastName).contains(medic1.getLastName(), medic2.getLastName(), medic3.getLastName());
        assertThat(medics).extracting(Medic::getEmail).contains(medic1.getEmail(), medic2.getEmail(), medic3.getEmail());
        assertThat(medics).extracting(Medic::getSpecialty).contains(medic1.getSpecialty(), medic2.getSpecialty(), medic3.getSpecialty());
        assertThat(medics).extracting(Medic::getPhoneNumber).contains(medic1.getPhoneNumber(), medic2.getPhoneNumber(), medic3.getPhoneNumber());
        assertThat(medics).extracting(Medic::getServiceTime).contains(medic1.getServiceTime(), medic2.getServiceTime(), medic3.getServiceTime());
    }    

    @Test
    @DisplayName("Test for getting a medic by id")
    public void testGetMedicById() {
        Medic medic1 = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Medic medic2 = new Medic("Jane", "Smith", "janesmith@ua.pt", "912345678", "Dermatology", Arrays.asList("10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h", "18h"));
        Medic medic3 = new Medic("David", "Silva", "davidsilva@ua.pt", "912345678", "Dermatology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        
        medicRepository.save(medic1);
        medicRepository.save(medic2);
        medicRepository.save(medic3);
        
        ResponseEntity<Medic> responseEntity1 = restTemplate.getForEntity("/api/medic/" + medic1.getMedicId(), Medic.class);
        Medic medic = responseEntity1.getBody();

        assertThat(medic)
            .isNotNull();
        assertThat(medic.getFirstName()).isEqualTo(medic1.getFirstName());
        assertThat(medic.getLastName()).isEqualTo(medic1.getLastName());
        assertThat(medic.getEmail()).isEqualTo(medic1.getEmail());
        assertThat(medic.getSpecialty()).isEqualTo(medic1.getSpecialty());
        assertThat(medic.getPhoneNumber()).isEqualTo(medic1.getPhoneNumber());
        assertThat(medic.getServiceTime()).isEqualTo(medic1.getServiceTime());
    }

    @Test
    @DisplayName("Test for getting a medic by specialty")
    public void testGetMedicBySpecialty() {
        Medic medic1 = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Medic medic2 = new Medic("Jane", "Smith", "janesmith@ua.pt", "912345678", "Dermatology", Arrays.asList("10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h", "18h"));
        Medic medic3 = new Medic("David", "Silva", "davidsilva@ua.pt", "912345678", "Dermatology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        
        medicRepository.save(medic1);
        medicRepository.save(medic2);
        medicRepository.save(medic3);

        ResponseEntity<Medic[]> responseEntity = restTemplate.getForEntity("/api/medic/specialty/" + medic1.getSpecialty(), Medic[].class);
        Medic[] medics = responseEntity.getBody();

        assertThat(medics)
            .isNotNull()
            .hasSize(1);
        assertThat(medics).extracting(Medic::getFirstName).contains(medic1.getFirstName());
        assertThat(medics).extracting(Medic::getLastName).contains(medic1.getLastName());
        assertThat(medics).extracting(Medic::getEmail).contains(medic1.getEmail()); 
        assertThat(medics).extracting(Medic::getSpecialty).contains(medic1.getSpecialty());
        assertThat(medics).extracting(Medic::getPhoneNumber).contains(medic1.getPhoneNumber());
        assertThat(medics).extracting(Medic::getServiceTime).contains(medic1.getServiceTime());
    }

    @Test
    @DisplayName("Test for getting service time of a medic")
    public void testGetServiceTime() {
        Medic medic1 = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Medic medic2 = new Medic("Jane", "Smith", "janesmith@ua.pt", "912345678", "Dermatology", Arrays.asList("10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h", "18h"));
        Medic medic3 = new Medic("David", "Silva", "davidsilva@ua.pt", "912345678", "Dermatology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        
        medicRepository.save(medic1);
        medicRepository.save(medic2);
        medicRepository.save(medic3);

        ResponseEntity<List<String>> responseEntity = restTemplate.exchange(
            "/api/medic/" + medic1.getMedicId() + "/serviceTime",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<String>>() {}
        );
    
        List<String> serviceTime = responseEntity.getBody();
    
        assertThat(serviceTime)
            .isNotNull()
            .hasSize(9);
        assertThat(serviceTime).containsExactlyElementsOf(medic1.getServiceTime());
    }

    @Test
    @DisplayName("Test find medic by name")
    public void testFindMedicByName() {
        Medic medic1 = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Medic medic2 = new Medic("Jane", "Smith", "janesmith@ua.pt", "912345678", "Dermatology", Arrays.asList("10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h", "18h"));
        Medic medic3 = new Medic("David", "Silva", "davidsilva@ua.pt", "912345678", "Dermatology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        
        medicRepository.save(medic1);
        medicRepository.save(medic2);
        medicRepository.save(medic3);

        ResponseEntity<Medic> responseEntity = restTemplate.getForEntity("/api/medic/name/" + medic1.getFirstName() + "/" + medic1.getLastName(), Medic.class);
        Medic medic = responseEntity.getBody();

        assertThat(medic)
        .isNotNull();
    assertThat(medic.getFirstName()).isEqualTo(medic1.getFirstName());
    assertThat(medic.getLastName()).isEqualTo(medic1.getLastName());
    assertThat(medic.getEmail()).isEqualTo(medic1.getEmail());
    assertThat(medic.getSpecialty()).isEqualTo(medic1.getSpecialty());
    assertThat(medic.getPhoneNumber()).isEqualTo(medic1.getPhoneNumber());
    assertThat(medic.getServiceTime()).isEqualTo(medic1.getServiceTime());
}

}
