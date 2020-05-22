console.log("login.js connected");

window.onload = function() {
    this.document.getElementById('loginFormSubmitButton').addEventListener('click', login);
}

function login() {
    let username = document.getElementById('loginFormUsernameInput').value;
    let password = document.getElementById('loginFormPasswordInput').value;

    console.log(username);
    console.log(password);

    const data = {"username": username, "password": password};

    fetch('http://localhost:8081/BhankWebApp/Login', {
  method: 'POST', // or 'PUT'
  headers: {
    'Content-Type': 'application/json',
  },
  body: JSON.stringify(data),
})
.then(response => {
    console.log(response);
    response.json()})
.then(data => {
  console.log('Success:', data);
})
.catch((error) => {
  console.error('Error:', error);
});

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