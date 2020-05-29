window.onload = function() {
    this.document.getElementById("subLogin").addEventListener("click", this.loginUser);
}

function loginUser(event) {
    event.preventDefault();
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    console.log(username);
    console.log(password);
    fetch('http://localhost:8090/Project1/app/Login', {
            method: 'POST',
            body: JSON.stringify({
                "userName": username,
                "password": password,
            }),
            headers: {"Content-type": "application/json; charset=UTF-8", "Accept": "application/json"}
        })
        .then(response => {
        	console.log(response)
        	window.location.href = response.url;
        })
        .catch(error => console.error(error));
}