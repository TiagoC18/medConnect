package project.medConnect.entitytest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Arrays;

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
        medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        patient = new Patient("David", "Silva", new Date(1999, 07, 10) , "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");
    
        appointment = new Appointment(patient, "Specialty", medic, "2024-06-08", "10h", "Scheduled", null);
    }

    @AfterEach
    public void tearDown() {
        appointment = null;
        medic = null;
        patient = null;
    }

    @Test
    public void testGetPatient() {
        assertEquals(patient, appointment.getPatient());
    }

    @Test
    public void testSetPatient() {
        Patient newPatient = new Patient("David", "Silva", new Date(1999, 07, 10) , "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");
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
        Medic newMedic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        appointment.setMedic(newMedic);
        assertEquals(newMedic, appointment.getMedic());
    }

    @Test
    public void testGetAppointmentDay() {
        assertEquals("2024-06-08", appointment.getAppointmentDay());
    }

    @Test
    public void testSetAppointmentDay() {
        appointment.setAppointmentDay("2024-06-08" );
        assertEquals( "2024-06-08", appointment.getAppointmentDay());
    }

    @Test
    public void testGetAppointmentTime() {
        assertEquals("10h", appointment.getAppointmentTime());
    }

    @Test
    public void testSetAppointmentTime() {
        appointment.setAppointmentTime("11h");
        assertEquals("11h", appointment.getAppointmentTime());
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

    @Test
    public void testGetSenha() {
        assertNull(appointment.getSenha());
    }

    @Test
    public void testSetSenha() {
        appointment.setSenha(1);
        assertEquals(1, appointment.getSenha());
    }
}
