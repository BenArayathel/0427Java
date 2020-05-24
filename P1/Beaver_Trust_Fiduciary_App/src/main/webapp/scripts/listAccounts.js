// goal: receive json from servlet, add li's with account names and balances to ul

let accountList = document.getElementById("accountList");
// let seeList = document.getElementById("accountListButton");

// console.log(accountList);

// seeList.addEventListener("click", listAccounts)
// submit.addEventListener("keyup", login)

// instead of a button, do it on load
window.addEventListener("load", listAccounts);

// get is default, doesn't need to be stated
function listAccounts() {
    fetch('http://localhost:9999/Beaver_Trust_Fiduciary_App/getaccounts')
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

                // create text nodes filled with JSON info
                let accountName = document.createTextNode
                (`${json[i].account_name}`);
                let accountBalance = document.createTextNode
                (`$${json[i].balance}`)

                // append text to each cell
                td1.appendChild(accountName);
                td2.appendChild(accountBalance);

                // append each cell in order to the row
                tr.appendChild(td1);
                tr.appendChild(td2);

                // finally, append the completed row to the table body
                accountList.appendChild(tr);
            }
        })
}

{/* <input type="radio" id="male" name="gender" value="male">
<label for="male">Male</label><br> */}

// var li = document.createElement("LI");                 // Create a <li> node
// var textnode = document.createTextNode("Water");         // Create a text node
// node.appendChild(textnode);                              // Append the text to <li>
// document.getElementById("myList").appendChild(node);     // Append <li> to <ul> with id="myList"