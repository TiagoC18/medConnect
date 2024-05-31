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
    appointmentsContainer.innerHTML = ''

    const email = localStorage.getItem('email');
    if (!email) {
        alert('You need to be logged in to view your appointments.');
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
                const appointments = appointment.filter(appointment => new Date(appointment.appointmentDay) > new Date());
                console.log(appointments)

                appointments.forEach(appointment => {
                    const appointmentDiv = document.createElement('div');
                    appointmentDiv.className = 'col1 padBot2';

                    const date = new Date(appointment.appointmentDay);
                    const dateDiv = document.createElement('div');
                    dateDiv.className = '_date';
                    dateDiv.innerHTML = `<span>${date.getDate()}<br>${date.toLocaleString('default', { month: 'short' })}</span>`;
                    appointmentDiv.appendChild(dateDiv);

                    const detailsDiv = document.createElement('div');
                    detailsDiv.className = 'col3 marTop1';
                    detailsDiv.innerHTML = `<h3 class="padNull"><a href="" class="_link3">${appointment.specialty}</a></h3>
                                            <p class="padNull">${appointment.medic.firstName} ${appointment.medic.lastName}</p>`;
                    appointmentDiv.appendChild(detailsDiv);

                    const timeP = document.createElement('p');
                    timeP.className = 'textStyle4';
                    timeP.innerHTML = `<a href="" class="_link4">${appointment.appointmentDay} às ${appointment.appointmentTime}</a>`;
                    appointmentDiv.appendChild(timeP);

                    const deleteButton = document.createElement('span');
                    deleteButton.className = 'delete-button';
                    deleteButton.innerHTML = '&#10060;'; // Código HTML para o ícone de cruz vermelha
                    deleteButton.addEventListener('click', () => openDeleteModal(appointment.appointmentId)); // Abre o modal de exclusão ao clicar

                    appointmentDiv.appendChild(deleteButton); // Adiciona o botão de exclusão à div de compromisso

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

function openDeleteModal(appointmentId) {
    // Cria um elemento de div para o modal
    const modal = document.createElement('div');
    modal.className = 'modal';
    
    // Cria um conteúdo para o modal
    const modalContent = document.createElement('div');
    modalContent.className = 'modal-content';
    
    // Adiciona a mensagem ao conteúdo do modal
    const message = document.createElement('p');
    message.textContent = 'Are you sure you want to cancel your appointment?';
    modalContent.appendChild(message);
    
    const yesButton = document.createElement('button');
    yesButton.textContent = 'Yes';
    yesButton.style.backgroundColor = '#FF0000'; // Vermelho
    yesButton.style.color = '#FFFFFF'; // Texto branco
    yesButton.addEventListener('click', () => {
        // Chamada da função para excluir o compromisso
        deleteAppointment(appointmentId);
        // Fecha o modal
        closeModal(modal);
    });
    modalContent.appendChild(yesButton);

    const noButton = document.createElement('button');
    noButton.textContent = 'No';
    noButton.style.backgroundColor = '#28A4E1'; // Azul
    noButton.style.color = '#FFFFFF'; // Texto branco
    noButton.addEventListener('click', () => {
        // Fecha o modal sem excluir o compromisso
        closeModal(modal);
    });
    modalContent.appendChild(noButton);
    
    // Adiciona o conteúdo do modal ao modal
    modal.appendChild(modalContent);
    
    // Adiciona o modal ao corpo do documento
    document.body.appendChild(modal);
}

function closeModal(modal) {
    // Remove o modal do corpo do documento
    document.body.removeChild(modal);
}

async function deleteAppointment(appointmentId) {
    try {
        const response = await fetch(`http://localhost:8080/api/appointment/delete/${appointmentId}`, {
            method: 'DELETE'
        });
        if (response.ok) {
            alert('Appointment deleted successfully.');
            loadAppointments();
        } else {
            alert('Failed to delete appointment.');
        }
    } catch (error) {
        console.error('Error deleting appointment:', error);
        alert('An error occurred while deleting the appointment. Please try again.');
    }
}

checkLogin();
loadAppointments();