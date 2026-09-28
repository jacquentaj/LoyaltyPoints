const API_URL = "http://localhost:8080/api/customer/all";
const TIER_API_URL = "http://localhost:8080/api/tier/all";

async function loadCustomers() {

    try {

        const response = await fetch(API_URL);

        const customers = await response.json();
        const tierResponse = await fetch(TIER_API_URL);
        const tiers = await tierResponse.json();

        const tableBody =
            document.getElementById("customerTableBody");

        tableBody.innerHTML = "";

        customers.forEach(customer => {
            const tier = tiers.find(
                t => t.id === customer.tierId
            );

            const tierName = tier
                ? tier.name
                : "Unknown";
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${customer.id}</td>
                <td>${customer.name}</td>
                <td>${customer.email}</td>
                <td>${customer.availablePoints}</td>
                <td>${customer.lifetimePoints}</td>
                <td>${tierName}</td>
            `;

            tableBody.appendChild(row);

        });

    } catch (error) {

        console.error(
            "Error loading customers:",
            error
        );

    }
}

loadCustomers();