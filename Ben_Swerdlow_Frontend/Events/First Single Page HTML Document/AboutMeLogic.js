function showAboutMeFromHobbies(){
    // Change title header
    let mainTitle = document.getElementById("mainTitle");
    mainTitle.innerText = "About Me";
    // Remove hobbyList
    document.getElementById("hobbyList").remove();
    // Change main paragraph
    let mainParagraph = document.getElementById("mainParagraph")
    mainParagraph.innerText = "Hi everyone! My name is Ben and I live in Ann Arbor, MI.";
    
    // mainDiv.innerHTML = `
    // <div class=main id=mainDiv>
    //     <p class=main id=mainParagraph>Hi everyone! My name is Ben and I live in Ann Arbor, MI.</p>
    // </div class=main id=mainDiv>
    // `
    
    hobbyButton.innerText = "My Hobbies";
    hobbyButton.removeEventListener("click", showAboutMeFromHobbies);
    hobbyButton.addEventListener("click", showHobbies);
}

function showAboutMeFromInterestingTopic(){
    // Change title header
    let mainTitle = document.getElementById("mainTitle");
    mainTitle.innerText = "About Me";
    // Change main div
    let mainParagraph = document.getElementById("mainParagraph");
    mainParagraph.innerText = "Hi everyone! My name is Ben and I live in Ann Arbor, MI.";
    // mainDiv.innerHTML = `
    // <div class=main id=mainDiv>
    //     <p class=main id=mainParagraph>Hi everyone! My name is Ben and I live in Ann Arbor, MI.</p>
    // </div class=main id=mainDiv>
    // `
    
    interestingTopicButton.innerText = "My Interesting Topic";
    interestingTopicButton.removeEventListener("click", showAboutMeFromInterestingTopic);
    interestingTopicButton.addEventListener("click", showInterestingTopic);
}

function showHobbies(){
    let intTable = document.getElementById("intTable");
    if (intTable){
        intTable.remove();
    }
    // Change title header
    let mainTitle = document.getElementById("mainTitle");
    mainTitle.innerText = "My Hobbies";
    // Change main div
    let mainDiv = document.getElementById("mainDiv");
    let mainParagraph = document.getElementById("mainParagraph");
    mainParagraph.innerText = "These are my hobbies:";
    let hobbyList = document.createElement("ul");
    hobbyList.setAttribute("id", "hobbyList");
    hobbyList.innerHTML = `
        <li>Watching YouTube, Netflix, etc.</li>
            <img src="https://havecamerawilltravel.com/photographer/files/2020/01/youtube-logo-new.jpg" class=inListImg>
            <img src="https://assets.brand.microsites.netflix.io/assets/df87ee0c-c4ea-11e7-8d40-066b49664af6_cm_800w.png?v=12" class=inListImg>
        <li>Playing games (video, board, card, etc.)</li>
            <img src="https://www.nintendo.com/content/dam/noa/en_US/games/switch/s/super-smash-bros-ultimate-switch/super-smash-bros-ultimate-switch-hero.jpg" class=inListImgLarge>
            <img src="https://images-na.ssl-images-amazon.com/images/I/81OjLGNO5VL._AC_SL1500_.jpg" class=inListImgLarge>
            <img src="https://upload.wikimedia.org/wikipedia/en/thumb/9/92/Bohnanza_game_box_art.jpeg/220px-Bohnanza_game_box_art.jpeg" class=inListImg>
        <li>Learning new stuff</li>
            <img src="https://techcrunch.com/wp-content/uploads/2016/11/sciencebook.jpg?w=730&crop=1" class="inListImg">
            <img src="https://upload.wikimedia.org/wikipedia/commons/6/65/HackerRank_logo.png" class="inListImg">
            <img src="https://static2.clutch.co/s3fs-public/logos/eli5_logo.jpg?r38KkugGYFYLCWZjgqewDVv3WAvpDdtP" class="inListImg">
        <li>Hanging out with friends and family</li>
        <li>Dividing by zero (loljk)</li>
    `;
    mainDiv.appendChild(hobbyList);

    hobbyButton.innerText = "About Me";
    hobbyButton.removeEventListener("click", showHobbies)
    hobbyButton.addEventListener("click", showAboutMeFromHobbies);

    interestingTopicButton.innerText = "My Interesting Topic";
    interestingTopicButton.removeEventListener("click", showAboutMeFromInterestingTopic);
    interestingTopicButton.addEventListener("click", showInterestingTopic);
}

function showInterestingTopic(){
    let hobbyList = document.getElementById("hobbyList");
    if (hobbyList) {
        hobbyList.remove();
    }
    // Change title header
    let mainTitle = document.getElementById("mainTitle");
    mainTitle.innerText = "Interesting Topic";
    // Change main div
    let mainDiv = document.getElementById("mainDiv");
    let mainParagraph = document.getElementById("mainParagraph");
    mainParagraph.innerText = "Did you know that showing things to people in tables "
    +"is fun? Especially if you are required to use a table "
    +"for your assigned project and don't want to have to "
    +"think of an interesting topic that requries a table! "
    +"Let me show you:";
    let table = document.createElement("table");
    table.setAttribute("id", "intTable");
    table.setAttribute("border", 2);
    table.innerHTML = `
        <tr>
            <th>Column Header 1</th>
            <th>Column Header 2</th>
        </tr>
        <tr>
            <td id=rad>this is rad!</td>
            <td id=surprised>you're telling me!</td>
        </tr>
    `;
    mainDiv.appendChild(table);
    
    interestingTopicButton.innerText = "About Me";
    interestingTopicButton.removeEventListener("click", showInterestingTopic);
    interestingTopicButton.addEventListener("click", showAboutMeFromInterestingTopic);

    hobbyButton.innerText = "My Hobbies";
    hobbyButton.removeEventListener("click", showAboutMeFromHobbies);
    hobbyButton.addEventListener("click", showHobbies);
}

console.log(document);
let hobbyButton = document.getElementById("myHobbiesButton");
hobbyButton.addEventListener("click", showHobbies);
let interestingTopicButton = document.getElementById("myIntTopic");
interestingTopicButton.addEventListener("click", showInterestingTopic);