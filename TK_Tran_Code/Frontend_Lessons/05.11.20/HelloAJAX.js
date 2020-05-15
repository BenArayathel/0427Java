console.log("Testing Console");

// AJAX Normal Method --------------------------------------------------------------------------------

window.onload = function () {
    // 1. Prepares event listener for submit button when window loads
    this.document.getElementById("swSubmit").addEventListener("click", getSW);
}

function getSW() {
    // Extracts value of user input
    let swID = document.getElementById("swID").value;

    // 2. Creates XMLHttpRequest object
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        console.log(`Ready state has changed: ${xhttp.readyState}`);

        // Only executes if response if readyState is ready (4), and HTTP status is successful (200)
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            console.log("HTTP Completed!");

            // 4 & 5 done server-side; response send back and gets parsed into an object
            let responseObject = JSON.parse(xhttp.responseText);

            // Invoking callback function, passing in response object as argument
            DOMManip(responseObject);
        }
    }

    // 3. Sends a request, passing in value of user input to server
    xhttp.open("GET", `https://swapi.dev/api/people/${swID}`, true); // true = async, false = sync
    xhttp.send();
}

// 7. Pick apart the response object and assigns it to an HTML element
// DOMManip considered a "callback function"; one passed as a param to another func, and called back after some task is completed
function DOMManip(JSON) {
    document.getElementById("swName").innerText = `Name: ${JSON.name}`;
    document.getElementById("swHeight").innerText = `Height: ${JSON.height}`;
    document.getElementById("swMass").innerText = `Mass: ${JSON.mass}`;
    document.getElementById("swHairColor").innerText = `Hair Color: ${JSON.hair_color}`;
    document.getElementById("swSkinColor").innerText = `Skin Color: ${JSON.skin_color}`;
    document.getElementById("swEyeColor").innerText = `Eye Color: ${JSON.eye_color}`;
    document.getElementById("swBirthYear").innerText = `Birth Year: ${JSON.birth_year}`;
}

/*
    AJAX: Asynchronous JavaScript and XML

        Synchronous:
            Means "not blocked".
            Things happen independently from each other.
            Can make multiple requests at once w/o needing to wait for other resuts to complete.
            Makes websites more responsive.

        Synchronous:
            Means "blocked".
            Stops all other "threads".

        What actually is AJAX?
            Technique for accessing web servers from a webpage asynchronously.

        Consists of:
            Browser built-in XMLHttpRequest Object (requests data from web server).
            JavaScript
            HTML DOM (to manipulate page)

    Steps:
        1. An event occurs in a webpage; prepare that event to send request
        2. An XMLHttpRequest obj is then created by JS.
        3. The XMLHttpRequest obj sends a request to a web server.
        4. Server processes the request.
        5. Server sends a response back to the web page.
        6. Response is read by JS.
        7. We do stuff with it (a proper action is performed by JS, e.g. info displayed).

    The readystate property holds status of XMLHttpRequest.
        0 - request not initialized
        1 - server connection established
        2 - request received
        3 - processing request
        4 - request finished and response ready

    HTTP
        http methods: attached to http REQUESTS, similar to CRUD operations
            GET - retrieve a resource
            POST - create a resource (and other stuff)
            PUT - update a resource
            DELETE - delete a resource
            PATCH, OPTIONS, TRACE, CONNECT, and even more..
        http statuses: attached to http RESPONSES
            100s - informational
            200s - success
            300s - redirect
            400s - client side errors
            500s - server side errors
*/