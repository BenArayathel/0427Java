let submit3 = document.getElementById("submit3")

submit3.addEventListener("click", test)

function test() {
    let feedback = document.getElementById("feedbackValue").value
    console.log(feedback)
}

// fetch('https://jsonplaceholder.typicode.com/posts/1', {
//     method: 'PUT',
//     body: JSON.stringify({
//         id: 1,
//         title: 'foo',
//         body: 'bar',
//         userId: 1
//     }),
//     headers: {
//         "Content-type": "application/json; charset=UTF-8"
//     }
//     })
//     .then(response => response.json())
//     .then(json => console.log(json))

// // Output
// {
//     id: 1,
//     title: 'foo',
//     body: 'bar',
//     userId: 1
// }