window.onload = function(){
    let approveSubmit = document.getElementById("approveSubmit");
    if(approveSubmit){
        approveSubmit.addEventListener("click", function(event){
        event.preventDefault();
                approve();
            }) 
    }

    let rejectSubmit = document.getElementById("rejectSubmit");
    if(rejectSubmit){
        rejectSubmit.addEventListener("click", function(event){
        event.preventDefault();
                reject();
            }) 
    }

    let viewSubmit = document.getElementById("viewSubmit");
    if(viewSubmit){
        viewSubmit.addEventListener("click", function(event){
        event.preventDefault();
                view();
            }) 
    }

    let viewTrans = document.getElementById("viewTrans");
    if(viewTrans){
        viewTrans.addEventListener("click", function(event){
        event.preventDefault();
                transaction();
            }) 
    }
}


function approve(){

    let approveAccountNumber = document.getElementById("approveAccountNumber").value;
    let approveAccount = document.getElementById("approveAccount").value;

    fetch('http://localhost:9090/MossBankSolutions/approveAccount',{
        
        method: 'POST',
            headers: {
                "Content-Type": "application/json; charset=UTF-8"
            },
        	body: JSON.stringify({
                "accountNumber": approveAccountNumber,
                "approveAccount": approveAccount
            })
            
        	}).then(response => {
                return response.json();
            }).then(function(response){
                console.log(response);
                    document.getElementById("customerInfo").innerText = response;
        })
    }

    function reject(){

        let rejectAccountNumber = document.getElementById("rejectAccountNumber").value;
        let rejectAccount = document.getElementById("rejectAccount").value;
    
        fetch('http://localhost:9090/MossBankSolutions/rejectAccount',{
            
            method: 'POST',
                headers: {
                    "Content-Type": "application/json; charset=UTF-8"
                },
                body: JSON.stringify({
                    "accountNumber": rejectAccountNumber,
                    "rejectAccount": rejectAccount
                })
                
                }).then(response => {
                    return response.json();
                }).then(function(response){
                    console.log(response);
                        document.getElementById("customerInfo").innerText = response;
            })
        }

        function view(){

            let viewAccountNumber = document.getElementById("viewAccountNumber").value;
        
            fetch('http://localhost:9090/MossBankSolutions/viewAccount',{
                
                method: 'POST',
                    headers: {
                        "Content-Type": "application/json; charset=UTF-8"
                    },
                    body: JSON.stringify({
                        "accountNumber": viewAccountNumber
                    })
                    
                    }).then(response => {
                        return response.json();
                    }).then(function(response){
                        console.log(response);
                            document.getElementById("customerInfo").innerText = "Username: "+response.username +", Account Balance: "+ response.accountBalance +", Account Number: "+ response.accountNumber +", Approval Status: "+ response.approved;
                })
            }

            function transaction(){
            
                let choice = document.getElementById("choice").value;
                fetch('http://localhost:9090/MossBankSolutions/viewTransaction',{
                    
                    method: 'POST',
                    headers: {
                        "Content-Type": "application/json; charset=UTF-8"
                    },
                    body: JSON.stringify({
                        "choice": choice
                    })
                        }).then(response => {
                            return response.json();
                        }).then(response =>{
                            for (let i in response){
                                let tr = document.createElement("tr");
                                 let td1 = document.createElement("td");
                                let td2 = document.createElement("td");
                                let td3 = document.createElement("td");

                                let accountNum = document.createTextNode
                                (`${response[i].accountNumber}`);
                                let transType = document.createTextNode
                                (`${response[i].transactionType}`);
                                let transAmount = document.createTextNode
                                (`${response[i].transactionAmount}`);

                                td1.appendChild(accountNum);
                                td2.appendChild(transType);
                                td3.appendChild(transAmount);

                                tr.appendChild(td1);
                                tr.appendChild(td2);
                                tr.appendChild(td3);


                                document.getElementById("table").style.display= "table";
                                document.getElementById("customerInfo").innerText = response;
                                document.getElementById("transactionValues").appendChild(tr);
                            }
                    })
                }