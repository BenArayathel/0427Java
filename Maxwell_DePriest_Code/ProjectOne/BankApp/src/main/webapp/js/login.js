window.onload = function() {
	let loginButton = document.getElementById("loginButton");
	if(loginButton) {
		loginButton.addEventListener("click", function(event) {
			event.preventDefault();
			checkCredentials();
		})
	}
}



function checkCredentials() {
    let email = document.getElementById("loginEmail");
    let password = document.getElementById("loginPassword");
//    if (!password || !email) {
//    	window.location.href("./404.html");
//    }
    if(password && email) {
    	console.log("Checking credentials...");
    	fetch('http://localhost:8088/BankApp/login', {
        	method: 'POST',
        	headers: {
        		"Content-Type": "application/json; charset=UTF-8",
        		"Accept" : "application/json"
        	},
        	body: JSON.stringify({
        			email: email.value,
        			password: password.value
        	})
        	}).then(
        		response => response.json()).then(
        				data => {
        					console.log(data);
        					if(data["status"] == "customer") {
        						localStorage.clear();
            					localStorage.setItem("userData", JSON.stringify(data));
        						window.location.href = "./index.html";
        					}
        					else if(data["status"] == "employee") {
        						localStorage.clear();
            					localStorage.setItem("userData", JSON.stringify(data));
        						window.location.href = "./employeePortal.html";
        					}
        					
        				}).catch(error =>{
        					alert("Invalid Login. Please try again");
        					//console.log(error)
        					
        				});
    	
    }

}

    // select user by email
    // if email is in db, check entered password against stored password
    // if match, send to index.html
    // store user email in cookies/session storage/ local storage/ etc

    // if password doesn't match, log error, inform customer to retry
    // if no email match, log error, inform customer to try again or sign up
