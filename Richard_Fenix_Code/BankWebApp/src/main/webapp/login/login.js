onload
loadHome();

function loadHome() {
	document.getElementById("myLogin").style.display = "none";
	document.getElementById("myNewAccount").style.display = "none";
	document.getElementById("mySignUp").style.display = "none";	
}

//Toggle to show and hide login form
function loadLogin(){
  console.log("clicked loadLogin.")
  let x = document.getElementById("myLogin");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
  // turn off all other Forms
  let y = document.getElementById("myNewAccount");
  y.style.display = "none";
  let z = document.getElementById("mySignUp");
  z.style.display = "none";
}

//Toggle to show and hide new account form
function loadNewAccount(){
  console.log("clicked loadNewAccount.")
  let x = document.getElementById("myNewAccount");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
  
  // turn off all other Forms
  let y = document.getElementById("myLogin");
  y.style.display = "none";
  let z = document.getElementById("mySignUp");
  z.style.display = "none";
}

//Toggle to show and hide login form
function loadSignUp(){
  console.log("clicked loadSignUp.")
  let x = document.getElementById("mySignUp");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
  // turn off all other Forms
  let y = document.getElementById("myLogin");
  y.style.display = "none";
  let z = document.getElementById("myNewAccount");
  z.style.display = "none";
}


function validateLogin() {
	console.log("validateLogin() has been clicked.");
	
	// collect login input:
	let usr = document.getElementById("usr").value;
	let pwd = document.getElementById("pwd").value;

	let jsonData = {loginName: usr, loginPassword: pwd};

	console.log(jsonData);
	
	/* Moved this from front end to a full postback to the server (posting via html form)
	fetch('http://localhost:9999/BankWebApp/api/login', {
		  method: 'POST',
		  mode: 'cors',
		  cache: 'no-cache',
		  headers: {'Content-type': 'application/json'}, 
		  body: JSON.stringify(jsonData)
	})
	return;
    */
	
};


function validateCustomerAccount(){
	console.log("validateCustomerAccount() has been clicked.");
	
	return;

}

function validateNewAccount() {
	console.log("validateNewAccount() has been clicked.");
	
	return;
	
	
};


function validateSignUp() {
	console.log("validateSignUp() has been clicked.");
	
	return;
	
	
};








