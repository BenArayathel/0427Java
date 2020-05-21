
window.onload = function () {
    console.log("Window has loaded");
    let submitButton = document.getElementById("rickMortySubmit");
    submitButton.addEventListener("click", queryAPI)

}

function queryAPI() {
    let charEntry = document.getElementById("characterName");
    let lifeStatus = document.getElementById("status");
    fetch("https://rickandmortyapi.com/api/character/?name=" + charEntry.value + "&status=" + lifeStatus.value).then(
        response => response.json()).then(
            data => {
                randomNumber = Math.floor((Math.random() * (data["results"].length - 1)) + 1);
                document.getElementById("charName").innerText = data["results"][randomNumber]["name"];
                document.getElementById("charImage").src = data["results"][randomNumber]["image"];
                
            });

    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function (rNum) {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let rm = JSON.parse(xhttp.responseText);
            
            let rNum = Math.floor((Math.random() * (rm["results"].length - 1)) + 1);
            console.log(rm["results"][rNum]["name"]);
            console.log(`Random Number from AJAX call- ${rNum}`);
            document.getElementById("charName2").innerText = rm["results"][rNum]["name"];
            document.getElementById("charImage2").src = rm["results"][rNum]["image"];
        }

    }

    xhttp.open("GET", ("https://rickandmortyapi.com/api/character/?name=" + charEntry.value + "&status=" + lifeStatus.value));
    xhttp.send();

    async function callAPI() {
        
        let response = await fetch("https://rickandmortyapi.com/api/character/?name=" + charEntry.value + "&status=" + lifeStatus.value);
        //let rN = Math.floor((Math.random() * (response["results"] - 1)) + 1);
        console.log(response);
        if (response["type"] == "cors") {
            document.getElementById("charName3").innerText = "CORS Problem";
            document.getElementById("charImage3").src = "images/gandalf404.jpg";
        }
        document.getElementById("charName3").innerText = response["results"][1]["name"];
        document.getElementById("charImage3").src = response["results"][1]["image"];

        return 0;

    }

    callAPI();


    
    
}

var form = new FormData();
form.append("image", { "value": "gisele_growl.jpg" });

fetch("https://ronreiter-meme-generator.p.rapidapi.com/images", {
    "method": "POST",
    "headers": {
        "x-rapidapi-host": "ronreiter-meme-generator.p.rapidapi.com",
        "x-rapidapi-key": "6132574a67msh6eac07a32ca57cfp1a87c8jsn9aed892bcc9b",
        "content-type": "multipart/form-data"
    }
})
    .then(response => {
        console.log(response);
    }).catch(error => {
        console.log(error);
    })
    

