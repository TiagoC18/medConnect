package project.medConnect.entitytest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.*;

import project.medConnect.entity.Appointment;
import project.medConnect.entity.Medic;
import project.medConnect.entity.Patient;

@SuppressWarnings("deprecation")
public class AppointmentTest {
    private Appointment appointment;
    private Medic medic;
    private Patient patient;

    @BeforeEach
    public void setUp() {
        medic = new Medic(1L, "John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", "9:00-17:00");
        patient = new Patient(1L, "David", "Silva", new Date(1999, 07, 10) , "Male", "123456789", "123456789", "davidsilva@ua.pt");
    
        appointment = new Appointment(1L, patient, "Specialty", medic, new Date(2024, 6, 8), "Scheduled");
    }

    @AfterEach
    public void tearDown() {
        appointment = null;
        medic = null;
        patient = null;
    }

    @Test
    public void testGetAppointmentId() {
        assertEquals(1L, appointment.getAppointmentId());
    }

    @Test
    public void testSetAppointmentId() {
        appointment.setAppointmentId(2L);
        assertEquals(2L, appointment.getAppointmentId());
    }

    @Test
    public void testGetPatient() {
        assertEquals(patient, appointment.getPatient());
    }

    @Test
    public void testSetPatient() {
        Patient newPatient = new Patient(1L, "David", "Silva", new Date(1999, 07, 10) , "Male", "123456789", "123456789", "davidsilva@ua.pt");
        appointment.setPatient(newPatient);
        assertEquals(newPatient, appointment.getPatient());
    }

    @Test
    public void testGetSpecialty() {
        assertEquals("Specialty", appointment.getSpecialty());
    }

    @Test
    public void testSetSpecialty() {
        appointment.setSpecialty("New Specialty");
        assertEquals("New Specialty", appointment.getSpecialty());
    }

    @Test
    public void testGetMedic() {
        assertEquals(medic, appointment.getMedic());
    }

    @Test
    public void testSetMedic() {
        Medic newMedic = new Medic(1L, "John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", "9:00-17:00");
        appointment.setMedic(newMedic);
        assertEquals(newMedic, appointment.getMedic());
    }

    @Test
    public void testGetAppointmentTime() {
        assertEquals(new Date(2024, 6, 8), appointment.getAppointmentTime());
    }

    @Test
    public void testSetAppointmentTime() {
        appointment.setAppointmentTime( new Date(2024, 6, 17) );
        assertEquals( new Date(2024, 6, 17) , appointment.getAppointmentTime());
    }

    @Test
    public void testGetStatus() {
        assertEquals("Scheduled", appointment.getStatus());
    }

    @Test
    public void testSetStatus() {
        appointment.setStatus("Cancelled");
        assertEquals("Cancelled", appointment.getStatus());
    }
}
