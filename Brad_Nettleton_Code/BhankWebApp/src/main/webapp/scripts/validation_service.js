console.log("validation_service connected");

function isValidUsername(username) {
    let b = false;
    if(/[a-zA-Z ]{1,20}/.test(username) && !isNull(username)) {
        b = true;
    } 
    return b;
}

function isValidPassword(password) {
    let b = false;
    if(/[a-zA-Z0-9]{4,20}/.test(password) && !isNull(password)) {
        b = true;
    }
    return b;
}

function isValidStartingBalance(startingBalance) {
    let b = false;
    if(/[0-9]{1,10}/.test(startingBalance) && !isNull(startingBalance) && parseFloat(startingBalance)>0) {
        b = true;
    }
    return b;
}

function isValidTransactionAmount(transactionAmount) {
    let b = false;
    if(/[0-9]{1,10}/.test(transactionAmount) && !isNull(transactionAmount) && parseFloat(transactionAmount)>0) {
        b = true;
    }
    return b;
}

function isNull(x) {
    let b = false;
    if(x === null) {
        b=true;
    }
    return b;
}