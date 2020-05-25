//uSI.transferFunds(currentUser.getEmail(), "checkingBalance", accNum, amt)

window.onload = function (){
	
	let currentUser = JSON.parse(localStorage.getItem("userData"));
	if(!currentUser) {
		window.location.href = "./login.html";
		
	}
	let transferButton = document.getElementById("transferButton");
	let receiverAccountNum = document.getElementById("receiverAccountNum");
	let transferAmt = document.getElementById("transferAmount");
	
	if(transferButton) {
		transferButton.addEventListener("click", function(event) {
	        event.preventDefault();
	        transferMoney(currentUser["email"], receiverAccountNum.value , transferAmt.value);
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

function transferMoney(senderAccount, receiverAccountNum, amt) {
    let userData = JSON.parse(localStorage.getItem("userData"));

    if (!isNaN(amt) && (amt < 1000.00)) {
    	let senderSv = parseFloat(userData["savingsBalance"]);
    	let senderCh = parseFloat(userData["checkingBalance"]);
    	console.log("Sending transfer fetch...");
    	fetch('http://localhost:8088/BankApp/api/put?direction=transfer', {
        	method: 'PUT',
        	headers: {
        		"Content-Type": "application/json; charset=UTF-8",
        		"Accept" : "application/json"
        	},
        	body: JSON.stringify({
        			email: senderAccount,
        			checkingAccountNumber: receiverAccountNum,
        			checkingBalance: amt
        	})
        	}).then(
        		response => response.json()).then(
        				data => {
        					localStorage.clear();
        					localStorage.setItem("userData", JSON.stringify(data));
        					alert("Money successfully transferred");
        					window.location.href = "./index.html"
        					
        				}).catch(error =>{
        					console.log(error)
        					//window.location.href = "./404.html";
        				});
        
    }
    else {
    	alert("Please only enter numerical values that are less than $1000.00.");
    }
}