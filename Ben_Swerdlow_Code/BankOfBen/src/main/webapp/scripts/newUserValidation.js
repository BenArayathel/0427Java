window.onload = function() {
    this.setUsernameAndEmailFields();

    this.setDobValidation();

    // this.document.addEventListener('DOMContentLoaded', function(e) {
    //     // const fv = FormValidation.formValidation(
    //     //     document.getElementById('regInfoForm'),
    //     //     {
    //     //         fields: {
    //     //             name: {
    //     //                 validators: {
    //     //                     notEmpty: {
    //     //                         message: 'The name is required'
    //     //                     }
    //     //                 }
    //     //             },
    //     //             date: {
    //     //                 validators: {
    //     //                     notEmpty: {
    //     //                         message: 'The date is required'
    //     //                     },
    //     //                     date: {
    //     //                         format: 'MM/DD/YYYY',
    //     //                         min: '01/01/2010',
    //     //                         max: '12/30/2020',
    //     //                         message: 'The date is not valid'
    //     //                     }
    //     //                 }
    //     //             },
    //     //         },
    //     //         plugins: {
    //     //             trigger: new FormValidation.plugins.Trigger(),
    //     //             bootstrap: new FormValidation.plugins.Bootstrap(),
    //     //             submitButton: new FormValidation.plugins.SubmitButton(),
    //     //             icon: new FormValidation.plugins.Icon({
    //     //                 valid: 'fa fa-check',
    //     //                 invalid: 'fa fa-times',
    //     //                 validating: 'fa fa-refresh',
    //     //             }),
    //     //         },
    //     //     }
    //     // );
    
    //     // $('[name="date"]')
    //     //     .datepicker({
    //     //         format: 'mm/dd/yyyy',
    //     //         startDate: '01/01/2010',
    //     //         endDate: '12/30/2020',
    //     //     })
    //     //     .on('changeDate', function(e) {
    //     //         // Revalidate the date field
    //     //         fv.revalidateField('date');
    //     //     });
    // });

    let regInfoForm = this.document.getElementById("regInfoForm");
    if (regInfoForm) {
        regInfoForm.addEventListener("submit", submitCompleteRegistration);
    }
}

function submitCompleteRegistration(event) {
    event.preventDefault();
    // password and confirm password are made globally and are checked in
    // validatePassword()
    fetch('http://localhost:9999/BankOfBen/api/newUserRegistration', {
        method: 'POST',
        body: JSON.stringify({
            "firstName": document.getElementById("firstName").value.trim(),
            "middleName": document.getElementById("middleName").value.trim(),
            "lastName": document.getElementById("lastName").value.trim(),
            "momsMaidenName": document.getElementById("momsMaidName").value.trim(),
            "dob": document.getElementById("dob").value.trim(),
            "ssn": document.getElementById("ssn").value.trim().replace(/[\- ]/g, ""),
            "email": document.getElementById('userEmail').innerText,
            "phoneNumber": document.getElementById("phoneNum").value.trim().replace(/[()\- ]/g, "").replace(/\+1/g,""),
            "username": document.getElementById('userUsername').innerText,
            "password": document.getElementById("inputPassword").value
        }),
        headers: {"Content-type": "application/json; charset=UTF-8"}
    })
    .then(response => processResponse(response))
    .catch(error => console.error(error));
}

async function processResponse(response) {
    let responseJSON;
    if (response.url.endsWith(".html") && response.url !== window.location.href) {
        window.location.href = response.url;
    } else {
        responseJSON = await response.json();
        if ("ssn" in responseJSON) {
            if (document.getElementById("ssnExists")){
                document.getElementById("ssnExists").innerText = `Social security number \"${responseJSON.ssn}\" is an existing Bank of Ben user social security number.`;
            } else {
                customCreateElement(
                    "ssnExists",
                    document.getElementById("ssn"),
                    {text: `Social security number \"${responseJSON.ssn}\" is an existing Bank of Ben user social security number.`});
                }
        } else {
            console.error("Couldn't figure out what to do");
        }
    }
}

function customCreateElement(id, attachToElement, inner){
    let div = document.createElement("div");
    div.id = id;
    if (inner && "text" in inner) {
        div.innerText = inner.text;
    } else if (inner && "html" in inner) {
        div.innerHTML = inner.html;
    } else if (inner) {
        throw "Couldn't figure out how to populate element. Inner object must contain either \"text\" or \"html\" property"
    }
    attachToElement.appendChild(div);
}

async function setUsernameAndEmailFields() {
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/GetTempUser', {
            method: 'POST',
            headers: {"Content-type": "application/json; charset=UTF-8"}
        });

    if (response.url.endsWith(".html") && response.url !== window.location.href) {
        window.location.href = response.url;
    } else {
        let userDetails = await response.json();
        let email = document.getElementById('userEmail');
        email.innerText = userDetails.email;
        let username = document.getElementById('userUsername');
        username.innerText = userDetails.username;
    }
}

function setDobValidation() {
    let dob = document.getElementById("dob");
    let today18YearsAgo = new Date(new Date().setFullYear(new Date().getFullYear() - 18))
    let yyyy = today18YearsAgo.getFullYear();
    let mm = `${today18YearsAgo.getMonth()+1}`
    mm = mm.padStart(2, '0');
    let dd = `${today18YearsAgo.getDate()}`.padStart(1, '0');
    // console.log(`${yyyy}-${mm}-${dd}`);
    dob.max = `${yyyy}-${mm}-${dd}`;
}

let password = document.getElementById("inputPassword");
let confirm_password = document.getElementById("inputPasswordConfirm");

function validatePassword(){
  if(password.value != confirm_password.value) {
    confirm_password.setCustomValidity("Password and confirm password fields do not match");
  } else {
    confirm_password.setCustomValidity('');
  }
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;