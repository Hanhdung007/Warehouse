// localStorageUtils.js

// Lưu token vào Local Storage
function saveToken(token) {
    localStorage.setItem('token', token);
}

// Lấy token từ Local Storage
function getToken() {
    return localStorage.getItem('token');
}

// Thực hiện yêu cầu API với token
function fetchWithToken(url, method, data) {
    const token = getToken();
    const headers = {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
    };

    const requestOptions = {
        method: method,
        headers: headers,
        body: JSON.stringify(data)
    };

    return fetch(url, requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error('Login failed!');
            }
            return response.json();
        })
        .catch(error => {
            console.error('API request error:', error);
        });
}

// Xóa token khỏi Local Storage khi đăng xuất
function logout() {
    localStorage.removeItem('token');
}

