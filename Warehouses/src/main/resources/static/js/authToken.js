// Lưu token vào cookies
function saveTokenToCookie(token) {
    const expirationDate = new Date();
    expirationDate.setDate(expirationDate.getDate() + 7); // Set thời hạn cho cookie (ví dụ: 7 ngày)

    // Sử dụng document.cookie để lưu token vào cookies
    document.cookie = `token=${token}; expires=${expirationDate.toUTCString()}; path=/; secure; samesite=strict`;
}

// Lấy token từ cookies
function getTokenFromCookie() {
    const cookies = document.cookie.split('; ');
    for (const cookie of cookies) {
        const [name, value] = cookie.split('=');
        if (name === 'token') {
            return value;
        }
    }
    return null;
}

fetch('/api/login', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    }
})
    .then(response => response.json())
    .then(data => {
        if (!data.error) {
            const token = data.token
            saveTokenToCookie(token);
        }
    })
    .catch(error => {
        console.error('API request error:', error);
    });

// Lấy token từ localStorage hoặc cookies (tùy theo cách bạn đã lưu token)
const token = getTokenFromCookie();
// Kiểm tra xem token có tồn tại
if (token) {
    fetch('/api/itemdata', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
        }
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Request Failed!');
            }
        })
        .then(json => {
            console.log(JSON.stringify(json));
        })
        .catch(error => {
            console.error(error);
        });
} else {
    // Xử lý trường hợp token không tồn tại, ví dụ: đăng nhập lại hoặc hiển thị thông báo
    console.log('Token Is Not Available. Please Login.');
}

function clearToken() {
    saveTokenToCookie('', -1); // -1 làm cho cookie hết hạn ngay lập tức
}

function logout() {
    fetch('/api/logout', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (response.ok) {
                window.location.href = '/login';
                clearToken();
            } else {
                console.error('Logout failed!');
            }
        })
        .catch(error => {
            console.error('API request error:', error);
        });
}

const logoutButton = document.getElementById('logoutButton');
if (logoutButton) {
    logoutButton.addEventListener('click', logout);
}

