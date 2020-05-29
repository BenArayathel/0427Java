//GET ALL TRANSACIONS
let lognAcc = document.getElementById('getAllTrans_btn');
window.addEventListener("dblclick",getAllTransaction)

function getAllTransaction() {
    fetch('http://localhost:9090/Project1_BankOfFriendship/EmpAllTransaction')
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
                	  	<td> ${json[i].transactionID} </td>
                	  	<td> ${json[i].accountID} </td>
                	  	<td> ${json[i].customerID} </td>
                	  	<td> ${json[i].accountType} </td>
                	    <td> ${json[i].balance} </td>
                	    <td> ${json[i].transAmount} </td>
                	  	<td> ${json[i].transType} </td>
                	  	<td> ${json[i].transDate} </td>
                	  </tr>
                  `;
            } 
                 console.log(custOutput);
                 document.getElementById('tableoutput').innerHTML = custOutput;
        })
    }