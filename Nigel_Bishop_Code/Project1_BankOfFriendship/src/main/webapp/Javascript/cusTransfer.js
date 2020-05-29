let depoist = document.getElementById("TransSubmit_btn");

depoist.addEventListener("click", bankTransferc)

//TRANSFER INTO BANK ACCOUNT
function bankTransferc(event) {
	
	event.preventDefault();

	let source = document.getElementById("selSource").value;
	let destination = document.getElementById("selDestination").value;
    let transferAmount = document.getElementById("transAmount").value;
    console.log(source);
    console.log(destination);
    console.log(transferAmount);

    let compMessage = '';
    
    if(source == destination)
    {
        compMessage = "TRANSACTION CANCEL"
    }
    else
    {
        if(transferAmount <= 0 || transferAmount == null || transferAmount < 1){
            compMessage = "TRANSACTION CANCEL"
       }
       else{
           fetch('http://localhost:9090/Project1_BankOfFriendship/CusTransfer', {
               method: 'POST',
               headers: {
                   'Accept': 'application/json,text/plain',
                   "Content-Type": "application/json; charset=UTF-8"
               },
               body: JSON.stringify({
            	   source: source,
            	   destination: destination,
            	   transferAmount: transferAmount,
               })
       
           })
            .then((res) => res.text())
                     .then(data => {
                    console.log(data)
                    window.location.assign(data);
                })
                compMessage = "TRANSFER TRANSACTION COMPLETED"
       }
    }

         document.getElementById('output').innerHTML = compMessage;
}
