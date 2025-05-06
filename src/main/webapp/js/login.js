// Password visibility toggle functionality
document.querySelector('.password-toggle').addEventListener('click', function() {
    // Get references to the password input and the eye-slash line
    const passwordInput = document.getElementById('password');
    const eyeSlash = document.getElementById('eye-slash');
    
    if (passwordInput.type === 'password') {
        // Show password 
        passwordInput.type = 'text';
        eyeSlash.style.display = 'none';
    } else {
        // Hide password 
        passwordInput.type = 'password';
        eyeSlash.style.display = 'block';
    }
});