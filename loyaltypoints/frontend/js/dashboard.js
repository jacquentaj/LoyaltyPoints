const API_BASE_URL = "http://localhost:8080/api";

async function loadDashboardData() {

    try {

        const customerResponse =
            await fetch(`${API_BASE_URL}/customer/all`);

        const tierResponse =
            await fetch(`${API_BASE_URL}/tier/all`);

        const purchaseResponse =
            await fetch(`${API_BASE_URL}/purchase/all`);

        const ledgerResponse =
            await fetch(`${API_BASE_URL}/ledger/all`);


        const customers = await customerResponse.json();
        const tiers = await tierResponse.json();
        const purchases = await purchaseResponse.json();
        const ledger = await ledgerResponse.json();


        document.getElementById("customerCount").textContent =
            customers.length;

        document.getElementById("tierCount").textContent =
            tiers.length;

        document.getElementById("purchaseCount").textContent =
            purchases.length;

        document.getElementById("ledgerCount").textContent =
            ledger.length;

    } catch (error) {

        console.error(
            "Error loading dashboard data:",
            error
        );

    }
}


loadDashboardData();