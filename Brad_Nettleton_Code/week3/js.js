console.log("connected");
function func1() {
    console.log("func1");
let h1 = document.querySelector("h1");
h1.innerText = h1.innerText.toUpperCase();
};


let but = document.getElementById("TheButton");
but.addEventListener("click", func2);

let tdList = document.querySelectorAll("td");

console.log(tdList);

let list = document.getElementById("List");

function func2() {
    func1();
    for(let i=0; i<tdList.length; i++){
        let listItem = list.appendChild(document.createElement("li"))
        listItem.innerText = tdList[i].innerText;
    }
}

let h1 = document.querySelector("h1");
let h1List = h1.innerText.split(" ");

console.log(h1List);

function func3() {
    tableHeads = document.querySelectorAll("thead th");
    tableHeads = Array.from(tableHeads);
    tableHeadsCopy = [];
    for(let i=0; i<tableHeads.length; i++) {
        tableHeadsCopy[i] = tableHeads[i]; 
    };
    // for(let i=0; i<tableHeads.length; i++) {
    //     console.log(tableHeads[i].innerText);
    // };
    // for(let i=0; i<tableHeads.length; i++) {
    //     console.log(tableHeadsCopy[i].innerText);
    // };
    console.log(tableHeads);
    console.log(tableHeads.length);
    for(let i=tableHeads.length-1; i >= 0; i--) {
        console.log((tableHeads.length-i)-1);
        console.log("i = "+i);
        // console.log(tableHeads[1]);
        tableHeads[i].innerText = tableHeadsCopy[Math.abs((tableHeads.length-i)-1)].innerText;
        console.log("tableHeads["+i+"] = "+tableHeads[i].innerText);
        console.log("tableHeadsCopy["+i+"] = "+tableHeadsCopy[i].innerText);
        // document.querySelectorAll("th:nth-child(i)").innerText = tableHeads[(tableHeads.length-i)+1].innerText;
    };
    for(let i=0; i<tableHeads.length; i++) {
        console.log("tableHeads["+i+"] = "+tableHeads[i].innerText);
    };
    for(let i=0; i<tableHeads.length; i++) {
        console.log("tableHeadsCopy["+i+"] = "+tableHeadsCopy[i].innerText);
    };
};
function func4() {

};

