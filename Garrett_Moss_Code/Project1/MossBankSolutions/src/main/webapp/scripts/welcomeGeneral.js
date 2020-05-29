window.onload = function() {
	let customerButton = document.getElementById("customerButton");

		customerButton.addEventListener("click", function() {
			welcomeCustomer();
		})
		let employeeButton = document.getElementById("employeeButton");

    employeeButton.addEventListener("click", function() {
			welcomeEmployee();
		})
}


function welcomeCustomer() {
	window.location.href = "./welcomeCustomer.html";
}


function welcomeEmployee() {
	window.location.assign("./employeeLogin");
}