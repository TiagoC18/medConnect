package project.medConnect.controller;

import project.medConnect.entity.Appointment;
import project.medConnect.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("")
    public List<Appointment> getAppointments() {
        return appointmentService.getAppointments();
    }

    @PostMapping("")
    public Appointment addAppointment(@RequestBody Appointment appointment) {
        return appointmentService.addAppointment(appointment);
    }
}