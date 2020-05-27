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









