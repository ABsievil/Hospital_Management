const baseUrl = `https://ngoc-chau-hospital.azurewebsites.net`;

const employeeId = document.getElementById('employeeId').textContent;

const daysOfWeek = ['CN', '2', '3', '4', '5', '6', '7'];

const today = new Date();
const timeZoneOffset = 7 * 60 * 60 * 1000; // 7 hours offset for GMT+7
const todayInGMTPlus7 = new Date(today.getTime() + timeZoneOffset);

const startOfDay = new Date(todayInGMTPlus7.getTime());
startOfDay.setUTCHours(0, 0, 0, 0);

const endOfDay = new Date(todayInGMTPlus7.getTime());
endOfDay.setUTCHours(23, 59, 59, 999);

const check = startOfDay.getDay() == 0 ? 7 : startOfDay.getDay();
const startOfWeek = new Date(
    startOfDay.getTime() - 1000 * 60 * 60 * 24 * check
);

const dates = [];

for (let i = 1; i <= 7; i++) {
    const date = new Date(startOfWeek.getTime() + 1000 * 60 * 60 * 24 * i);
    dates.push(date);
    // console.log(date.toISOString());
}

const noteContent = document.getElementById('noteContent');
noteContent.textContent += `Hôm nay là Thứ ${
    daysOfWeek[todayInGMTPlus7.getUTCDay()]
}, 
    ngày ${todayInGMTPlus7
        .toISOString()
        .split('T')[0]
        .split('-')
        .reverse()
        .join('/')}`;

const calendarIcon = document.createElement('i');
calendarIcon.className = 'bx bxs-calendar';
noteContent.prepend(calendarIcon);

const monday = document.getElementById('monday');
monday.textContent = dates[0]
    .toISOString()
    .split('T')[0]
    .split('-')
    .reverse()
    .join('/');
const tuesday = document.getElementById('tuesday');
tuesday.textContent = dates[1]
    .toISOString()
    .split('T')[0]
    .split('-')
    .reverse()
    .join('/');
const wednesday = document.getElementById('wednesday');
wednesday.textContent = dates[2]
    .toISOString()
    .split('T')[0]
    .split('-')
    .reverse()
    .join('/');
const thursday = document.getElementById('thursday');
thursday.textContent = dates[3]
    .toISOString()
    .split('T')[0]
    .split('-')
    .reverse()
    .join('/');
const friday = document.getElementById('friday');
friday.textContent = dates[4]
    .toISOString()
    .split('T')[0]
    .split('-')
    .reverse()
    .join('/');
const saturday = document.getElementById('saturday');
saturday.textContent = dates[5]
    .toISOString()
    .split('T')[0]
    .split('-')
    .reverse()
    .join('/');
const sunday = document.getElementById('sunday');
sunday.textContent = dates[6]
    .toISOString()
    .split('T')[0]
    .split('-')
    .reverse()
    .join('/');

