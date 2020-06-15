let checking = document.getElementById("reqChecking_btn");

checking.addEventListener("click", reqCheckingAcc)

//CREATE NEW CHECKING ACCOUNT
function reqCheckingAcc(event) {
	
	event.preventDefault();
    let accountType = "CHECKING";
    let accountStatus = "PENDING";
       
    console.log(accountType);
    console.log(accountStatus);

    fetch('http://localhost:9090/Project1_BankOfFriendship/ReqBankingAccount', {
        method: 'POST',
        headers: {
            'Accept': 'application/json,text/plain',
            "Content-Type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify({
        	accountType: accountType,
        	accountStatus: accountStatus,
        })

    })
     .then((res) => res.text())
              .then(data => {
             console.log(data)
             window.location.assign(data);
         })
         
         let compMessage = "YOUR REQUEST FOR A CHECKING ACCOUNT HAS BEEN COMPLETED, A DEFAULT BALANCE OF $300 HAS BEEN ADDED TO THE ACCOUNT"
         document.getElementById('output').innerHTML = compMessage;
}



let saving = document.getElementById("reqSaving_btn");

saving.addEventListener("click", reqSavingAcc)

//CREATE NEW SAVING ACCOUNT
function reqSavingAcc(event) {
	
	event.preventDefault();
    let accountType = "SAVING";
    let accountStatus = "PENDING";
       
    console.log(accountType);
    console.log(accountStatus);

    fetch('http://localhost:9090/Project1_BankOfFriendship/ReqBankingAccount', {
        method: 'POST',
        headers: {
            'Accept': 'application/json,text/plain',
            "Content-Type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify({
        	accountType: accountType,
        	accountStatus: accountStatus,
        })

    })
     .then((res) => res.text())
              .then(data => {
             console.log(data)
             window.location.assign(data);
         })
         let compMessage = "YOUR REQUEST FOR A SAVING ACCOUNT HAS BEEN COMPLETED, A DEFAULT BALANCE OF $100 HAS BEEN ADDED TO THE ACCOUNT"
             document.getElementById('output').innerHTML = compMessage;
}



