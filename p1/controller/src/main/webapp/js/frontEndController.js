/**
 * window.location.assign("/home.html");
 */


 //========================== WORKING CODE =======================
function sendData(){

    let user = document.getElementById("user_name_input").value;
    let pw = document.getElementById("user_password_input").value;

    if(user != "" && pw != "") {
        // console.log("I am a user");
        user_login(user, pw);
    }
    else if(user == "0000"){
        // console.log("I am an employee");
        window.location.replace("employee.html");
    }else{
        document.getElementById("error").innerText = "Incorrect login/PIN";
    }

}

//Function call to verify userID and password
function user_login(user, pw){
    
    fetch("http://localhost:8090/controller/api/LoginServlet", {
        method: "POST",
        body: JSON.stringify({
            userID: user,
            password: pw
        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        }
    })
    .then(response => response.json())
    .then(function (json){
        console.log(json);
        if(json.responseStatus == "200"){
            window.location.replace("home.html");
        }else if (json.responseStatus == "403"){
            document.getElementById("error").innerText = json.message;
        }else{
            //window.location.replace("/controller");
            document.getElementById("error").innerText = "Incorrect login/PIN";
        }
    }).catch(e => console.log('Connection error', e));

}

//Gets the login button
var loginButton = document.getElementById("login_button");
loginButton.addEventListener("click", sendData);

//Gets the signup button and does local redirect
document.getElementById("signup_button").addEventListener("click", function(){
    window.location.replace("signup.html");
});




//================ NOT YET USED CODE ===============================


function postData(user, pw){
    

    fetch("http://localhost:9999/controller/master", {
        method: "post",
        body: JSON.stringify({
            userID: user,
            password: pw
        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8",
            "Accept": "application/json"
        }
    })
    .then(response => response.json())
    .then(function ret(json){
        console.log("I am here! " + json.userID);
    });
}






//========================================================================
// OLD CODE

// This is the controller or the front end acceptance and processing of user data

// function login(){

//     //Gets user ID and PIN
//     let usernameText = document.getElementById("user_name_input").value;
//     let passwordText = document.getElementById("password_input").value;

//     // console.log("Username: " + usernameText);
//     // console.log("Password: " + passwordText);

//     postData(usernameText, passwordText);
    
    
// }

// function postData(userID, passwd){
//     fetch('http://localhost:9999/servelets-app/login', {
//         method: 'POST',
//         body: JSON.stringify({
//             userID: userID,
//             password: passwd
//         }), 
//         headers: {
//             "Content-type": "application/json; charset=UTF-8",
//             "Accept": "application/json"
//         }
//     })
//     .then(response => response.json())
//     .then(function ret(json){
//         //var fname = json.val;
//         console.log("I am here now!!! " + json.userID);
//         //TODO: Will get data from query for verification
//         //This is to verify the users credentials
//         if(json.userID == "15000" && json.password == "1234"){
//             console.log("Login was successful");
//             incorrectUNPW.innerText = "Login was successful";
//         }else{
//             console.log("Failure");
//             incorrectUNPW.innerText = "You have entered an incorrect userID/pin\nPlease try again";
//         }
//         //AppendDOM(response);
//     });




// }

// let logInButton = document.getElementById("login_button");
// logInButton.addEventListener("click", login);

// let incorrectUNPW = document.getElementById("error");

// document.getElementById("signup_button").addEventListener("click", function(){
//     window.location.replace("signup.html");
// });