fetch(
    `${baseUrl}/api/v1/EmployeeSchedule/getScheduleByEmployeeIdBetweenTime/${employeeId}?startTime=${startOfDay.toISOString()}&endTime=${endOfDay.toISOString()}`
)
    .then((response) => response.json())
    .then((fetchedData) => {
        // console.log(fetchedData);
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

function mondaySchedule() {
    const start = new Date(dates[0].getTime());
    const end = new Date(dates[0].getTime());
    start.setUTCHours(0, 0, 0, 0);
    end.setUTCHours(23, 59, 59, 999);
    // console.log(start.toISOString());
    // console.log(end.toISOString());
    fetch(
        `${baseUrl}/api/v1/EmployeeSchedule/getScheduleByEmployeeIdBetweenTime/${employeeId}?startTime=${start.toISOString()}&endTime=${end.toISOString()}`
    )
        .then((response) => response.json())
        .then((fetchedData) => {
            const timeLineBox = document.getElementById('timeLineBox');
            timeLineBox.innerHTML = '';
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
}

function tuesdaySchedule() {
    const start = new Date(dates[1].getTime());
    const end = new Date(dates[1].getTime());
    start.setUTCHours(0, 0, 0, 0);
    end.setUTCHours(23, 59, 59, 999);
    // console.log(start.toISOString());
    // console.log(end.toISOString());
    fetch(
        `${baseUrl}/api/v1/EmployeeSchedule/getScheduleByEmployeeIdBetweenTime/${employeeId}?startTime=${start.toISOString()}&endTime=${end.toISOString()}`
    )
        .then((response) => response.json())
        .then((fetchedData) => {
            const timeLineBox = document.getElementById('timeLineBox');
            timeLineBox.innerHTML = '';
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
}

function wednesdaySchedule() {
    const start = new Date(dates[2].getTime());
    const end = new Date(dates[2].getTime());
    start.setUTCHours(0, 0, 0, 0);
    end.setUTCHours(23, 59, 59, 999);
    // console.log(start.toISOString());
    // console.log(end.toISOString());
    fetch(
        `${baseUrl}/api/v1/EmployeeSchedule/getScheduleByEmployeeIdBetweenTime/${employeeId}?startTime=${start.toISOString()}&endTime=${end.toISOString()}`
    )
        .then((response) => response.json())
        .then((fetchedData) => {
            const timeLineBox = document.getElementById('timeLineBox');
            timeLineBox.innerHTML = '';
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
}

function thursdaySchedule() {
    const start = new Date(dates[3].getTime());
    const end = new Date(dates[3].getTime());
    start.setUTCHours(0, 0, 0, 0);
    end.setUTCHours(23, 59, 59, 999);
    // console.log(start.toISOString());
    // console.log(end.toISOString());
    fetch(
        `${baseUrl}/api/v1/EmployeeSchedule/getScheduleByEmployeeIdBetweenTime/${employeeId}?startTime=${start.toISOString()}&endTime=${end.toISOString()}`
    )
        .then((response) => response.json())
        .then((fetchedData) => {
            const timeLineBox = document.getElementById('timeLineBox');
            timeLineBox.innerHTML = '';
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
}

function fridaySchedule() {
    const start = new Date(dates[4].getTime());
    const end = new Date(dates[4].getTime());
    start.setUTCHours(0, 0, 0, 0);
    end.setUTCHours(23, 59, 59, 999);
    // console.log(start.toISOString());
    // console.log(end.toISOString());
    fetch(
        `${baseUrl}/api/v1/EmployeeSchedule/getScheduleByEmployeeIdBetweenTime/${employeeId}?startTime=${start.toISOString()}&endTime=${end.toISOString()}`
    )
        .then((response) => response.json())
        .then((fetchedData) => {
            const timeLineBox = document.getElementById('timeLineBox');
            timeLineBox.innerHTML = '';
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
}

function saturdaySchedule() {
    const start = new Date(dates[5].getTime());
    const end = new Date(dates[5].getTime());
    start.setUTCHours(0, 0, 0, 0);
    end.setUTCHours(23, 59, 59, 999);
    // console.log(start.toISOString());
    // console.log(end.toISOString());
    fetch(
        `${baseUrl}/api/v1/EmployeeSchedule/getScheduleByEmployeeIdBetweenTime/${employeeId}?startTime=${start.toISOString()}&endTime=${end.toISOString()}`
    )
        .then((response) => response.json())
        .then((fetchedData) => {
            const timeLineBox = document.getElementById('timeLineBox');
            timeLineBox.innerHTML = '';
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
}


function sundaySchedule() {
    const start = new Date(dates[6].getTime());
    const end = new Date(dates[6].getTime());
    start.setUTCHours(0, 0, 0, 0);
    end.setUTCHours(23, 59, 59, 999);
    // console.log(start.toISOString());
    // console.log(end.toISOString());
    fetch(
        `${baseUrl}/api/v1/EmployeeSchedule/getScheduleByEmployeeIdBetweenTime/${employeeId}?startTime=${start.toISOString()}&endTime=${end.toISOString()}`
    )
        .then((response) => response.json())
        .then((fetchedData) => {
            const timeLineBox = document.getElementById('timeLineBox');
            timeLineBox.innerHTML = '';
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
}

document
    .getElementById('infoForm')
    .addEventListener('submit', function (event) {
        // prevent default behavior of submit
        event.preventDefault();

        const formData = new FormData(event.target);

        const newSchedule = {
            startTime: formData.get('date') + 'T' + formData.get('start'),
            endTime: formData.get('date') + 'T' + formData.get('end'),
            patientId: formData.get('name'),
            title: formData.get('title'),
            description: formData.get('content'),
            room: formData.get('room')
        };
        
        // console.log(newSchedule);

        fetch(
            `${baseUrl}/api/v1/EmployeeSchedule/insertSchedule/${employeeId}`,
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(newSchedule),
            }
        )
            .then((response) => {
                return response.json(); // Parse the response as JSON (if applicable)
            })
            .then((responseData) => {
                // Use the parsed response data (responseData)
                console.log('Response:', responseData);
                const status = document.getElementById('postStatus');
                const message = document.getElementById('postMessage');
                status.textContent = responseData.status;
                message.textContent = responseData.message;
                // You can handle the response data here (e.g., display success message)
            })
            .catch((error) => {
                // Handle errors
                console.error('Error:', error);
            });
    });
