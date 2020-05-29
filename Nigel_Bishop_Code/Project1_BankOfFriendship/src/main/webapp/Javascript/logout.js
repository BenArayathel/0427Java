//let planet = document.getElementById('getplanet');
//planet.addEventListener('click',getPlanet)
window.addEventListener("load",bankLogout)

//let output = document.getElementById('output');


function bankLogout(){
    fetch('http://localhost:9090/Project1_BankOfFriendship/BankLogOut')
    .then((res) => res.text())
    .then((data) => {
    	 document.getElementById('output').innerHTML = data;
    })
    .catch((err) => console.log(err)); 
}