/**
 * 
 */

function sendData(){

    let user = document.getElementById("user_name_input").value;
    let pw = document.getElementById("user_password_input").value;

    // console.log("UserName: " + user);
    // console.log("Password: " + pw);
    postData2(user, pw);

}

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

function postData2(user, pw){
    
    fetch("http://localhost:9999/controller/master", {
        method: "POST",
        body: JSON.stringify({
            userID: user,
            password: pw
        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8",
            // "Accept": "application/json"
        }
    });
    // .then(response => response.json())
    // .then(function ret(json){
    //     //var fname = json.val;
    //     console.log("I am here now!!! " + json.userID);
       
    // });
}


let submitButton = document.getElementById("submit_button").addEventListener("click", sendData);
