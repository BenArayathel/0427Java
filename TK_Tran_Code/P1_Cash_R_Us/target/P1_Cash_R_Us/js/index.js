// window.onload = function () {
//     this.document.getElementById("loginBtn").addEventListener("click", this.loginUser)
//     this.document.getElementById("registerBtn").addEventListener("click", this.registerUser)
// }
//
// function loginUser(event) {
//     event.preventDefault();
//
//     let username = document.getElementById("usernameInput").value; // assigns username value from form
//     let password = document.getElementById("passwordInput").value; // assigns password value from form
//
//     // Pulls from API and redirects to next page.
//     fetch("http://localhost:9999/loginServlet", {
//         method: "POST",
//         body: JSON.stringify({ // converts JS obj to JSON string
//             "username": username,
//             "password": password
//         }),
//         headers: {"Content-type": "application/json"}
//     }) // fetch will return a promise that contains a JSON string response
//
//         // To redirect user after successful login:
//         .then(response => redirect(response))
//
//         // To manipulate DOM:
//         // .then(response => response.json()) // converts JSON string back to JS obj
//         // .then(object => DOMManip(object)) // with that object, we're able to manipulate the DOM
//         .catch(error => console.log(`Error..Status Code: ${error.status}`)); // else catch any errors
// }
//
// function redirect(json) {
//     // console.log(json); // prints response JSON
//     // console.log(json.url); // prints endpoint
//     if (json.url.includes("testSuccess")) {
//         window.location.href = json.url;
//         // window.location.href = "/loginServlet";
//     } else {
//         console.log("Cannot redirect; invalid credentials!");
//     }
// }
//
// function DOMManip(JSON) {
//     let div = document.createElement("div");
//     let p = document.createElement("p");
//     p.innerText = `Username: ${JSON.username}, Password: ${JSON.password}`;
//     document.body.append(div);
//     div.appendChild(p);
// }
//
// function registerUser(event) {
//     event.preventDefault();
//
//     // TODO: create a new account and send to DB.
// }