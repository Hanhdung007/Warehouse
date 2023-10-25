document.addEventListener('DOMContentLoaded', function () {
    const nameInput = document.querySelector('.name');
    const emailInput = document.querySelector('.email');
    const passwordInput = document.querySelector('.password');
    const phoneInput = document.querySelector('.phone');

    const nameError = document.querySelector('.showNameError')
    const emailError = document.querySelector('.showEmailError');
    const passwordError = document.querySelector('.showPasswordError');
    const phoneError = document.querySelector('.showPhoneError');
    const nameSuccess = document.querySelector('.showNameSuccess');
    const emailSuccess = document.querySelector('.showEmailSuccess');
    const passwordSuccess = document.querySelector('.showPasswordSuccess');
    const phoneSuccess = document.querySelector('.showPhoneSuccess');

    const checkCreate = document.querySelector('.checkCreate')

    nameInput.addEventListener('input', function () {
        const inputValue = nameInput.value.trim();
         nameError.innerText = '';
        nameSuccess.innerText = '';
        checkCreate.disabled = false;
        checkCreate.style.opacity = '1';

        if (!isValidName(inputValue)) {
            nameError.innerText = 'Name Requires Characters!';
            nameSuccess.innerText = '';
            checkCreate.disabled = true;
            checkCreate.style.opacity = '0.5';
        }
        else {
            nameSuccess.innerText = 'Name Is Valid!';
            nameError.innerText = '';
            checkCreate.disabled = false;
            checkCreate.style.opacity = '1';
        }
    });

    emailInput.addEventListener('input', function () {
        const inputValue = emailInput.value.trim();
        emailError.innerText = '';
        emailSuccess.innerText = '';
        checkCreate.disabled = false;
        checkCreate.style.opacity = '1';

        if (!isValidEmail(inputValue)) {
            emailError.innerText = 'Email Is Not Valid!';
            emailSuccess.innerText = '';
            checkCreate.disabled = true;
            checkCreate.style.opacity = '0.5';
        }
        else {
            emailSuccess.innerText = 'Email Is Valid!';
            emailError.innerText = '';
            checkCreate.disabled = false;
            checkCreate.style.opacity = '1';
        }
    });

    passwordInput.addEventListener('input', function () {
        const inputValue = passwordInput.value.trim();
        passwordError.innerText = '';
        passwordSuccess.innerText = '';
        checkCreate.disabled = false;
        checkCreate.style.opacity = '1';

        if (!isValidPassword(inputValue)) {
            passwordError.innerText = 'Password Requires At Least 8 Characters,' +
                ' Including UpperCase Letter And Special Character!';
            passwordSuccess.innerText = '';
            checkCreate.disabled = true;
            checkCreate.style.opacity = '0.5';
        }
        else {
            passwordSuccess.innerText = 'Password Is Valid!';
            passwordError.innerText = '';
            checkCreate.disabled = false;
            checkCreate.style.opacity = '1';
        }
    });

    phoneInput.addEventListener('input', function () {
        const inputValue = phoneInput.value.trim();
        phoneError.innerText = '';
        phoneSuccess.innerText = '';
        checkCreate.disabled = false;
        checkCreate.style.opacity = '1';

        if (!isValidPhone(inputValue)) {
            phoneError.innerText = 'Phone Requires 10 Or 11 Digits!';
            phoneSuccess.innerText = '';
            checkCreate.disabled = true;
            checkCreate.style.opacity = '0.5';
        }
        else {
            phoneSuccess.innerText = 'Phone Is Valid!';
            phoneError.innerText = '';
            checkCreate.disabled = false;
            checkCreate.style.opacity = '1';
        }
    });

    function isValidName(name){
        const nameRegex = /^[A-Za-z ]+$/;
        return nameRegex.test(name);
    }

    function isValidEmail(email) {
        const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        return emailRegex.test(email);
    }

    function isValidPassword(password) {
        const passwordRegex = /(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
        return passwordRegex.test(password);
    }

    function isValidPhone(phone) {
        const phoneRegex = /^0\d{9,10}$/;
        return phoneRegex.test(phone);
    }
});
