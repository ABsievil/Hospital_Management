const baseUrl = `http://localhost:8080`;

const patientId = document.getElementById('patientId').textContent;

fetch(
    `${baseUrl}/api/v1/TreatmentHistory/getTreatmentHistoryByPatientId/${patientId}`
)
    .then((response) => response.json())
    .then((fetchedData) => {
        if (fetchedData && fetchedData.data) {
            const treatmentHistory =
                document.getElementById('treatmentHistory');
            
            fetchedData.data.forEach((th) => {
                const content = document.createElement('div');
                content.classList.add('content');
                const describeContent = document.createElement('div');
                describeContent.classList.add('describe-content');
                describeContent.innerHTML = `
                    <p>Bác sĩ: ${
                        th.employeeLastName + ' ' + th.employeeFirstName
                    }</p>
                    <p>Ngày kê đơn : ${th.admissionDate}</p>
                    <p>Bệnh nhân : ${
                        th.patientLastName + ' ' + th.patientFirstName
                    }</p>
                    <p>Bệnh: ${th.disease}</p>
                    <p>Chi phí: ${th.cost + '$'}</p>
                    <p>Đánh giá của bệnh nhân: ${th.rate}</p>
                `;
                content.appendChild(describeContent);
                const numerousContent = document.createElement('div');
                numerousContent.classList.add('numerous-content');
                const h1 = document.createElement('h1');
                h1.textContent = "Đơn thuốc";
                const table = document.createElement('table');
                table.classList.add('table');
                const thead = document.createElement('thead');
                thead.innerHTML = `
                    <tr>
                        <th>STT</th>
                        <th>Tên thuốc</th>
                        <th>Số lượng</th>
                    </tr>
                `;
                console.log(th.medicationList);
                const tbody = document.createElement('tbody');
                for (let i = 0; i < th.medicationList.length; i++) {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${i + 1}</td>
                        <td>${th.medicationList[i]}</td>
                        <td>${th.medicationQuantity[i]}</td>
                    `;
                    tbody.appendChild(row);
                }
                table.appendChild(thead);
                table.appendChild(tbody);
                numerousContent.appendChild(h1);
                numerousContent.appendChild(table);
                content.appendChild(numerousContent);
                treatmentHistory.appendChild(content);
                
            });
        }
    });
