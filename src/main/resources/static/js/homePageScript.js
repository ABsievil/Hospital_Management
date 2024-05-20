// Lấy lịch từ Database theo ngày
const baseUrl = 'https://ngoc-chau-hospital.azurewebsites.net';
const today = new Date();
const timeZoneOffset = 7 * 60 * 60 * 1000; // 7 hours offset for GMT+7
const todayInGMTPlus7 = new Date(today.getTime() + timeZoneOffset);
// console.log(today.toISOString());
// console.log(todayInGMTPlus7.toISOString());
const startOfDay = new Date(todayInGMTPlus7.getTime());
startOfDay.setUTCHours(0, 0, 0, 0);

const endOfDay = new Date(todayInGMTPlus7.getTime());
endOfDay.setUTCHours(23, 59, 59, 999);
// console.log(startOfDay.toISOString());
// console.log(endOfDay.toISOString());



fetch(
    `${baseUrl}/getScheduleBetweenTime?startTime=${startOfDay.toISOString()}&endTime=${endOfDay.toISOString()}`
)
    .then((response) => response.json())
    .then((fetchedData) => {
        const scheduleBody = document.getElementById('scheduleBody');
        let stt = 1;
        if (fetchedData && fetchedData.data) {
            fetchedData.data.forEach((schedule) => {
                const row = document.createElement('tr');
                // STT
                const index = document.createElement('td');
                index.textContent = stt++;
                row.appendChild(index);
                // Tên bác sĩ
                const doctor = document.createElement('td');
                doctor.textContent = schedule.employee.information.lastName + " " + schedule.employee.information.firstName;
                row.appendChild(doctor);
                // Tên bệnh nhân
                const patient = document.createElement('td');
                patient.textContent = schedule.patientLastName + " " + schedule.patientFirstName;
                row.appendChild(patient);
                // Số phòng
                const room = document.createElement('td');
                room.textContent = schedule.room;
                row.appendChild(room);
                // Thời gian bắt đầu
                const startTime = document.createElement('td');
                startTime.textContent = new Date(schedule.startTime).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
                row.appendChild(startTime);
                // Thời gian kết thúc
                const endTime = document.createElement('td');
                endTime.textContent = new Date(schedule.endTime).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
                row.appendChild(endTime);
                // Chi tiết
                const detail = document.createElement('td');
                detail.textContent = schedule.description;
                row.appendChild(detail);

                // Thêm hàng vào bảng
                scheduleBody.appendChild(row);
            });
        } else {
            console.error('Error: fetchedData is null or undefined');
        }
    })
    .catch((error) => {
        console.error('Error:', error);
    });
