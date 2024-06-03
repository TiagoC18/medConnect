package project.medconnect.integrationTest;

import project.medconnect.entity.Appointment;
import project.medconnect.entity.Medic;
import project.medconnect.entity.Patient;
import project.medconnect.repository.AppointmentRepository;
import project.medconnect.repository.MedicRepository;
import project.medconnect.repository.PatientRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
public class AppointmentIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentIntegrationTest.class);

    @LocalServerPort
    private int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    public void setUp() {
        appointmentRepository.deleteAll();
        medicRepository.deleteAll();
        patientRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        appointmentRepository.deleteAll();
        medicRepository.deleteAll();
        patientRepository.deleteAll();
    }

    @Test
    @DisplayName("Get all appointments")
    void testGetAllAppointments() throws Exception {
        Medic medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Patient patient = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "david123");

        medic = medicRepository.save(medic);
        patient = patientRepository.save(patient);

        Long medicId = medic.getMedicId();
        Long patientId = patient.getPatientId();

        Appointment appointment1 = new Appointment(patient, "Cardiology", medic, "2024-06-08", "10h", "Scheduled", null);
        Appointment appointment2 = new Appointment(patient, "Cardiology", medic, "2024-07-08", "11h", "Cancelled", null);

        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);

        ResponseEntity<Appointment[]> responseEntity = restTemplate.getForEntity("/api/appointment", Appointment[].class);
        Appointment[] appointments = responseEntity.getBody();

        assertThat(appointments)
            .isNotNull()
            .hasSize(2);
        assertThat(appointments).extracting(Appointment::getAppointmentDay).containsOnly("2024-06-08", "2024-07-08");
        assertThat(appointments).extracting(Appointment::getAppointmentTime).containsOnly("10h", "11h");
        assertThat(appointments).extracting(Appointment::getStatus).containsOnly("Scheduled", "Cancelled");
        assertThat(appointments).extracting(Appointment::getSpecialty).containsOnly("Cardiology", "Cardiology");
        assertThat(appointments).extracting(a -> a.getMedic().getMedicId()).containsOnly(medicId, medicId);
        assertThat(appointments).extracting(a -> a.getPatient().getPatientId()).containsOnly(patientId, patientId);
        assertThat(appointments).extracting(Appointment::getSenha).containsOnly(null, null);
    }


    @Test
    @DisplayName("Add Appointment")
    void testAddAppointment() throws Exception {
        Medic medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Patient patient = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "david123");

        medic = medicRepository.save(medic);
        patient = patientRepository.save(patient);

        Long medicId = medic.getMedicId();
        Long patientId = patient.getPatientId();

        Appointment appointment1 = new Appointment(patient, "Cardiology", medic, "2024-06-08", "10h", "Scheduled", null);

        Appointment appointment = restTemplate.postForObject("/api/appointment", appointment1, Appointment.class);

        assertThat(appointment).extracting(Appointment::getAppointmentDay).isEqualTo("2024-06-08");
        assertThat(appointment).extracting(Appointment::getAppointmentTime).isEqualTo("10h");
        assertThat(appointment).extracting(Appointment::getStatus).isEqualTo("Scheduled");
        assertThat(appointment).extracting(Appointment::getSpecialty).isEqualTo("Cardiology");
        assertThat(appointment).extracting(a -> a.getMedic().getMedicId()).isEqualTo(medicId);
        assertThat(appointment).extracting(a -> a.getPatient().getPatientId()).isEqualTo(patientId);
        assertThat(appointment).extracting(Appointment::getSenha).isNull();

    }


    @Test
    @DisplayName("Get Booked Appointments")
    void testGetBookedAppointments() throws Exception {
        Medic medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Patient patient = new Patient("David", "Silva", new Date(1999 - 1900, 6, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "david123");
    
        medic = medicRepository.save(medic);
        patient = patientRepository.save(patient);
    
        Appointment appointment1 = new Appointment(patient, "Cardiology", medic, "2024-06-08", "10h", "Scheduled", null);
        Appointment appointment2 = new Appointment(patient, "Cardiology", medic, "2024-06-09", "10h", "Scheduled", null);
    
        appointmentRepository.saveAll(Arrays.asList(appointment1, appointment2));
        
        ResponseEntity<List<String>> responseEntity = restTemplate.exchange(
            "/api/appointment/booked/Cardiology/John/Doe/2024-06-08",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<String>>() {}
        );
        List<String> bookedAppointments = responseEntity.getBody();

        assertThat(bookedAppointments)
            .isNotNull()
            .hasSize(1)
            .containsOnly("10h");
    }
}