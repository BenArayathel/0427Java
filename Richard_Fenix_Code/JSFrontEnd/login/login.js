$(function() {
  $('#login-form').validate({
    rules: {
      userName: {
        required: true
      }
    }
  });
})

function postLoginInfo(){

  let loginName = document.getElementById("usr").value;
  let password = document.getElementById("pwd").value;



  //XMLHttpRequest.OPENED("POST, "" true)
  console.log(loginName);
  console.log(password);

  let reg = new ServiceWorkerRegistration(null, null, loginName, password);
  getLogin()
  alert("You clicked me!")


  // GET method implementation that should return Customer object.
  fetch("https://swapi.dev/api/people/" + swId)
    .then(response => response.json())
    .then(data => {
        console.log(data);
        DOMManipulation(data);
        return data.gender;
    }).then( daResponse => {

        console.log("data.mass output : " + daResponse);
    }).catch( (error) => {
            console.log("Error: " + ßerror);
            console.log("We've got an error!");
        }
    )
}

getLogin('https://swapi.dev/api/people/1')
  .then(data => {
    console.log(data); // JSON data parsed by `response.json()` call
  });
