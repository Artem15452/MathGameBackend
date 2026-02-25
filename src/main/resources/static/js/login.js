let email = document.getElementById("email");
let password = document.getElementById("password");
let confBtn = document.getElementById("confirmBtn");

function login() {
    fetch('/api/users/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: email.value,
            password: password.value
        })
    })
        .then(response => {
            if (response.ok) {
                localStorage.setItem("userEmail", email.value);

                window.location.href = "/index.html";
            } else {
                alert("Помилка входу: перевірте дані або зареєструйтесь.");
            }
        })
        .catch(error => {
            console.error("Помилка зв'язку з сервером:", error);
        });
}

confBtn.addEventListener("click", login);