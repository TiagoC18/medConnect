function register() {
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const dateOfBirth = document.getElementById('dateOfBirth').value;
    const gender = document.getElementById('gender').value;
    const ccNumber = document.getElementById('ccNumber').value;
    const phoneNumber = document.getElementById('phoneNumber').value;
    const email = document.getElementById('email').value;
    const newPassword = document.getElementById('new_password').value;
    const confirmPassword = document.getElementById('confirm_password').value;

    if (newPassword !== confirmPassword) {
        alert('Passwords do not match. Please try again.');
        return;
    }

    const patient = {
        firstName,
        lastName,
        dateOfBirth,
        gender,
        ccNumber,
        phoneNumber,
        email,
        password: newPassword // Assuming the password is also needed
    };

    // For now, we will just log the data to the console
    // You will need to send this data to the backend using an AJAX request or fetch API
    console.log(patient);

    // Example of sending data to the backend
    /*
    fetch('YOUR_BACKEND_ENDPOINT', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(patient)
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert('Registration successful! You can now log in.');
            window.location.href = '#!/pageLogin';
        } else {
            alert('Registration failed: ' + data.message);
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Registration failed. Please try again.');
    });
    */
}
