onload
document.getElementById("myLogin").style.display = "none";

// document.getElementById("login-form").addEventListener("click", function(event){
//   event.preventDefault()
// });


function loadLogin(){
  console.log("clicked loadLogin.")
  let x = document.getElementById("myLogin");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}

// function myFunction() {
//   var x = document.getElementById('myDIV');
//   if (x.style.visibility === 'hidden') {
//     x.style.visibility = 'visible';
//   } else {
//     x.style.visibility = 'hidden';
//   }
// }


