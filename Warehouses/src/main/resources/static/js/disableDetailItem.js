let selectedItem = null;

// function openConfirmationModal(itemId) {
//     selectedItem = itemId;
//     const modal = document.getElementById('confirmationModal');
//     modal.style.display = 'block';
// }

function closeConfirmationModal() {
    const modal = document.getElementById('confirmationModal');
    modal.style.display = 'none';
}

function openConfirmationModal(event) {
    // Lấy thẻ modal-title trong modal

    var itemName = event.target.getAttribute('data-name');

    // Đặt giá trị của modal-title bằng itemName
    var modalTitle = document.getElementById('modal-title');
    modalTitle.textContent = 'Are You Sure To Close ' + itemName + '?';
}

//Xử lý sự kiện khi nhấn vào button("Sure")
$('.disable').click(function () {
    var itemID = $(this).data('id');

    function confirmClosure() {
        if (selectedItem !== null) {
            // Send an AJAX request or update the 'disable' property for the selected item
            // and apply the CSS changes as needed.
            // After that, close the modal.
            // Example:
            // updateItemDisable(selectedItem, true); // Update the 'disable' property
            // applyCSSChanges(selectedItem); // Apply CSS changes
            closeConfirmationModal();
        }
    }
})