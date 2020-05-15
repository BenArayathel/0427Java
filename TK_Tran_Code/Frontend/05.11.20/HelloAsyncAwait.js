console.log("Testing Console");

// Async/Await Method [INCOMPLETE] --------------------------------------------------------------------------------

window.onload = function () {
    this.document.getElementById("swSubmit").addEventListener("click", getSW);
}

async function getSW() {
    let swID = document.getElementById("swID").value;

    // Succinct: uses arrow functions (=>)
    await fetch(`https://swapi.dev/api/people/${swID}`)
        .then(response => response.json())
        .then(response => DOMManip(response))
        .catch(response => console.log(`Error..Status Code: ${response.status}`));
}

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
    Async and Await enable asynchronouse, promise-based behavior to be written cleanly.
        Eliminates the need to explicitly configure promise chains.

    Async:
        async keyword placed before a function definition.
        Means a function always returns a promise; other values are wrapped in a resolved promise automatically.
        Example: this func returns a resolved promise w/ result of 1
            async function f() {
                return 1;
            }

            f().then(alert); // 1
        Example: we could also explicitly return a promise..
            async function f() {
                return Promise.resolve(1);
            }

            f().then(alert); // 1

    Await:
        await keyword can only be placed inside async funcs; cannot be used in regular funcs.
        Example: works only inside async funcs
            let value = await promise;
        Makes JS wait until that promise settles and returns its results.


*/