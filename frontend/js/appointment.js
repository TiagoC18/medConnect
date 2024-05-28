async function checkAvailability() {
    const date = document.getElementById('appointment-date').value;
    const time = document.getElementById('appointment-time').value;
    const specialty = document.getElementById('specialty').value;

    if (date && time && specialty) {
        try {
            const response = await fetch(`/api/medic/specialty/${specialty}`);
            if (response.ok) {
                const availableMedics = await response.json();
                const medicSelect = document.getElementById('medic');

                // Clear previous options
                medicSelect.innerHTML = '';

                availableMedics.forEach(medic => {
                    const option = document.createElement('option');
                    option.value = medic.id;
                    option.textContent = medic.firstName + ' ' + medic.lastName;
                    medicSelect.appendChild(option);
                });

                document.getElementById('medics-availability').style.display = 'block';
            } else {
                alert('No medics available for the selected specialty.');
            }
        } catch (error) {
            console.error('Error fetching available medics:', error);
            alert('An error occurred while fetching available medics. Please try again.');
        }
    } else {
        alert('Please fill in all fields.');
    }
}

async function bookAppointment() {
    const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';

    if (!isLoggedIn) {
        alert('You need to be logged in to book an appointment.');
        window.location.href = '#!/pageLogin';
        return;
    }

    const date = document.getElementById('appointment-date').value;
    const time = document.getElementById('appointment-time').value;
    const medicId = document.getElementById('medic').value;
    const email = localStorage.getItem('email'); // Assuming email is stored in localStorage after login

    if (date && time && medicId && email) {
        try {
            const patientResponse = await fetch(`/api/patient/byEmail/${email}`);
            if (patientResponse.ok) {
                const patient = await patientResponse.json();

                const appointment = {
                    date,
                    time,
                    medicId,
                    patientId: patient.patientId
                };

                const response = await fetch('/api/appointment', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(appointment)
                });

                if (response.ok) {
                    alert('Appointment booked successfully!');
                } else {
                    alert('Failed to book appointment.');
                }
            } else {
                alert('Failed to find patient information.');
            }
        } catch (error) {
            console.error('Error booking appointment:', error);
            alert('An error occurred while booking the appointment. Please try again.');
        }
    } else {
        alert('Please fill in all fields.');
    }
}

// Check login status on page load
window.onload = checkLogin;


/*functions for static info

function checkAvailability() {
    const date = document.getElementById('appointment-date').value;
    const time = document.getElementById('appointment-time').value;
    const specialty = document.getElementById('specialty').value;

    if (date && time && specialty) {
        // Simulate fetching available medics for the given date and specialty
        const availableMedics = getAvailableMedics(date, time, specialty);
        const medicSelect = document.getElementById('medic');

        // Clear previous options
        medicSelect.innerHTML = '';

        availableMedics.forEach(medic => {
            const option = document.createElement('option');
            option.value = medic.id;
            option.textContent = medic.name;
            medicSelect.appendChild(option);
        });

        document.getElementById('medics-availability').style.display = 'block';
    } else {
        alert('Please fill in all fields.');
    }
}

function getAvailableMedics(date, time, specialty) {
    // Simulate fetching medics from a database or API
    // This is just sample data
    const medics = [
        { id: 1, name: 'Dr. John Doe', specialty: 'cardiology' },
        { id: 2, name: 'Dr. Jane Smith', specialty: 'dermatology' },
        { id: 3, name: 'Dr. Emily Johnson', specialty: 'neurology' },
        { id: 4, name: 'Dr. Michael Brown', specialty: 'pediatrics' },
        { id: 5, name: 'Dr. Sarah Davis', specialty: 'orthopedics' },
        { id: 6, name: 'Dr. Robert Wilson', specialty: 'gynecology' }
    ];

    // Filter medics based on the selected specialty
    return medics.filter(medic => medic.specialty === specialty);
}

function bookAppointment() {
    const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';

    if (!isLoggedIn) {
        alert('You need to be logged in to book an appointment.');
        window.location.href = '#!/pageLogin';
        return;
    }

    const date = document.getElementById('appointment-date').value;
    const time = document.getElementById('appointment-time').value;
    const medic = document.getElementById('medic').value;

    if (date && time && medic) {
        alert(`Appointment booked with ${document.querySelector('#medic option:checked').textContent} on ${date} at ${time}.`);
        // Here you can add code to save the appointment details
    } else {
        alert('Please select a medic.');
    }
}

// Check login status on page load
window.onload = checkLogin;
*/