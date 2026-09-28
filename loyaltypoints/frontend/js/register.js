document
    .getElementById("registerForm")
    .addEventListener("submit", function (event) {

        event.preventDefault();

        const name =
            document.getElementById("registerName").value;

        const email =
            document.getElementById("registerEmail").value;

        const password =
            document.getElementById("registerPassword").value;

        const confirmPassword =
            document.getElementById("confirmPassword").value;


        if (password !== confirmPassword) {

            alert("Passwords do not match.");

            return;
        }


        if (name === "" ||
            email === "" ||
            password === "") {

            alert("Please fill all fields.");

            return;
        }


        alert("Registration successful!");

        window.location.href = "login.html";
    });