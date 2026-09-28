document
    .getElementById("customerForm")
    .addEventListener("submit", async function (event) {

        event.preventDefault();

        const customer = {

            id: Number(
                document.getElementById("customerId").value
            ),

            name:
            document.getElementById("customerName").value,

            email:
            document.getElementById("customerEmail").value,

            availablePoints: 0,

            lifetimePoints: 0,

            tierId: Number(
                document.getElementById("customerTier").value
            )
        };


        try {

            const response = await fetch(
                "http://localhost:8080/api/customer/create",
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(customer)
                }
            );


            if (!response.ok) {

                const error =
                    await response.json();

                alert(
                    Object.values(error).join("\n")
                );

                return;
            }


            alert("Customer added successfully!");

            document.getElementById(
                "customerForm"
            ).reset();

            window.location.reload();

        } catch (error) {

            alert(
                "Unable to connect to the server."
            );

            console.error(error);
        }

    });