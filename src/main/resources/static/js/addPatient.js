const baseUrl = `http://localhost:8080`;

function addPatient() {
    const information = {
        firstName: document.getElementById('name').value,
        lastName: '',
        gender: document.getElementById('gender').value == "Nam" ? true : false,
        occupation: document.getElementById('occupation').value,
        dateOfBirth: document.getElementById('birth').value,
        nationalID: document.getElementById('nationalID').value,
        ethnic: document.getElementById('ethnic').value,
        religion: document.getElementById('religion').value,
        address: document.getElementById('address').value,
        email: document.getElementById('email').value,
        phoneNumber: document.getElementById('phone').value,
        hiCode: document.getElementById('hiCode').value,
        hiStartDate: document.getElementById('hiStart').value,
        hiEndDate: document.getElementById('hiEnd').value,
        relative: document.getElementById('relative').value,
        relationWithRelative: document.getElementById('relation').value,
        relativePhoneNumber1: document.getElementById('relativePhone').value,
        relativePhoneNumber2: document.getElementById('relativePhone2').value,
        relativeAddress: document.getElementById('relativeAddress').value,
        relativeOccupation: document.getElementById('relativeOccupation').value,
    };
    const newPatient = {
        status: "OK",
        information: information
    };
    // console.log(information);
    fetch(`${baseUrl}/api/v1/Patient/insertPatient`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(newPatient),
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
