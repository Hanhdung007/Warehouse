//document.addEventListener('DOMContentLoaded', (event) => {
//    const nameInput = document.getElementById('whName');
//    const nameExportError = document.getElementById('nameExportError');  // Add this line
//
//    if (nameInput) {
//        nameInput.addEventListener('input', function () {
//            const nameValue = nameInput.value.trim();
//            if (nameValue === '') {
//                nameExportError.innerText = 'Warehouse name cannot be empty!';
//            } else {
//                nameExportError.innerText = '';
//            }
//        });
//    }
//});
document.addEventListener('DOMContentLoaded', (event) => {
    const nameInput = document.getElementById('name');
    const codeInput = document.getElementById('code');
    const capacityInput = document.getElementById('capacity');

    const handleInputChange = (inputElement, errorElement, errorMessage) => {
        inputElement.addEventListener('input', function () {
            const inputValue = inputElement.value.trim();
            if (inputValue === '') {
                errorElement.innerText = errorMessage;
            } else {
                errorElement.innerText = '';
            }
        });
    };

    if (codeInput) {
        const errorCode = document.getElementById('errorCode');
        handleInputChange(codeInput, errorCode, 'Location code cannot be empty!');
    }

    if (nameInput) {
        const errorName = document.getElementById('errorName');
        handleInputChange(nameInput, errorName, 'Location name cannot be empty!');
    }
    if (capacityInput || capacityInput <= 0) {
        const errorCapacity = document.getElementById('errorCapacity');
        handleInputChange(capacityInput, errorCapacity, 'capacity cannot be empty or < 0!');
    }
});
