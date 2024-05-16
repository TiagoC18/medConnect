package project.medConnect.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @NotNull(message = "Patient is required")
    private Patient patient;

    @NotNull(message = "Specialty is required")
    private String specialty;

    @ManyToOne
    @JoinColumn(name = "medic_id")
    @NotNull(message = "Medic is required")
    private Medic medic;

    @NotNull(message = "Appointment time is required")
    @Future(message = "Appointment time must be in the future")
    private Date appointmentTime;

    @NotNull(message = "Status is required")
    private String status;

    public Appointment() {
    }

    public Appointment(Long appointmentId, Patient patient, String specialty, Medic medic, Date appointmentTime, String status) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.specialty = specialty;
        this.medic = medic;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}