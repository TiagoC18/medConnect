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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import project.medConnect.controller.StaffController;
import project.medConnect.entity.Staff;
import project.medConnect.service.StaffService;

@Deprecated
@WebMvcTest(StaffController.class)
public class StaffCtrlTest {
        
    @Autowired
    private MockMvc mvc;

    @MockBean
    private StaffService staffService; 

    @BeforeEach
    public void setUp() {

    }
    
    @Test
    @DisplayName("Get all staff")
    void testGetAllStaff() throws Exception {
        Staff staff1 = new Staff("Maria", "Dolores", "mariadolores@ua.pt", "mdolores123");
        Staff staff2 = new Staff("John", "Doe", "johndoe@ua.pt", "john123");

        when(staffService.getAllStaff()).thenReturn(java.util.Arrays.asList(staff1, staff2));

        mvc.perform(get("/api/staff")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(status().isOk()) 
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].firstName", is(staff1.getFirstName())))
            .andExpect(jsonPath("$[1].firstName", is(staff2.getFirstName())));

        verify(staffService, times(1)).getAllStaff();
    }

    @Test
    @DisplayName("Check password")
    void testCheckPassword() throws Exception {
        when(staffService.checkPassword("mariadolores@ua.pt", "mdolores123")).thenReturn(Boolean.TRUE);

        mvc.perform(post("/api/staff/checkPassword")
        .param("email", "mariadolores@ua.pt")
        .param("password", "mdolores123")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", is(true)));

    }

}
