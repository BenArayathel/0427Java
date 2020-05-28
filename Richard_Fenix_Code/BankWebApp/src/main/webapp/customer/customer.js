onload
document.getElementById("myDeposit").style.display = "none";
document.getElementById("myWithdraw").style.display = "none";

fetchCustomerInfo();

function loadDeposit(){
  console.log("clicked loadDeposit.")
  let x = document.getElementById("myDeposit");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
  
  // turn off all other Forms
  let y = document.getElementById("myWithdraw");
  y.style.display = "none";

}

function loadWithdraw(){
  console.log("clicked loadWithdraw.")
  let x = document.getElementById("myWithdraw");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
  // turn off all other Forms
  let y = document.getElementById("myDeposit");
  y.style.display = "none";
	  
}



function fetchCustomerInfo() {
	fetch('http://localhost:9999/BankWebApp/api/customer')
	//capture response back from the servlet
	.then(response => response.text())
	.then((text) => text.length ? JSON.parse(text) : {})
	.then(json => populateCustomerPage(json))
	.then(json => fetchAccountListByCustomer())
	.catch(error => console.error(error));
}

function populateCustomerPage(json) {

    console.log("inside populateCustomerPage");
    console.log(json.firstName);
    document.getElementById("greet").innerHTML = json.firstName;
    document.getElementById("customerId").innerHTML = json.customerId;
    document.getElementById("fName").innerHTML = json.firstName;
    document.getElementById("lName").innerHTML = json.lastName; 
    return json;
}

function fetchAccountListByCustomer() {
	fetch('http://localhost:9999/BankWebApp/api/customer/accountlist')
	//capture response back from the servlet
	.then(response => response.text())
	.then((text) => text.length ? JSON.parse(text) : {})
	.then(json => populateCustomerAccountSection(json))
	.catch(error => console.error(error));

}

function populateCustomerAccountSection(json) {
    console.log("inside populateCustomerAccountSection");
    console.log(json[0].accountId, json[0].customerId, json[0].accountType, json[0].balance, json[0].approved);
    let rowCount = 0;
	let accountTable = document.getElementById("accountBody");
    json.forEach(obj => {
    		  var row = accountTable.insertRow(rowCount);
    		  var cell1 = row.insertCell(0);
    		  var cell2 = row.insertCell(1);
    		  var cell3 = row.insertCell(2);
    		  cell1.innerHTML = json[rowCount].accountId;
    		  cell2.innerHTML = json[rowCount].accountType;
    		  cell3.innerHTML = json[rowCount].balance;
    		  rowCount++;

    })
//    document.getElementById("greet").innerHTML = json.firstName;
//    document.getElementById("fName").innerHTML = json.firstName;
//    document.getElementById("lName").innerHTML = json.lastName;        
	
}

// List<Account> aList = bankServiceController.getAccountListByCustomerId(customer.getCustomerId());




