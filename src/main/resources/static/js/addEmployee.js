const baseUrl = `http://localhost:8080`;

function addEmployee() {
    const information = {
        firstName: document.getElementById('name').value,
        lastName: '',
        gender: document.getElementById('gender').value == "Nam" ? true : false,
        occupation: document.getElementById('occupation').value,
        dateOfBirth: document.getElementById('birth').value,
        nationalID: document.getElementById('nationalId').value,
        ethnic: document.getElementById('ethnic').value,
        religion: document.getElementById('religion').value,
        address: document.getElementById('address').value,
        email: document.getElementById('email').value,
        phoneNumber: document.getElementById('phone').value,
        hiCode: document.getElementById('hiCode').value,
        hiStartDate: document.getElementById('hiStart').value,
        hiEndDate: document.getElementById('hiEnd').value,
        relative: document.getElementById('relativeName').value,
        relationWithRelative: document.getElementById('relation').value,
        relativePhoneNumber1: document.getElementById('relativePhone').value,
        relativePhoneNumber2: document.getElementById('relativePhone2').value,
        relativeAddress: document.getElementById('relativeAddress').value,
        relativeOccupation: document.getElementById('relativeOccupation').value,
    };
    const newEmployee = {
        personalCode: document.getElementById('personalCode').value,
        username: document.getElementById('username').value,
        password: document.getElementById('password').value,
        role: document.getElementById('role').value,
        specialty: document.getElementById('specialty').value,
        position: document.getElementById('position').value,
        academicRank: document.getElementById('academicRank').value,
        degree: document.getElementById('degree').value,
        trainingPlace: document.getElementById('trainingPlace').value,
        information: information,
    };
    // console.log(information);
    fetch(`${baseUrl}/api/v1/Employee/insertEmployee`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(newEmployee),
    })
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            console.log(data);
        })
        .catch((error) => {
            console.log('Error: ', error);
        });
}
