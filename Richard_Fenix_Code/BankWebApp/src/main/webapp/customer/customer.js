onload
document.getElementById("myDeposit").style.display = "none";

populateCustomerPage();

function loadDeposit(){
  console.log("clicked loadDeposit.")
  let x = document.getElementById("myDeposit");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}

//function populateCustomerPage(json) {
//    //let customerPage = window.open('customerPage.html','_self',false);
//    //document.open("customerPage.html");
//    console.log("inside populateCustomerPage");
//    alert(json.firstName);
//    document.getElementById("greet").innerHTML = "Richard";
//    document.getElementById("fName").innerHTML = json.firstName;
//    document.getElementById("lName").innerHTML = json.lastName;        
//}

function populateCustomerPage() {
    //let customerPage = window.open('customerPage.html','_self',false);
    //document.open("customerPage.html");
    console.log("inside populateCustomerPage");
    console.log(window.mainCustomer);
    //alert(window.mainCustomer);
    document.getElementById("greet").innerHTML = "Richard";
    document.getElementById("fName").innerHTML = window.mainCustomer.firstName;
    document.getElementById("lName").innerHTML = window.mainCustomer.lastName;        
}

