let submit = document.getElementById("submit_login");

submit.addEventListener("click", test)
submit.addEventListener("keyup", test)

function test() {
    let username = document.getElementById("username").value;
    // let password = document.getElementById("password").value;
    
    // console.log(username);
    // console.log(password);    

    fetch('https://jsonplaceholder.typicode.com/posts', {
        method: 'POST',
        body: JSON.stringify({
            title: "Feedback",
            body: username,
            userId: 1
        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
        })
        .then(response => response.json())
        .then(json => console.log(json))
        .then(json => repeatFeedback(json))

        function repeatFeedback(json) {
            console.log(json)
        }
    

    // fetch('https://jsonplaceholder.typicode.com/posts/1', {
    //     method: 'POST',
    //     body: JSON.stringify({
    //         username: username,
    //         password: password,
    //     }),
    //     headers: {
    //         "Content-type": "application/json; charset=UTF-8"
    //     }
    //     })
    //     .then(response => response.json())
    //     // .then(json => console.log(json))
    //     .then(json => repeatFeedback(json))

    //     function repeatFeedback(json) {
    //         console.log(json.body)
    //         // rtfeed.innerHTML = json.body
    //     }
}