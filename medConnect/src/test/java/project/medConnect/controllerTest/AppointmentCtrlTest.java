package project.medConnect.controllerTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import project.medConnect.controller.PatientController;
import project.medConnect.entity.Patient;
import project.medConnect.service.PatientService;

import project.medConnect.controller.MedicController;
import project.medConnect.entity.Medic;
import project.medConnect.service.MedicService;

import project.medConnect.controller.AppointmentController;
import project.medConnect.entity.Appointment;
import project.medConnect.service.AppointmentService;


@WebMvcTest(AppointmentController.class)
public class AppointmentCtrlTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AppointmentService appointmentService;

    @MockBean
    private MedicService medicService;

    @MockBean
    private PatientService patientService;

    @BeforeEach
    public void setUp() {

    }

    @Test
    @DisplayName("Get all appointments")
    void testGetAllAppointments() throws Exception {
        Medic medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Patient patient = new Patient("David", "Silva", new Date(1999, 07, 10) , "Male", "123456789", "123456789", "davidsilva@ua.pt");
    
        Appointment appointment1 = new Appointment(patient, "Cardiology", medic, "2024-06-08", "10h", "Scheduled");
        Appointment appointment2 = new Appointment(patient, "Cardiology", medic, "2024-07-08", "11h", "Cancelled");

        when(appointmentService.getAppointments()).thenReturn(Arrays.asList(appointment1, appointment2));
        mvc.perform(get("/api/appointment")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(status().isOk()) 
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].status", is("Scheduled"))) 
            .andExpect(jsonPath("$[1].status", is("Cancelled")));

        verify(appointmentService, times(1)).getAppointments();
    }


    @Test
    @DisplayName("Add an appointment")
    void testAddAppointment() throws Exception {
        Medic medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Patient patient = new Patient("David", "Silva", new Date(1999, 07, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt");

        Appointment appointment = new Appointment(patient, "Cardiology", medic, "2024-06-08", "10h", "Scheduled");

        when(appointmentService.addAppointment(Mockito.any(Appointment.class))).thenReturn(appointment);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        mvc.perform(post("/api/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(appointment)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status", is("Scheduled")))
                .andExpect(jsonPath("$.specialty", is("Cardiology")))
                .andExpect(jsonPath("$.appointmentTime", is("10h")));

        verify(appointmentService, times(1)).addAppointment(Mockito.any(Appointment.class));
    }

    @Test
    @DisplayName("Get booked appointments")
    void testGetBookedAppointments() throws Exception {
        Medic medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));

        when(medicService.findMedicByName("John", "Doe")).thenReturn(medic);
        when(appointmentService.getBookedAppointments("Cardiology", medic, "2024-06-08")).thenReturn(Arrays.asList("10h", "11h"));

        mvc.perform(get("/api/appointment/booked/Cardiology/John/Doe/2024-06-08")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0]", is("10h")))
                .andExpect(jsonPath("$[1]", is("11h")));

        verify(appointmentService, times(1)).getBookedAppointments("Cardiology", medic, "2024-06-08");
    }
    
}