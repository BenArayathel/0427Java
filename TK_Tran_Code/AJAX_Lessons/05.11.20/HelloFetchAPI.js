console.log("Testing Console");

// Fetch API Method --------------------------------------------------------------------------------

window.onload = function () {
	this.document.getElementById("swSubmit").addEventListener("click", getSW);
}

function getSW() {
	let swID = document.getElementById("swID").value;

	// Verbose
	fetch(`https://swapi.dev/api/people/${swID}`) // fetch() takes one argument; the resource path
		.then(function (response) { // .then() attaches callback(s) for the resolution and/or rejection of Promises
			return response.json(); // .json() parses JSON response and promises an object (returns an object)
		})
		.then(function (object) { // insert object into callback function that manipulates the DOM
			DOMManip(object);
		})
		.catch(function (errorResponse) { // .catch() attaches a callback for only the rejection of Promises
			console.log(`Error..Status Code: ${errorResponse.status}`);
		})

	// Succinct: uses arrow functions (=>)
	fetch(`https://swapi.dev/api/people/ ${swID}`)
		.then(response => response.json())
		.then(object => DOMManip(object))
		.catch(error => console.log(`Error..Status Code: ${error.status}`));
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
    Fetch API: JS interface for accessing and manipulating parts of the HTTP pipeline, such as requests and responses.
        Provides global fetch() that provides an easy, logical way to fetch resources asynchronously across the network.
        Supercedes XMLHttpRequest method.

    TEMPLATE 1
        fetch('./api/some.json')
            .then(function(response) {
                if (response.status !== 200) {
                    console.log('Error..Status Code: ' +
                    response.status);
                    return;
                }

                response.json()
                    .then(function(data) {
                    console.log(data);
                });
            }
        )
        .catch(function(err) {
            console.log('Fetch Error :-S', err);
        });

    TEMPLATE 2
        fetch('./api/some.json')
            .then(response => {
                return response.json()
            })
        .then(json => {
            empty_array_setting_to_json = json
            renderFunction( empty_array_setting_to_json)
        })
*/