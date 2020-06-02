(function () {
    window.addEventListener('load', function () {

        let fSetup = document.querySelector("#fSetup");

        let btnCreate = fSetup.querySelector("#btnCreate");
        btnCreate.addEventListener('click', function (event) {
            event.preventDefault();
            console.log('hi you are in setup');
            if (!fSetup.checkValidity()) {
                event.stopPropagation();
            } else {
                let email = document.querySelector("#inpEmail").value;
                let password = document.querySelector("#inpPassword").value;
                let repassword = document.querySelector("#inpRePassword").value;
                if (password === repassword) {
                    callSetupUser(email, password);
                } else {
                    // console.log("Invalid password and repassword");
                    fSetup.classList.add('was-validated');
                }
            }
            fSetup.classList.add('was-validated');
        })

    }, false);
})();

let callSetupUser = async (email, password) => {

    let personId = getCookie('customerId');
    let url = `${BASE_URL}user`
    let options = {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({
            'email': email,
            'password': password,
            'personId': personId,
            'userType': 'Customer'
        })
    }

    try {
        let response = await fetch(url, options);
        let result = await response.json();
        if (result.data) {
            window.location.assign('index.html');
        }
    } catch (error) {
        console.log("USER ERROR", error);
    }
}