onload
loadAdminHome();
fetchAdminInfo();

function loadAdminHome(){
  document.getElementById("myViewCustomer").style.display = "none";
//  document.getElementById("myApprovePending").style.display = "none";
  document.getElementById("myViewTransaction").style.display = "none";
  document.getElementById("myCustomerAccountDetail").style.display = "none";

}


//Toggle to show and hide View Customer form
function loadViewCustomer(){
  console.log("clicked loadViewCustomer.")
  let x = document.getElementById("myViewCustomer");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
  
  // turn off all other Forms
//  let y = document.getElementById("myApprovePending");
//  y.style.display = "none";
  let z = document.getElementById("myViewTransaction");
  z.style.display = "none";
  document.getElementById("myCustomerAccountDetail").style.display = "none";

}

//Toggle to show and hide Approve Pending form
//function loadApprovePending(){
//  console.log("clicked loadApprovePending.")
//  let x = document.getElementById("myApprovePending");
//  if (x.style.display === "none") {
//    x.style.display = "block";
//  } else {
//    x.style.display = "none";
//  }
//  // turn off all other Forms
//  let y = document.getElementById("myViewCustomer");
//  y.style.display = "none";
//  let z = document.getElementById("myViewTransaction");
//  z.style.display = "none";
//  document.getElementById("myCustomerAccountDetail").style.display = "none";
//
//}

//Toggle to show and hide View Transaction form
function loadViewTransaction(){
  console.log("clicked loadViewTransaction.")
  let x = document.getElementById("myViewTransaction");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
  // turn off all other Forms
  let y = document.getElementById("myViewCustomer");
  y.style.display = "none";
//  let z = document.getElementById("myApprovePending");
//  z.style.display = "none";
  document.getElementById("myCustomerAccountDetail").style.display = "none";
  
  fetchTransactionTable()

}

function fetchTransactionTable(){
	fetch('http://localhost:9999/BankWebApp/api/admin/transactions')
	//capture response back from the servlet
	.then(response => response.text())
	.then((text) => text.length ? JSON.parse(text) : {})
	.then(json => populateTransactionTable(json))
	.catch(error => console.error(error));

}

function populateTransactionTable(json) {
    console.log("inside populateTransactionTable.");
    console.log(json[0].accountId, json[0].customerId, json[0].accountType, json[0].balance, json[0].approved);
    let rowCount = 0;
	let accountTable = document.getElementById("transactionBody");
    json.forEach(obj => {
    		  var row = accountTable.insertRow(rowCount);
    		  var cell1 = row.insertCell(0);
    		  var cell2 = row.insertCell(1);
    		  var cell3 = row.insertCell(2);
    		  var cell4 = row.insertCell(3);
    		  var cell5 = row.insertCell(4);
    		  var cell6 = row.insertCell(5);

    		  cell1.innerHTML = json[rowCount].transactionId;
    		  cell2.innerHTML = json[rowCount].accountId;
    		  cell3.innerHTML = json[rowCount].transactionType;
    		  cell4.innerHTML = json[rowCount].amount;
    		  cell5.innerHTML = json[rowCount].endingBalance;
    		  cell6.innerHTML = new Date(json[rowCount].transTime);
    		  rowCount++;

    })
}



function fetchAdminInfo() {
	fetch('http://localhost:9999/BankWebApp/api/admin')
	//capture response back from the servlet
	.then(response => response.text())
	.then((text) => text.length ? JSON.parse(text) : {})
	.then(json => populateCustomerPage(json))
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


function viewCustomer(){
	console.log("entered viewCustomer.");
	let accountId = document.getElementById("view-acct").value;
//	if (isNaN(accountId)) {
//		alert("Account ID must be numeric.");
//		document.getElementById("view-acct").focus();
//		return false;
//	};

	fetch('http://localhost:9999/BankWebApp/api/admin/viewcustomer?accountId='+accountId)
	//capture response back from the servlet
	.then(response => response.text())
	.then((text) => text.length ? JSON.parse(text) : {})
	.then(json => displayCustomerAccountDetail(json))
	.catch(error => console.error(error));
	
}

function displayCustomerAccountDetail(json){
	console.log(json);
	document.getElementById("myCustomerAccountDetail").style.display = "block";
    document.getElementById("a").value = json.accountId;
    document.getElementById("b").value = json.customerId;
    document.getElementById("c").value = json.firstName;
    document.getElementById("d").value = json.lastName; 
    document.getElementById("e").value = getFormattedDate(new Date(json.birthday)); 
    document.getElementById("f").value = json.state; 
    document.getElementById("g").value = json.accountDescription; 
    document.getElementById("h").value = json.balance; 
    document.getElementById("approval").value = json.approved; 
    
    if (json.approved){
        document.getElementById("approve-button").style.display = "none";;
    } else {
        document.getElementById("approve-button").style.display = "block";;
    }
}

function approveAccount(){
	console.log("approving account...");			
}

	

function signOff(){
    console.log("Signing off...");
}

// helper
function getFormattedDate(date) {
    let year = date.getFullYear();
    let month = (1 + date.getMonth()).toString().padStart(2, '0');
    let day = date.getDate().toString().padStart(2, '0');
  
    return month + '/' + day + '/' + year;
}


