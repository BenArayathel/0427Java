window.onload(console.log("hi"));

let account = document.getElementById("depositAccount");
let amount = document.getElementById("depositAmount");
let submit = document.getElementById("depositSubmit");

submit.addEventListener("click", test);

function test() {
    console.log(account);
    console.log(amount);
    console.log(submit);
}




