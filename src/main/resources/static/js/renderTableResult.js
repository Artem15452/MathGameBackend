let allResults = [];
let userStats = {};

async function loadResults() {
    try {
        const response = await fetch("/api/userResults");
        if (!response.ok) throw new Error("Помилка завантаження");

        allResults = await response.json();
        console.log("Завантажено результатів:", allResults.length);
        console.log("Перший результат:", allResults[0]);

        calculateUserStats();
        console.log("Статистика користувачів:", userStats);

        renderUsersTable();

    } catch (error) {
        console.error("Не вдалося завантажити дані:", error);
    }
}

function calculateUserStats() {
    userStats = {};

    allResults.forEach(item => {
        const email = item.user ? item.user.email : "Гість";

        if (!userStats[email]) {
            userStats[email] = { correct: 0, wrong: 0, results: [] };
        }

        // Перевіряємо чи користувач угадав обидва числа правильно
        const isCorrect = item.userFirstNumber === item.firstNumber && 
                         item.userSecondNumber === item.secondNumber;

        if (isCorrect) {
            userStats[email].correct++;
        } else {
            userStats[email].wrong++;
        }

        userStats[email].results.push({
            expression: `${item.firstNumber} ${item.operation} ${item.secondNumber}`,
            userExpression: `${item.userFirstNumber} ${item.operation} ${item.userSecondNumber}`,
            userAnswer: item.userAnswer,
            correctAnswer: item.correctAnswer,
            isCorrect: isCorrect
        });
    });
}

function renderUsersTable() {
    const tbody = document.getElementById("usersTableBody");
    tbody.innerHTML = "";

    let index = 1;
    for (const [email, stats] of Object.entries(userStats)) {
        const row = document.createElement('tr');

        const td1 = document.createElement('td');
        td1.textContent = index++;

        const td2 = document.createElement('td');
        td2.textContent = email;

        const td3 = document.createElement('td');
        td3.textContent = stats.correct;
        td3.className = 'clickable';
        td3.addEventListener('click', () => {
            console.log("Клік на correct для", email);
            showDetails(email, 'correct');
        });

        const td4 = document.createElement('td');
        td4.textContent = stats.wrong;
        td4.className = 'clickable';
        td4.addEventListener('click', () => {
            console.log("Клік на wrong для", email);
            showDetails(email, 'wrong');
        });

        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);

        tbody.appendChild(row);
    }

    console.log("Таблиця відрендерена, рядків:", tbody.children.length);
}

function showDetails(email, type) {
    console.log("showDetails викликано для:", email, type);
    const stats = userStats[email];
    console.log("Статистика для користувача:", stats);

    if (!stats) {
        console.error("Не знайдено статистику для:", email);
        return;
    }

    const detailsSection = document.getElementById("detailsSection");
    const detailsTitle = document.getElementById("detailsTitle");
    const detailsBody = document.getElementById("detailsBody");

    const filtered = stats.results.filter(r =>
        type === 'correct' ? r.isCorrect : !r.isCorrect
    );

    console.log("Відфільтровано результатів:", filtered.length);

    detailsTitle.innerText = type === 'correct'
        ? `Вгадані результати - ${email}`
        : `Помилки - ${email}`;

    detailsBody.innerHTML = "";
    filtered.forEach((item, index) => {
        const statusIcon = item.isCorrect ? "✅" : "❌";
        detailsBody.innerHTML += `
            <tr>
                <td>${index + 1}</td>
                <td>${item.userExpression}</td>
                <td>${item.expression}</td>
                <td>${statusIcon}</td>
            </tr>
        `;
    });

    console.log("Рядків в detailsBody:", detailsBody.children.length);
    console.log("detailsSection:", detailsSection);
    console.log("Ховаю mainSection, показую detailsSection");

    document.getElementById("mainSection").classList.add("hidden");
    detailsSection.classList.remove("hidden");

    console.log("mainSection hidden:", document.getElementById("mainSection").classList.contains("hidden"));
    console.log("detailsSection hidden:", detailsSection.classList.contains("hidden"));
}

document.getElementById("backButton").onclick = () => {
    document.getElementById("detailsSection").classList.add("hidden");
    document.getElementById("mainSection").classList.remove("hidden");
};

loadResults();
