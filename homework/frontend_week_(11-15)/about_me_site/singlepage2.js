let dude = document.getElementById("transformguy");

let animate = () => {
    let x = Math.random(100, 500) * 1000;
    dude.style.width = x + "px"
}

dude.addEventListener("drag", animate)

// lllllllllllllllllllllllllllllllllllllllllllll

let video1 = document.getElementById("videocontent1");
let video2 = document.getElementById("videocontent2");

video1.style = "display: block"
video2.style = "display: none"

let videochange = () => {
    video1.style = "display: none"
    video2.style = "display: block"
}

video.addEventListener("mouseleave", videochange)