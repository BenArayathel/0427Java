let approve = document.getElementById("approveSub");

approve.addEventListener("click", approveAccount);

function approveAccount() {
	let accountId = document.getElementById("pendingAccounts").value;
	
	fetch('http://localhost:8090/Project1/ApproveAccount', {
		method: 'POST',
		headers: {
            "Content-Type": "application/json; charset=UTF-8",
            'Accept': 'application/json',
        },
        body: JSON.stringify({
        	'accountId': accountId
        })
	})
	.then(response => response.text())
	.then(responseText => {
		alert(responseText);
		location.reload();
	})
}