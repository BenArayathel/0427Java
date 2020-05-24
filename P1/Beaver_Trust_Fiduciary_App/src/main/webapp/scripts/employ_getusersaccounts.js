let userAccountsList = document.getElementById("userAccountsList");
let submit5 = document.getElementById("seeAllUserAcounts");

submit5.addEventListener("click", getAccounts);

function getAccounts() {
    userAccountsList.style.display = "block";
    let username = document.getElementById("whichUser").value;

    // make call to api/servlet
    fetch("http://localhost:9999/Beaver_Trust_Fiduciary_App/getuseraccounts", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
            'Accept': 'application/json',
        },
        body: JSON.stringify({
            username: username,
        })
    })
    .then(response => {
        console.log(response)
        return response.json();
    })
    .then(json => {
        // console.log(json)
        for (let i in json) {
            let li = document.createElement("LI");
            let transInfo = document.createTextNode
            (`Account Name: ${json[i].account_name} 
                Account_ID: ${json[i].account_id}    
                    User ID: ${json[i].user_id}
                        Balance: ${json[i].balance}`);
            li.appendChild(transInfo);
            userAccountsList.appendChild(li);
        }
    })
}