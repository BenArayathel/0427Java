window.onload = function(){

    let loginButton = document.getElementById("loginButton");
    if(loginButton){
		loginButton.addEventListener("click", function(event){
            event.preventDefault();
            verify();
        }) 
}
}

function verify(){
    
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    

        fetch('http://localhost:9090/MossBankSolutions/employeeLogin',{
            method: 'POST',
            headers: {
                "Content-Type": "application/json; charset=UTF-8"
            },
        	body: JSON.stringify({
                "username": username,
                "password": password
            })
            
        	}).then(response => response.text())
            .then(response => {
                    window.location.href = response;
        });
}