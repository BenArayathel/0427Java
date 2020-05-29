window.onload = function(){
let balanceSubmit = document.getElementById("balanceSubmit");
if(balanceSubmit){
balanceSubmit.addEventListener("click", function(event){
    event.preventDefault();
            view();
        }) 
}

let withdrawSubmit = document.getElementById("withdrawSubmit");
if(withdrawSubmit){
withdrawSubmit.addEventListener("click", function(event){
    event.preventDefault();
            withdraw();
        }) 
}

let depositSubmit = document.getElementById("depositSubmit");
if(depositSubmit){
depositSubmit.addEventListener("click", function(event){
    event.preventDefault();
            deposit();
        }) 
}

let transferSubmit = document.getElementById("transferSubmit");
if(transferSubmit){
transferSubmit.addEventListener("click", function(event){
    event.preventDefault();
            transfer();
        }) 
}

let acceptSubmit = document.getElementById("acceptSubmit");
if(acceptSubmit){
acceptSubmit.addEventListener("click", function(event){
    event.preventDefault();
            acceptTransfer();
        }) 
}

let customer = document.getElementById("newCustomer");
if(customer){
    customer.addEventListener("click", function(event){
        event.preventDefault();
        newAccount();
    }) 
}
}

function view(){

    let accountNumber = document.getElementById("balanceAccount").value;

    fetch('http://localhost:9090/MossBankSolutions/viewBalance',{
        
        method: 'POST',
            headers: {
                "Content-Type": "application/json; charset=UTF-8"
            },
        	body: JSON.stringify({
                "accountNumber": accountNumber
            })
            
        	}).then(response => {
                return response.json();
            }).then(function(response){
                console.log(response);
                    document.getElementById("customerInfo").innerText = response.accountBalance;
        })
    }

    function withdraw(){

        let accountNumber = document.getElementById("withdrawAccount").value;
        let withdrawAmount = document.getElementById("withdrawAmount").value;
        let withdrawBalance = document.getElementById("balanceWithdraw").value;
        fetch('http://localhost:9090/MossBankSolutions/withdraw',{
            
            method: 'POST',
                headers: {
                    "Content-Type": "application/json; charset=UTF-8"
                },
                body: JSON.stringify({
                    "accountNumber": accountNumber,
                    "withdraw": withdrawAmount,
                    "accountBalance": withdrawBalance
                })
                
                }).then(response => {
                    return response.json();
                }).then(function(response){
                    console.log(response);
                        document.getElementById("customerInfo").innerText = response;
            })
        }

        function deposit(){

            let accountNumber = document.getElementById("withdrawAccount").value;
            let depositAmount = document.getElementById("depositAmount").value;
            fetch('http://localhost:9090/MossBankSolutions/deposit',{
                
                method: 'POST',
                    headers: {
                        "Content-Type": "application/json; charset=UTF-8"
                    },
                    body: JSON.stringify({
                        "accountNumber": accountNumber,
                        "deposit": depositAmount,
                    })
                    
                    }).then(response => {
                        return response.json();
                    }).then(function(response){
                        console.log(response);
                            document.getElementById("customerInfo").innerText = response;
                })
            }

            function transfer(){

                let fromAccountNumber = document.getElementById("fromAccount").value;
                let transferAmount = document.getElementById("transferAmount").value;
                let toAccountNumber = document.getElementById("toAccount").value;
                fetch('http://localhost:9090/MossBankSolutions/transfer',{
                    
                    method: 'POST',
                        headers: {
                            "Content-Type": "application/json; charset=UTF-8"
                        },
                        body: JSON.stringify({
                            "fromAccountNumber": fromAccountNumber,
                            "transfer": transferAmount,
                            "toAccountNumber": toAccountNumber
                        })
                        
                        }).then(response => {
                            console.log(response)
                            return response.json();
                        }).then(function(response){
                            console.log(response);
                                document.getElementById("customerInfo").innerText = response;
                    })
                }

                function acceptTransfer(){

                    let elseAccountNumber = document.getElementById("elseAccount").value;
                    let acceptAmount = document.getElementById("acceptAmount").value;
                    let yourAccountNumber = document.getElementById("yourAccount").value;
                    fetch('http://localhost:9090/MossBankSolutions/acceptTransfer',{
                        
                        method: 'POST',
                            headers: {
                                "Content-Type": "application/json; charset=UTF-8"
                            },
                            body: JSON.stringify({
                                "fromAccountNumber": elseAccountNumber,
                                "transfer": acceptAmount,
                                "toAccountNumber": yourAccountNumber
                            })
                            
                            }).then(response => {
                                console.log(response)
                                return response.json();
                            }).then(function(response){
                                console.log(response);
                                    document.getElementById("customerInfo").innerText = response;
                        })
                    }
                    function newAccount(){
    
                        let username = document.getElementById("username").value;
                        let password = document.getElementById("password").value;
                        let startingBalance = document.getElementById("startingBalance").value;
                        
                    
                            fetch('http://localhost:9090/MossBankSolutions/newAccount',{
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
                                    console.log(response)
                                    document.getElementById("customerInfo").innerText = response.accountNumber;
                            });
                    }