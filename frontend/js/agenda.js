async function checkLogin() {
    const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
    if (isLoggedIn) {
        document.getElementById('menuAgenda').style.display = 'block';
        document.getElementById('loginButton').style.display = 'none';
        document.getElementById('logoutButton').style.display = 'block';
    } else {
        document.getElementById('menuAgenda').style.display = 'none';
        document.getElementById('loginButton').style.display = 'block';
        document.getElementById('logoutButton').style.display = 'none';
    }
}

async function loadAppointments() {
    const appointmentsContainer = document.getElementById('appointments-container');
    if (!appointmentsContainer) return;  // Exit if not on the appointments page

    appointmentsContainer.innerHTML = '';

    const email = localStorage.getItem('email');
    if (!email) {
        alert('You need to be logged in to view your appointments.');
        return;
    }

    try {
        const patientResponse = await fetch(`/api/patient/byEmail/${email}`);
        if (patientResponse.ok) {
            const patient = await patientResponse.json();
            const response = await fetch(`/api/appointment/patient/${patient.patientId}`);
            if (response.ok) {
                const appointments = await response.json();

                appointments.forEach(appointment => {
                    const appointmentDiv = document.createElement('div');
                    appointmentDiv.className = 'col1 padBot2';

                    const date = new Date(appointment.date);
                    const dateDiv = document.createElement('div');
                    dateDiv.className = '_date';
                    dateDiv.innerHTML = `<span>${date.getDate()}<br>${date.toLocaleString('default', { month: 'short' })}</span>`;
                    appointmentDiv.appendChild(dateDiv);

                    const detailsDiv = document.createElement('div');
                    detailsDiv.className = 'col3 marTop1';
                    detailsDiv.innerHTML = `<h3 class="padNull"><a href="#!/pageMore" class="_link3">${appointment.specialty}</a></h3>
                                            <p class="padNull">${appointment.medic.firstName} ${appointment.medic.lastName}</p>`;
                    appointmentDiv.appendChild(detailsDiv);

                    const timeP = document.createElement('p');
                    timeP.className = 'textStyle4';
                    timeP.innerHTML = `<a href="#!/pageMore" class="_link4">${appointment.date} às ${appointment.time}</a>`;
                    appointmentDiv.appendChild(timeP);

                    appointmentsContainer.appendChild(appointmentDiv);
                });
            } else {
                alert('Failed to load appointments.');
            }
        } else {
            alert('Failed to load patient information.');
        }
    } catch (error) {
        console.error('Error loading appointments:', error);
        alert('An error occurred while loading appointments. Please try again.');
    }
}

async function loadPastAppointments() {
    const pastAppointmentsContainer = document.getElementById('past-appointments-container');
    if (!pastAppointmentsContainer) return;  // Exit if not on the past appointments page

    pastAppointmentsContainer.innerHTML = '';

    const email = localStorage.getItem('email');
    if (!email) {
        alert('You need to be logged in to view your past appointments.');
        return;
    }

    try {
        const patientResponse = await fetch(`/api/patient/byEmail/${email}`);
        if (patientResponse.ok) {
            const patient = await patientResponse.json();
            const response = await fetch(`/api/appointment/patient/${patient.patientId}`);
            if (response.ok) {
                const appointments = await response.json();
                const pastAppointments = appointments.filter(appointment => new Date(appointment.date) < new Date());

                pastAppointments.forEach(appointment => {
                    const appointmentDiv = document.createElement('div');
                    appointmentDiv.className = 'col1 padBot2';

                    const date = new Date(appointment.date);
                    const dateDiv = document.createElement('div');
                    dateDiv.className = '_date';
                    dateDiv.innerHTML = `<span>${date.getDate()}<br>${date.toLocaleString('default', { month: 'short' })}</span>`;
                    appointmentDiv.appendChild(dateDiv);

                    const detailsDiv = document.createElement('div');
                    detailsDiv.className = 'col3 marTop1';
                    detailsDiv.innerHTML = `<h3 class="padNull"><a href="#!/pageMore" class="_link3">${appointment.specialty}</a></h3>
                                            <p class="padNull">${appointment.medic.firstName} ${appointment.medic.lastName}</p>`;
                    appointmentDiv.appendChild(detailsDiv);

                    const timeP = document.createElement('p');
                    timeP.className = 'textStyle4';
                    timeP.innerHTML = `<a href="#!/pageMore" class="_link4">${appointment.date} às ${appointment.time}</a>`;
                    appointmentDiv.appendChild(timeP);

                    pastAppointmentsContainer.appendChild(appointmentDiv);
                });
            } else {
                alert('Failed to load past appointments.');
            }
        } else {
            alert('Failed to load patient information.');
        }
    } catch (error) {
        console.error('Error loading past appointments:', error);
        alert('An error occurred while loading past appointments. Please try again.');
    }
}

