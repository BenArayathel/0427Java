/**
 * 
 */

onload
document.getElementById("myLogin").style.display = "none";


function loadLogin(){
  console.log("clicked loadLogin.")
  let x = document.getElementById("myLogin");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}