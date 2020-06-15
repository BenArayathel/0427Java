let login = document.getElementById("login_btn");

login.addEventListener("click", banklogin)

//BANK LOGIN
function banklogin(event) {
	
	event.preventDefault();
    let userName = document.getElementById("username").value;
    let userPassword = document.getElementById("password").value;
    
    console.log(username);
    console.log(password);    

    fetch('http://localhost:9090/Project1_BankOfFriendship/BankLoginPage', {
        method: 'POST',
        headers: {
            'Accept': 'application/json,text/plain',
            "Content-Type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify({
        	userName: userName,
        	userPassword: userPassword
        })

    })
     .then((res) => res.text())
//     .then((data) => console.log(data))
              .then(data => {
             console.log(data)
             window.location.assign(data);
         })

}



