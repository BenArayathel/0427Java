(function () {

    window.addEventListener("load", function () {

        let btnLogin = document.querySelector("#btnLogin");
        btnLogin.addEventListener("click", function (event) {
            event.preventDefault();
            let email = document.querySelector("#inpEmail").value;
            let password = document.querySelector("#inpPassword").value;
            if (isValidEmail(email) && isValidPassword(password)) {
                auth(email, password);
            } else {
                createToast(`Invalid Credentials...Try Again.`, 'danger');
            }
        })

        let btnRegister = this.document.querySelector("#btnRegister");
        btnRegister.addEventListener("click", function (event) {
            event.preventDefault();
            window.location.assign('./registration.html');
        })

    }, false);

})();

let auth = async (email, password) => {
    let options = {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({
            'email': email,
            'password': password
        })
    }

    let url = BASE_URL + "auth";
    try {
        let response = await fetch(url, options);
        let result = await response.json();
        if (result.status === 'ok' && result.data) {

            setSession('user', result.data);

            if (result.data['userType'] === 'Employee')
                document.location.assign("adminportal.html")
            else
                document.location.assign("customerportal.html")
        } else {
            createToast(`Invalid Credentials...Try Again.`, 'danger');
            console.log('Invalid credentials');
        }
    } catch (error) {
        console.error("AUTH ERROR", error);
    }
}

function isValidEmail(email) {
    let patt = /([a-zA-Z]{1}[\w]+)@([\w]+\.)([\w])+/;
    return patt.test(email);
}

function isValidPassword(password) {
    /** 
     * Start of group
     * (?=.*\d)			#   must contains one digit from 0-9
     * (?=.*[a-z])		#   must contains one lowercase characters
     * (?=.*[A-Z])		#   must contains one uppercase characters
     * (?=.*[@#$%\.])		#   must contains one special symbols in the list "@#$%"
     * .				#   match anything with previous condition checking
     * {6,10}			#   length at least 6 characters and maximum of 10
    */
    let patt = /((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%\.]).{6,10})/;
    return patt.exec(password);
}