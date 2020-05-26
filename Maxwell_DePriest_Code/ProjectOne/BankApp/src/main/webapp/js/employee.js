window.onload = function() {
	let currentUser = JSON.parse(localStorage.getItem("userData"));
	console.log(currentUser);
	if (currentUser["status"] == "customer") {
		window.location.href= "./index.html";
	}
	let deleteButton = document.getElementById("deleteButton");
	let viewButton = document.getElementById("viewButton");
	let activateButton = document.getElementById("activateButton");
	let viewDiv = document.getElementById("viewDiv");
	let deleteDiv = document.getElementById("deleteDiv");
	let activateDiv = document.getElementById("activateDiv");
	
	let viewUserButton = document.getElementById("viewUserButton");
	let activateAccountButton = document.getElementById("activateAccountButton");
	let deleteAccountButton = document.getElementById("deleteAccountButton");
	let viewInactiveButton = document.getElementById("viewInactiveButton");
	let employeeLogoutButton = document.getElementById("employeeLogoutButton");
	
	employeeLogoutButton.addEventListener("click", function() {
		logOut();
	})

	deleteButton.addEventListener("click", function() {
		if(deleteDiv.classList.contains("hidden")) {
			deleteDiv.classList.remove("hidden");
			viewDiv.classList.add("hidden");
			activateDiv.classList.add("hidden");
		}
		else {
			deleteDiv.classList.add("hidden");
		}
	})
	viewButton.addEventListener("click", function() {
		if(viewDiv.classList.contains("hidden")) {
			deleteDiv.classList.add("hidden");
			viewDiv.classList.remove("hidden");
			activateDiv.classList.add("hidden");
		}
		
		else {
			viewDiv.classList.add("hidden");
		}
	})
	activateButton.addEventListener("click", function() {
		if(activateDiv.classList.contains("hidden")) {
			deleteDiv.classList.add("hidden");
			viewDiv.classList.add("hidden");
			activateDiv.classList.remove("hidden");
		}
		
		else {
			activateDiv.classList.add("hidden");
		}
		
	})
	viewUserButton.addEventListener("click", function(event) {
		event.preventDefault();
		viewUser();
	})
	
	viewInactiveButton.addEventListener("click", function(event) {
		event.preventDefault();
		findInactiveAccount();
	})
	
	activateAccountButton.addEventListener("click", function(event) {
		event.preventDefault();
		activateAccount();
	})
	
	deleteAccountButton.addEventListener("click", function(event) {
		event.preventDefault();
		let userEmail = document.getElementById("emailDelete").value;
		deleteAccount(userEmail);
	})
	
	

}

function viewUser() {
	let emailSearch = document.getElementById("emailView").value;
	console.log("Searching for user...");
	fetch('http://localhost:8088/BankApp/api/get?direction=user&email=' + emailSearch, {
    	method: 'GET',
    	headers: {
    		"Content-Type": "application/json; charset=UTF-8",
    		"Accept" : "application/json"
    	}
    	}).then(
    		response => response.json()).then(
    				data => {
    					document.getElementById("viewEmailRecord").innerText = data["email"];
    					document.getElementById("viewNameRecord").innerText = data["name"];
    					document.getElementById("viewCheckingRecord").innerText = data["checkingBalance"].slice(0, -3);
    					document.getElementById("viewSavingRecord").innerText = data["savingsBalance"].slice(0, -3);
    					let accountStatus = (data["status"] == "true" ? "Activated" : "Not Activated");
    					document.getElementById("viewStatusRecord").innerText = accountStatus;
    					
    					document.getElementById("viewResult").classList.remove("hidden");

    				}).catch(error =>{
    					alert("Couldn't find an account with that email");
    					//window.location.href = "./404.html";
    				});
}


function findInactiveAccount() {
	var activateSearch = document.getElementById("emailActivate").value;
	console.log("Searching for user...");
	fetch('http://localhost:8088/BankApp/api/get?direction=user&email=' + activateSearch, {
    	method: 'GET',
    	headers: {
    		"Content-Type": "application/json; charset=UTF-8",
    		"Accept" : "application/json"
    	}
    	}).then(
    		response => response.json()).then(
    				data => {
    					document.getElementById("activateEmailRecord").innerText = data["email"];
    					document.getElementById("activateNameRecord").innerText = data["name"];
    					document.getElementById("activateCheckingRecord").innerText = data["checkingBalance"].slice(0, -3);
    					document.getElementById("activateSavingRecord").innerText = data["savingsBalance"].slice(0, -3);
    					let accountStatus = (data["status"] == "true" ? "Activated" : "Not Activated");
    					document.getElementById("activateStatusRecord").innerText = accountStatus;
    					
    					document.getElementById("activateResult").classList.remove("hidden");

    				}).catch(error =>{
    					alert("Couldn't find an account with that email");
    					console.log(error)
    					//window.location.href = "./404.html";
    				});
	
}

function activateAccount() {
	var activateSearch = document.getElementById("emailActivate").value;
	console.log("Activating account...");
	fetch('http://localhost:8088/BankApp/api/put?direction=activate', {
    	method: 'PUT',
    	headers: {
    		"Content-Type": "application/json; charset=UTF-8",
    		"Accept" : "application/json"
    	},
    	body: JSON.stringify({
    			email: activateSearch,
    			active: "true"
    	})
    	}).then(
        		response => response.json()).then(
        				data => {
        					document.getElementById("activateEmailRecord").innerText = data["email"];
        					document.getElementById("activateNameRecord").innerText = data["name"];
        					document.getElementById("activateCheckingRecord").innerText = data["checkingBalance"].slice(0, -3);
        					document.getElementById("activateSavingRecord").innerText = data["savingsBalance"].slice(0, -3);
        					let accountStatus = (data["status"] == "true" ? "Activated" : "Not Activated");
        					document.getElementById("activateStatusRecord").innerText = accountStatus;
        					
        					document.getElementById("activateResult").classList.remove("hidden");

        				}).catch(error =>{
        					alert("Something went wrong. Please try again");
        					//window.location.href = "./404.html";
        				});
}
function deleteAccount(email) {
	console.log("Deleting accounts...");
    fetch('http://localhost:8088/BankApp/api/put?direction=user', {
    	method: 'DELETE',
    	headers: {
    		"Content-Type": "application/json; charset=UTF-8",
    		"Accept" : "application/json"
    	},
    	body: JSON.stringify({
    			email: email
    	})
    	}).then(alert("Account deleted. Good riddance")).catch(error =>{
    					alert("Couldn't delete account.");
    					//console.log(error)
    					//window.location.href = "./404.html";
    				});
}

function logOut() {
	localStorage.clear();
	window.location.href = "./login.html";
}
