let login = document.getElementById("newLogin_btn");

login.addEventListener("click", newBankLogin)

//BANK CREATE NEW CUSTOMER PROFILE
function newBankLogin(event) {
	
	event.preventDefault();
    let userName = document.getElementById("userName").value;
    let userPassword = document.getElementById("userPassword").value;
    let userPassCon = document.getElementById("userPassCon").value;

       
    console.log(userName);
    console.log(userPassword);
    console.log(userPassCon);


    fetch('http://localhost:9090/Project1_BankOfFriendship/CreateNewLogin', {
        method: 'POST',
        headers: {
            'Accept': 'application/json,text/plain',
            "Content-Type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify({
        	userName: userName,
        	userPassword: userPassword,
        	userPassCon: userPassCon,

        })

    })
     .then((res) => res.text())
//     .then((data) => console.log(data))
              .then(data => {
             console.log(data)
             window.location.assign(data);
         })

}



