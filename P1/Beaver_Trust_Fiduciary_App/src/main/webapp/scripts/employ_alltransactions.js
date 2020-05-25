let transactionList = document.getElementById("transactionBody");
let transactionButton = document.getElementById("seeAllTransactions");
let table2 = document.getElementById("transactionsTable");


transactionButton.addEventListener("click", listAllTransactions)

// get is default, doesn't need to be stated
function listAllTransactions() {
    // delete any old rows from table body
    // while(allCustomers.hasChildNodes()) {
    //     allCustomers.removeChild(allCustomers.firstChild);
    // }

    table1.style.display = "none";
    table2.style.display = "table";
    table3.style.display = "none";

    fetch('http://localhost:9999/Beaver_Trust_Fiduciary_App/getalltransactions')
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
                let td5 = document.createElement("td");

                // create text nodes filled with JSON info
                let transID = document.createTextNode
                (`${json[i].transaction_id}`);
                let amount = document.createTextNode
                (`${json[i].amount}`);
                let accountName = document.createTextNode
                ( `${json[i].account_name}`);
                let userID = document.createTextNode
                ( `${json[i].user_id}`);
                let type = document.createTextNode
                ( `${json[i].type}`);

                // append text to each cell
                td1.appendChild(transID);
                td2.appendChild(amount);
                td3.appendChild(accountName);
                td4.appendChild(userID);
                td5.appendChild(type);

                // append each cell in order to the row
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tr.appendChild(td5);

                // finally, append the completed row to the table body
                transactionList.appendChild(tr);
            }
        })
}