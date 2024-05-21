package project.medConnect.controller;

import project.medConnect.entity.Appointment;
import project.medConnect.entity.Medic;
import project.medConnect.service.AppointmentService;
import project.medConnect.service.MedicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private MedicService MedicService;

    @GetMapping("")
    public List<Appointment> getAppointments() {
        return appointmentService.getAppointments();
    }

    @PostMapping("")
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {
        Appointment savedAppointment = appointmentService.addAppointment(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAppointment);
    }

    @GetMapping("/booked/{specialty}/{firstName}/{lastName}/{date}")
    public List<String> getBookedAppointments(@PathVariable String specialty, @PathVariable String firstName, @PathVariable String lastName, @PathVariable String date) {

        Medic medic = MedicService.findMedicByName(firstName, lastName);

        return appointmentService.getBookedAppointments(specialty, medic, date);
    }
        
    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentsByPatient(@PathVariable Long patientId) {
        return appointmentService.getAppointmentsByPatient(patientId);
    }
}