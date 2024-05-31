async function checkLogin() {
    const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
    if (isLoggedIn) {
        document.getElementById('menuAgenda').style.display = 'block';
        document.getElementById('loginButton').style.display = 'none';
        document.getElementById('logoutButton').style.display = 'block';
        loadPastAppointments();
    } else {
        document.getElementById('menuAgenda').style.display = 'none';
        document.getElementById('loginButton').style.display = 'block';
        document.getElementById('logoutButton').style.display = 'none';
    }
}


async function loadPastAppointments() {

    const pastAppointmentsContainer = document.getElementById('past-appointments-container');
    pastAppointmentsContainer.innerHTML = ''

    const email = localStorage.getItem('email');
    if (!email) {
        alert('You need to be logged in to view your past appointments.');
        return;
    }

    try {
        const patientResponse = await fetch(`http://localhost:8080/api/patient/byEmail/${email}`);
        console.log(patientResponse)
        if (patientResponse.ok) {
            const patient = await patientResponse.json();
            const response = await fetch(`http://localhost:8080/api/appointment/patient/${patient.patientId}`);
            if (response.ok) {
                const appointment = await response.json();
                console.log(appointment)
                const pastAppointments = appointment.filter(appointment => new Date(appointment.appointmentDay) < new Date());
                console.log(pastAppointments)

                pastAppointments.forEach(appointment => {
                    const appointmentDiv = document.createElement('div');
                    appointmentDiv.className = 'col1 padBot2';

                    const date = new Date(appointment.appointmentDay);
                    const dateDiv = document.createElement('div');
                    dateDiv.className = '_date';
                    dateDiv.innerHTML = `<span>${date.getDate()}<br>${date.toLocaleString('default', { month: 'short' })}</span>`;
                    appointmentDiv.appendChild(dateDiv);

                    const detailsDiv = document.createElement('div');
                    detailsDiv.className = 'col3 marTop1';
                    detailsDiv.innerHTML = `<h3 class="padNull"><a class="_link3">${appointment.specialty}</a></h3>
                                            <p class="padNull">${appointment.medic.firstName} ${appointment.medic.lastName}</p>`;
                    appointmentDiv.appendChild(detailsDiv);

                    const timeP = document.createElement('p');
                    timeP.className = 'textStyle4';
                    timeP.innerHTML = `<a class="_link4">${appointment.appointmentDay} às ${appointment.appointmentTime}</a>`;
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



checkLogin();