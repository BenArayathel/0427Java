 let checking = document.getElementById("checkingBalance");

window.addEventListener("load", CheckingAccBal)

function CheckingAccBal() {
    fetch('http://localhost:9090/Project1_BankOfFriendship/CustomerDashboard')
        .then(response => {
             console.log(response)
            return response.json();
        })
        .then(json => {


          let custOutput = '';
                 console.log(json)   
                  custOutput += `
                      <h4>$ ${json}</h4>
                  `;
                  document.getElementById('checkingBalance').innerHTML = custOutput;

        })
    }




let depoist = document.getElementById("DepSubmit_btn");

depoist.addEventListener("click", depCheckingAcc)

//DEPOSIT INTO CHECKING ACCOUNT
function depCheckingAcc(event) {
	
	event.preventDefault();


    let balance = document.getElementById("checkDeposit").value;
    console.log(balance);
    let compMessage = '';
    if(balance <= 0 || balance == null || balance < 1){
         compMessage = "TRANSACTION CANCEL"
         document.getElementById('output').innerHTML = compMessage;
    }
    else{
        fetch('http://localhost:9090/Project1_BankOfFriendship/CusChecking', {
            method: 'POST',
            headers: {
                'Accept': 'application/json,text/plain',
                "Content-Type": "application/json; charset=UTF-8"
            },
            body: JSON.stringify({
                balance: balance,
            })
    
        })
         .then((res) => res.text())
                  .then(data => {
                 console.log(data)
                 window.location.assign(data);
             })
             compMessage = "DEPOSIT TRANSACTION COMPLETED"
    }

         document.getElementById('output').innerHTML = compMessage;
}



let withdrawl = document.getElementById("WithSubmit_btn");

withdrawl.addEventListener("click", withCheckingAcc)

//WITHDRAWL FROM CHECKING ACCOUNT
function withCheckingAcc(event) {
	
	event.preventDefault();


    let balance = document.getElementById("checkWithdrawl").value;
    console.log(balance);
    let compMessage = '';
    if(balance <= 0 || balance == null || balance < 1){
         compMessage = "TRANSACTION CANCEL"
         document.getElementById('output').innerHTML = compMessage;
    }
    else{
        fetch('http://localhost:9090/Project1_BankOfFriendship/CusCheckingS', {
            method: 'POST',
            headers: {
                'Accept': 'application/json,text/plain',
                "Content-Type": "application/json; charset=UTF-8"
            },
            body: JSON.stringify({
                balance: balance,
            })
    
        })
         .then((res) => res.text())
                  .then(data => {
                 console.log(data)
                 window.location.assign(data);
             })
             compMessage = "WITHDRAWL TRANSACTION COMPLETED"
    }

         document.getElementById('output').innerHTML = compMessage;
}



