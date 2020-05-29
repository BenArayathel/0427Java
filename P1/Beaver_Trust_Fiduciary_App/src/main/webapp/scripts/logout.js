// goal: logout, then redirect home

let logoutButton = document.getElementById("logoutButton");

logoutButton.addEventListener("click", logout);

// get is default, doesn't need to be stated
function logout() {
    fetch('http://localhost:9999/Beaver_Trust_Fiduciary_App/logout')
        .then(
            console.log("Session terminated")
        )
    };