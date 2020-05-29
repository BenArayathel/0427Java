
//GET ALL LOGIN ACCOUNTS
let lognAcc = document.getElementById('logAcc_btn');
window.addEventListener("dblclick",getAllLoginAcc)

function getAllLoginAcc() {
    fetch('http://localhost:9090/Project1_BankOfFriendship/EmpLoginAccA')
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
                	  	<td> ${json[i].userID} </td>
                	  	<td> ${json[i].customerID} </td>
                	  	<td> ${json[i].userName} </td>
                	  	<td> ${json[i].userPassword} </td>
                	    <td> ${json[i].accountStatus} </td>
                	  </tr>

                  `;
            } 
                 console.log(custOutput);
                 document.getElementById('tableoutput').innerHTML = custOutput;
        })
    }


//GET ALL PENDING LOGIN ACCOUNTS
let pendlognAcc = document.getElementById('pendLoginAcc_btn');
window.addEventListener("dblclick",getAllLoginAccPen)

function getAllLoginAccPen() {
    fetch('http://localhost:9090/Project1_BankOfFriendship/EmpLoginAccB')
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
                	  	<td> ${json[i].userID} </td>
                	  	<td> ${json[i].customerID} </td>
                	  	<td> ${json[i].userName} </td>
                	  	<td> ${json[i].userPassword} </td>
                	    <td> ${json[i].accountStatus} </td>
                	  </tr>

                  `;
            } 
                 console.log(custOutput);
                 document.getElementById('tableoutput').innerHTML = custOutput;
        })
    }





//UPDATE LOGIN ACCOUNT STATUS
let acclist = document.getElementById("accSubmit_btn");

acclist.addEventListener("click", updateLogAccStatus)

function updateLogAccStatus(event) {
	
	event.preventDefault();


    let customerID = document.getElementById("Customerid").value;
    let accountStatus = document.getElementById("accountstatus").value;

    console.log(customerID);
    console.log(accountStatus);
    let compMessage = '';

        fetch('http://localhost:9090/Project1_BankOfFriendship/EmpLoginAccA', {
            method: 'POST',
            headers: {
                'Accept': 'application/json,text/plain',
                "Content-Type": "application/json; charset=UTF-8"
            },
            body: JSON.stringify({
            	customerID: customerID,
            	accountStatus: accountStatus,

            })
    
        })
         .then((res) => res.text())
                  .then(data => {
                 console.log(data)
                 window.location.assign(data);
             })
             compMessage = "LOGIN ACCOUNT STATUS HAS BEEN UPDATED"
    

         document.getElementById('output').innerHTML = compMessage;
}