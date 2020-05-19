window.onload = function() {
    this.document.getElementById("subLogin").addEventListener("click", loginUser);
}

function loginUser() {
    fetch('')
}



// Fetch Syntax
function postToPlaceHolderFetch() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    fetch('http://localhost:9999/BasicServlets/', {
            method: 'POST',
            body: JSON.stringify({
                "username": username,
                "password": password
            }),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        })
        .then(response => response.json())
        .then(json => DOMManipulationPost(json))
        .catch(error => console.error(error));
}