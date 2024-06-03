function login() {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    fetch('http://localhost:8080/api/staff/checkPassword', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            email: email,
            password: password
        })
    })
    .then(response => response.json())
    .then(result => {
        if (result) {
            localStorage.setItem('isLoggedIn', 'true');
            alert('Login successful!');
            checkLogin();
            window.location.href = '#!/pageAddPatient';
        } else {
            alert('Invalid credentials. Please try again.');
        }
    })
    .catch(error => {
        console.error('Error during login:', error);
        alert('An error occurred. Please try again.');
    });
}

function logout() {
    localStorage.setItem('isLoggedIn', 'false');
    checkLogin();
    alert('Logged out successfully.');
    window.location.reload();
}

function checkLogin() {
    const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
    if (isLoggedIn) {
        document.getElementById('loginButton').style.display = 'none';
        document.getElementById('logoutButton').style.display = 'block';
        document.getElementById('menuAddPatient').style.display = 'block';
        document.getElementById('menuManageWaitingList').style.display = 'block';
        window.location.href = '#!/pageAddPatient';
    } else {
        document.getElementById('loginButton').style.display = 'block';
        document.getElementById('logoutButton').style.display = 'none';
        document.getElementById('menuAddPatient').style.display = 'none';
        document.getElementById('menuManageWaitingList').style.display = 'none';
        window.location.href = '#!/pageHome';
    }
}

// Check login status on page load
window.onload = checkLogin;