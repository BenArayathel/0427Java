let pendingAccounts = document.getElementById("pendingAccounts");

window.addEventListener("load", listPendingAccounts);

function listPendingAccounts() {
	event.preventDefault();
	fetch('http://localhost:8090/Project1/ViewPendingAccounts')
		.then(response => {
			return response.json();
		})
		.then (json => {
			for (let j in json) {
				let row = document.createElement("option");
				row.value = json[j].accountId;
				row.text = 'User: ' + json[j].userName + ', Account: ' + json[j].accountId + ", Balance: $" + json[j].balance.toFixed(2);
				
				pendingAccounts.appendChild(row);
			}
		})
		.catch(error => console.error(error));
}