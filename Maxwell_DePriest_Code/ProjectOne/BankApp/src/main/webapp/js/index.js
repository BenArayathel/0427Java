window.onload = function () {
	let logOutButton = document.getElementById("logOutButton");
	if(logOutButton) {
		logOutButton.addEventListener("click", function(event) {
			event.preventDefault();
			logOut();
		});
	}
}
let storedData = JSON.parse(localStorage.getItem("userData"))
var cBal = document.getElementById("checkingBalance");
var sBal = document.getElementById("savingsBalance");
var greet = document.getElementById("customerGreeting");

if(!storedData) {
	greet.innerHTML = "Hello friend, it appears that you haven't logged in yet";
	cBal.innerHTML= "";
	sBal.innerHTML = "";
	
}
else {
	var chBalance = storedData["checkingBalance"];
	var svBalance = storedData["savingsBalance"];
	
	greet.innerHTML = `Hello, ${storedData["name"]}. How may we assist you?`;
	cBal.innerHTML = `Checking Balance: $${chBalance}`;
	sBal.innerHTML = `Savings Balance: $${svBalance}`;
}


function logOut() {
	localStorage.clear();
	window.location.href = "./login.html";
}