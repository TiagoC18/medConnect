function addPatient() {
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const ccNumber = document.getElementById('ccNumber').value;

    const patient = {
        firstName,
        lastName,
        ccNumber
    };

    // For now, we will just log the data to the console
    // You will need to send this data to the backend using an AJAX request or fetch API
    console.log(patient);

    // Example of sending data to the backend and then fetching appointments
    fetch('YOUR_BACKEND_ENDPOINT/patients', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(patient)
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert('Patient added to waiting list!');
            fetchAppointments(ccNumber);
        } else {
            alert('Failed to add patient: ' + data.message);
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Failed to add patient. Please try again.');
    });
}

function fetchAppointments(ccNumber) {
    // Simulate fetching appointments for the patient
    // Replace with real API call
    const appointments = getAppointments(ccNumber);

    const appointmentsContainer = document.getElementById('appointments');
    const appointmentsList = document.getElementById('appointments-list');

    appointmentsList.innerHTML = ''; // Clear previous appointments

    appointments.forEach(appointment => {
        const appointmentDiv = document.createElement('div');
        appointmentDiv.className = 'appointment-item';
        appointmentDiv.innerHTML = `
            <p>Date: ${appointment.date}</p>
            <p>Time: ${appointment.time}</p>
            <p>Specialty: ${appointment.specialty}</p>
            <p>Doctor: ${appointment.doctor}</p>
        `;
        appointmentsList.appendChild(appointmentDiv);
    });

    appointmentsContainer.style.display = 'block';
}

function getAppointments(ccNumber) {
    // Simulate fetching appointments from a database or API
    // This is just sample data
    return [
        { date: '2024-08-13', time: '14:00', specialty: 'Neurology', doctor: 'Dr. Asdrubal' },
        { date: '2024-08-14', time: '15:00', specialty: 'Cardiology', doctor: 'Dr. John Doe' },
        { date: '2024-08-15', time: '16:00', specialty: 'Dermatology', doctor: 'Dr. Jane Smith' }
    ];
}

function loadWaitingList() {
    const waitingListContainer = document.getElementById('waiting-list-container');
    waitingListContainer.innerHTML = '';

    // Simulate fetching waiting list from a backend
    const waitingList = getWaitingList();

    waitingList.forEach(patient => {
        const patientDiv = document.createElement('div');
        patientDiv.className = 'waiting-list-item';
        patientDiv.innerHTML = `
            <p>Name: ${patient.firstName} ${patient.lastName}</p>
            <p>Date of Birth: ${patient.dateOfBirth}</p>
            <p>Gender: ${patient.gender}</p>
            <p>CC Number: ${patient.ccNumber}</p>
            <p>Phone Number: ${patient.phoneNumber}</p>
            <p>Email: ${patient.email}</p>
        `;
        waitingListContainer.appendChild(patientDiv);
    });
}

function getWaitingList() {
    // Simulate fetching waiting list from a database or API
    // This is just sample data
    return [
        { firstName: 'John', lastName: 'Doe', dateOfBirth: '1990-01-01', gender: 'Male', ccNumber: '123456', phoneNumber: '123456789', email: 'john.doe@example.com' },
        { firstName: 'Jane', lastName: 'Doe', dateOfBirth: '1992-02-02', gender: 'Female', ccNumber: '654321', phoneNumber: '987654321', email: 'jane.doe@example.com' }
    ];
}

// Load waiting list on page load
window.onload = function() {
    checkLogin();
    loadWaitingList();
};
