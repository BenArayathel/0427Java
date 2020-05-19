
let loginButton = document.getElementById("loginButton");
loginButton.addEventListener("click", checkCredentials);

function checkCredentials() {
    let email = document.getElementById("loginEmail");
    let password = document.getElementById("loginPassword");
    var checkEmail = "max@email.com";
    var checkPassword = "guest";
    
    const data = {"email": "max@email.com", "password": "guest"};

    if (checkEmail == email.value) {
        console.log("Emails match and paswords match");
        console.log("About to fetch");
        
        fetch('http://localhost:8088/com.hello.servlets/login', {
        	method: 'POST',
        	headers: {
        		'Content-Type': 'application/json'},
        		body: JSON.stringify(data),
        	}).then(
        		response => {
        			
        			//console.log(JSON.parse(response));
        			console.log(response.text());
        			//console.log(response);
        			//console.log(response.text());
        			//window.location.href="index.html";
        			//localStorage.setItem("email", email.value);
        			}).then(
        				data => {
        					console.log(data);
        				}).catch(error =>{
        					console.log(error)
        				});
        console.log("Fetch should have returned something");
        
        //window.location.href="/index.html";
        //localStorage.setItem("email", email.value);
    }
    else {
        console.log("Emails don't match");
        alert("Incorrect Email");
    }
    // select user by email
    // if email is in db, check entered password against stored password
    // if match, send to index.html
    // store user email in cookies/session storage/ local storage/ etc

    // if password doesn't match, log error, inform customer to retry
    // if no email match, log error, inform customer to try again or sign up

}