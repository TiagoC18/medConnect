function login() {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    // Example login validation (replace with real authentication logic)
    if (email === 'staff@example.com' && password === 'password') {
        localStorage.setItem('isLoggedIn', 'true');
        alert('Login successful!');
        checkLogin();
        window.location.href = '#!/pageAddPatient';
    } else {
        alert('Invalid credentials. Please try again.');
    }
}

function logout() {
    localStorage.setItem('isLoggedIn', 'false');
    checkLogin();
    alert('Logged out successfully.');
    window.location.href = '#!/pageHome';
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

// Check login status on page load
window.onload = checkLogin;
