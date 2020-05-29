/* window.onload = function() {
	document.getElementById("accountSelect").addEventListener('click', goToAccount());
}

function goToAccount() {
	event.preventDefault();
	let account = document.getElementById("userAccounts").value;
	console.log(account);
	
	/* fetch('http://localhost:8090/Project1/Account', {
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