// https://api.giphy.com/v1/gifs/trending?api_key=GGR8ONXTcf3yjY4GJiQtNO0RyLKlAIY2&limit=1&rating=G

let submit2 = document.getElementById('submit2')

submit2.addEventListener('click', apiCall2);
submit2.addEventListener('keyup', apiCall2);



function apiCall2(){
    fetch("https://api.giphy.com/v1/gifs/trending?api_key=GGR8ONXTcf3yjY4GJiQtNO0RyLKlAIY2&limit=25&rating=G")
    .then((response) => {
        return response.json();
    }).then(function(response){
        updateDOM2(response);
        // console.log(response)
        // what did this line do?
        // return response.mass;
    }).catch(
        error => {
            alert(error)
        }
    )
}

function getRndInteger(min, max) {
    return Math.floor(Math.random() * (max - min + 1) ) + min;
}

function updateDOM2(response) {
    let random = getRndInteger(1, 24);
    let gifholder = document.getElementById("gif-holder")
    let result = response.data[random].embed_url
    let embed = document.getElementById("embed")
    embed.setAttribute("src", result)
    gifholder.appendChild(embed)
}