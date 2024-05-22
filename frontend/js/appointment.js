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
