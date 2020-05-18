window.onload = function() {
    this.document.getElementById("jphSubmit")
        .addEventListener("click", this.getPlaceHolder);
    this.document.getElementById("jphSubFetch")
        .addEventListener("click", this.fetchPlaceHolder);
    this.document.getElementById("jphSubAsync")
        .addEventListener("click", this.getPlaceHolderAsync);
    this.document.getElementById("jphSubPost")
        .addEventListener("click", this.postToPlaceHolder);
    this.document.getElementById("jphSubPostF")
        .addEventListener("click", this.postToPlaceHolderFetch);
    this.document.getElementById("jphSubPostAW")
        .addEventListener("click", this.getPokeInfo);
    this.document.getElementById("getPokeSprite")
        .addEventListener("click", this.getPokeInfo);
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

    xhttp.send();
}

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

function DOMManipulation(contentJSON) {
    let jphTitle = document.getElementById("jphTitle")
    jphTitle.innerText = contentJSON.title;
    let jphBody = document.getElementById("jphBody");
    jphBody.innerText = contentJSON.body;
}

// POST
// AJAX Syntax
function postToPlaceHolder() {
    // Get the entry
    let jphPT = document.getElementById("jphPT").value;
    let jphPB = document.getElementById("jphPB").value;
    // Make the XMLHttpRequest object
    let xhttp = new XMLHttpRequest();
    // Tell xhttp what to do when it's state changes (i.e.)
    xhttp.onreadystatechange = function () {
        console.log(`ready state has changed to ${xhttp.readyState}`);
        console.log(xhttp.status);
        // console.log(xhttp.responseText);
        // If a valid response is received
        // The confirmation that a request has been created
        // is xhttp code 201!!!!
        if (xhttp.readyState==4 && xhttp.status==201){
            // Parse the response
            let jphContent = JSON.parse(xhttp.responseText);
            // console.log(jphContent);
            // // Update the DOM based on it
            DOMManipulationPost(jphContent);
        }
    }
    xhttp.open("POST", "https://jsonplaceholder.typicode.com/posts/",
        true);
        // The last argument tells xhttp to do this asynchronously
        // (default, so it doesn't have to be givien)
    xhttp.setRequestHeader(
        "Content-type", "application/json; charset=UTF-8"
    );
    xhttp.send(JSON.stringify(
        {title: jphPT,
        body: jphPB,
        userId: 1}
        )
    );
}

// Fetch Syntax
function postToPlaceHolderFetch() {
    let jphPTFetch = document.getElementById("jphPTFetch").value;
    let jphPBFetch = document.getElementById("jphPBFetch").value;
    fetch('https://jsonplaceholder.typicode.com/posts', {
            method: 'POST',
            body: JSON.stringify({
                "title": jphPTFetch,
                "body": jphPBFetch
            }),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        })
        .then(response => response.json())
        .then(json => DOMManipulationPost(json))
        .catch(error => console.error(error));
}

function postToPlaceHolderAW() {
    let jphPTAW = document.getElementById("jphPTAW").value;
    let jphPBAW = document.getElementById("jphPBAW").value;
    let contentJSON = {title: jphPTAW, body: jphPBAW};
    awaitPostToPlaceHolder(contentJSON)
        .then(json => DOMManipulationPost(json));
}

async function awaitPostToPlaceHolder(contentJSON) {
    const response = await fetch('https://jsonplaceholder.typicode.com/posts', {
        method: 'POST',
        body: JSON.stringify(contentJSON),
        headers: {"Content-type": "application/json; charset=UTF-8"}
    });
    return response.json();
}

function DOMManipulationPost(contentJSON) {
    let jphPostTitle = document.getElementById("jphPostTitle");
    jphPostTitle.innerText = contentJSON.title;
    let jphPostBody = document.getElementById("jphPostBody");
    jphPostBody.innerText = contentJSON.body;
}

function getPokeInfo() {
    let pokeNum = document.getElementById("pokeNum").value;
    console.log(pokeNum);
    fetch("https://pokeapi.co/api/v2/pokemon/"+pokeNum)
        .then(pokeResponse => {
            return pokeResponse.json()
        }).then(pokeInfoJSON => {
            showPokeSprites(pokeInfoJSON)
        }).catch(pokeError => {
            console.error(pokeError);
        });
}

function showPokeSprites(pokeInfoJSON) {
    let pokeId = pokeInfoJSON.id;
    let spriteArea = document.getElementById("spriteArea");
    let name = document.getElementById("name");
    let types = document.getElementById("types");
    const pokeX = Math.round(1024/25);
    const pokeY = Math.round(811/20);

    if (pokeId <= 493) {
        spriteArea.style.width = pokeX+"px";
        spriteArea.style.height = pokeY+"px";
        let pokeImageX = ((pokeId-1) % 25)*pokeX;
        let pokeImageY = Math.trunc((pokeId-1)/25)*pokeY;
        spriteArea.style.backgroundPositionX = -pokeImageX+"px "
        spriteArea.style.backgroundPositionY = -pokeImageY+"px";
    } else {
        spriteArea.style.width = 0;
        spriteArea.style.height = 0;
    }

    // x y
    let pokeName = pokeInfoJSON.name[0].toUpperCase()+pokeInfoJSON.name.slice(1);
    name.innerText = `#${pokeInfoJSON.id}: ${pokeName}`;

    let pokeTypes = pokeInfoJSON.types[0].type.name[0].toUpperCase()
        +pokeInfoJSON.types[0].type.name.slice(1);
    if (pokeInfoJSON.types.length > 1){
        pokeTypes += ", "+pokeInfoJSON.types[1].type.name[0].toUpperCase()
        +pokeInfoJSON.types[1].type.name.slice(1);
    }
    types.innerText = pokeTypes;
}