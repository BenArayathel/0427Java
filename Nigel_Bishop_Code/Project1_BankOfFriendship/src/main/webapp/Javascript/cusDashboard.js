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




let saving = document.getElementById("savingBalance");

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






