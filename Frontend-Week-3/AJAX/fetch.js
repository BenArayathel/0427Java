
window.onload = function(){
	document
		.getElementById('swSubmit')
		.addEventListener('click', getSW);
}

function getSW(){

    let swId = document.getElementById("swId").value;

    //ANOTHER WAY
    fetch("https://swapi.dev/api/people/" + swId)
    .then((daResponse) => {
        return daResponse.json();

    }).then(function(daResponse){

        console.log(daResponse);
        DOMManipulation(daResponse);
        return daResponse.mass;

    }).then( daResponse => {

        console.log(daResponse);
    }).catch(

        function(daResponse){
            console.log(daResponse);
            console.log("We've got an error!");
        }
    )
}


function DOMManipulation(ourJSON){
	document.getElementById('swName').innerText=ourJSON.name;
	document.getElementById('swBirthYear').innerText=ourJSON.birth_year;
	//to find films, for instance
}