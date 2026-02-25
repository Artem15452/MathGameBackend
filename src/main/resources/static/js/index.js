const numberOne = document.getElementById("numberOne");
const numberTwo = document.getElementById("numberTwo");
const symbolDisp = document.getElementById("symbol");
const selectedSymbol = document.getElementById("selectSymbol");
const resultDisp = document.getElementById("result");
const realResult = document.getElementById("realResult");
const selectedRandom = document.getElementById("selectedRandom");
const buttonStart = document.getElementById("startGame");
const confirmBtn = document.getElementById("confirmBtn");

let compN1 = 0;        
let compN2 = 0;        
let targetResult = 0; 
let currentSymbol = "";
let allUserResults = [];
let allCompResults = [];

function start() {
    currentSymbol = selectedSymbol.value;
    let maxLimit = Number(selectedRandom.value);
    
    if (!maxLimit || maxLimit <= 0) {
        alert("Будь ласка, введіть коректне числове обмеження!");
        return;
    }

    if (currentSymbol === '+') {
        do {
            compN2 = Math.floor(Math.random() * 10000);
            compN1 = Math.floor(Math.random() * 10000);
            targetResult = compN1 + compN2;
        } while (targetResult > maxLimit); 
    } 

    else if (currentSymbol === '-') {
        do {
            compN1 = Math.floor(Math.random() * 10000);
            compN2 = Math.floor(Math.random() * 10000);
            targetResult = compN1 - compN2;
        } while (targetResult < 0 || targetResult > maxLimit);
    }
    renderGame();
}

function renderGame() {
    symbolDisp.innerHTML = currentSymbol;
    realResult.innerHTML = `Знайдіть два числа, щоб отримати: <b>${targetResult}</b>`;
    resultDisp.style.color = "black";
    
    numberOne.value = "";
    numberTwo.value = "";
}

function confirm() {
   
    let userN1 = Number(numberOne.value);
    let userN2 = Number(numberTwo.value);
    let userCalc = 0;

    if (currentSymbol === '+') {
        userCalc = userN1 + userN2;
    } else if (currentSymbol === '-') {
        userCalc = userN1 - userN2;
    }

let currentUserData = {
        n1: userN1,
        n2: userN2,
        symbol: currentSymbol,
        calc: userCalc,
        isCorrect: userCalc === targetResult && userN1 === compN1 
    };

    let currentCompData = {
        n1: compN1,
        n2: compN2,
        symbol: currentSymbol,
        target: targetResult
    };

    allUserResults.push(currentUserData);
    allCompResults.push(currentCompData);

    saveGameResultToServer(currentUserData, currentCompData);
    renderHistory();
    start();
}

const historyBody = document.getElementById("historyBody");

function renderHistory() {

    historyBody.innerHTML = "";

    allUserResults.forEach((userItem, index) => {
        const compItem = allCompResults[index];
   
        let statusText = "";
        let rowStyle = "";

        if (userItem.n1 === compItem.n1 && userItem.n2 === compItem.n2) {
            statusText = "✅ Вгадано!";
            rowStyle = "background-color: #d4edda;"; 
        } else if (userItem.calc === compItem.target) {
            statusText = "❌ Не вгадано";
            rowStyle = "background-color: #fff3cd;"; 
        } else {
            statusText = "❌ Помилка";
            rowStyle = "background-color: #f8d7da;"; 
        }

        historyBody.innerHTML += `
            <tr style="${rowStyle}">
                <td>${index + 1}</td>
                <td>${userItem.n1} ${userItem.symbol} ${userItem.n2} = ${userItem.calc}</td>
                <td>${compItem.n1} ${compItem.symbol} ${compItem.n2} = ${compItem.target}</td>
                <td><b>${statusText}</b></td>
            </tr>
        `;
    });
}

async function saveGameResultToServer(userItem, compItem) {
    const gameData = {
        userEmail: savedEmail,
        firstNumber: compItem.n1,
        secondNumber: compItem.n2,
        operation: compItem.symbol,
        userFirstNumber: userItem.n1,
        userSecondNumber: userItem.n2,
        userAnswer: userItem.calc,
        correctAnswer: compItem.target
    };

    try {
        const response = await fetch("/api/gameResults/save", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(gameData)
        });

        if (response.ok) {
            console.log("Результат збережено в базу");
        }
    } catch (error) {
        console.error("Помилка зв'язку з сервером:", error);
    }
}

const userNameElement = document.createElement("div");
const savedEmail = localStorage.getItem("userEmail") || "Гість";

userNameElement.innerHTML = `<h4>Ви успішно залогінились як: <b>${savedEmail}</b></h4>`;
document.body.prepend(userNameElement);
buttonStart.addEventListener("click", start);
confirmBtn.addEventListener("click", confirm);