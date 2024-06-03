let currentPage = 1;
const itemsPerPage = 4;
let totalItems = 0;

function loadWaitingList(page = 1) {
    const waitingListContainer = document.getElementById('waiting-list-container');
    waitingListContainer.innerHTML = '';

    fetch('http://localhost:8080/api/appointment/waiting')
        .then(response => response.json())
        .then(data => {
            totalItems = data.length;
            const start = (page - 1) * itemsPerPage;
            const end = start + itemsPerPage;
            const itemsToShow = data.slice(start, end);

            itemsToShow.forEach(appointment => {
                const patient = appointment.patient;
                const patientDiv = document.createElement('div');
                patientDiv.className = 'waiting-list-item';
                patientDiv.innerHTML = `
                    <p>Name: ${patient.firstName} ${patient.lastName}</p>
                    <p>DOB: ${patient.dateOfBirth}</p>
                    <p>Gender: ${patient.gender}</p>
                    <p>CC: ${patient.ccNumber}</p>
                    <button onclick="callPatient(${appointment.id})">Call</button>
                    <button onclick="markAsDone(${appointment.id})">Done</button>
                `;
                waitingListContainer.appendChild(patientDiv);
            });

            updatePagination();
        })
        .catch(error => console.error('Error loading waiting list:', error));
}

function updatePagination() {
    const pageIndicator = document.getElementById('pageIndicator');
    const prevPageButton = document.getElementById('prevPage');
    const nextPageButton = document.getElementById('nextPage');

    const totalPages = Math.ceil(totalItems / itemsPerPage);

    pageIndicator.textContent = `Page ${currentPage} of ${totalPages}`;

    prevPageButton.disabled = currentPage === 1;
    nextPageButton.disabled = currentPage === totalPages;
}

function changePage(direction) {
    currentPage += direction;
    loadWaitingList(currentPage);
}

function callPatient(appointmentId) {
    fetch(`http://localhost:8080/api/appointment/${appointmentId}/Waiting`, {
        method: 'PUT'
    })
    .then(response => response.json())
    .then(() => {
        alert('Patient called successfully.');
        loadWaitingList(currentPage);
    })
    .catch(error => console.error('Error calling patient:', error));
}

function markAsDone(appointmentId) {
    fetch(`http://localhost:8080/api/appointment/${appointmentId}/Done`, {
        method: 'PUT'
    })
    .then(response => response.json())
    .then(() => {
        alert('Appointment marked as done.');
        loadWaitingList(currentPage);
    })
    .catch(error => console.error('Error marking appointment as done:', error));
}

window.onload = function() {
    loadWaitingList();
};
