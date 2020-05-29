window.onload = function(){

    let customer = document.getElementById("newCustomer");
    if(customer){
		customer.addEventListener("click", function(event){
            event.preventDefault();
            newAccount();
        }) 
}
}

function newAccount(){
    
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let startingBalance = document.getElementById("startingBalance").value;
    

        fetch('http://localhost:9090/MossBankSolutions/newCustomer',{
            method: 'POST',
            headers: {
                "Content-Type": "application/json; charset=UTF-8"
            },
        	body: JSON.stringify({
                "username": username,
                "password": password,
                "accountBalance": startingBalance
            })
            
        	}).then(response => response.text())
            .then(response => {
                    window.location.href = response;
        });
}