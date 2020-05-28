console.log("index.js connected");

window.onload = function() {
    this.document.getElementById('customerLoginButton').addEventListener('click', goToCustomerLogin);
    this.document.getElementById('customerRegistrationButton').addEventListener('click', goToCustomerRegistration);
    this.document.getElementById('employeeLoginButton').addEventListener('click', goToEmployeeLogin);
}

function goToCustomerLogin() {
  fetch('http://localhost:9090/BhankWebApp/CustomerLogin', { method: 'GET' })
  .then(response => {
     return response.text();
  }).then(response => {
    window.location.href=response;
  }).catch(error => {
    console.error('Error:', error);
  })
}

function goToCustomerRegistration() {
  fetch('http://localhost:9090/BhankWebApp/CustomerRegistration', { method: 'GET' })
  .then(response => {
     return response.text();
  }).then(response => {
    window.location.href=response;
  }).catch(error => {
    console.error('Error:', error);
  })
}

function goToEmployeeLogin() {
  fetch('http://localhost:9090/BhankWebApp/EmployeeLogin', { method: 'GET' })
  .then(response => {
     return response.text();
  }).then(response => {
    window.location.href=response;
  }).catch(error => {
    console.error('Error:', error);
  })
}