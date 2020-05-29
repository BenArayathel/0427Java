window.onload = function() {
	let loginButton = document.getElementById("loginButton");

		loginButton.addEventListener("click", function() {
			loginCustomer();
		})
		let newCustomerButton = document.getElementById("customer");

		newCustomerButton.addEventListener("click", function() {
			newCustomer();
		})
}


function loginCustomer() {
	window.location.assign("./login");
}


function newCustomer() {
	window.location.assign("./newCustomer");
}