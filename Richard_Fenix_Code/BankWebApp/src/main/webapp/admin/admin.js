onload

loadAdminHome();
fetchAdminInfo();

function loadAdminHome(){
  document.getElementById("myViewCustomer").style.display = "none";
  document.getElementById("myApprovePending").style.display = "none";
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
  let y = document.getElementById("myApprovePending");
  y.style.display = "none";
  let z = document.getElementById("myViewTransaction");
  z.style.display = "none";
  document.getElementById("myCustomerAccountDetail").style.display = "none";

}

//Toggle to show and hide Approve Pending form
function loadApprovePending(){
  console.log("clicked loadApprovePending.")
  let x = document.getElementById("myApprovePending");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
  // turn off all other Forms
  let y = document.getElementById("myViewCustomer");
  y.style.display = "none";
  let z = document.getElementById("myViewTransaction");
  z.style.display = "none";
  document.getElementById("myCustomerAccountDetail").style.display = "none";

}

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
  let z = document.getElementById("myViewCustomer");
  z.style.display = "none";
  document.getElementById("myCustomerAccountDetail").style.display = "none";

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
	accountId = document.getElementById("view-acct").value;
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


