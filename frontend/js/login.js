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

    const params = new URLSearchParams();
    params.append('email', email);
    params.append('password', password);

    try {
        const response = await fetch('http://deti-tqs-14.ua.pt:8080/api/patient/checkPassword', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: params.toString()
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
    const email = document.getElementById('newEmail').value;
    const password = document.getElementById('new_password').value;
    const confirmPassword = document.getElementById('confirm_password').value;
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const dateOfBirth = document.getElementById('dateOfBirth').value;
    const gender = document.getElementById('gender').value;
    const ccNumber = document.getElementById('ccNumber').value;
    const phoneNumber = document.getElementById('phoneNumber').value;

    if (!email || !password || !confirmPassword || !firstName || !lastName || !dateOfBirth || !gender || !ccNumber || !phoneNumber) {
        alert('Please fill in all required fields.');
        return;
    }

    if (password !== confirmPassword) {
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
        password
    };

    try {
        const response = await fetch('http://deti-tqs-14.ua.pt:8080/api/patient', {
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
    window.location.href = '#!/pageHome'; // Redireciona para a página inicial
    localStorage.clear();
    location.reload(true); // Força a recarga completa da página
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