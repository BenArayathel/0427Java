 let checking = document.getElementById("savingBalance");

window.addEventListener("load", SavingAccBal)

function SavingAccBal() {
    fetch('http://localhost:9090/Project1_BankOfFriendship/CustomerDashboardS')
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
                  document.getElementById('savingBalance').innerHTML = custOutput;

        })
    }




let depoist = document.getElementById("DepSubmit_btn");

depoist.addEventListener("click", depSavingAcc)

//DEPOSIT INTO SAVING ACCOUNT
function depSavingAcc(event) {
	
	event.preventDefault();


    let balance = document.getElementById("saveDeposit").value;
    console.log(balance);
    let compMessage = '';
    if(balance <= 0 || balance == null || balance < 1){
         compMessage = "TRANSACTION CANCEL"
         document.getElementById('output').innerHTML = compMessage;
    }
    else{
        fetch('http://localhost:9090/Project1_BankOfFriendship/CusSavingAA', {
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

withdrawl.addEventListener("click", withSavingAcc)

//WITHDRAWL FROM CHECKING ACCOUNT
function withSavingAcc(event) {
	
	event.preventDefault();


    let balance = document.getElementById("saveWithdrawl").value;
    console.log(balance);
    let compMessage = '';
    if(balance <= 0 || balance == null || balance < 1){
         compMessage = "TRANSACTION CANCEL"
         document.getElementById('output').innerHTML = compMessage;
    }
    else{
        fetch('http://localhost:9090/Project1_BankOfFriendship/CusSavingBB', {
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



