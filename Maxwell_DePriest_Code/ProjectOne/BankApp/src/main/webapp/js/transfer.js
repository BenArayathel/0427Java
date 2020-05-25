//uSI.transferFunds(currentUser.getEmail(), "checkingBalance", accNum, amt)

window.onload = function (){
	let transferButton = document.getElementById("transferButton");
	let receiverAccNum = document.getElementById("receiverAccountNumber");
	let transferAmt = document.getElementById("transferAmount");
	let currentUser = JSON.parse(localStorage.getItem("userData"));
	
	if(transferButton) {
		withdrawButton.addEventListener("click", function(event) {
	        event.preventDefault();
	        transferMoney(currentUser["email"], receiverAccNum , transferAmt);
	    })
	}
}

function transferMoney(senderAccount, receiverAccount, amt) {
    let userData = JSON.parse(localStorage.getItem("userData"));

    if (!isNaN(amt.value) && (amt.value < 1000.00) && !isNaN(receiverAccount)) {
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
        			checkingAccountNumber: receiverAccNum.value,
        			checkingBalance: transferAmt.value
        	})
        	}).then(
        		response => response.json()).then(
        				data => {
        					console.log(data);
        					receiverCh = data["checkingBalance"];
        					//receiverSv = data["savingsBalance"];
        					
        				}).catch(error =>{
        					console.log(error)
        					//window.location.href = "./404.html";
        				});
        
    }
    else {
    	alert("Please only enter numerical values that are less than $1000.00. Receiving account number can only contain numerical digits.");
    }
}