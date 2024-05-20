const baseUrl = `https://ngoc-chau-hospital.azurewebsites.net`;


fetch(
    `${baseUrl}/api/v1/Equipment/getBigEquipment`
)
    .then((response) => response.json())
    .then((fetchedData) => {
        const bigTable = document.getElementById('bigTable');
        let stt = 1;
        if (fetchedData && fetchedData.data) {
            fetchedData.data.forEach((equipment) => {
                const row = document.createElement('tr');
                // STT
                const index = document.createElement('td');
                index.textContent = stt++;
                row.appendChild(index);
                // name
                const name = document.createElement('td');
                name.textContent = equipment.name;
                row.appendChild(name);
                // room
                const room = document.createElement('td');
                room.textContent = equipment.room;
                row.appendChild(room);
                // status
                const available = document.createElement('td');
                available.textContent = equipment.available? "Sẵn sàng" : "Đang sửa chữa";
                row.appendChild(available);
                // Thêm hàng vào bảng
                bigTable.appendChild(row);
            });
        } else {
            console.error('Error: fetchedData is null or undefined');
        }
    })
    .catch((error) => {
        console.error('Error fetching schedules:', error);
    });


fetch(
    `${baseUrl}/api/v1/Equipment/getSmallEquipment`
)
    .then((response) => response.json())
    .then((fetchedData) => {
        const smallTable = document.getElementById('smallTable');
        let stt = 1;
        if (fetchedData && fetchedData.data) {
            fetchedData.data.forEach((equipment) => {
                const row = document.createElement('tr');
                // STT
                const index = document.createElement('td');
                index.textContent = stt++;
                row.appendChild(index);
                // name
                const name = document.createElement('td');
                name.textContent = equipment.name;
                row.appendChild(name);
                // status
                const available = document.createElement('td');
                available.textContent = equipment.available? "Còn" : "Đã Hết";
                row.appendChild(available);
                // room
                const room = document.createElement('td');
                room.textContent = equipment.room;
                row.appendChild(room);
                // Thêm hàng vào bảng
                smallTable.appendChild(row);
            });
        } else {
            console.error('Error: fetchedData is null or undefined');
        }
    })
    .catch((error) => {
        console.error('Error fetching schedules:', error);
    });