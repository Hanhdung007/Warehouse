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
    const nameInput = document.getElementById('whName');
    const codeInput = document.getElementById('whCode'); // Thêm các phần tử khác nếu cần

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

    if (nameInput) {
        const nameExportError = document.getElementById('nameExportError');
        handleInputChange(nameInput, nameExportError, 'Warehouse name cannot be empty!');
    }

    if (codeInput) {
        const codeExportError = document.getElementById('codeExportError');
        handleInputChange(codeInput, codeExportError, 'Code cannot be empty!');
    }
});
