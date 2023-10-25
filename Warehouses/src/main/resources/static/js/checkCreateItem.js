document.addEventListener('DOMContentLoaded', function () {
    const quantityInput = document.querySelector('.quantity');
    // const recieveInput = document.querySelector('.recieve');

    const quantityError = document.querySelector('.showQuantityError')
    // const recieveError = document.querySelector('.showRecieveError');
    const quantitySuccess = document.querySelector('.showQuantitySuccess')
    // const recieveError = document.querySelector('.showRecieveError');

    const checkCreate = document.querySelector('.checkCreate')

    quantityInput.addEventListener('input', function () {
        const inputValue = quantityInput.value.trim();
        quantityError.innerText = '';
        quantitySuccess.innerText = '';
        checkCreate.disabled = false;
        checkCreate.style.opacity = '1';

        if (!isValidQuantity(inputValue)) {
            quantityError.innerText = 'Quantity Requires An Integer And Must Be Greater Than 0!';
            quantitySuccess.innerText = '';
            checkCreate.disabled = true;
            checkCreate.style.opacity = '0.5';
        } else {
            quantitySuccess.innerText = 'Quantity Is Valid!';
            quantityError.innerText = '';
            checkCreate.disabled = false;
            checkCreate.style.opacity = '1';
        }
    });

    // phoneInput.addEventListener('input', function () {
    //     const inputValue = phoneInput.value.trim();
    //     phoneError.innerText = '';
    //     phoneSuccess.innerText = '';
    //     checkCreate.disabled = false;
    //     checkCreate.style.opacity = '1';
    //
    //     if (!isValidPhone(inputValue)) {
    //         phoneError.innerText = 'Phone Requires 10 Or 11 Digits!';
    //         phoneSuccess.innerText = '';
    //         checkCreate.disabled = true;
    //         checkCreate.style.opacity = '0.5';
    //     } else {
    //         phoneSuccess.innerText = 'Phone Is Valid!';
    //         phoneError.innerText = '';
    //         checkCreate.disabled = false;
    //         checkCreate.style.opacity = '1';
    //     }
    // });

    function isValidQuantity(quantity) {
        // Kiểm tra xem quantity là số nguyên dương và không âm
        const quantityRegex = /^[1-9]\d*$/;
        return quantityRegex.test(quantity);
    }

    // function isValidPhone(phone) {
    //     const phoneRegex = /^0\d{9,10}$/;
    //     return phoneRegex.test(phone);
    // }
});
