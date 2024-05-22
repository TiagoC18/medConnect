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
