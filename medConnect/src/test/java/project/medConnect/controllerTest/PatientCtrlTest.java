package project.medConnect.controllerTest;

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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import project.medConnect.controller.PatientController; 
import project.medConnect.entity.Patient;
import project.medConnect.service.PatientService;

@Deprecated
@WebMvcTest(PatientController.class)
public class PatientCtrlTest {
    
        @Autowired
        private MockMvc mvc;
    
        @MockBean
        private PatientService patientService; 

        @BeforeEach
        public void setUp() {
    
        }

        @Test
        @DisplayName("Get all patients")
        public void testGetAllPatients() throws Exception {
            Patient patient1 = new Patient("David", "Silva", new Date(1999, 07, 10) , "Male", "123456789", "123456789", "davidsilva@ua.pt");
            Patient patient2 = new Patient("John", "Doe", new Date(1999, 03, 27) , "Male", "123456789", "123456789", "johndoe@ua.pt");

            when(patientService.getAllPatients()).thenReturn(Arrays.asList(patient1, patient2));

            mvc.perform(get("/api/patient")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is("David"))) 
                .andExpect(jsonPath("$[1].email", is("johndoe@ua.pt")));

            verify(patientService, times(1)).getAllPatients();

        }

        @Test
        @DisplayName("Get patient by id")
        public void testGetPatientById() throws Exception {
            Patient patient1 = new Patient("David", "Silva", new Date(1999, 07, 10) , "Male", "123456789", "123456789", "davidsilva@ua.pt");
            when(patientService.getPatientById(1L)).thenReturn(patient1);

            mvc.perform(get("/api/patient/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("David")));

            verify(patientService, times(1)).getPatientById(1L);

        }
    
}
