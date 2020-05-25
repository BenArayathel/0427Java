let userAccountsList = document.getElementById("userAccountsList");
let submit5 = document.getElementById("seeAllUserAcounts");
let table3 = document.getElementById("userAccountsTable");


submit5.addEventListener("click", getAccounts);

function getAccounts() {
    // delete any old rows from table body
    while(userAccountsList.hasChildNodes()) {
        userAccountsList.removeChild(userAccountsList.firstChild);
    }

    table1.style.display = "none";
    table2.style.display = "none";
    table3.style.display = "table";

    let username = document.getElementById("whichUser").value;

    // make call to api/servlet
    fetch("http://localhost:9999/Beaver_Trust_Fiduciary_App/getuseraccounts", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
            'Accept': 'application/json',
        },
        body: JSON.stringify({
            username: username,
        })
    })
    .then(response => {
        // console.log(response)
        return response.json();
    })
    .then(json => {
        // console.log(json)
        for (let i in json) {

            //for each entry, create a new empty row and cells
            let tr = document.createElement("tr");
            let td1 = document.createElement("td");
            let td2 = document.createElement("td");
            let td3 = document.createElement("td");
            let td4 = document.createElement("td");


            // create text nodes filled with JSON info
            let accountID = document.createTextNode
            (`${json[i].account_id}`);
            let userID = document.createTextNode
            (`${json[i].user_id}`);
            let accountName = document.createTextNode
            ( `${json[i].account_name}`);
            let balance = document.createTextNode
            ( `${json[i].balance}`);

            // append text to each cell
            td1.appendChild(accountID);
            td2.appendChild(userID);
            td3.appendChild(accountName);
            td4.appendChild(balance);

            // append each cell in order to the row
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);

            // finally, append the completed row to the table body
            userAccountsList.appendChild(tr);

            // clean up input
            document.getElementById("whichUser").value = "";
        }
    })
}