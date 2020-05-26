window.onload = function (){
	let currentUser = JSON.parse(localStorage.getItem("userData"));
	if(!currentUser) {
		window.location.href = "./login.html";
	}
	let withdrawButton = document.getElementById("withdrawButton");
	let withdrawAmt = document.getElementById("withdrawAmount");

	if(withdrawButton) {
		withdrawButton.addEventListener("click", function(event) {
	        event.preventDefault();
	        let accountRadio = document.querySelector('input[name="accountRadios"]:checked').value;

	        withdrawMoney(accountRadio, withdrawAmt, currentUser);
	    })
	}
	
	let logOutButton = document.getElementById("logOutButton");
	if(logOutButton) {
		logOutButton.addEventListener("click", function(event) {
			event.preventDefault();
			logOut();
		});
	}
}

function withdrawMoney(whichAccount, amt, currentUser) {

    if (!isNaN(amt.value) && (amt.value < 1000.00)) {
    	let userSv = parseFloat(currentUser["savingsBalance"]);
    	let userCh = parseFloat(currentUser["checkingBalance"]);
        if (whichAccount == "checking") {
        	if(userCh - parseFloat(amt.value) >= 0.00) {
        		userCh -= parseFloat(amt.value);
        	}
        	else {
        		alert("Withdrawl Amount cannot exceed current balance");
        		return false;
        	}
        }
        else if(whichAccount == "savings") {
        	if(userSv - parseFloat(amt.value) >= 0.00) {
        		userSv -= parseFloat(amt.value);
        	}
        	else {
        		alert("Withdrawl Amount cannot exceed current balance");
        		return false;
        	}
        }
        userSv = userSv.toFixed(2);
    	userCh = userCh.toFixed(2);
        console.log("Sending put fetch");
        fetch('http://localhost:8088/BankApp/api/put?direction=account&whichAccount=' + whichAccount, {
        	method: 'PUT',
        	headers: {
        		"Content-Type": "application/json; charset=UTF-8",
        		"Accept" : "application/json"
        	},
        	body: JSON.stringify({
        			email: currentUser["email"],
        			name: currentUser["name"],
        			checkingBalance: userCh,
        			savingsBalance: userSv
        	})
        	}).then(
        		response => response.json()).then(
        				data => {
        					localStorage.clear();
        					localStorage.setItem("userData", JSON.stringify(data));
        					alert("Money successfully withdrawn");
        					window.location.href= "./index.html";
        					
        				    
        				}).catch(error =>{
        					alert("Account may not have been activated yet. Please be patient with us. Thank you.");
        					//console.log(error)
        					//window.location.href = "./404.html";
        				});
        
    }
    else {
    	alert("Please only enter numerical values that are less than $1000.00");
    }
}

function logOut() {
	localStorage.clear();
	window.location.href = "./login.html";
}