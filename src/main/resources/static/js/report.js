const baseUrl = `http://localhost:8080`;

const today = new Date();
const timeZoneOffset = 7 * 60 * 60 * 1000; // 7 hours offset for GMT+7
const todayInGMTPlus7 = new Date(today.getTime() + timeZoneOffset);

const startOfDay = new Date(todayInGMTPlus7.getTime());
startOfDay.setUTCHours(0, 0, 0, 0);

const startOfMonth = new Date(startOfDay.getTime());
startOfMonth.setDate(1);

const endOfMonth = new Date(startOfMonth.getTime());
endOfMonth.setMonth(startOfMonth.getMonth() + 1);

const startOfLastMonth = new Date(startOfMonth.getTime());
startOfLastMonth.setMonth(startOfMonth.getMonth() - 1);
// console.log(startOfMonth.toISOString());
// console.log(endOfMonth.toISOString());

fetch(
    `${baseUrl}/api/v1/TreatmentHistory/getTreatmentHistoryBetweenDate?startDate=${
        startOfMonth.toISOString().split('T')[0]
    }&endDate=${endOfMonth.toISOString().split('T')[0]}`
)
    .then((response) => response.json())
    .then((fetchedData) => {
        if (fetchedData && fetchedData.data) {
            const tableBody = document.getElementById('tableBody');
            let totalCost = 0;
            let count = 0;
            let patientCount = 0;
            let patient = new Map();
            let totalRating = 0;
            fetchedData.data.forEach((treatmentHistory) => {
                const row = document.createElement('tr');
                count++;
                patient.set(
                    treatmentHistory.patientId,
                    (patient.get(treatmentHistory.patientId) || 0) + 1
                );
                const day = document.createElement('td');
                day.textContent = treatmentHistory.admissionDate
                    .split('-')
                    .reverse()
                    .join('/');
                row.appendChild(day);

                const name = document.createElement('td');
                name.textContent =
                    treatmentHistory.patientLastName +
                    ' ' +
                    treatmentHistory.patientFirstName;
                row.appendChild(name);

                const cost = document.createElement('td');
                cost.textContent = treatmentHistory.cost;
                totalCost += treatmentHistory.cost;
                row.appendChild(cost);

                const rate = document.createElement('td');
                switch (treatmentHistory.rate) {
                    case 'VERYGOOD':
                        rate.textContent = 5;
                        break;
                    case 'GOOD':
                        rate.textContent = 4;
                        break;
                    case 'NORMAL':
                        rate.textContent = 3;
                        break;
                    case 'BAD':
                        rate.textContent = 2;
                        break;
                    case 'VERYBAD':
                        rate.textContent = 1;
                        break;
                    default:
                        break;
                }
                totalRating += Number(rate.textContent);
                const starIcon = document.createElement('i');
                starIcon.className = 'bx bxs-star';
                rate.append(starIcon);
                row.appendChild(rate);
                tableBody.appendChild(row);
            });
            patientCount = patient.size;
            const numPatient = document.getElementById('numPatient');
            numPatient.textContent = patientCount;
            const revenue = document.getElementById('revenue');
            revenue.textContent = totalCost.toFixed(2);
            const rating = document.getElementById('rating');
            rating.textContent = (totalRating / count).toFixed(2);
            let reExamCount = 0;
            patient.forEach((value, key) => {
                if (value >= 2) {
                    reExamCount++;
                }
            });
            const reExam = document.getElementById('reExam');
            reExam.textContent = (reExamCount / patientCount).toFixed(2) * 100;
        }
    })
    .catch((error) => {
        console.error('Error fetching schedules:', error);
    });

