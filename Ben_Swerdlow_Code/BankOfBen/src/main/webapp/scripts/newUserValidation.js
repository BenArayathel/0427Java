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
    let firstName = document.getElementById("firstName").value;
    let middleName = document.getElementById("middleName").value;
    let lastname = document.getElementById("lastName").value;
    let momsMaidName = document.getElementById("momsMaidName").value;
    let dob = document.getElementById("dob").value;
    let ssn = document.getElementById("ssn").value;
    let phoneNum = document.getElementById("phoneNum").value;
    // password and confirm password are made globally and are checked in
    // validatePassword()
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
        let email = this.document.getElementById('userEmail');
        email.innerText = userDetails.email;
        let username = this.document.getElementById('userUsername');
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
    console.log(`${yyyy}-${mm}-${dd}`);
    dob.max = `${yyyy}-${mm}-${dd}`;
}

// let password = document.getElementById("inputPassword");
// let confirm_password = document.getElementById("inputPasswordConfirm");

// function validatePassword(){
//   if(password.value != confirm_password.value) {
//     confirm_password.setCustomValidity("Password and confirm password fields do not match");
//   } else {
//     confirm_password.setCustomValidity('');
//   }
// }

// password.onchange = validatePassword;
// confirm_password.onkeyup = validatePassword;