let customerList = document.getElementById("allCustomers");
let customerButton = document.getElementById("seeAllCustomers");

console.log(customerList);
console.log(customerButton);


customerButton.addEventListener("click", listAllAccounts)

// get is default, doesn't need to be stated
function listAllAccounts() {
    customerList.style.display = "block";
    fetch('http://localhost:9999/Beaver_Trust_Fiduciary_App/getallaccounts')
        .then(response => {
            console.log(response)
            return response.json();
        })
        .then(json => {
            // console.log(json)
            for (let i in json) {
                let li = document.createElement("LI");
                let userInfo = document.createTextNode
                (`${json[i].user_id}: ${json[i].username} Approved? ${json[i].approved}`);
                li.appendChild(userInfo);
                customerList.appendChild(li);
            }
        })
}