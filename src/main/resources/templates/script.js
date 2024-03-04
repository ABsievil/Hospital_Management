function login() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // Gửi dữ liệu đến backend
    fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
    })
    .then(response => response.json())
    .then(data => {
        // Xử lý phản hồi từ backend
        if (data.success) {
            alert('Login finished!');
        } else {
            alert('Login failed. Please check your login information again!');
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
