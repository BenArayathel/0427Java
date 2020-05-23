let submit = document.getElementById("submit_request");

// get back a url to either 'sorry didn't work' or 'thanks, check back later' and 
// make html and success redirects to welcome

submit.addEventListener("click", createAccount)

function createAccount() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    
    console.log(username);
    console.log(password);    

    fetch('http://localhost:9999/Beaver_Trust_Fiduciary_App/createuseraccount', {
        method: 'POST',
        body: JSON.stringify({
            username: username,
            password: password
        }),
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
            'Accept': 'application/json'
        }
        })
        // THIS working approach does a redirect
        .then(response => response.text())
        .then(response => {
            console.log(response)
            window.location.assign(response);
        })
        .catch(error => {
            console.log(error);
        })
}