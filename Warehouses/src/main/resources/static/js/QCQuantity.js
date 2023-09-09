// Xử lý sự kiện khi nút "Accept" hoặc "Inject" được nhấn
function checkQC(formId) {
    document.querySelector(`#form-${formId}`).submit();
}

const acceptButtons = document.querySelectorAll("button[data-toggle='modal']");
acceptButtons.forEach(button => {
    button.addEventListener("click", function () {
        const productId = this.getAttribute("data-product-id");
        openModal(productId);
    });
});

// Hàm để mở modal và hiển thị thông tin sản phẩm
function openModal(productId) {
    // Đặt giá trị của input và nút "Accept" theo sản phẩm tương ứng
    const quantityInput = document.getElementById("quantityInput");
    const acceptButton = document.getElementById("acceptButton");
    const injectButton = document.getElementById("injectButton");

    // Gán sự kiện cho nút "Accept"
    acceptButton.addEventListener("click", function () {
        const quantity = parseInt(quantityInput.value);
        if (!isNaN(quantity)) {
            // Gửi yêu cầu "Accept" đến Controller
            sendAcceptRequest(productId, quantity);
        }
    });

    // Gán sự kiện cho nút "Inject"
    injectButton.addEventListener("click", function () {
        // Gửi yêu cầu "Inject" đến Controller
        sendInjectRequest(productId);
    });

    // Hiển thị modal
    $('#myModal').modal('show');
}

// Hàm để gửi yêu cầu "Accept" đến Controller
function sendAcceptRequest(productId, quantity) {
    $.ajax({
        url: '/qc/accept',
        type: 'POST',
        data: {
            id: productId,
            quantityInput: quantity
        },
        success: function (data) {
            // Xử lý phản hồi từ Server nếu cần
            $('#myModal').modal('hide'); // Đóng modal sau khi hoàn thành
        },
        error: function (error) {
            // Xử lý lỗi nếu cần
            $('#myModal').modal('hide'); // Đóng modal sau khi hoàn thành (có thể thay đổi theo cách xử lý lỗi)
        }
    });
}

// Hàm để gửi yêu cầu "Inject" đến Controller
function sendInjectRequest(productId) {
    $.ajax({
        url: '/qc/inject',
        type: 'POST',
        data: {
            id: productId
        },
        success: function (data) {
            // Xử lý phản hồi từ Server nếu cần
            $('#myModal').modal('hide'); // Đóng modal sau khi hoàn thành
        },
        error: function (error) {
            // Xử lý lỗi nếu cần
            $('#myModal').modal('hide'); // Đóng modal sau khi hoàn thành (có thể thay đổi theo cách xử lý lỗi)
        }
    });
}


const item4QC = document.getElementById('item4QC');
const itemQuantity = document.getElementById('itemQuantity');
const checkQuantity = document.getElementById("checkQuantity");

// Sử dụng sự kiện input để kiểm tra email trong thời gian thực
item4QC.addEventListener('input', function () {
    if (item4QC > itemQuantity) {
        checkQuantity.innerText = 'Quantity For QC Must Be Less Than Or Equal To The Current Quantity!';
    } else {
        checkQuantity.innerText = '';
    }
});

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