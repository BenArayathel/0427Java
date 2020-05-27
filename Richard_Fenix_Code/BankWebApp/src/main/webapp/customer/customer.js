onload
document.getElementById("myDeposit").style.display = "none";

fetchCustomerInfo();

function loadDeposit(){
  console.log("clicked loadDeposit.")
  let x = document.getElementById("myDeposit");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}


function fetchCustomerInfo() {
	fetch('http://localhost:9999/BankWebApp/api/customer')
	//capture response back from the servlet
	.then(response => response.text())
	.then((text) => text.length ? JSON.parse(text) : {})
	.then(json => populateCustomerPage(json))
	.catch(error => console.error(error));
}


function populateCustomerPage(json) {

    console.log("inside populateCustomerPage");
    console.log(json.firstName);
    document.getElementById("greet").innerHTML = "Richard";
    document.getElementById("fName").innerHTML = json.firstName;
    document.getElementById("lName").innerHTML = json.lastName;        
}



