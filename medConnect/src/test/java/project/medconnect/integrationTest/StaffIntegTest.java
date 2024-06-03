package project.medconnect.integrationTest;

import project.medconnect.entity.Patient;
import project.medconnect.entity.Staff;
import project.medconnect.repository.StaffRepository;

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
public class StaffIntegTest {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentIntegTest.class);

    @LocalServerPort
    private int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StaffRepository staffRepository;

    @BeforeEach
    public void setUp() {
        staffRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        staffRepository.deleteAll();
    }

    @Test
    @DisplayName("Get all staff")
    public void testGetAllStaff() {
        Staff staff1 = new Staff("Maria", "Dolores", "mariadolores@ua.pt", "mdolores123");
        Staff staff2 = new Staff("John", "Doe", "johndoe@ua.pt", "john123");

        staffRepository.save(staff1);
        staffRepository.save(staff2);

        ResponseEntity<Staff[]> responseEntity = restTemplate.getForEntity("/api/staff", Staff[].class);
        Staff[] staffs = responseEntity.getBody();

        assertThat(staffs)
            .isNotNull()
            .hasSize(2);
        assertThat(staffs).extracting(Staff::getFirstName).contains(staff1.getFirstName(), staff2.getFirstName());
        assertThat(staffs).extracting(Staff::getLastName).contains(staff1.getLastName(), staff2.getLastName());
        assertThat(staffs).extracting(Staff::getEmail).contains(staff1.getEmail(), staff2.getEmail());
        assertThat(staffs).extracting(Staff::getPassword).contains(staff1.getPassword(), staff2.getPassword());
    }

    @Test
    @DisplayName("Check staff password")
    public void testCheckStaffPassword() {
        Staff staff1 = new Staff("Maria", "Dolores", "mariadolores@ua.pt", "mdolores123");
        Staff staff2 = new Staff("John", "Doe", "johndoe@ua.pt", "john123");

        staffRepository.save(staff1);
        staffRepository.save(staff2);

        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity("/api/staff/checkPassword?email=" + staff1.getEmail() + "&password=" + staff1.getPassword(), null, Boolean.class);
        Boolean result = responseEntity.getBody();

        assertThat(result)
                .isTrue();

    }
}