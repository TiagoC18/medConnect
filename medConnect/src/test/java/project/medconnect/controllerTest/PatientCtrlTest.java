package project.medconnect.controllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import project.medconnect.controller.PatientController;
import project.medconnect.entity.Patient;
import project.medconnect.service.PatientService;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.Date;

@Deprecated
@WebMvcTest(PatientController.class)
class PatientCtrlTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PatientService patientService; 

    @BeforeEach
    void setUp() {
        // No setup required as of now
    }

    @Test
    @DisplayName("Get all patients")
    void testGetAllPatients() throws Exception {
        Patient patient1 = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "david123");
        Patient patient2 = new Patient("John", "Doe", new Date(1999, 3, 27), "Male", "123456789", "123456789", "johndoe@ua.pt","jonh123");

        when(patientService.getAllPatients()).thenReturn(Arrays.asList(patient1, patient2));

        mvc.perform(get("/api/patient")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].firstName", is("David"))) 
            .andExpect(jsonPath("$[1].email", is("johndoe@ua.pt")));

        verify(patientService, times(1)).getAllPatients();
    }


    @Test
    @DisplayName("Add patient")
    void testAddPatient() throws Exception {
        doNothing().when(patientService).addPatient(any(Patient.class));

        mvc.perform(post("/api/patient")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n" +
                "    \"firstName\": \"David\",\n" +
                "    \"lastName\": \"Silva\",\n" +
                "    \"dateOfBirth\": \"1999-07-10\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"phoneNumber\": \"123456789\",\n" +
                "    \"nif\": \"123456789\",\n" +
                "    \"email\": \"davidsilva@ua.pt\",\n" +
                "    \"password\": \"password\"\n" +
                "}"))
        .andExpect(status().isCreated());

        verify(patientService, times(1)).addPatient(any(Patient.class));
    }

    @Test
    @DisplayName("Get patient by id")
    void testGetPatientById() throws Exception {
        Patient patient1 = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");
        when(patientService.getPatientById(1L)).thenReturn(patient1);

        mvc.perform(get("/api/patient/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName", is("David")));

        verify(patientService, times(1)).getPatientById(1L);
    }

    @Test
    @DisplayName("Get patient by email")
    void testGetPatientByEmail() throws Exception {
        Patient patient1 = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");
        when(patientService.getPatientByEmail("davidsilva@ua.pt")).thenReturn(patient1);

        mvc.perform(get("/api/patient/byEmail/davidsilva@ua.pt")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName", is("David")));
        
        verify(patientService, times(1)).getPatientByEmail("davidsilva@ua.pt");
    }

    @Test
    @DisplayName("Check password")
    void testCheckPassword() throws Exception {
        when(patientService.checkPassword("davidsilva@ua.pt", "password")).thenReturn(true);

        mvc.perform(post("/api/patient/checkPassword")
            .param("email", "davidsilva@ua.pt")
            .param("password", "password")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", is(true)));
    }
}
