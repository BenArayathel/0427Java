let storedData = JSON.parse(localStorage.getItem("userData"));
console.log(storedData);
var uName = storedData["name"];
var chBalance = storedData["checkingBalance"];
var svBalance = storedData["savingsBalance"];
var cBal = document.getElementById("checkingBalance");
var sBal = document.getElementById("savingsBalance");
var greet = document.getElementById("customerGreeting")
greet.innerHTML = `Hello, ${uName}. How may we assist you?`;
cBal.innerHTML = `$${chBalance}`;
sBal.innerHTML = `$${svBalance}`;