// Load appropriate appointments on page load based on URL
window.onload = function() {
    checkLogin();
    loadPastAppointments();
    loadAppointments();
};





/*functions for static info

function checkLogin() {
    const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
    if (isLoggedIn) {
        document.getElementById('menuAgenda').style.display = 'block';
        document.getElementById('loginButton').style.display = 'none';
        document.getElementById('logoutButton').style.display = 'block';
    } else {
        document.getElementById('menuAgenda').style.display = 'none';
        document.getElementById('loginButton').style.display = 'block';
        document.getElementById('logoutButton').style.display = 'none';
    }
}

function loadAppointments() {
    const appointmentsContainer = document.getElementById('appointments-container');
    if (!appointmentsContainer) return;  // Exit if not on the appointments page

    appointmentsContainer.innerHTML = '';

    // Simulate fetching appointments for the logged-in user
    const appointments = getAppointments();

    appointments.forEach(appointment => {
        const appointmentDiv = document.createElement('div');
        appointmentDiv.className = 'col1 padBot2';

        const dateDiv = document.createElement('div');
        dateDiv.className = '_date';
        dateDiv.innerHTML = `<span>${appointment.date.split('-')[2]}<br>${appointment.month}</span>`;
        appointmentDiv.appendChild(dateDiv);

        const detailsDiv = document.createElement('div');
        detailsDiv.className = 'col3 marTop1';
        detailsDiv.innerHTML = `<h3 class="padNull"><a href="#!/pageMore" class="_link3">${appointment.specialty}</a></h3>
                                <p class="padNull">${appointment.medic}</p>`;
        appointmentDiv.appendChild(detailsDiv);

        const timeP = document.createElement('p');
        timeP.className = 'textStyle4';
        timeP.innerHTML = `<a href="#!/pageMore" class="_link4">${appointment.date} às ${appointment.time}</a>`;
        appointmentDiv.appendChild(timeP);

        appointmentsContainer.appendChild(appointmentDiv);
    });
}

function loadPastAppointments() {
    const pastAppointmentsContainer = document.getElementById('past-appointments-container');
    if (!pastAppointmentsContainer) return;  // Exit if not on the past appointments page

    pastAppointmentsContainer.innerHTML = '';

    // Simulate fetching past appointments for the logged-in user
    const pastAppointments = getPastAppointments();

    pastAppointments.forEach(appointment => {
        const appointmentDiv = document.createElement('div');
        appointmentDiv.className = 'col1 padBot2';

        const dateDiv = document.createElement('div');
        dateDiv.className = '_date';
        dateDiv.innerHTML = `<span>${appointment.date.split('-')[2]}<br>${appointment.month}</span>`;
        appointmentDiv.appendChild(dateDiv);

        const detailsDiv = document.createElement('div');
        detailsDiv.className = 'col3 marTop1';
        detailsDiv.innerHTML = `<h3 class="padNull"><a href="#!/pageMore" class="_link3">${appointment.specialty}</a></h3>
                                <p class="padNull">${appointment.medic}</p>`;
        appointmentDiv.appendChild(detailsDiv);

        const timeP = document.createElement('p');
        timeP.className = 'textStyle4';
        timeP.innerHTML = `<a href="#!/pageMore" class="_link4">${appointment.date} às ${appointment.time}</a>`;
        appointmentDiv.appendChild(timeP);

        pastAppointmentsContainer.appendChild(appointmentDiv);
    });
}

function getAppointments() {
    // Simulate fetching appointments from a database or API
    // This is just sample data
    return [
        { date: '2024-08-13', month: 'aug', specialty: 'Neurology', medic: 'Dr. Asdrubal', time: '14h' },
        { date: '2024-08-14', month: 'aug', specialty: 'Cardiology', medic: 'Dr. John Doe', time: '15h' },
        { date: '2024-08-15', month: 'aug', specialty: 'Dermatology', medic: 'Dr. Jane Smith', time: '16h' },
        { date: '2024-08-16', month: 'aug', specialty: 'Pediatrics', medic: 'Dr. Emily Johnson', time: '17h' }
    ];
}

function getPastAppointments() {
    // Simulate fetching past appointments from a database or API
    // This is just sample data
    return [
        { date: '2024-05-01', month: 'may', specialty: 'Neurology', medic: 'Dr. Asdrubal', time: '14h' },
        { date: '2024-05-02', month: 'may', specialty: 'Cardiology', medic: 'Dr. John Doe', time: '15h' },
        { date: '2024-05-03', month: 'may', specialty: 'Dermatology', medic: 'Dr. Jane Smith', time: '16h' },
        { date: '2024-05-04', month: 'may', specialty: 'Pediatrics', medic: 'Dr. Emily Johnson', time: '17h' }
    ];
}

// Load appropriate appointments on page load based on URL
window.onload = function() {
    checkLogin();
    loadPastAppointments();
    loadAppointments();
    
};
