/**
 * 
 */

function validateLogin() {
	console.log("validateLogin() has been clicked.");
	
	// collect login input:
	let usr = document.getElementById("usr").value;
	let pwd = document.getElementById("pwd").value;

	let jsonData = {loginName: usr, loginPassword: pwd};

	console.log(jsonData);
	
	fetch('http://localhost:9999/BankWebApp/login', {
		  method: 'POST',
		  mode: 'cors',
		  cache: 'no-cache',
		  headers: {'Content-type': 'application/json'}, 
		  body: JSON.stringify(jsonData)
	})
	// capture response back from the servlet
	.then(response => response.json())
	.then(data => console.log(data))
	.catch(error => console.error(error));

		
};
