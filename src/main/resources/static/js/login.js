const baseUrl = `http://localhost:8080`;

document.addEventListener('DOMContentLoaded', () => {
    document
        .getElementById('loginForm')
        .addEventListener('submit', function (event) {
            // prevent default behavior of submit
            event.preventDefault();

            const formData = new FormData(event.target);

            const loginRequest = {
                username: formData.get("username"),
                password: formData.get("password")
            }

            fetch(
                `${baseUrl}/api/authenticate`,
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(loginRequest),
                }
            )
            .then(response => response.json())
            .then(data => {
                const responsedData = data.data; // token value

                localStorage.setItem("token", responsedData);
                window.location.href = '/home';
            })
            .catch(error => console.error('Error:', error));
            });
    });