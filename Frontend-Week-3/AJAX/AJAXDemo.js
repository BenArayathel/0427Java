

/*
AJAX - Asyncronous Javascript and XML

Asynchronous? 
    We are able to make multiple requests at one tmie without waiting for the other requests to 
    complete. This makes the web page more responsive. 

Synchronous means "blocked"
    -stops all other "threads"
Asynchornous means "not-blocked"
    -deosn't stop all other "threads"


What actually is AJAX?
A technique for accesing web servers from a web page asynchronously. 

Conosists of:
    - Broswer built-in XMLHttpRequest Object (request data from the web server)
    - JavaScript
    - HTML DOM

How do we do it?
1) An event occurs in a web page 
2) An XMLHttpRequest object is the created by JS
3) The XMLHttpRequest object sends a request to a web server
4) Server processes the request 
5) Server sends a response back to the web page 
6) Response is read by JavaScript 
7) We do stuff with it (A proper action is performed by JS, e.g. info displayed)
*/

window.onload = function(){
    //Step 1 - prepare the event, in this "click"
    document.getElementById("swSubmit").addEventListener("mouseenter",getSW);
}

function getSW(){
    // console.log("button clicked");

    let swId = document.getElementById("swId").value;
    // console.log(swId);

    //Step 2 - create the XMLHttpRequest object
    //this object allows us to make request and get back data. 
    // (data retriever objects)

    let xhttp = new XMLHttpRequest();

    // console.log(xhttp);

    xhttp.onreadystatechange = function(){

        console.log(`ready state has changed: ${xhttp.readyState}`);

        /*
        The readystate property hold the status of the XMLHttpRequest
        0 - request not initialized 
        1 - server connection established 
        2 - request received 
        3 - processing request 
        4 - request is finished and response is ready
        */

        if(xhttp.readyState == 4 && xhttp.status == 200){
            //This block will only trigger when shttp object is done AND successful 
            console.log("xhttp done!!!")

            //Step 6
            let sw = JSON.parse(xhttp.responseText);

            //EXTRA: JSON.stringify will turn a JS object into JSON

            console.log(sw);

            //step 7
            DOMManipulation(sw);

        }
    }

    //Step 3: send the request

    xhttp.open("GET","https://swapi.dev/api/people/" + swId);

    xhttp.send();
}

function DOMManipulation(ourJSON){
    document.getElementById("swName").innerText = ourJSON.name;
    document.getElementById("swBirthYear").innerText = ourJSON.birth_year;
}


/*
What is HTTP?
    http methods: <-- attached to http requests

    GET vs POST? 

    GET - R: retrieving a resource
    POST - C: create a new resource (and other stuff)
    PUT - U: update a resource
    DELETE - D: deletes a resource 

    PATCH
    OPTIONS
    TRACE
    CONNECT

    and there's even more.....

http statuses: <-- attached to http response
    100s: informational 
    200s: success
    300s: redirect
    400s: client side error
    500s: server side error
*/