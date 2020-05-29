/**
 * 
 */

let userAccounts = document.getElementById("userAccounts");

window.addEventListener("load", listAllAccounts);

function listAllAccounts() {
	event.preventDefault();
	fetch('http://localhost:8090/Project1/viewAccounts')
		.then(response => {
			return response.json();
		})
		.then (json => {
			for (let j in json) {
				let row = document.createElement("option");
				row.value = json[j].accountId;
				row.text = 'Account: ' + json[j].accountId + ", Balance: $" + json[j].balance.toFixed(2);
				
				userAccounts.appendChild(row);
			}
		})
		.catch(error => console.error(error));
}

/* document.getElementById("accountSelect").addEventListener('click', goToAccount());

function goToAccount() {
	let account = document.getElementById("userAccounts").value;
	console.log(document.getElementById("userAccounts").selectedIndex);
	console.log(account);
	
	 fetch('http://localhost:8090/Project1/Account', {
		method: 'POST',
		body: JSON.stringify({
			"accountId": account,
			"userName": "doesntmatter",
			"balance": 0,
			"approved": false
		}),
		headers: {
			"Content-Type": "application/json; charset=UTF-8",
	        'Accept': 'application/json'
			}
	})
	.then(response => response.text())
        .then(response => {
        console.log(response);
        window.location.assign(response);
        })
        
  
}

*/