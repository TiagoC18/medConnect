package project.medConnect.controllerTest;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

import project.medConnect.controller.MedicController;
import project.medConnect.entity.Medic;
import project.medConnect.service.MedicService;

import static org.hamcrest.Matchers.hasSize;
@WebMvcTest(MedicController.class)
public class MedicControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MedicService medicService;


    @BeforeEach
    public void setUp() {

    }

    @Test
    @DisplayName("Get all medics")
    public void testGetAllMedics() throws Exception {
        Medic medic1 = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Medic medic2 = new Medic( "Jane", "Smith", "janesmith@ua.pt", "912345678", "Dermatology", Arrays.asList("10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h", "18h"));
        Medic medic3 = new Medic("David", "Silva", "davidsilva@ua.pt", "912345678", "Dermatology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        
        when(medicService.getAllMedics()).thenReturn(Arrays.asList(medic1, medic2, medic3));
        mvc.perform(get("/api/medic")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(status().isOk()) 
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].firstName", is("John"))) 
            .andExpect(jsonPath("$[1].specialty", is("Dermatology"))); 

        verify(medicService, times(1)).getAllMedics();
    }

    @Test
    @DisplayName("Get medic by id")
    public void testGetMedicById() throws Exception {
        Medic medic1 = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        when(medicService.getMedicById(1L)).thenReturn(medic1);

        mvc.perform(get("/api/medic/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName", is("John")))
            .andExpect(jsonPath("$.specialty", is("Cardiology")));
        
        verify(medicService, times(1)).getMedicById(1L);
    
    }

    @Test
    @DisplayName("Get medic by specialty")
    public void testGetMedicBySpecialty() throws Exception {

        Medic medic1 = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Medic medic2 = new Medic("Jane", "Smith", "janesmith@ua.pt", "912348678", "Cardiology", Arrays.asList("10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h", "18h"));
        when(medicService.getMedicBySpecialty("Cardiology")).thenReturn(Arrays.asList(medic1, medic2));

        mvc.perform(get("/api/medic/specialty/Cardiology")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[0].specialty", is("Cardiology")));

        verify(medicService, times(1)).getMedicBySpecialty("Cardiology");
    }



    
}
