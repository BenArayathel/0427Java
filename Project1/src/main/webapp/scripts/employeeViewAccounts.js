let accountView = document.getElementById("accViewSub");
let accountList = document.getElementById("accountList");

accountView.addEventListener("click", displayAccounts);

function displayAccounts() {
	let username = document.getElementById("username").value;
	accountList.innerHTML = "";
	
	fetch('http://localhost:8090/Project1/EmployeeViewAccounts', {
		method: 'POST',
		headers: {
            "Content-Type": "application/json; charset=UTF-8",
            'Accept': 'application/json',
        },
		body: JSON.stringify({
			'username': username
		})
	})
	.then (response => {
		return response.json();
	})
	.then (json => {
		for (let j in json) {
			let tr = document.createElement("tr");
			let tdAcc = document.createElement("td");
			let tdBal = document.createElement("td");
			
			let accountNum = document.createTextNode(`${json[j].accountId}`);
			let balance = document.createTextNode(`${json[j].balance}`);
			
			tdAcc.appendChild(accountNum);
			tdBal.appendChild(balance);
			tr.appendChild(tdAcc);
			tr.appendChild(tdBal);
			
			accountList.appendChild(tr);
		}
	})
}