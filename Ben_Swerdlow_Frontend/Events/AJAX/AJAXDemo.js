/*
AJAX - Asynchronous Javascript and XML

Asynchronous
    We are able to make multiple requests at one time without waiting
        for the other requests to complete
    The reqeusts are independent of each other.
    This makes the webpage a lot more responsive
    Synchronous means "blocked" in Java, it stops all other threads
    Asynchronous means doesn't stop all other threads

What actually is AJAX?
    AJAX is a technique for accessing we servers from a webpage 
        asynchronously
    Consists of:
        1) Browser built-in XMLHttpRequest - this requests data from the
            web server
        2) Javascript
        3) HTML DOM

How do we do it?
1) An event occurs in a web page
2) An XMLHttpRequest object is created by JS
3) The XMLHttpRequest object sends a request to a web server
4) Server processes the request
5) Server sends response back to the web page
6) Response is read by JavaScript
7) We do stuff with it (i.e. a proper action is performed by JS, 
    e.g. information is displayed)

We need an api to do it, we'll use the swapi.dev Star Wars API
The benefits of online API's is that we don't have to make a database
of information, someone else already did it for us and we can grab
it quickly!
*/

window.onload = function(){
    // this refers to itself (the window)
    // it *may* work without it, but this is more correct, I guess?
    this.document.getElementById("swSubmit").addEventListener("click", getSW);
}

function getSW(){
    // console.log("Button clicked");
    // this is step 1


    let swId = document.getElementById("swId").value;
    console.log(swId);

    // Step 2 - create and set up the XMLHttpRequest object
    // this object allows us to make requests and get back data
    // (data retriever objects)

    let xhttp = new XMLHttpRequest();

    console.log(xhttp);

    // the readystate property holds the status of the xmlhttprequest
    xhttp.onreadystatechange = function(){
        console.log(`ready state has changed to ${xhttp.readyState}`);
        /*
        the readystate property holds the status of the XMLHttpRequest
        0 - request not initialized
        1 - server connection established
        2 - request received
        3 - processing request
        4 - request is finished and response is ready
        */
       // need to check if the response is ready AND that the response is successful (200)
        if (xhttp.readyState==4 && xhttp.status==200){
            // this block will only execute if done and valid
            console.log("xhttp done");

            // Step 6
            let sw = JSON.parse(xhttp.responseText);

            // Extra: JSON.stringify will turn a JS object into JSON

            // console.log(sw);


            // Step 7 DOMManipulation
            DOMManipulation(sw);
        }
    }

    // Step 3 - send the request

    xhttp.open("GET", "https://swapi.dev/api/people/"+swId);
    xhttp.send();
}

function DOMManipulation(ourJSON){
    document.getElementById("swName").innerText = ourJSON.name;
    document.getElementById("swBirthYear").innerText = ourJSON.birth_year;
}

/*
What is HTTP?
    http methods: <--- attached to http requests

    4 most common (i.e. CRUD operations):
        GET - read/retrieve a resource
        POST - create-ish a new resource and other stuff
        PUT - updates a resource
        DELETE - deletes a resource

    Look up finer details of the difference between get and post
        get is limited in how much info you can get and send
        get is less secure because it'll show sensitive information
            in the URL
        

    Also, there's (you can play with this if you want):
        PATCH
        OPTIONS
        TRACE
        CONNECT

        and even more....
    
http statuses: <--- attached to http response
    100s: informational
    200s: success
    300s: redirect
    400s: client-side error
    500s: server side error
*/