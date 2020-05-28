//Get all the buttons and input values


//BUTTONS
let checkBalanceButton = document.getElementById("check_balance_button");
let depositButton = document.getElementById("deposit_button");
let withdrawButton = document.getElementById("withdraw_button");
let transferButton = document.getElementById("transfer_funds_button");
let exitButton = document.getElementById("exit_button");

//INPUT VALUES
let checkingRadio = document.getElementById("checking_radio");
let savingsRadion = document.getElementById("savings_radio");
let depositAmountInput = document.getElementById("deposit_amount_input");
let withdrawAmountInput = document.getElementById("withdrawl_amount_input");
let transferAccountInput = document.getElementById("transfer_to_acctnum_input");
let transferAmountInput = document.getElementById("transfer_amount_input");

//ADD EVENT LISTENERS
checkBalanceButton.addEventListener("click", checkBalance);
depositButton.addEventListener("click", deposit);
withdrawButton.addEventListener("click", withdraw);
transferButton.addEventListener("click", transfer);
exitButton.addEventListener("click", exit);

//TODO Create FUNCTIONS

//Checks the balance of the selected account
//csg = cgeckingSavingsGroup, a group of radio buttons
function checkBalance(){
    let csg = document.getElementsByName("csg_balance");
    let acct = null;
    //Checking selected
    if(csg[0].checked){
        console.log("I have checked my checking account");
        acct = "checking";
    }
    //Savings selected
    if(csg[1].checked){
        console.log("I have checked my savings account");
        acct = "savings";
    }

    //Call the fetch function to post
    fetch("http://localhost:8090/controller/api/checkBalance", {
        method: "POST",
        body: JSON.stringify(
            {
            accountType: acct
            }
        ),
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        }
    })
    .then(response => response.json())
    .then(function (json){
        console.log(json);
        if(json.responseStatus == "200" && acct == "checking"){
            document.getElementById("information").innerText = "You have " + json.message + " in checking."
        }else if(json.responseStatus == "200" && acct == "savings"){
            document.getElementById("information").innerText = "You have " + json.message + " in savings."
        }
    }).catch(e => console.log('Connection error', e));
}

//Deposit into selected account
function deposit(){
    let csg = document.getElementsByName("csg_balance");
    let depAmt = depositAmountInput.value;
    let acct = null;
    //Checking selected
    if(csg[0].checked){
        console.log("I have checked my checking account");
        acct = "checking";
    }
    //Savings selected
    if(csg[1].checked){
        console.log("I have checked my savings account");
        acct = "savings";
    }

    //Call the fetch function to post
    fetch("http://localhost:8090/controller/api/deposit", {
        method: "POST",
        body: JSON.stringify({
            accountType: acct,
            depositAmount: depAmt
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
function withdraw(){
    let csg = document.getElementsByName("csg_balance");
    let wdAmt = withdrawAmountInput.value;
    let acct = null;
    console.log(wdAmt);
    //Checking selected
    if(csg[0].checked){
        console.log("I have checked my checking account");
        acct = "checking";
    }
    //Savings selected
    if(csg[1].checked){
        console.log("I have checked my savings account");
        acct = "savings";
    }
    
    //Call the fetch function to post
    fetch("http://localhost:8090/controller/api/withdraw", {
        method: "POST",
        body: JSON.stringify({
            accountType: acct,
            withdrawAmount: wdAmt
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
function transfer(){
    console.log("I have transfered frunds from my account");
}
function exit(){
    console.log("I have exited my account");
}

function updateDOM(inValue){
    let str = document.getElementById("information_div");
    str.innerText = "<h3>" + inValue + "</h3>";
}