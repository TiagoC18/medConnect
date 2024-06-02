package project.medConnect;

import project.medConnect.entity.Medic;
import project.medConnect.repository.AppointmentRepository;
import project.medConnect.repository.MedicRepository;

import project.medConnect.entity.Patient;
import project.medConnect.repository.PatientRepository;

import project.medConnect.entity.Appointment;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Deprecated
@Component
public class DataInitializer implements CommandLineRunner {

    private final MedicRepository medicRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public DataInitializer(MedicRepository medicRepository, PatientRepository patientRepository,
            AppointmentRepository appointmentRepository) {
        this.medicRepository = medicRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void run(String... args) {
        // Criação de médicos
        final String cardiology = "Cardiology";
        final String pediatrician = "Pediatrician";
        final String masculino = "Masculino";
        final String feminino = "Feminino";
        Medic medic1 = new Medic("Dr. John", "Doe", "johndoe@hospital.com", "123456789", cardiology,
                Arrays.asList("9h", "10h", "11h", "14h", "15h"));
        medicRepository.save(medic1);

        Medic medic2 = new Medic("Dr. Jane", "Smith", "janesmith@hospital.com", "987654321", pediatrician,
                Arrays.asList("10h", "11h", "13h", "15h", "17h"));
        medicRepository.save(medic2);

        // Criação de pacientes
        Patient patient1 = new Patient("Alice", "Johnson", new Date(1990, 5, 15), feminino, "123456789",
                "987654321", "alice@example.com", "senha123");
        patientRepository.save(patient1);

        Patient patient2 = new Patient("Bob", "Smith", new Date(1985, 8, 25), masculino, "234567890", "876543219",
                "bob@example.com", "senha456");
        patientRepository.save(patient2);

        Patient patient3 = new Patient("Charlie", "Brown", new Date(1982, 10, 8), masculino, "345678901",
                "765432198", "charlie@example.com", "senha789");
        patientRepository.save(patient3);

        Patient patient4 = new Patient("Diana", "Lee", new Date(1978, 4, 20), feminino, "456789012", "654321987",
                "diana@example.com", "senhaabc");
        patientRepository.save(patient4);

        Patient patient5 = new Patient("Eva", "Garcia", new Date(1995, 12, 10), feminino, "567890123",
                "543219876", "eva@example.com", "senhadef");
        patientRepository.save(patient5);

        Patient patient6 = new Patient("Frank", "Miller", new Date(1989, 2, 3), masculino, "678901234",
                "432198765", "frank@example.com", "senhaghi");
        patientRepository.save(patient6);

        // Criação de consultas
        Appointment appointment1 = new Appointment(patient1, cardiology, medic1, "2024-06-15", "10h", "Done", 6);
        appointmentRepository.save(appointment1);

        Appointment appointment2 = new Appointment(patient2, pediatrician, medic2, "2024-06-16", "11h", "Called", 1);
        appointmentRepository.save(appointment2);

        Appointment appointment3 = new Appointment(patient3, cardiology, medic1, "2024-06-17", "9h", "Waiting", 2);
        appointmentRepository.save(appointment3);

        Appointment appointment4 = new Appointment(patient4, pediatrician, medic2, "2024-06-18", "10h", "Called", 5);
        appointmentRepository.save(appointment4);

        Appointment appointment5 = new Appointment(patient5, cardiology, medic1, "2024-06-19", "11h", "Waiting", 4);
        appointmentRepository.save(appointment5);

        Appointment appointment6 = new Appointment(patient6, pediatrician, medic2, "2024-06-20", "9h", "Done", null);
        appointmentRepository.save(appointment6);

    }
}
