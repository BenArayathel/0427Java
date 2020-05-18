// LONG WAY
/*
    Steps:
            1. An event occurs in a webpage; prepare that event to send
            2. An XMLHttpRequest obj is then created by JS.
            3. The XMLHttpRequest obj sends a request to a web server.
            4. Server processes the request.
            5. Server sends a response back to the web page.
            6. Response is read by JS.
            7. We do stuff with it (a proper action is performed by JS, e.g. info displayed).
*/

window.onload = function () {
    // Step 1, prepare the event
    this.document.getElementById("swSubmit").addEventListener("click", getSW);
}

function getSW() {
    let swID = document.getElementById("swID").value;

    // Step 2, create XMLHttpRequest object; this object allows us to make requests to get data
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () { // property onreadystatechange will contain the event handler to be called when readystate event is fired.
        console.log(`Ready state has changed: ${xhttp.readyState}`);
        /*
            The readystate property holds status of XMLHttpRequest.
            0 - request not initialized
            1 - server connection established
            2 - request received
            3 - processing request
            4 - request finished and response ready
        */

        // Steps 4 and 5 done on server
        if (xhttp.readyState == 4 && xhttp.status == 200) { // wait for readyState response (4), and wait for a status response (200)
            console.log("Http DONE");

            // Step 6, receive and parse the response
            // JSON.parse turns string into JSON object
            // JSON.stringify turns JS object into JSON
            let sw = JSON.parse(xhttp.responseText);

            // Step 7, DOM manipulation
            DOMManipulation(sw);
        }
    }

    // Step 3, send the request
    xhttp.open("GET", "https://swapi.dev/api/people/" + swID, true);
    xhttp.send();
}

function DOMManipulation(ourJSON) {
    document.getElementById("swName").innerText = ourJSON.name; // using dot notation to access JSON
    document.getElementById("swBirthYear").innerText = ourJSON.birth_year;
}
