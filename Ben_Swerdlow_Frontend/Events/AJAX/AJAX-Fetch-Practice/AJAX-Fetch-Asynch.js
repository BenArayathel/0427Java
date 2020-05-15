window.onload = function(){
    this.document.getElementById("jphSubmit").addEventListener("keyup", getSW);
}

function getPlaceHolder(){
    // Get the entry
    let id = document.getElementById("jphId");
    // Make the XMLHttpRequest object
    let xhttp = new XMLHttpRequest();
    // Tell xhttp what to do when it's state changes (i.e.)
    xhttp.onreadystatechange = function {
        // If a valid response is received
        if (xhttp.readyState==4 && xhttp.status==200){
            // Parse the response
            let sw = JSON.parse(xhttp.responseText);
            // Update the DOM based on it
            DOMManipulation(sw);
        }
    }
    xhttp.open("GET", "https://jsonplaceholder.typicode.com/posts/"+swId);
    xhttp.send();
}

function DOMManipulation(JSONcontent){
    let jphTitle = document.getElementById("jphTitle")
    jphTitle.innerText = JSONcontent.title;
    let jphBody = document.getElementById("jphBody");
    jphBody.innerText = JSONcontent.body;
}