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

async function login() {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    try {
        const response = await fetch('/api/patient/checkPassword', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        });

        if (response.ok) {
            const isValid = await response.json();
            if (isValid) {
                localStorage.setItem('isLoggedIn', 'true');
                localStorage.setItem('email', email); // Store the email for later use
                alert('Login successful!');
                checkLogin();
                window.location.href = '#!/pageHome';
            } else {
                alert('Invalid credentials. Please try again.');
            }
        } else {
            alert('Login request failed. Please try again.');
        }
    } catch (error) {
        console.error('Error during login:', error);
        alert('An error occurred during login. Please try again.');
    }
}

async function register() {
    const newEmail = document.getElementById('new_email').value;
    const newPassword = document.getElementById('new_password').value;
    const confirmPassword = document.getElementById('confirm_password').value;
    const firstName = document.getElementById('first_name').value;
    const lastName = document.getElementById('last_name').value;
    const dateOfBirth = document.getElementById('date_of_birth').value;
    const gender = document.getElementById('gender').value;
    const ccNumber = document.getElementById('cc_number').value;
    const phoneNumber = document.getElementById('phone_number').value;

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
        email: newEmail,
        password: newPassword
    };

    try {
        const response = await fetch('/api/patient', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(patient)
        });

        if (response.ok) {
            alert('Registration successful! You can now log in.');
            window.location.href = '#!/pageLogin';
        } else {
            alert('Registration failed. Please try again.');
        }
    } catch (error) {
        console.error('Error during registration:', error);
        alert('An error occurred during registration. Please try again.');
    }
}

function logout() {
    localStorage.setItem('isLoggedIn', 'false');
    checkLogin();
    alert('Logged out successfully.');
    window.location.href = '#!/pageHome';
}

// Check login status on page load
window.onload = checkLogin;


/*old one with static
// Check if user is logged in
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

// Login function
function login() {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    // Simple validation for demonstration purposes
    if (email === 'user' && password === 'password') {
        localStorage.setItem('isLoggedIn', 'true');
        alert('Login successful!');
        checkLogin();
        window.location.href = '#!/pageHome';
    } else {
        alert('Invalid credentials. Please try again.');
    }
}

// Register function
function register() {
    const newEmail = document.getElementById('new_email').value;
    const newPassword = document.getElementById('new_password').value;
    const confirmPassword = document.getElementById('confirm_password').value;

    if (newPassword === confirmPassword) {
        localStorage.setItem('new_email', newEmail);
        localStorage.setItem('new_password', newPassword);
        alert('Registration successful! You can now log in.');
        window.location.href = '#!/pageLogin';
    } else {
        alert('Passwords do not match. Please try again.');
    }
}

// Logout function
function logout() {
    localStorage.setItem('isLoggedIn', 'false');
    checkLogin();
    alert('Logged out successfully.');
    window.location.href = '#!/pageHome';
}

// Check login status on page load
window.onload = checkLogin;
*/