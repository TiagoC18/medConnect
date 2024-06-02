package project.medconnect.integrationTest;

import project.medconnect.entity.Appointment;
import project.medconnect.entity.Medic;
import project.medconnect.entity.Patient;
import project.medconnect.repository.AppointmentRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
public class AppointmentIntegrationTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    public void setUp() {
        appointmentRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        appointmentRepository.deleteAll();
    }

    @Test
    @DisplayName("Get all appointments")
    void testGetAllAppointments() throws Exception {
        Medic medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Patient patient = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "david123");
    
        Appointment appointment1 = new Appointment(patient, "Cardiology", medic, "2024-06-08", "10h", "Scheduled", null);
        Appointment appointment2 = new Appointment(patient, "Cardiology", medic, "2024-07-08", "11h", "Cancelled", null);

        appointment1.setAppointmentId(1L);
        appointment2.setAppointmentId(2L);

        appointment1 = appointmentRepository.save(appointment1);
        appointment2 = appointmentRepository.save(appointment2);

        ResponseEntity<Appointment[]> responseEntity = restTemplate.getForEntity("/api/appointment", Appointment[].class);
        Appointment[] appointments = responseEntity.getBody();

        assertThat(appointments)
            .isNotNull()
            .hasSize(2);
        assertThat(appointments).extracting(Appointment::getAppointmentDay).contains("2024-06-08", "2024-07-08");
        assertThat(appointments).extracting(Appointment::getAppointmentTime).contains("10h", "11h");
        assertThat(appointments).extracting(Appointment::getStatus).contains("Scheduled", "Cancelled");
        assertThat(appointments).extracting(Appointment::getSpecialty).contains("Cardiology", "Cardiology");
        assertThat(appointments).extracting(Appointment::getAppointmentId).contains(1L, 2L);
        assertThat(appointments).extracting(Appointment::getMedic).contains(medic, medic);
        assertThat(appointments).extracting(Appointment::getPatient).contains(patient, patient);
        assertThat(appointments).extracting(Appointment::getSenha).contains(null, null);
    }

    @Test
    @DisplayName("Add Appointment")
    void testAddAppointment() throws Exception {
        Medic medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Patient patient = new Patient("David", "Silva", new Date(1999 - 1900, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "david123");
        Appointment appointment1 = new Appointment(patient, "Cardiology", medic, "2024-06-08", "10h", "Scheduled", null);
        appointment1.setAppointmentId(1L);
        Appointment appointment = restTemplate.postForObject("/api/appointment", appointment1, Appointment.class);



        assertThat(appointment).extracting(Appointment::getAppointmentDay).isEqualTo("2024-06-08");
        assertThat(appointment).extracting(Appointment::getAppointmentTime).isEqualTo("10h");
        assertThat(appointment).extracting(Appointment::getStatus).isEqualTo("Scheduled");
        assertThat(appointment).extracting(Appointment::getSpecialty).isEqualTo("Cardiology");
        assertThat(appointment).extracting(Appointment::getAppointmentId).isEqualTo(1L);
        assertThat(appointment).extracting(Appointment::getMedic).isEqualTo(medic);
        assertThat(appointment).extracting(Appointment::getPatient).isEqualTo(patient);
        assertThat(appointment).extracting(Appointment::getSenha).isNull();

    }


    // @Test
    // @DisplayName("Get Booked Appointments")
    // void testGetBookedAppointments() throws Exception {
    //     Medic medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
    //     Patient patient = new Patient("David", "Silva", new Date(1999 - 1900, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "david123");

    //     // Add appointments to the database
    //     Appointment appointment1 = new Appointment(patient, "Cardiology", medic, "2024-06-08", "10h", "Scheduled", null);
    //     Appointment appointment2 = new Appointment(patient, "Cardiology", medic, "2024-06-08", "11h", "Scheduled", null);

    //     appointment1.setAppointmentId(1L);
    //     appointment2.setAppointmentId(2L);

    //     appointment1 = appointmentRepository.save(appointment1);
    //     appointment2 = appointmentRepository.save(appointment2);

    //     restTemplate.getForEntity("/api/appointment/booked/"+ appointment1.getAppointmentId() + "/" + appointment1.getSpecialty() + "/" + appointment1.get(), Reserva.class);

    // }
}
