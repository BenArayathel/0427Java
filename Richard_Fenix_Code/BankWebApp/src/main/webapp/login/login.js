/**
 * 
 */

onload
document.getElementById("myLogin").style.display = "none";

function loadLogin(){
  console.log("clicked loadLogin.")
  let x = document.getElementById("myLogin");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}


function validateLogin() {
	console.log("validateLogin() has been clicked.");
	
	// collect login input:
	let usr = document.getElementById("usr").value;
	let pwd = document.getElementById("pwd").value;

	let jsonData = {loginName: usr, loginPassword: pwd};

	console.log(jsonData);
	
	fetch('http://localhost:9999/BankWebApp/api/login', {
		  method: 'POST',
		  mode: 'cors',
		  cache: 'no-cache',
		  headers: {'Content-type': 'application/json'}, 
		  body: JSON.stringify(jsonData)
	})
	 //capture response back from the servlet
	.then(response => response.text())
	.then((text) => text.length ? JSON.parse(text) : {})
	.then(json => processLogin(json))
////	.then(json => populateCustomerPage(json))
	.catch(error => console.error(error));
	
};


function processLogin(json) {
	  console.log("processLogin function called.")
	  // Check if object is not empty.
	  if (Object.keys(json).length != 0) {
		  console.log(json);
		  window.mainCustomer = Object.assign({}, json);
		  console.log(window.mainCustomer.firstName + " " + window.mainCustomer.lastName);
//		  populateCustomerPage(json);
		  window.open ('customerPage.html','_self',false);
//		  document.open("customerPage.html");
//		  console.log(json.json());
		  return json;
	  } else {
		  console.log("Customer name not found.");
		  //windows.open('index.html');
		  return null;
	  }
}








