let regButton = document.getElementById("registerButton");
regButton.addEventListener("click", function(event) {
	event.preventDefault();
	comparePasswords();
});

function comparePasswords() {
    let firstPassword = document.getElementById("newPassword");
    let secondPassword = document.getElementById("confirmPassword");
    let name = document.getElementById("nameRegistration");
    let email = document.getElementById("emailRegistration");
    let phone = document.getElementById("phoneRegistration");

    if(firstPassword.value == secondPassword.value && firstPassword != "") {
        registerNewCustomer(name.value, email.value, phone.value, firstPassword.value);
    }
    else {
        alert("Passwords don't match or fields are empty. Please try again.");
    }
}

function registerNewCustomer(name, email, phoneNum, password) {
	console.log("Submitting new information...");
	fetch('http://localhost:8088/BankApp/api/post?direction=user', {
    	method: 'POST',
    	headers: {
    		"Content-Type": "application/json; charset=UTF-8",
    		"Accept" : "application/json"
    	},
    	body: JSON.stringify({
    			id: "",
    			name: name,
    			email: email,
    			phoneNumber: phoneNum,
    			password: password,
    			status: "customer"
    	})
    	}).then(
    		response => response.json()).then(
    				data => {
    					console.log(data);
    					console.log("Name- " + data["name"]);
    					localStorage.clear();
    					localStorage.setItem("userData", JSON.stringify(data));
    					window.location.href = "index.html";
    				    
    				}).catch(error =>{
    					console.log(error)
    					//window.location.href = "./404.html";
    				});
	
	
	
	
    

}