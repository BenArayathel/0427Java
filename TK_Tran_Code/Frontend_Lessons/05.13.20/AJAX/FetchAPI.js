// SHORTER WAY, USING FETCH API

window.onload = function () {
	this.document
		.getElementById("swSubmit")
		.addEventListener("click", getSW);
}

function getSW() {
	let swID = document.getElementById("swID").value;

	fetch("https://swapi.dev/api/people/" + swID) // send response to API, passing in an argument
		.then(function (daResponse) { // receives response and parse to JSON
			return daResponse.json();
		})
		.then(function (data) { // use response to manipulate
			DOMManipulation(data);
		})
		.catch(function (error) {
			console.log("ERROR");
		})
}

function DOMManipulation(ourJSON) {
	document.getElementById("swName").innerText = ourJSON.name; // using dot notation to access JSON
	document.getElementById("swBirthYear").innerText = ourJSON.birth_year;
}

/*
    TEMPLATE 1
    fetch('./api/some.json')
        .then(
            function(response) {
            if (response.status !== 200) {
                console.log('Looks like there was a problem. Status Code: ' +
                response.status);
                return;
            }

            // Examine the text in the response
            response.json().then(function(data) {
                console.log(data);
            });
        }
    )
    .catch(function(err) {
        console.log('Fetch Error :-S', err);
    });

    TEMPLATE 2
    function method_name() {
        fetch(api-url)
            .then(response => {
            return response.json()
        })
        .then(json => {
            empty_array_setting_to_json = json
            renderFunction( empty_array_setting_to_json)
        })
    }
*/
