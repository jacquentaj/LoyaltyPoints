const API_URL = "http://localhost:8080/api/tier/all";

async function loadTiers() {

    try {

        const response = await fetch(API_URL);

        const tiers = await response.json();

        const tableBody =
            document.getElementById("tierTableBody");

        tableBody.innerHTML = "";

        tiers.forEach(tier => {

            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${tier.id}</td>
                <td>${tier.name}</td>
                <td>${tier.minimumPoints}</td>
                <td>${tier.discountPercentage}%</td>
            `;

            tableBody.appendChild(row);

        });

    } catch (error) {

        console.error(
            "Error loading tiers:",
            error
        );

    }
}

loadTiers();
document
    .getElementById("tierForm")
    .addEventListener("submit", async function (event) {

        event.preventDefault();

        const tier = {
            id: Number(
                document.getElementById("tierId").value
            ),

            name:
            document.getElementById("tierName").value,

            minimumPoints: Number(
                document.getElementById("minimumPoints").value
            ),

            discountPercentage: Number(
                document.getElementById("discountPercentage").value
            )
        };

        try {

            const response = await fetch(
                "http://localhost:8080/api/tier/create",
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(tier)
                }
            );

            if (!response.ok) {

                const error = await response.json();

                alert(
                    Object.values(error).join("\n")
                );

                return;
            }

            alert("Tier added successfully!");

            document.getElementById(
                "tierForm"
            ).reset();

            window.location.reload();

        } catch (error) {

            alert(
                "Unable to connect to the server."
            );

            console.error(error);
        }
    });