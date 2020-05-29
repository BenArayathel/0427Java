window.onload = function() {
    this.setFirstLastName();
    let seeAllBalances = this.document.getElementById("seeAllBalances");
    if (seeAllBalances) {
        seeAllBalances.addEventListener("click", getAllBalances);
    }
    let seeCustomerBalances = this.document.getElementById("seeCustomerBalances");
    if (seeCustomerBalances) {
        seeCustomerBalances.addEventListener("submit", getCustomerBalances);
    }
    let seeAllTransactions = this.document.getElementById("seeAllTransactions");
    if (seeAllTransactions) {
        seeAllTransactions.addEventListener("click", getAllTransactions);
    }
}

async function setFirstLastName() {
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/getFirstLastName', {
            method: 'POST',
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    if (response.url.endsWith(".html") && response.url !== window.location.href) {
        console.log(response.url);
        window.location.href = response.url;
    } else {
        console.log("setting first last name")
        let userDetails = await response.json();
        let firstLast = document.getElementById('firstLast');
        firstLast.innerText = `${userDetails.firstName} ${userDetails.lastName}`;
    }
}

function getAllBalances(event) {
    event.preventDefault();
}

function getCustomerBalances(event) {
    event.preventDefault();
}

function getAllTransactions(event) {
    event.preventDefault();
}

/* <div id="viewBalances">
<label for="balances">Account Information</label>
<table class="table table-dark" id="balances">
    <thead>
        <tr id="balHeader">
            <th class="scope" id="balAccNum">Account Number</th>
            <th class="scope" id="balBalance">Balance</th>
            <th class="scope" id="balCustId">Customer ID</th>
            <th class="scope" id="balPending">Pending</th>
            <!--<th>Approve</th>
                <th>Reject</th>-->
        </tr>
    </thead>
</table>
</div> */