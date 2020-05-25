window.onload = function (){

	
	let currentUser = JSON.parse(localStorage.getItem("userData"));
	if(!currentUser) {
		window.location.href = "./login.html";
	}
	let depositButton = document.getElementById("depositButton");
	let depAmt = document.getElementById("depositAmount");
	
	
	//document.querySelector('input[name="genderS"]:checked').value;
	if(depositButton) {
		depositButton.addEventListener("click", function(event) {
	        event.preventDefault();
	        let accountRadio = document.querySelector('input[name="accountRadios"]:checked').value;
	        depositMoney(accountRadio, depAmt);
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



function depositMoney(whichAccount, amt) {
    
    console.log(userData);
    console.log(`Checking balance- ${userData["checkingBalance"]}  Savings balance- ${userData["savingsBalance"]}`);
    if (!isNaN(amt.value) && (amt.value < 1000.00)) {
    	let userSv = parseFloat(currentUser["savingsBalance"]);
    	let userCh = parseFloat(currentUser["checkingBalance"]);
        if (whichAccount == "checking") {
        	userCh += parseFloat(amt.value);
        	userCh = userCh.toFixed(2);
        	userSv = userSv.toFixed(2);
        }
        
        else if(whichAccount == "savings") {
        	userSv += parseFloat(amt.value);
        	userSv = userSv.toFixed(2);
        	userCh = userCh.toFixed(2);
        }
        console.log("Sending put fetch");
        fetch('http://localhost:8088/BankApp/api/put?direction=account&whichAccount=' + whichAccount, {
        	method: 'PUT',
        	headers: {
        		"Content-Type": "application/json; charset=UTF-8",
        		"Accept" : "application/json"
        	},
        	body: JSON.stringify({
        			email: userData["email"],
        			name: userData["name"],
        			checkingBalance: userCh,
        			savingsBalance: userSv
        	})
        	}).then(
        		response => response.json()).then(
        				data => {
        					localStorage.clear();
        					localStorage.setItem("userData", JSON.stringify(data));
        					alert("Money successfully deposited");
        					window.location.href = "./index.html"
        					
        				    
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