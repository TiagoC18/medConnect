package project.medConnect.service;

import project.medConnect.entity.Appointment;
import project.medConnect.entity.Medic;
import project.medConnect.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments;
    }

    public Appointment addAppointment(Appointment appointment) {
        Appointment newAppointment = appointmentRepository.save(appointment);
        return newAppointment;
    }

    public List<Appointment> getAppointmentsBySpecialtyAndMedic(String specialty, Medic medic, Date appointmentTime){
        List<Appointment> availableAppoints = appointmentRepository.findAvailableAppointmentsForSpecialtyAndMedic(specialty, medic, appointmentTime);
        return availableAppoints;
    }
}
