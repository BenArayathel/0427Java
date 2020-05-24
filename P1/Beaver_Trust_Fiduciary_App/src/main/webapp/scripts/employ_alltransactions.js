let transactionList = document.getElementById("allTransactions");
let transactionButton = document.getElementById("seeAllTransactions");

console.log(transactionList);
console.log(customerButton);


transactionButton.addEventListener("click", listAllTransactions)

// get is default, doesn't need to be stated
function listAllTransactions() {
    transactionList.style.display = "block";
    fetch('http://localhost:9999/Beaver_Trust_Fiduciary_App/getalltransactions')
        .then(response => {
            console.log(response)
            return response.json();
        })
        .then(json => {
            // console.log(json)
            for (let i in json) {
                let li = document.createElement("LI");
                let transInfo = document.createTextNode
                (`Transaction ID: ${json[i].transaction_id}
                    Amount: ${json[i].amount}
                        Account Name: ${json[i].account_name}
                            User ID: ${json[i].user_id}
                                Type: ${json[i].type}`);
                li.appendChild(transInfo);
                transactionList.appendChild(li);
            }
        })
}