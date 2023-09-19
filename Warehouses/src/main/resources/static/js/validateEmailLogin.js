const emailInput = document.getElementById('email');
const emailError = document.getElementById('emailError');
const emailTrue = document.getElementById('emailTrue');

// Sử dụng sự kiện input để kiểm tra email trong thời gian thực
emailInput.addEventListener('input', function () {
    const emailValue = emailInput.value.trim();
    if (!isValidEmail(emailValue)) {
        emailError.innerText = 'The email address is not valid!';
        emailError.classList.add('errorEmail');

        // Loại bỏ thông báo email hợp lệ nếu có
        emailTrue.innerText = '';
        emailTrue.classList.remove('emailTrue');
    } else {
        emailTrue.innerText = 'The email address is valid!';
        emailTrue.classList.add('emailTrue');
        emailError.innerText = '';
        emailError.classList.remove('errorEmail');
    }
});

function isValidEmail(email) {
    const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    return emailRegex.test(email);
}