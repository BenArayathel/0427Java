// goal: receive json from servlet, add li's with account names and balances to ul

let accountList = document.getElementById("accountList");
let seeList = document.getElementById("accountListButton");

console.log(accountList);

seeList.addEventListener("click", listAccounts)
// submit.addEventListener("keyup", login)

// get is default, doesn't need to be stated
function listAccounts() {
    fetch('http://localhost:9999/Beaver_Trust_Fiduciary_App/getaccounts')
        .then(response => {
            console.log(response)
            return response.json();
        })
        .then(json => {
            console.log(json)
            for (let i in json) {
                let li = document.createElement("LI");
                let accountInfo = document.createTextNode(json[i].account_name);
                li.appendChild(accountInfo);
                accountList.appendChild(li);
            }
        })
}

// var li = document.createElement("LI");                 // Create a <li> node
// var textnode = document.createTextNode("Water");         // Create a text node
// node.appendChild(textnode);                              // Append the text to <li>
// document.getElementById("myList").appendChild(node);     // Append <li> to <ul> with id="myList"