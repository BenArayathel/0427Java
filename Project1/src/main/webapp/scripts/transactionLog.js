let transLog = document.getElementById("transLog");

window.addEventListener("load", listAllTransactions);

function listAllTransactions() {
	event.preventDefault();
	fetch('http://localhost:8090/Project1/ListTransactions')
		.then(response => {
			return response.json();
		})
		.then (json => {
			for (let j in json) {
				let row = document.createElement("p");
				row.innerHTML = json[j];
				
				transLog.appendChild(row);
			}
		})
		.catch(error => console.error(error));
}