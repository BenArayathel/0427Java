
let loginButton = document.getElementById("loginButton");

if(loginButton) {
	loginButton.addEventListener("click", checkCredentials);
}


function checkCredentials() {
    let email = document.getElementById("loginEmail");
    let password = document.getElementById("loginPassword");
    if (!password || !email) {
    	window.location.href("./404.html");
    }
    else if(password && email) {
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
        					console.log("Name- " + data["name"]);
        					localStorage.clear();
        					localStorage.setItem("userData", JSON.stringify(data));
        					window.location.href = "./index.html";
        				    
        				}).catch(error =>{
        					console.log(error)
        					window.location.href = "./404.html";
        				});
    	
    }

}

    // select user by email
    // if email is in db, check entered password against stored password
    // if match, send to index.html
    // store user email in cookies/session storage/ local storage/ etc

    // if password doesn't match, log error, inform customer to retry
    // if no email match, log error, inform customer to try again or sign up
