const API_URL = "http://localhost:8080/api/purchase/all";

async function loadPurchases() {

    try {

        const response = await fetch(API_URL);

        const purchases = await response.json();

        const tableBody =
            document.getElementById("purchaseTableBody");

        tableBody.innerHTML = "";

        purchases.forEach(purchase => {

            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${purchase.id}</td>
                <td>${purchase.customerId}</td>
                <td>₹${purchase.purchaseAmount}</td>
                <td>${purchase.pointsEarned}</td>
                <td>${purchase.purchaseDate}</td>
            `;

            tableBody.appendChild(row);

        });

    } catch (error) {

        console.error(
            "Error loading purchases:",
            error
        );

    }
}

loadPurchases();
document
    .getElementById("purchaseForm")
    .addEventListener("submit", async function (event) {

        event.preventDefault();

        const purchase = {

            id: Number(
                document.getElementById("purchaseId").value
            ),

            customerId: Number(
                document.getElementById("purchaseCustomerId").value
            ),

            purchaseAmount: Number(
                document.getElementById("purchaseAmount").value
            ),

            pointsEarned: Number(
                document.getElementById("pointsEarned").value
            ),

            purchaseDate:
            document.getElementById("purchaseDate").value
        };

        try {

            const response = await fetch(
                "http://localhost:8080/api/purchase/create",
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(purchase)
                }
            );

            if (!response.ok) {

                const error = await response.json();

                alert(
                    Object.values(error).join("\n")
                );

                return;
            }

            alert("Purchase recorded successfully!");

            document.getElementById(
                "purchaseForm"
            ).reset();

            window.location.reload();

        } catch (error) {

            alert(
                "Unable to connect to the server."
            );

            console.error(error);
        }
    });