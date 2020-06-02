// const BASE_URL = "http://localhost:8989/POneV1/api/";
let regContent = document.querySelector("#regContent");

let btnNewUser = document.querySelector("#btnNewUser");
btnNewUser.addEventListener("click", function (event) {
    event.preventDefault();
    createNewUserForm();
})

let btnFinishRegistration = document.querySelector("#btnFinishRegistration");
btnFinishRegistration.addEventListener("click", function (event) {
    event.preventDefault();
    finishRegistration();
})

function createNewUserForm() {
    regContent.innerHTML = `
    <div class="card shadow mt-3">
        <div class="card-body">
            <form class="form-signin" style="max-width: 100%;" id="fCreateCustomer">
                <h1 class="h3 mb-3 font-weight-normal text-center"><strong>Registration Form</strong></h1>
                <h5 class="mb-3 font-weight-normal text-center">Enter the following information</h5>
                <div class="row br-bottom">

                    <div class="col-12 col-md-6 br-right">

                        <div class="form-group">
                            <label for="inpName" class="text-left">First Name:</label>
                            <input type="text" id="inpName" class="form-control mb-3" placeholder="First Name"
                                required>
                        </div>
                        <div class="form-group">
                            <label for="inpLName" class="text-left">Last Name:</label>
                            <input type="text" id="inpLName" class="form-control mb-3" placeholder="Last Name"
                                required>
                        </div>
                        <div class="form-group">
                            <label for="inpDob" class="text-left">Date of Birth:</label>
                            <input type="date" id="inpDob" class="form-control mb-3" placeholder="MM-DD-YEAR"
                                required min="1920-01-01" max="2002-5-29">
                        </div>
                        <div class="form-group">
                            <label for="inpCity" class="text-left">City:</label>
                            <input type="text" id="inpCity" class="form-control mb-3" placeholder="City"
                                required>
                        </div>

                    </div>

                    <div class="col-12 col-md-6">

                        <div class="form-group">
                            <label for="inpSsn" class="text-left">SSN:</label>
                            <input type="password" id="inpSsn" class="form-control mb-3"
                                placeholder="Social Security Number..." required>
                        </div>
                        <div class="form-group">
                            <label for="inpPhoneNumber" class="text-left">Phone Number:</label>
                            <input type="tel" id="inpPhoneNumber" class="form-control mb-3"
                                placeholder="Phone number..." required>
                        </div>
                        <div class="form-group">
                            <label class="mr-sm-2" for="selAType">Account type:</label>
                            <select class="custom-select mr-sm-2" id="selAType" required>
                                <option selected value="1">Saving</option>
                                <option value="1">Checking</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="inpBalance" class="text-left">Starting Balance:</label>
                            <input type="number" id="inpBalance" class="form-control mb-3" step="0.01"
                                placeholder="Starting Balance..." required>
                        </div>
                        <button class="btn btn-danger btn-block text-uppercase" type="submit">
                            <strong>Accept</strong>
                        </button>

                    </div>

                </div>

                <p class="mt-4 mb-0 text-muted text-center">Hackbank © 2020</p>
            </form>
        </div>
    </div>`;
    let fCreateCustomer = document.querySelector("#fCreateCustomer");
    fCreateCustomer.reset;
    let btnSubmit = fCreateCustomer.querySelector("button");
    btnSubmit.addEventListener("click", function (event) {
        event.preventDefault();

        if (!fCreateCustomer.checkValidity()) {
            event.stopPropagation();
        } else {

            let typeAccount = document.querySelector("#selAType").value;
            let startBalance = document.querySelector('#inpBalance').value;
            let auxSBalance = parseFloat(startBalance);

            if (typeAccount > "0" && auxSBalance >= 0) {
                createCustomer();
            } else {
                createToast('Fill the form to create an Account', 'danger');
                console.log("Fill the form to create an Account");
            }
        }
        fCreateCustomer.classList.add('was-validated');



    })
}

function finishRegistration() {
    regContent.innerHTML = `
    <div class="row mt-3 d-flex justify-content-center">
        <div class="card shadow rounded">
            <div class="card-body">
                <form class="form-signin text-center">
                    <h1 class="h3 mb-3 font-weight-normal"><strong>Finishing Registration</strong></h1>
                    <h5 class="mb-3 font-weight-normal">Please enter the following information:</h5>
                    <div class="form-group">
                        <label for="inpNoAccount" class="text-left">Account Number</label>
                        <input type="email" id="inpNoAccount" class="form-control mb-3"
                            placeholder="Account Number..." required="">
                    </div>
                    <button class="btn btn-lg btn-danger btn-block text-uppercase" type="submit">
                        <strong>Accept</strong>
                    </button>
                    <p class="mt-4 mb-0 text-muted">HackBank © 2020</p>
                </form>
            </div>
        </div>
    </div>`;
    let form = document.querySelector("form");
    form.reset;
    let btn = form.querySelector("button");
    btn.addEventListener("click", function (event) {
        event.preventDefault();
        verifiedAccount()
    })
}

let verifiedAccount = async () => {
    let account = document.querySelector("#inpNoAccount").value;

    let url = `${BASE_URL}account/verified/${account}`;
    try {
        let response = await fetch(url);
        let result = await response.json();
        if (result.data) {
            setCookie('customerId', result.data, 1);
            window.location.assign('setupuser.html');
        }
    } catch (error) {
        console.error("VERIFIED ACCOUNT ERROR", error);
    }

}

let createCustomer = async () => {
    let name = document.querySelector("#inpName").value;
    let lName = document.querySelector("#inpLName").value;
    let dob = new Date(document.querySelector("#inpDob").value);
    let city = document.querySelector("#inpCity").value;
    let ssn = document.querySelector("#inpSsn").value;
    let phoneNumber = document.querySelector("#inpPhoneNumber").value;
    let aType = document.querySelector("#selAType").value;
    let balance = document.querySelector("#inpBalance").value;

    let newDob = `${dob.getMonth()}-${dob.getDate()}-${dob.getFullYear()}`;

    let url = `${BASE_URL}customer`;
    try {
        let response = await fetch(url, {
            method: "POST",
            mode: 'cors',
            header: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify({
                'name': name,
                'lastName': lName,
                'dob': newDob,
                'city': city,
                'ssn': ssn,
                'phoneNumber': phoneNumber,
            })
        });
        let result = await response.json();
        if (result.data["id"]) {
            // Send the approval account
            createAccount(aType, balance, result.data["id"]);
        } else {
            createToast('There is an Error creating the customer, please contact HackBank support (000)000-0000.', 'danger');
            console.log('you are in customer', result);
        }
    } catch (error) {
        console.log(error);
    }

}

let createAccount = async (type, balance, personId) => {

    let nameAccount = '';

    if (type === '1') nameAccount = 'Saving';
    if (type === '2') nameAccount = 'Checking';

    let url = `${BASE_URL}approval`;
    let options = {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({
            'accountTypeId': type,
            'accountTypeName': nameAccount,
            'status': 'Pending',
            'balance': balance,
            'personId': personId
        })
    }

    try {
        let response = await fetch(url, options);
        let result = await response.json();
        if (result.status === 'ok') {
            createToast('Congratulations Your new account is pending to approve, call a representant to finish it.', 'success');
        } else {
            createToast('There is an Error with the registration, please contact HackBank support (000)000-0000.', 'danger');
            console.log("NO DATA - Account doesn't exist.");
        }
    } catch (error) {
        console.error("ACCOUNT ERROR", error);
    }

}

