package project.medConnect.service;

import project.medConnect.model.Appointment;
import project.medConnect.model.Specialty;
import project.medConnect.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> findAvailableAppointments(Specialty specialty, Date start, Date end) {
        return appointmentRepository.findBySpecialtyAndAppointmentTimeBetween(specialty, start, end);
    }

    public Appointment bookAppointment(@Valid Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
}
