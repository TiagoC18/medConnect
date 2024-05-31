/*let currentPage = 1;
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
};*/
let currentPage = 1;
const itemsPerPage = 4;
let waitingList = [];
let calledList = [];
let currentList = 'waiting'; // To keep track of which list is being displayed

function addPatient() {
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const ccNumber = document.getElementById('ccNumber').value;

    const patient = {
        firstName,
        lastName,
        ccNumber,
        dateOfBirth: '1990-01-01', // Simulated data
        gender: 'Unknown',         // Simulated data
        phoneNumber: '0000000000', // Simulated data
        email: 'unknown@example.com', // Simulated data
        status: 'Waiting'          // Initial status
    };

    waitingList.push(patient); // Add patient to the local waiting list
    alert('Patient added to waiting list!');
    loadCurrentList(); // Reload the current list

    // Clear form fields
    document.getElementById('firstName').value = '';
    document.getElementById('lastName').value = '';
    document.getElementById('ccNumber').value = '';
}

function loadCurrentList() {
    if (currentList === 'waiting') {
        loadWaitingList(currentPage);
    } else {
        loadCalledList(currentPage);
    }
}

function loadWaitingList(page = 1) {
    const waitingListContainer = document.getElementById('waiting-list-container');
    waitingListContainer.innerHTML = '';
    document.getElementById('list-title').textContent = 'Waiting List';

    const start = (page - 1) * itemsPerPage;
    const end = start + itemsPerPage;
    const itemsToShow = waitingList.slice(start, end);

    itemsToShow.forEach((patient, index) => {
        const patientDiv = document.createElement('div');
        patientDiv.className = 'waiting-list-item';
        patientDiv.innerHTML = `
            <p>Name: ${patient.firstName} ${patient.lastName}</p>
            <p>DOB: ${patient.dateOfBirth}</p>
            <p>Gender: ${patient.gender}</p>
            <p>CC: ${patient.ccNumber}</p>
            <p>Phone: ${patient.phoneNumber}</p>
            <p>Email: ${patient.email}</p>
            <button onclick="callPatient(${start + index})">Call</button>
        `;
        waitingListContainer.appendChild(patientDiv);
    });

    updatePagination(waitingList);
}

function loadCalledList(page = 1) {
    const calledListContainer = document.getElementById('called-list-container');
    calledListContainer.innerHTML = '';
    document.getElementById('list-title').textContent = 'Called List';

    const start = (page - 1) * itemsPerPage;
    const end = start + itemsPerPage;
    const itemsToShow = calledList.slice(start, end);

    itemsToShow.forEach((patient, index) => {
        const patientDiv = document.createElement('div');
        patientDiv.className = 'called-list-item';
        patientDiv.innerHTML = `
            <p>Name: ${patient.firstName} ${patient.lastName}</p>
            <p>DOB: ${patient.dateOfBirth}</p>
            <p>Gender: ${patient.gender}</p>
            <p>CC: ${patient.ccNumber}</p>
            <p>Phone: ${patient.phoneNumber}</p>
            <p>Email: ${patient.email}</p>
            <button onclick="markAsDone(${start + index})">Done</button>
        `;
        calledListContainer.appendChild(patientDiv);
    });

    updatePagination(calledList);
}

function updatePagination(list) {
    const pageIndicator = document.getElementById('pageIndicator');
    const prevPageButton = document.getElementById('prevPage');
    const nextPageButton = document.getElementById('nextPage');

    const totalPages = Math.ceil(list.length / itemsPerPage);

    pageIndicator.textContent = `Page ${currentPage} of ${totalPages}`;

    prevPageButton.disabled = currentPage === 1;
    nextPageButton.disabled = currentPage === totalPages;
}

function changePage(direction) {
    currentPage += direction;
    loadCurrentList();
}

function toggleList() {
    currentPage = 1; // Reset to first page
    if (currentList === 'waiting') {
        currentList = 'called';
        document.getElementById('waiting-list-container').style.display = 'none';
        document.getElementById('called-list-container').style.display = 'block';
        document.querySelector('.toggle-button').textContent = 'Switch to Waiting List';
    } else {
        currentList = 'waiting';
        document.getElementById('waiting-list-container').style.display = 'block';
        document.getElementById('called-list-container').style.display = 'none';
        document.querySelector('.toggle-button').textContent = 'Switch to Called List';
    }
    loadCurrentList();
}

function callPatient(index) {
    const patient = waitingList[index];
    patient.status = 'Called';
    calledList.push(patient);
    waitingList.splice(index, 1);
    alert('Patient called successfully.');
    loadCurrentList();
}

function markAsDone(index) {
    calledList.splice(index, 1);
    alert('Appointment marked as done.');
    loadCurrentList();
}

function getWaitingList() {
    // Simulate fetching waiting list from a database or API
    return [
        { firstName: 'John', lastName: 'Doe', dateOfBirth: '1990-01-01', gender: 'Male', ccNumber: '123456', phoneNumber: '123456789', email: 'john.doe@example.com', status: 'Waiting' },
        { firstName: 'Jane', lastName: 'Doe', dateOfBirth: '1992-02-02', gender: 'Female', ccNumber: '654321', phoneNumber: '987654321', email: 'jane.doe@example.com', status: 'Waiting' },
        { firstName: 'Alice', lastName: 'Smith', dateOfBirth: '1985-03-03', gender: 'Female', ccNumber: '111222', phoneNumber: '555666777', email: 'alice.smith@example.com', status: 'Waiting' },
        { firstName: 'Bob', lastName: 'Johnson', dateOfBirth: '1988-04-04', gender: 'Male', ccNumber: '333444', phoneNumber: '888999000', email: 'bob.johnson@example.com', status: 'Waiting' },
        { firstName: 'Charlie', lastName: 'Brown', dateOfBirth: '1977-05-05', gender: 'Male', ccNumber: '555666', phoneNumber: '123123123', email: 'charlie.brown@example.com', status: 'Waiting' },
        { firstName: 'Dana', lastName: 'White', dateOfBirth: '1991-06-06', gender: 'Female', ccNumber: '777888', phoneNumber: '321321321', email: 'dana.white@example.com', status: 'Waiting' },
    ];
}

window.onload = function() {
    checkLogin();
    waitingList = getWaitingList(); // Initialize the waiting list
    document.getElementById('waiting-list-container').style.display = 'block';
    loadCurrentList();
};