fetch(
    `${baseUrl}/api/v1/TreatmentHistory/getTreatmentHistoryBetweenDate?startDate=${
        startOfLastMonth.toISOString().split('T')[0]
    }&endDate=${startOfMonth.toISOString().split('T')[0]}`
)
    .then((response) => response.json())
    .then((fetchedData) => {
        if (fetchedData && fetchedData.data) {
            let totalCost = 0;
            let count = 0;
            let patientCount = 0;
            let patient = new Map();
            let totalRating = 0;
            fetchedData.data.forEach((treatmentHistory) => {
                count++;
                patient.set(
                    treatmentHistory.patientId,
                    (patient.get(treatmentHistory.patientId) || 0) + 1
                );
                totalCost += treatmentHistory.cost;
                const rate = document.createElement('td');
                switch (treatmentHistory.rate) {
                    case 'VERYGOOD':
                        rate.textContent = 5;
                        break;
                    case 'GOOD':
                        rate.textContent = 4;
                        break;
                    case 'NORMAL':
                        rate.textContent = 3;
                        break;
                    case 'BAD':
                        rate.textContent = 2;
                        break;
                    case 'VERYBAD':
                        rate.textContent = 1;
                        break;
                    default:
                        break;
                }
                totalRating += Number(rate.textContent);
            });
            patientCount = patient.size;
            const numPatient = document.getElementById('numPatient');
            const revenue = document.getElementById('revenue');
            const rating = document.getElementById('rating');
            const reExam = document.getElementById('reExam');
            let reExamCount = 0;
            patient.forEach((value, key) => {
                if (value >= 2) {
                    reExamCount++;
                }
            });
            const numPatientStat = document.getElementById('numPatientStat');
            const newPatient = Number(numPatient.textContent);
            if (patientCount <= 0) {
                const icon = document.createElement('i');
                numPatientStat.textContent = "100%";
                icon.className = 'bx bx-up-arrow-alt';
                numPatientStat.prepend(icon);
            } else if (patientCount < newPatient) {
                const icon = document.createElement('i');
                numPatientStat.textContent = ((newPatient - patientCount) / patientCount * 100).toFixed(2) + "%";
                icon.className = 'bx bx-up-arrow-alt';
                numPatientStat.prepend(icon);
            } else {
                const icon = document.createElement('i');
                numPatientStat.textContent = ((patientCount - newPatient) / patientCount * 100).toFixed(2) + "%";
                icon.className = 'bx bx-down-arrow-alt';
                numPatientStat.prepend(icon);
            }
            const revenueStat = document.getElementById('revenueStat');
            const newRevenue = Number(revenue.textContent);
            if (totalCost == 0) {
                const icon = document.createElement('i');
                revenueStat.textContent = "100%";
                icon.className = 'bx bx-up-arrow-alt';
                revenueStat.prepend(icon);
            } else if (totalCost < newRevenue) {
                revenueStat.textContent = ((newRevenue - totalCost) / totalCost * 100).toFixed(2) + "%";
                const icon = document.createElement('i');
                icon.className = 'bx bx-up-arrow-alt';
                revenueStat.prepend(icon);
            } else {
                revenueStat.textContent = ((totalCost - newRevenue) / totalCost * 100).toFixed(2) + "%";
                const icon = document.createElement('i');
                icon.className = 'bx bx-down-arrow-alt';
                revenueStat.prepend(icon);
            }
            const reExamStat = document.getElementById('reExamStat');
            const newReExam = Number(reExam.textContent);
            const oldReExam = patientCount == 0? 0 : (reExamCount / patientCount * 100).toFixed(2);
            if (oldReExam == 0) {
                reExamStat.textContent = "100%";
                const icon = document.createElement('i');
                icon.className = 'bx bx-up-arrow-alt';
                reExamStat.prepend(icon);
            } else if (oldReExam < newReExam) {
                reExamStat.textContent = ((newReExam - oldReExam) / oldReExam * 100).toFixed(2) + "%";
                const icon = document.createElement('i');
                icon.className = 'bx bx-up-arrow-alt';
                reExamStat.prepend(icon);
            } else {
                reExamStat.textContent = ((oldReExam - newReExam) / oldReExam * 100).toFixed(2) + "%";
                const icon = document.createElement('i');
                icon.className = 'bx bx-down-arrow-alt';
                reExamStat.prepend(icon);
            }
            const ratingStat = document.getElementById('ratingStat');
            const newRating = Number(rating.textContent);
            const oldRating = count == 0? 0 : (totalRating / count).toFixed(2);
            if (oldRating == 0) {
                ratingStat.textContent = "100%";
                const icon = document.createElement('i');
                icon.className = 'bx bx-up-arrow-alt';
                ratingStat.prepend(icon);
            } else if (oldRating < newRating) {
                ratingStat.textContent = ((newRating - oldRating) / oldRating * 100).toFixed(2) + "%";
                const icon = document.createElement('i');
                icon.className = 'bx bx-up-arrow-alt';
                ratingStat.prepend(icon);
            } else {
                ratingStat.textContent = ((oldRating - newRating) / oldRating * 100).toFixed(2) + "%";
                const icon = document.createElement('i');
                icon.className = 'bx bx-down-arrow-alt';
                ratingStat.prepend(icon);
            }
            revenue.textContent += " Triệu";
            const starIcon = document.createElement('i');
            starIcon.className = 'bx bxs-star';
            rating.append(starIcon);
            reExam.textContent += "%";

        }
    })
    .catch((error) => {
        console.error('Error fetching schedules:', error);
    });

