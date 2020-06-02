(function () {
    window.addEventListener('load', function () {
        let user = getSession('user');
        // let user = JSON.parse(this.sessionStorage.getItem("user"));
        if (user["userType"] === 'Customer') {
            // TODO fecth all account by customer ID
            getCustomerAccountsById(`customer/id/${user["personId"]}`);
        }

    }, false)
})();

let getCustomerAccountsById = async (path) => {

    let url = BASE_URL + path;
    try {
        let response = await fetch(url);
        let result = await response.json();

        if (Array.isArray(result.data["accounts"])) {
            customerDetail(result.data);
        } else {
            console.log("NO DATA - Customer doesn't have any account.");
        }
    } catch (error) {
        console.error("SEARCH CUSTOMER ERROR", error);
    }
}

function customerDetail(data) {

    let user = getSession('user');
    if (user) {
        let regContent = document.querySelector("#regContent");

        let nRow = document.querySelector("#panel2");
        if (!nRow) {
            nRow = document.createElement("div");
            nRow.id = "panel2";
            nRow.setAttribute("class", "row");
        }

        let dob = new Date(data["person"]["dob"]);

        nRow.innerHTML = `
        <div class="col-sm-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title"><strong>${data["person"]["name"]} ${data["person"]["lastName"]}</strong></h5>
                    <h6 class="card-subtitle mb-2 text-muted"><strong>Phone:</strong> ${data["person"]["phoneNumber"]}</h6>
                    <p class="card-text"><strong>City:</strong> ${data["person"]["city"]}</p>
                    <p class="card-text"><strong>DOB:</strong> ${dob.getMonth()}/${dob.getDate()}/${dob.getFullYear()}</p>
                    <h5><strong>Accounts</strong></h5>

                    <div class="list-group" id="listTabAccount" role="tablist">

                    </div>

                </div>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="card">
                <div class="card-body">
                    <div class="tab-content" id="listAccountDetails">

                    </div>
                </div>
            </div>
        </div>
        `;

        regContent.appendChild(nRow);

        let listAccounts = document.querySelector("#listAccountDetails");
        let listTabAccount = document.querySelector("#listTabAccount");
        let accounts = data["accounts"];

        for (let index in accounts) {

            let linkList = document.createElement("a");
            linkList.id = "listAccount" + index;
            linkList.setAttribute("class", "list-group-item list-group-item-action");
            linkList.setAttribute("role", "tab");
            linkList.setAttribute("data-toggle", "list");
            linkList.setAttribute("aria-controls", "control-" + index);
            linkList.href = "#tabAccount" + index;
            linkList.innerText = "No. " + accounts[index]["id"];

            listTabAccount.appendChild(linkList);

            let itemAccount = document.createElement("div");
            itemAccount.id = "tabAccount" + index;
            itemAccount.setAttribute("class", "tab-pane fade show");
            itemAccount.setAttribute("role", "tabpanel");
            itemAccount.setAttribute("aria-labelledby", "listAccount" + index);

            let h4Type = document.createElement("h4");
            let h5Number = document.createElement("h5");
            let h5Routing = document.createElement("h5");
            h4Type.innerHTML = `<strong>${accounts[index]["accountType"]["name"]}</strong>`;
            h5Number.innerText = `Account Number: ${accounts[index]["id"]}`;
            h5Routing.innerText = `Routing number: ${accounts[index]["routingNumber"]}`;

            itemAccount.appendChild(h4Type);
            itemAccount.appendChild(h5Number);
            itemAccount.appendChild(h5Routing);

            if (user["userType"] === 'Customer') {
                let btnShowBalance = document.createElement("button");
                btnShowBalance.innerText = 'Show Balance';
                btnShowBalance.setAttribute("class", "btn btn-primary");
                btnShowBalance.addEventListener("click", function (event) {
                    event.preventDefault();
                    setSession('account', accounts[index]);
                    window.location.assign('account.html');
                })
                itemAccount.appendChild(btnShowBalance);
            }

            listAccounts.appendChild(itemAccount);
        }
    }

}

