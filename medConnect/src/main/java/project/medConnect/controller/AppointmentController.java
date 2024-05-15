package project.medConnect.controller;

import project.medConnect.entity.Appointment;
import project.medConnect.entity.Medic;
import project.medConnect.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @GetMapping("/available")
    public List<Appointment> getAppointmentsBySpecialtyAndMedic(@PathVariable String specialty, @PathVariable Medic medic, @RequestParam Date appointmentTime) {
        return appointmentService.getAppointmentsBySpecialtyAndMedic(specialty, medic, appointmentTime);
    }

}