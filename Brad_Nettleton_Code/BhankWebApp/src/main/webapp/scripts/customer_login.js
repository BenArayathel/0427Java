console.log("customer_login.js connected");

var validationServiceScript = document.createElement('script');
validationServiceScript.src = './scripts/validation_service.js';
document.getElementsByTagName("head")[0].appendChild(validationServiceScript);

window.onload = function() {
    this.document.getElementById('customerLoginButton').addEventListener('click', login);
}

const usernameInput = document.getElementById('customerLoginUsernameInput');
const passwordInput = document.getElementById('customerLoginPasswordInput');

usernameInput.addEventListener('input', function(event) {
  if(!isValidUsername(usernameInput.value)) {
    usernameInput.setCustomValidity("Invalid Username (must contain only 1-20 alpha and space characters");
  } else {
    usernameInput.setCustomValidity("");
  }
});

passwordInput.addEventListener('input', function(event) {
  if(!isValidPassword(passwordInput.value)) {
    passwordInput.setCustomValidity("Invalid password (must contain only 4-20 alphanumeric characters");
  } else {
    passwordInput.setCustomValidity("");
  }
});

function login() {
console.log(usernameInput.value+' '+passwordInput.value);
  if(isValidUsername(usernameInput.value) && isValidPassword(passwordInput.value)) {
    const data = {"username": usernameInput.value, "password": passwordInput.value};
    fetch('http://localhost:9090/BhankWebApp/CustomerLogin', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
  },
  body: JSON.stringify(data),
})
.then(response => {
  return response.text();
})
.then(response => {
  console.log(document.cookie);
  document.cookie.split(';').forEach((item) => {
    if (item.trim().startsWith('customerId=')) {
      let customerId = item.split('=')[1];
      localStorage.setItem('customerId', customerId);
    }
  })
  localStorage.setItem("username", usernameInput.value);
  // console.log(localStorage.getItem("username"));
  window.location.href = response;
})
.catch((error) => {
  console.error('Error:', error);
});
  } else {
    console.log("invalid username or password");
  }
  
  



    // postData('http://localhost:8081/BhankWebApp/Login', {username: username, password: password})
    // .then(respons => {
    //     console.log(respons);
    // })
}

// async function postData(url = '', data = {}) {
//     // Default options are marked with *
//     const response = await fetch(url, {
//       method: 'POST', // *GET, POST, PUT, DELETE, etc.
//     //   mode: 'cors', // no-cors, *cors, same-origin
//     //   cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
//     //   credentials: 'same-origin', // include, *same-origin, omit
//       headers: {
//         'Content-Type': 'application/json'
//         // 'Content-Type': 'application/x-www-form-urlencoded',
//       },
//     //   redirect: 'follow', // manual, *follow, error
//     //   referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
//       body: JSON.stringify(data) // body data type must match "Content-Type" header
//     });
//     console.log(data);
//     return response.json(); // parses JSON response into native JavaScript objects
//   };