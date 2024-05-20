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

    public List<String> getBookedAppointments(String specialty, Medic medic, String day) {
        List<String> bookedAppointments = appointmentRepository.findBookedAppointments(specialty, medic, day);
        return bookedAppointments;
    }

}
