const baseUrl = `http://localhost:8080`;


fetch(
    `${baseUrl}/api/v1/Employee/getDoctor`
)
    .then((response) => response.json())
    .then((fetchedData) => {
        const cardContainer = document.getElementById('cardContainer');
        if (fetchedData && fetchedData.data) {
            fetchedData.data.forEach((doctor) => {
                const card = document.createElement('div');
                card.classList.add('card');
                const cardContent = `
                <img src="/images/avatar.jpg" alt="avt" class="avatar" onclick="location.href='/user/infor'">
                <span class="staff-infor" onclick="location.href='/user/infor'">
                    <div class="staff-name">${doctor.information.lastName + " " + doctor.information.firstName}</div>
                    <div class="position">${doctor.position}</div>
                    <div class="education-infor">
                        <div>
                            <i class="fas fa-id-card" aria-hidden="true"></i>
                            <span>${doctor.id}</span>
                        </div>
                        <div>
                            <i class="fas fa-user-tie" aria-hidden="true"></i>
                            <span>${doctor.academicRank}</span>
                        </div>
                        <div>
                            <i class="fas fa-graduation-cap" aria-hidden="true"></i>
                            <span>${doctor.degree}</span>
                        </div>
                        <div>
                            <i class="fas fa-university" aria-hidden="true"></i>
                            <span>${doctor.trainingPlace}</span>
                        </div>
                    </div>
                </span>
                `;
                card.innerHTML = cardContent;
                cardContainer.appendChild(card);
            });
        } else {
            console.error('Error: fetchedData is null or undefined');
        }
    })
    .catch((error) => {
        console.error('Error:', error);
    });