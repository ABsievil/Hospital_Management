const baseUrl = `http://localhost:8080`;

function md5(string) {
    return CryptoJS.MD5(string).toString();
}

fetch(`${baseUrl}/api/v1/Patient/getAllPatient`)
    .then((response) => response.json())
    .then((fetchedData) => {
        const tableBody = document.getElementById('tableBody');
        let stt = 1;
        if (fetchedData && fetchedData.data) {
            fetchedData.data.forEach((patient) => {
                const tr = document.createElement('tr');
                const trContent = `
                    <td>${stt++}</td>
                    <td style="display: none">${patient.id}</td>
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
                    <td><button class="link-button" data-patient-id="${
                        patient.id
                    }">Xem thông tin</button></td>
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
                const patientId = this.getAttribute('data-patient-id');
                const hashedPatientId = md5(patientId);
                window.location.href = `/patientlist/patientinfor/${hashedPatientId}`;
            });
        }
    })
    .catch((error) => {
        console.error('Error:', error);
    });
