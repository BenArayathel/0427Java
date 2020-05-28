/**
 * 
 */

function get_accounts(){
    
    fetch("http://localhost:8090/controller/api/ReturnAccounts", {
        method: "POST",
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        }
    })
    .then(response => response.text())
    .then(function (json){
        console.log(json);
        
    }).catch(e => console.log('Connection error', e));

}

let returnAccountsButton = document.getElementById("return_accounts_button");
returnAccountsButton.addEventListener("click", get_accounts);