let login = document.getElementById("newCustomer_btn");

login.addEventListener("click", newBankCustomer)

//BANK CREATE NEW CUSTOMER PROFILE
function newBankCustomer(event) {
	
	event.preventDefault();
    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    let emailAddress = document.getElementById("emailAddress").value;
    let contactNumber = document.getElementById("contactNumber").value;
    let dob = document.getElementById("dob").value;
    let street = document.getElementById("street").value;
    let city = document.getElementById("city").value;
    let state = document.getElementById("state").value;
       
    console.log(firstName);
    console.log(lastName);
    console.log(emailAddress);
    console.log(contactNumber); 
    console.log(dob);
    console.log(street); 
    console.log(city);
    console.log(state); 

    fetch('http://localhost:9090/Project1_BankOfFriendship/CreateNewCustomer', {
        method: 'POST',
        headers: {
            'Accept': 'application/json,text/plain',
            "Content-Type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify({
        	firstName: firstName,
        	lastName: lastName,
        	emailAddress: emailAddress,
        	contactNumber: contactNumber,
        	dob: dob,
        	street: street,
        	city: city,
        	state: state

        })

    })
     .then((res) => res.text())
//     .then((data) => console.log(data))
              .then(data => {
             console.log(data)
             window.location.assign(data);
         })

}



