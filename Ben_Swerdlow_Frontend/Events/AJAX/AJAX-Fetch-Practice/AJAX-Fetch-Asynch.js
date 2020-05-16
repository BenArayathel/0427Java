window.onload = function() {
    this.document.getElementById("jphSubmit")
        .addEventListener("click", this.getPlaceHolder);
    this.document.getElementById("jphSubFetch")
        .addEventListener("click", this.fetchPlaceHolder);
    this.document.getElementById("jphSubAsync")
        .addEventListener("click", this.getPlaceHolderAsync);
    this.document.getElementById("jphSubPost")
        .addEventListener("click", this.postToPlaceHolder);
    // this.document.getElementById("jphSubmit").onkeypress = alert("onkeypress detected");
    // this.document.getElementById("jphSubmit").addEventListener("keypress", alert("keypress detected"));
    //this.fetchOnEnter);
}

// AJAX Syntax
function getPlaceHolder() {
    // Get the entry
    let jphId = document.getElementById("jphId").value;
    // Make the XMLHttpRequest object
    let xhttp = new XMLHttpRequest();
    // Tell xhttp what to do when it's state changes (i.e.)
    xhttp.onreadystatechange = function () {
        console.log(`ready state has changed to ${xhttp.readyState}`);
        // If a valid response is received
        if (xhttp.readyState==4 && xhttp.status==200){
            // Parse the response
            let jphContent = JSON.parse(xhttp.responseText);
            // Update the DOM based on it
            DOMManipulation(jphContent);
        }
    }
    xhttp.open("GET", "https://jsonplaceholder.typicode.com/posts/"+jphId);
    //"https://pokeapi.co/api/v2/pokemon/"+jphId);
    xhttp.send();
}

// // Use this function to use fetch when submission is entered (keyboard), not clicked
// function fetchOnEnter(event) {
//     console.log("Event registered"+event);
//     if (event.keyCode==13) {
//         console.log("Enter pressed");
//         event.preventDefault();
//         fetchPlaceHolder();
//     }
// }

// Fetch Syntax
function fetchPlaceHolder(){
    // Get the entry
    let jphId = document.getElementById("jphIdFetch").value;
    // open endpoint and send the request
    fetch("https://jsonplaceholder.typicode.com/posts/"+jphId)
        // Then we get the response
        .then(jphResponse =>
            {return jphResponse.json();
        // Then we do the response
        }).then(jphJSON => {
            console.log(jphJSON)
            DOMManipulation(jphJSON);
        // And we even catch the errors
        }).catch(jphError => {
            console.log(jphError);
        });
}

// Async/await Syntax
/* 
The keyword async before a function makes it always return a Promise object
If you don't specify one and return anything else, it'll wrap it in a Promise object
for you.
*/
async function getPlaceHolderAsync() {
    // Get the entry
    let jphId = document.getElementById("jphIdAsync").value;
    // Set explicitly tell Javascript to wait for this to happen
    // A chain of await replaces the chain of Promise().then() that we needed
    // for the normal fetch syntax
    // Regardless of how long the chain was, when you use an asyc function, the
    // promise returned allows you to just have 1 then method (and 1 catch method)
    // as opposed to a chain of thens
    awaitPlaceHolder(jphId)
        .then(jphJSON => {
            DOMManipulation(jphJSON);
        })
        .catch(jphError =>{
            console.error(jphError);
        });
    // Now we can proceed like normal
}

async function awaitPlaceHolder(jphId) {
    // It looks a little silly to have just one fetch here, but you could 
    // imagine how much easier this syntax is when you have lots of fetches chained
    // together.
    let jphResponse = await fetch("https://jsonplaceholder.typicode.com/posts/"+jphId);
    return jphResponse.json();
}

// POST
function postToPlaceHolder() {
    // Get the entry
    let jphPost = document.getElementById("jphPost").value;
    // Make the XMLHttpRequest object
    let xhttp = new XMLHttpRequest();
    // Tell xhttp what to do when it's state changes (i.e.)
    xhttp.onreadystatechange = function () {
        console.log(`ready state has changed to ${xhttp.readyState}`);
        console.log(xhttp.responseText);
        // If a valid response is received
        if (xhttp.readyState==4 && xhttp.status==200){
            // Parse the response
            console.log(xhttp.responseText);
            // let jphContent = JSON.parse(xhttp.responseText);
            // // Update the DOM based on it
            // DOMManipulation(jphContent);
        }
    }
    xhttp.open("POST", "https://jsonplaceholder.typicode.com/posts/", true);
    //"https://pokeapi.co/api/v2/pokemon/"+jphId);
    xhttp.setRequestHeader({
        "Content-type": "application/json; charset=UTF-8"
    });
    xhttp.send();
}

function postToPlaceHolderFetch() {
    let jphPost = document.getElementById("jphPost").value;
    console.log(jphPost);
    fetch('https://jsonplaceholder.typicode.com/posts', {
            method: 'POST',
            body: JSON.stringify(jphPost),
            headers: {"Content-type": "application/json; charset=UFT-8"}})
        .then(response => response.json())
        .then(json => DOMManipulation(json));
}

function DOMManipulation(contentJSON) {
    let jphTitle = document.getElementById("jphTitle")
    jphTitle.innerText = contentJSON.title;
    // jphTitle.innerText = JSONcontent.species.name;
    let jphBody = document.getElementById("jphBody");
    jphBody.innerText = contentJSON.body;
    // jphBody.innerText = JSONcontent.species.url;
}

function DOMManipulationPost(contentJSON) {
    let jphTitlePost = document.getElementById("jphTitlePost");
    jphTitlePost.innerText = contentJSON.title;
    let jphBodyPost = document.getElementById("jphBodyPost");
    jphBodyPost.innerText = contentJSON.body;
}