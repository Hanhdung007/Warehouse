document.addEventListener('DOMContentLoaded', function () {
    const item4QCs = document.querySelectorAll('.item4QC');
    const itemQuantities = document.querySelectorAll('.itemQuantity');
    const checkQuantities = document.querySelectorAll('.checkQuantity');
    const acceptButtons = document.querySelectorAll('.acceptButton');
    const injectButtons = document.querySelectorAll('.injectButton');

    item4QCs.forEach((item4QC, index) => {
        item4QC.addEventListener('input', function () {
            const inputQuantity = parseInt(item4QC.value.trim());
            const currentQuantity = Math.floor(parseFloat(itemQuantities[index].innerText));

            // Xóa thông báo hiện có
            checkQuantities[index].innerText = '';
            acceptButtons[index].style.opacity = '1';
            injectButtons[index].style.opacity = '1';
            acceptButtons[index].disabled = false;
            injectButtons[index].disabled = false;

            if (inputQuantity < 0) {
                checkQuantities[index].innerText = 'Item Quantity For QC Cannot Be Less Than 0!';
                acceptButtons[index].style.opacity = '0.5';
                injectButtons[index].style.opacity = '0.5';
                acceptButtons[index].disabled = true;
                injectButtons[index].disabled = true;
            } else if (inputQuantity > currentQuantity) {
                checkQuantities[index].innerText = 'Item Quantity For QC Must Be Less Or Equal To The Current Quantity!';
                acceptButtons[index].style.opacity = '0.5';
                injectButtons[index].style.opacity = '0.5';
                acceptButtons[index].disabled = true;
                injectButtons[index].disabled = true;
            }
        });
    });
});
