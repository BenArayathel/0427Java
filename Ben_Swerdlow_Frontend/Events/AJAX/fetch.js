window.onload = function(){
    document
        .getElementById("swSubmit")
        .addEventListener("click", getSW);
}

function getSW(){
    // another way
    let swId = document.getElementById("swId").value;
    // open endpoint and send the request
    fetch("https://swapi.dev/api/people/"+swId)
        // Then we get the response
        .then(function(daResponse){
            console.log(daResponse);
            return daResponse.json();
        // Then we do the response
        }).then(function(daResponse){
            console.log(daResponse);
            DOMManipulation(daResponse);
            return daResponse.mass;
        }).catch(function(error){
            console.log("This is an error");
            console.log(error);
        });
}

function getSW2(){
    // another way
    let swId = document.getElementById("swId").value;
    // open endpoint and send the request
    fetch("https://swapi.dev/api/people/"+swId)
        // Then we get the response
        .then(daResponse =>
            {return daResponse.json();
        // Then we do the response
        }).then(daResponse => {
            DOMManipulation(daResponse);
        }).catch(errorResponse => {
            console.log(errorResponse);
        });
}

function DOMManipulation(ourJSON){
    document.getElementById("swName").innerText = ourJSON.name;
    document.getElementById("swBirthYear").innerText = ourJSON.birth_year;
}

/* 
Go over promises, callbacks, and observables
HTTP status codes
    403, 527, etc.
jsonplaceholder.typicode.com - go here to play with api stuff
github.com/public-apis/public-apis is good as well
play with APIs and have at least GET and POST (you can post 
    to the placeholder api, can't really do it for realones 
    you don't actually own)
ajaxp
consumption of an AJAX api
and sprites?
*/

/*
Ben's official HW list from Slack
At least one call based on 
	AJAX
	fetch API
    Async/wait

Use at least 2 HTTP methods
Utilize an image/sprite. 
DOM manipulation. 
callback vs observables vs promises
    See google doc https://docs.google.com/document/d/1Z4ZPTZXG7OVWw-NR3rULqDFnBjGTxQm8x75tt3-Vujg/edit
What is JAXP?
Consumption of a REST API 
*/