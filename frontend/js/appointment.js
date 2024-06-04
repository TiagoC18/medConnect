async function checkMedic() {
    const date = document.getElementById('appointment-date').value;
    const specialty = document.getElementById('specialty').value;

    if (date && specialty) {
        try {
            const response = await fetch(`http://deti-tqs-14.ua.pt:8080/api/medic/specialty/${specialty}`);
            if (response.ok) {
                const specialtyMedic = await response.json();
                console.log(specialtyMedic);
                const medicSelect = document.getElementById('medic');

                medicSelect.innerHTML = '';

                if (specialtyMedic.length > 0) {
                    specialtyMedic.forEach(medic => {
                        const option = document.createElement('option');
                        option.value = `${medic.firstName} ${medic.lastName}`; // Define o valor como nome completo
                        option.textContent = medic.firstName + ' ' + medic.lastName;
                        medicSelect.appendChild(option);
                    });

                    document.getElementById('appointment-date').disabled = true;
                    document.getElementById('specialty').disabled = true;
                    document.getElementById('checkMedics').style.display = 'none';
                    document.getElementById('medics-availability').style.display = 'block';
                    document.getElementById('checkHours').style.display = 'block';
                } else {
                    alert('No medics available for the selected specialty.');
                    document.getElementById('medics-availability').style.display = 'none';
                    document.getElementById('checkHours').style.display = 'none';
                }
            } else {
                alert('No medics available for the selected specialty.');
                document.getElementById('medics-availability').style.display = 'none';
                document.getElementById('checkHours').style.display = 'none';
            }
        } catch (error) {
            console.error('Error fetching available medics:', error);
            alert('An error occurred while fetching available medics. Please try again.');
            document.getElementById('medics-availability').style.display = 'none';
            document.getElementById('checkHours').style.display = 'none';
        }
    } else {
        alert('Please fill in all fields.');
    }
}

async function checkHours() {
    const date = document.getElementById('appointment-date').value;
    const specialty = document.getElementById('specialty').value;
    const medic = document.getElementById('medic').value;
    const timeAvailable = document.getElementById('time-available');
    const timeSelect = document.getElementById('time');

    if (date && specialty && medic) {
        try {
            // Dividir o nome completo apenas no último espaço
            const lastSpaceIndex = medic.lastIndexOf(' ');
            const firstName = medic.substring(0, lastSpaceIndex).trim();
            const lastName = medic.substring(lastSpaceIndex + 1).trim();

            // Buscar o médico pelo nome
            const medicResponse = await fetch(`http://deti-tqs-14.ua.pt:8080/api/medic/name/${encodeURIComponent(firstName)}/${encodeURIComponent(lastName)}`);
            if (medicResponse.ok) {
                const medicData = await medicResponse.json();

                // Usar o ID do médico para buscar os horários de atendimento
                const serviceTimeResponse = await fetch(`http://deti-tqs-14.ua.pt:8080/api/medic/${medicData.medicId}/serviceTime`);
                if (serviceTimeResponse.ok) {
                    const serviceTimes = await serviceTimeResponse.json();

                    // Buscar horários já reservados
                    const bookedResponse = await fetch(`http://deti-tqs-14.ua.pt:8080/api/appointment/booked/${encodeURIComponent(specialty)}/${encodeURIComponent(firstName)}/${encodeURIComponent(lastName)}/${encodeURIComponent(date)}`);
                    if (bookedResponse.ok) {
                        const bookedTimes = await bookedResponse.json();

                        // Limpar a lista de horários anteriores
                        timeSelect.innerHTML = '';

                        // Verificar se há horários disponíveis excluindo os horários já reservados
                        const availableTimes = serviceTimes.filter(time => !bookedTimes.includes(time));

                        if (availableTimes.length > 0) {
                            // Mostrar os horários disponíveis
                            availableTimes.forEach(time => {
                                const option = document.createElement('option');
                                option.value = time;
                                option.textContent = time;
                                timeSelect.appendChild(option);
                            });

                            // Exibir o seletor de horários

                            document.getElementById('medic').disabled = true;
                            document.getElementById('checkHours').style.display = 'none';
                            document.getElementById('bookAppointment').style.display = 'block';
                            timeAvailable.style.display = 'block';
                        } else {
                            alert('No available service times for the selected medic.');
                            timeAvailable.style.display = 'none';
                        }
                    } else {
                        alert('Error fetching booked appointments.');
                        timeAvailable.style.display = 'none';
                    }
                } else {
                    alert('No available service times for the selected medic.');
                    timeAvailable.style.display = 'none';
                }
            } else {
                alert('Medic not found.');
                timeAvailable.style.display = 'none';
            }
        } catch (error) {
            console.error('Error fetching medic details or service times:', error);
            alert('An error occurred while fetching medic details or service times. Please try again.');
            timeAvailable.style.display = 'none';
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
    const time = document.getElementById('time').value;
    const medic = document.getElementById('medic').value;
    const specialty = document.getElementById('specialty').value;
    const email = localStorage.getItem('email'); // Assuming email is stored in localStorage after login

    if (date && time && medic && email && specialty) {
        try {
            // Dividir o nome completo apenas no último espaço
            const lastSpaceIndex = medic.lastIndexOf(' ');
            const firstName = medic.substring(0, lastSpaceIndex).trim();
            const lastName = medic.substring(lastSpaceIndex + 1).trim();

            // Buscar o médico pelo nome
            const medicResponse = await fetch(`http://deti-tqs-14.ua.pt:8080/api/medic/name/${encodeURIComponent(firstName)}/${encodeURIComponent(lastName)}`);
            const medicData = await medicResponse.json();

            const patientResponse = await fetch(`http://deti-tqs-14.ua.pt:8080/api/patient/byEmail/${email}`);
            if (patientResponse.ok) {
                const patient = await patientResponse.json();

                const appointment = {
                    appointmentDay: date,
                    appointmentTime: time,
                    medic: { medicId: medicData.medicId }, // Assume que o objeto medic precisa do campo medicId
                    patient: { patientId: patient.patientId }, // Assume que o objeto patient precisa do campo patientId
                    specialty: specialty, // Incluindo a especialidade
                    status: 'Scheduled' // Status padrão da consulta
                };

                const response = await fetch('http://deti-tqs-14.ua.pt:8080/api/appointment', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(appointment)
                });

                if (response.ok) {
                    window.location.href = '#!/pageAgenda';
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