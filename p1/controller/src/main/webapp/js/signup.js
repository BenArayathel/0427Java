
//============== SIGN UP ==========================================

function inputUserInfo(){
    console.log("Inputting user information");
    let fName = document.getElementById("user_fName_input").value;
    let lName = document.getElementById("user_lName_input").value;
    let ssn = document.getElementById("user_ssn_input").value;
    let occupation = document.getElementById("user_occupation_input").value;
    let income = document.getElementById("user_income_input").value;
    let isMarried = document.getElementById("user_married_selection").value;
    let ownsHome = document.getElementById("user_ownhome_selection").value;
    let pinOne = document.getElementById("pin_one").value;
    let pinTwo = document.getElementById("pin_two").value;
    let initAmount = document.getElementById("init_amount").value;


    //TODO: Add logic to validate both pin numbers
    //are the same and if not return message
    
    // console.log("Name: " + fName + " " + lName
    //     + "\nSocial Security Number: " + ssn
    //     + "\nOccupation: " + occupation
    //     + "\nAnnual Income: " + income 
    //     + "\nMarried: " + isMarried
    //     + "\nOwns Home: " + ownsHome);

    //Call the fetch function to post
    fetch("http://localhost:9999/controller/api/signup", {
        method: "POST",
        body: JSON.stringify({
            firstName: fName,
            lastName: lName,
            ssn: ssn,
            occupation: occupation,
            income: income,
            married:isMarried,
            ownsHome: ownsHome,
            pin: pinOne,
            initAmount: initAmount
        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        }
    })
    .then(async function (data) {
        if (data.ok) {
            data = await data.json();
            //Here you have your data...
            // console.log("Data: "  + data);
        }
    }).catch(e => console.log('Connection error', e));

}

document.getElementById("apply_button")
    .addEventListener("click", inputUserInfo);

document.getElementById("back_button").addEventListener("click", function(){
    window.location.replace("index.html");
});