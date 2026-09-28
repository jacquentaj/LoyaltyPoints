const API_URL = "http://localhost:8080/api/ledger/all";

async function loadLedger() {

    try {

        const response = await fetch(API_URL);

        const transactions = await response.json();

        const tableBody =
            document.getElementById("ledgerTableBody");

        tableBody.innerHTML = "";

        transactions.forEach(transaction => {

            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${transaction.id}</td>
                <td>${transaction.customerId}</td>
                <td>${transaction.transactionType}</td>
                <td>${transaction.points}</td>
                <td>${transaction.transactionDate}</td>
                <td>${transaction.description}</td>
            `;

            tableBody.appendChild(row);

        });

    } catch (error) {

        console.error(
            "Error loading ledger:",
            error
        );

    }
}

loadLedger();
document
    .getElementById("ledgerForm")
    .addEventListener("submit", async function (event) {

        event.preventDefault();

        const transaction = {

            id: Number(
                document.getElementById("ledgerId").value
            ),

            customerId: Number(
                document.getElementById("ledgerCustomerId").value
            ),

            transactionType:
            document.getElementById("transactionType").value,

            points: Number(
                document.getElementById("ledgerPoints").value
            ),

            transactionDate:
            document.getElementById("transactionDate").value,

            description:
            document.getElementById("ledgerDescription").value
        };

        try {

            const response = await fetch(
                "http://localhost:8080/api/ledger/create",
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(transaction)
                }
            );

            if (!response.ok) {

                const error = await response.json();

                alert(
                    Object.values(error).join("\n")
                );

                return;
            }

            alert("Points transaction added successfully!");

            document.getElementById(
                "ledgerForm"
            ).reset();

            window.location.reload();

        } catch (error) {

            alert(
                "Unable to connect to the server."
            );

            console.error(error);
        }
    });