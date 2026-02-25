let email = document.getElementById("email");
let password = document.getElementById("password");
let confBtn = document.getElementById("confirmBtn");


function register() {
    fetch('/api/users/createUser', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: email.value,
            password: password.value
        })
    })
}
confBtn.addEventListener("click", register);