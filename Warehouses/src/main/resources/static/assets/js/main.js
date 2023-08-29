// main.js

// Save token to local storage
function saveToken(token) {
    localStorage.setItem('token', token);
}

// Lấy token từ Local Storage
function getToken() {
    return localStorage.getItem('token');
}
saveToken(token);

// Retrieve token from local storage
const savedToken = getToken();
console.log('Saved Token:', savedToken);

// Example: Using the fetchWithToken function
fetchWithToken('/login', 'POST', { key: 'value' })
    .then(data => {
        // Handle API response data
        console.log('API Response:', data);
    })
    .catch(error => {
        // Handle API request error
        console.error('API Request Error:', error);
    });

// Example: Logging out and removing token
logout();
