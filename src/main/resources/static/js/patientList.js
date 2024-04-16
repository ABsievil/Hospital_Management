const baseUrl = `http://localhost:8080`;

fetch(`${baseUrl}/api/v1/Patient/getAllPatient`)
    .then((response) => response.json())
    .then((fetchedData) => {
        const tableBody = document.getElementById('tableBody');
        if (fetchedData && fetchedData.data) {
            fetchedData.data.forEach((patient) => {
                const tr = document.createElement('tr');
                const trContent = `
                    <td>${patient.id}</td>
                    <td>${
                        patient.information.lastName +
                        ' ' +
                        patient.information.firstName
                    }</td>
                    <td>${
                        patient.status == 'OK'
                            ? 'Bình thường'
                            : patient.status == 'ACTIVE'
                            ? 'Đang chữa bệnh'
                            : 'Đã chết'
                    }</td>
                    <td><button class="link-button">Xem thông tin</button></td>
                `;
                tr.innerHTML = trContent;
                tableBody.appendChild(tr);
            });
        } else {
            console.error('Error: fetchedData is null or undefined');
        }
        buttons = document.querySelectorAll('.link-button');
        [...buttons].forEach(myfunc);

        function myfunc(item) {
            item.addEventListener('click', function () {
                window.location.href = '/patientlist/patientinfor';
            });
        }
    })
    .catch((error) => {
        console.error('Error:', error);
    });
