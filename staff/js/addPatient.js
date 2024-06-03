function fetchPatientAppointments() {
    const emailStaff = document.getElementById('emailStaff').value;

    fetch(`http://localhost:8080/api/patient/byEmail/${emailStaff}`)
        .then(response => response.json())
        .then(patient => {
            if (patient) {
                fetchAppointments(patient.patientId);
            } else {
                alert('Patient not found.');
            }
        })
        .catch(error => console.error('Error fetching patient:', error));
}

function fetchAppointments(patientId) {
    fetch(`http://localhost:8080/api/appointment/patient/${patientId}`)
        .then(response => response.json())
        .then(appointments => {
            const appointmentsList = document.getElementById('appointments-list');

            appointmentsList.innerHTML = ''; // Clear previous appointments

            appointments.forEach(appointment => {
                const appointmentDiv = document.createElement('div');
                appointmentDiv.className = 'appointment-item';
                appointmentDiv.innerHTML = `
                    <p>Date: ${appointment.date}</p>
                    <p>Time: ${appointment.time}</p>
                    <p>Specialty: ${appointment.specialty}</p>
                    <p>Doctor: ${appointment.medic.firstName} ${appointment.medic.lastName}</p>
                    <button onclick="markAsWaiting(${appointment.id})">Mark as Waiting</button>
                `;
                appointmentsList.appendChild(appointmentDiv);
            });

            // Show the modal
            const modal = document.getElementById('appointmentsModal');
            modal.style.display = 'block';
        })
        .catch(error => console.error('Error fetching appointments:', error));
}

function markAsWaiting(appointmentId) {
    fetch(`http://localhost:8080/api/appointment/${appointmentId}/Waiting`, {
        method: 'PUT'
    })
    .then(response => response.json())
    .then(() => {
        alert('Appointment status updated to Waiting.');
        // Redirect to manageWaitingList page
        window.location.href = 'manageWaitingList.html';
    })
    .catch(error => console.error('Error updating appointment status:', error));
}

function closeModal() {
    const modal = document.getElementById('appointmentsModal');
    modal.style.display = 'none';
}

// Close the modal if the user clicks outside of it
window.onclick = function(event) {
    const modal = document.getElementById('appointmentsModal');
    if (event.target === modal) {
        modal.style.display = 'none';
    }
}

// Load waiting list on page load
window.onload = function() {
    // Any initial setup if needed
};
