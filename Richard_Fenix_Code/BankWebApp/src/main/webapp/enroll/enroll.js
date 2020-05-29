onload

fetchCustomerInfo();

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
    document.getElementById("greet").innerHTML = json.firstName;
    document.getElementById("customerId").innerHTML = json.customerId;
    document.getElementById("fName").innerHTML = json.firstName;
    document.getElementById("lName").innerHTML = json.lastName; 
    return json;
}

