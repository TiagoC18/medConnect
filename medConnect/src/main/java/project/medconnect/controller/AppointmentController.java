package project.medconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import project.medconnect.entity.Appointment;
import project.medconnect.entity.Medic;
import project.medconnect.service.AppointmentService;
import project.medconnect.service.MedicService;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final MedicService medicService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, MedicService medicService) {
        this.appointmentService = appointmentService;
        this.medicService = medicService;
    }

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

        Medic medic = medicService.findMedicByName(firstName, lastName);

        return appointmentService.getBookedAppointments(specialty, medic, date);
    }
        
    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentsByPatient(@PathVariable Long patientId) {
        return appointmentService.getAppointmentsByPatient(patientId);
    }

    @GetMapping("/scheduled")
    public List<Appointment> getAppointmentsScheduled() {
        return appointmentService.getAppointmentsScheduled();
    }

    @GetMapping("/waiting")
    public List<Appointment> getAppointmentsWaiting() {
        return appointmentService.getAppointmentsWaiting();
    }

    @GetMapping("/called")
    public List<Appointment> getAppointmentsCalled() {
        return appointmentService.getAppointmentsCalled();
    }

    @GetMapping("/done")
    public List<Appointment> getAppointmentsDone() {
        return appointmentService.getAppointmentsDone();
    }

    @PutMapping("/{appointmentId}/{newStatus}")
    public Appointment updateAppointmentStatus(@PathVariable Long appointmentId, @PathVariable String newStatus) {
        return appointmentService.updateAppointmentStatus(appointmentId, newStatus);
    }

    @DeleteMapping("/delete/{appointmentId}")
    public void deleteAppointment(@PathVariable Long appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
    }
}
