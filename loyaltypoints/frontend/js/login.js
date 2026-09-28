document
    .getElementById("loginForm")
    .addEventListener("submit", function (event) {

        event.preventDefault();

        const email =
            document.getElementById("email").value;

        const password =
            document.getElementById("password").value;

        if (email === "" || password === "") {

            alert("Please enter email and password.");

            return;
        }

        alert("Login successful!");

        window.location.href = "../index.html";
    });
function logout() {
    window.location.href = "login.html";
}