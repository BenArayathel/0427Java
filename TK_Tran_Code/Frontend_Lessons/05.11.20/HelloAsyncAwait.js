console.log("Testing Console");

/*
    Arrow Functions:
    These two are the same:
        let func = (arg1, arg2, ...argN) => expression

        let func = function (arg1, arg2, ...argN) {
            return expression;
        };
*/

// let func = (a, b) => a + b;
// console.log(func(3, 2)); // 5

// let func2 = (x, y) => x * y - 10;
// console.log(func2(4, 20)); // 70s

// let func3 = () => alert("Hello, Arrow Functions!");
// func3();

// Fetch w/ Async/Await Method --------------------------------------------------------------------------------

window.onload = function () {
	this.document.getElementById("swSubmit").addEventListener("click", getImg);
}

async function getImg() {
	// Even MORE Succinct: uses async/await
	let response = await fetch(`https://dog.ceo/api/breeds/image/random`);
	let object = await response.json(); // .json() parses JSON response and promises an object (returns an object)
	DOMManip(object); // insert object into callback function that manipulates the DOM
	console.log(object); // prints object to console so we can see content
}

function DOMManip(JSON) {
	document.getElementById("img").src = `${JSON.message}`;
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
