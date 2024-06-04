function loadWaitingList() {
    const waitingListContainer = document.getElementById('waiting-list-container');
    waitingListContainer.innerHTML = '';

    const fetchWaiting = fetch('http://deti-tqs-14.ua.pt:8080/api/appointment/waiting').then(response => response.json());
    const fetchCalled = fetch('http://deti-tqs-14.ua.pt:8080/api/appointment/called').then(response => response.json());

    Promise.all([fetchWaiting, fetchCalled])
        .then(data => {
            const [waitingData, calledData] = data;
            const combinedData = [...waitingData, ...calledData];

            combinedData.forEach(appointment => {
                const patient = appointment.patient;
                const patientDiv = document.createElement('div');
                patientDiv.className = 'waiting-list-item';

                patientDiv.innerHTML = `
                    <p style="margin-top: 22px">${appointment.senha}</p>
                    <p style="margin-top: 22px">${patient.firstName} ${patient.lastName}</p>
                    ${appointment.status === 'Waiting' ? 
                        `<button onclick="callPatient(${appointment.appointmentId})">Call</button>` : 
                        `<button onclick="markAsDone(${appointment.appointmentId})">Done</button>`
                    }
                `;
                waitingListContainer.appendChild(patientDiv);
            });
        })
        .catch(error => console.error('Error loading waiting list:', error));
}

function callPatient(appointmentId) {
    fetch(`http://deti-tqs-14.ua.pt:8080/api/appointment/${appointmentId}/Called`, {
        method: 'PUT'
    })
    .then(response => response.json())
    .then(() => {
        alert('Patient called successfully.');
        loadWaitingList();
    })
    .catch(error => console.error('Error calling patient:', error));
}

function markAsDone(appointmentId) {
    fetch(`http://deti-tqs-14.ua.pt:8080/api/appointment/${appointmentId}/Done`, {
        method: 'PUT'
    })
    .then(response => response.json())
    .then(() => {
        alert('Appointment marked as done.');
        loadWaitingList();
    })
    .catch(error => console.error('Error marking appointment as done:', error));
}

function checkLogin() {
    const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
    if (isLoggedIn) {
        document.getElementById('loginButton').style.display = 'none';
        document.getElementById('logoutButton').style.display = 'block';
        document.getElementById('menuAddPatient').style.display = 'block';
        document.getElementById('menuManageWaitingList').style.display = 'block';
    } else {
        document.getElementById('loginButton').style.display = 'block';
        document.getElementById('logoutButton').style.display = 'none';
        document.getElementById('menuAddPatient').style.display = 'none';
        document.getElementById('menuManageWaitingList').style.display = 'none';
    }
}

function handlePageChange() {
    if (window.location.hash.includes('pageManageWaitingList')) {
        checkLogin();
        loadWaitingList();
    }
}

// Adicionar listener para o evento 'hashchange'
window.addEventListener('hashchange', handlePageChange);