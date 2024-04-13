

const baseUrl = `http://localhost:8080`;

const employeeId = document.getElementById('employeeId').textContent;

const daysOfWeek = ['CN', '2', '3', '4', '5', '6', '7'];

const today = new Date();
const timeZoneOffset = 7 * 60 * 60 * 1000; // 7 hours offset for GMT+7
const todayInGMTPlus7 = new Date(today.getTime() + timeZoneOffset);

const startOfDay = new Date(todayInGMTPlus7.getTime());
startOfDay.setUTCHours(0, 0, 0, 0);

const endOfDay = new Date(todayInGMTPlus7.getTime());
endOfDay.setUTCHours(23, 59, 59, 999);

const startOfWeek = new Date(startOfDay.getTime() - 1000 * 60 * 60 * 24 * startOfDay.getDay());
const endOfWeek = new Date(startOfWeek.getTime() + 1000 * 60 * 60 * 24 * 7);
endOfWeek.setUTCHours(23, 59, 59, 999);

console.log(startOfWeek.toISOString());
console.log(endOfWeek.toISOString());

const noteContent = document.getElementById('noteContent');
noteContent.textContent += `Hôm nay là Thứ ${daysOfWeek[todayInGMTPlus7.getUTCDay()]}, 
    ngày ${todayInGMTPlus7.toISOString().split('T')[0].split('-').reverse().join('/')}`;

const calendarIcon = document.createElement('i');
calendarIcon.className = 'bx bxs-calendar';

noteContent.prepend(calendarIcon);


fetch(
    `${baseUrl}/api/v1/EmployeeSchedule/getScheduleByEmployeeIdBetweenTime/${employeeId}?startTime=${startOfWeek.toISOString()}&endTime=${endOfWeek.toISOString()}`
)
    .then((response) => response.json())
    .then((fetchedData) => {
        const scheduleBody = document.getElementById('scheduleBody');
        if (fetchedData && fetchedData.data) {
            fetchedData.data.forEach((schedule) => {
                const row = document.createElement('tr');
                // thứ
                const weekDay = document.createElement('td');
                const dayOfWeek = new Date(schedule.startTime.split('T')[0]);
                weekDay.textContent = daysOfWeek[dayOfWeek.getDay()];
                row.appendChild(weekDay);
                // ngày
                const date = document.createElement('td');
                date.textContent = schedule.startTime.split('T')[0].split('-').reverse().join('/');
                if (date.textContent == todayInGMTPlus7.toISOString().split('T')[0].split('-').reverse().join('/')) {
                    row.style = "color: black; background-color: rgb(219, 213, 213);";
                }
                row.appendChild(date);
                // title
                const title = document.createElement('td');
                title.textContent = schedule.title;
                row.appendChild(title);
                // start time
                const startTime = document.createElement('td');
                startTime.textContent = schedule.startTime
                    .split('T')[1]
                    .slice(0, 5);
                row.appendChild(startTime);
                // end time
                const endTime = document.createElement('td');
                endTime.textContent = schedule.endTime
                    .split('T')[1]
                    .slice(0, 5);
                row.appendChild(endTime);
                // room
                const room = document.createElement('td');
                room.textContent = schedule.room;
                row.appendChild(room);
                // add row to schedule
                scheduleBody.appendChild(row);

            });
        }    
    })
    .catch((error) => {
        console.error('Error fetching schedules:', error);
    });


fetch(
    `${baseUrl}/api/v1/EmployeeSchedule/getScheduleByEmployeeIdBetweenTime/${employeeId}?startTime=${startOfDay.toISOString()}&endTime=${endOfDay.toISOString()}`
)
    .then((response) => response.json())
    .then((fetchedData) => {
        const timeLineBox = document.getElementById('timeLineBox');
        if (fetchedData && fetchedData.data) {
            // console.log(fetchedData);
            fetchedData.data.forEach((schedule) => {
                // thêm vô lịch bên phải
                const timeLineContent = document.createElement('div');
                timeLineContent.classList.add('timeline-content');

                const content = document.createElement('div');
                content.classList.add('content');
                timeLineContent.appendChild(content);

                const startTime = document.createElement('td');
                startTime.textContent = schedule.startTime
                    .split('T')[1]
                    .slice(0, 5);

                const endTime = document.createElement('td');
                endTime.textContent = schedule.endTime
                    .split('T')[1]
                    .slice(0, 5);
                const year = document.createElement('div');
                year.classList.add('year');
                year.textContent =
                    startTime.textContent + ' - ' + endTime.textContent;
                content.appendChild(year);

                const description = document.createElement('p');
                description.textContent = schedule.description;
                content.appendChild(description);

                timeLineBox.appendChild(timeLineContent);

            });
        }    
    })
    .catch((error) => {
        console.error('Error fetching schedules:', error);
    });