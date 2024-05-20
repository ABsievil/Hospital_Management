const baseUrl = `https://ngoc-chau-hospital.azurewebsites.net`;

// Send GET request to backend API
const employeeID = document.getElementById('employeeID').textContent;

// send request to change username infor
document.addEventListener('DOMContentLoaded', () => {
  document
      .getElementById('changeUsername')
      .addEventListener('submit', function (event) {
          // prevent default behavior of submit
          event.preventDefault();
  
          //const formData = new FormData(event.target);
  
          const oldUsername = document.getElementById("oldUsername");
          const newUsername = document.getElementById("newUsername");
          const pwd = document.getElementById("pwd");
  
          const changeUsername = {
            oldUsername: oldUsername.value,
            newUsername: newUsername.value,
            pwd: pwd.value
          };
  
          fetch(
              `${baseUrl}/api/v1/Employee/changeUsername/${employeeID}`,
              {
                  method: 'POST',
                  headers: {
                      'Content-Type': 'application/json',
                  },
                  body: JSON.stringify(changeUsername),
              }
          )
              .then((response) => {
                  return response.json(); // Parse the response as JSON (if applicable)
              })
              .then((responseData) => {
                  // Use the parsed response data (responseData)
                  console.log('Response:', responseData);
                  const status = document.getElementById('postStatus1');
                  const message = document.getElementById('postMessage1');
                  status.textContent = responseData.status;
                  message.textContent = responseData.message;
                  // You can handle the response data here (e.g., display success message)
  
                  // Show the BE messages
                  status.style.display = 'block';
                  message.style.display = 'block';
              })
              .catch((error) => {
                  // Handle errors
                  console.error('Error:', error);
              });
      });
  });

  
// send request to change password infor
document.addEventListener('DOMContentLoaded', () => {
    document
        .getElementById('changePwd')
        .addEventListener('submit', function (event) {
            // prevent default behavior of submit
            event.preventDefault();
    
            //const formData = new FormData(event.target);
    
            const oldPassword = document.getElementById("oldPassword");
            const newPassword = document.getElementById("newPassword");
    
            const changePwd = {
              oldPassword: oldPassword.value,
              newPassword: newPassword.value
            };
            
            fetch(
                `${baseUrl}/api/v1/Employee/changePwd/${employeeID}`,
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(changePwd),
                }
            )
                .then((response) => {
                    return response.json(); // Parse the response as JSON (if applicable)
                })
                .then((responseData) => {
                    // Use the parsed response data (responseData)
                    console.log('Response:', responseData);
                    const status = document.getElementById('postStatus2');
                    const message = document.getElementById('postMessage2');
                    status.textContent = responseData.status;
                    message.textContent = responseData.message;
                    // You can handle the response data here (e.g., display success message)
    
                    // Show the BE messages
                    status.style.display = 'block';
                    message.style.display = 'block';
                })
                .catch((error) => {
                    // Handle errors
                    console.error('Error:', error);
                });
        });
    });
  