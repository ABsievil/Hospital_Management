const baseUrl = `http://localhost:8080`;

fetch(
    `${baseUrl}/api/v1/Medicine/getAllMedicine`
)
    .then((response) => response.json())
    .then((fetchedData) => {
        const tableBody = document.getElementById('tableBody');
        let stt = 1;
        if (fetchedData && fetchedData.data) {
            fetchedData.data.forEach((medicine) => {
                const row = document.createElement('tr');
                
                const index = document.createElement('td');
                index.textContent = stt++;
                row.appendChild(index);

                const name = document.createElement('td');
                name.textContent = medicine.name;
                row.appendChild(name);

                const status = document.createElement('td');
                if (medicine.available) {
                    status.style = "text-align: center; color:green;";
                    status.textContent = '✓';
                } else {
                    status.style = "text-align: center; color:red;";
                    status.textContent = '✕';
                }
                row.appendChild(status);

                const component = document.createElement('td');
                component.textContent = medicine.components;
                row.appendChild(component);

                const usage = document.createElement('td');
                usage.textContent = medicine.uses;
                row.appendChild(usage);

                const note = document.createElement('td');
                note.textContent = medicine.note;
                row.appendChild(note);

                tableBody.appendChild(row);
            });
        } else {
            console.error('Error: fetchedData is null or undefined');
        }
    })
    .catch((error) => {
        console.error('Error fetching schedules:', error);
    });
