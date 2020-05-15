// console.log("working")
// this is the old school, least abstracted method
// GGR8ONXTcf3yjY4GJiQtNO0RyLKlAIY2

document.getElementById("submit1").addEventListener("click", apiCall1);
document.getElementById("submit1").addEventListener("keyup", apiCall1);


function apiCall1() {
    let value1 = document.getElementById("value1").value
    // console.log(value1)
    let xhttp = new XMLHttpRequest()
    xhttp.onreadystatechange = function() {
        // console.log(xhttp.readyState)
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let response = JSON.parse(xhttp.responseText)
            updateDOM(response)
            // console.log(response)
        }
    }

    xhttp.open("GET", "https://api.giphy.com/v1/gifs/search?api_key=GGR8ONXTcf3yjY4GJiQtNO0RyLKlAIY2&q=" + value1 + "&limit=1&offset=0&rating=G&lang=en");
    xhttp.send()
}

function updateDOM(response) {
    let gifholder = document.getElementById("gif-holder")
    let result = response.data[0].embed_url
    let embed = document.getElementById("embed")
    embed.setAttribute("src", result)
    gifholder.appendChild(embed)
}