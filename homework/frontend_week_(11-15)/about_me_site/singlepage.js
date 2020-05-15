let mainContent = document.querySelector("#index-main")
let hobbyContent = document.querySelector("#hobby-content")

let updateButton1 = document.getElementById("updatePage1")
let updateButton2 = document.getElementById("updatePage2")

hobbyContent.style = "display: none"

updateButton1.onclick = updatePage1
updateButton2.oninput = updatePage2

function updatePage1() {
    mainContent.remove()
    hobbyContent.style = "display: block"
}

function updatePage2() {
    document.body.appendChild(mainContent)
    hobbyContent.style = "display: none"
}