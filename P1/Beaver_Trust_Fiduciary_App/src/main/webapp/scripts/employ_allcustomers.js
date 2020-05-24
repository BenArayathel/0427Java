let customerList = document.getElementById("allCustomers");
let customerButton = document.getElementById("seeAllCustomers");

console.log(customerList);
console.log(customerButton);


customerButton.addEventListener("click", listAllAccounts)

// get is default, doesn't need to be stated
function listAllAccounts() {
    // customerList.style.display = "block";
    fetch('http://localhost:9999/Beaver_Trust_Fiduciary_App/getallaccounts')
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

                // create text nodes filled with JSON info
                let userId = document.createTextNode
                (`${json[i].user_id}`)
                let userName = document.createTextNode
                (`${json[i].username}`)
                let userApproved = document.createTextNode
                ( `${json[i].approved}`)

                // append text to each cell
                td1.appendChild(userId);
                td2.appendChild(userName);
                td3.appendChild(userApproved);

                // append each cell in order to the row
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);

                // finally, append the completed row to the table body
                customerList.appendChild(tr);
            }
        })
}