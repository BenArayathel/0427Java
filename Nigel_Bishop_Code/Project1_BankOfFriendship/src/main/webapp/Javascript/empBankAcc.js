
//GET ALL LOGIN ACCOUNTS
let lognAcc = document.getElementById('allBankAcc_btn');
window.addEventListener("dblclick",getAllBankAcc)

function getAllBankAcc() {
    fetch('http://localhost:9090/Project1_BankOfFriendship/EmpBankAccA')
        .then(response => {
             console.log(response)
            return response.json();
        })
        .then(json => {

            let custOutput = '';

                 console.log(json)
                for (let i in json) {
    
                  custOutput += `
                	  <tr>
                	  	<td> ${json[i].accountID} </td>
                	  	<td> ${json[i].customerID} </td>
                	  	<td> ${json[i].accountType} </td>
                	  	<td> ${json[i].balance} </td>
                	    <td> ${json[i].accountStatus} </td>
                	  </tr>
                  `;
            } 
                 console.log(custOutput);
                 document.getElementById('tableoutput').innerHTML = custOutput;
        })
    }


//GET ALL PENDING LOGIN ACCOUNTS
let pendlognAcc = document.getElementById('pendBankAcc_btn');
window.addEventListener("dblclick",getAllBankAccPend)

function getAllBankAccPend() {
    fetch('http://localhost:9090/Project1_BankOfFriendship/EmpBankAccB')
        .then(response => {
             console.log(response)
            return response.json();
        })
        .then(json => {

            let custOutput = '';

                 console.log(json)
                for (let i in json) {
    
                  custOutput += `
                	  <tr>
                	  	<td> ${json[i].accountID} </td>
                	  	<td> ${json[i].customerID} </td>
                	  	<td> ${json[i].accountType} </td>
                	  	<td> ${json[i].balance} </td>
                	    <td> ${json[i].accountStatus} </td>
                	  </tr>

                  `;
            } 
                 console.log(custOutput);
                 document.getElementById('tableoutput').innerHTML = custOutput;
        })
    }





//UPDATE LOGIN ACCOUNT STATUS
let acclist = document.getElementById("Submit_btn");

acclist.addEventListener("click", updateLogAccStatus)

function updateLogAccStatus(event) {
	
	event.preventDefault();

	
    let customerID = document.getElementById("Customerid").value;
    let accountType = document.getElementById("accountType").value;
    let accountStatus = document.getElementById("accountstatus").value;

    console.log(customerID);
    console.log(accountStatus);
    let compMessage = '';

        fetch('http://localhost:9090/Project1_BankOfFriendship/EmpBankAccA', {
            method: 'POST',
            headers: {
                'Accept': 'application/json,text/plain',
                "Content-Type": "application/json; charset=UTF-8"
            },
            body: JSON.stringify({
            	customerID: customerID,
            	accountType: accountType,
            	accountStatus: accountStatus,

            })
    
        })
         .then((res) => res.text())
                  .then(data => {
                 console.log(data)
                 window.location.assign(data);
             })
             compMessage = "BANK ACCOUNT STATUS HAS BEEN UPDATED"
    

         document.getElementById('output').innerHTML = compMessage;
}