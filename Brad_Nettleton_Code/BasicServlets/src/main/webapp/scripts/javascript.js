console.log("connected");

window.onload = function() {
    this.document.getElementById('ccSubmit').addEventListener('click', getCC);
};

function getCC() {

    let ccId = document.getElementById("ccId").value;

    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
        console.log(`ready state has changed ${xhttp.readyState}`);

        if(xhttp.readyState == 4 && xhttp.status == 200) {
            console.log("xhttp done");
            let cc = JSON.parse(xhttp.responseText);

            console.log(cc);

            DOMManipulation(cc);
        };
    };

    xhttp.open("GET", "https://api.coinlore.net/api/ticker/?id=" + ccId);

    xhttp.send();
};

function DOMManipulation(ccJSON) {
    document.getElementById("ccName").innerText = ccJSON[0].name;
    document.getElementById("ccSymbol").innerText = ccJSON[0].symbol;
    document.getElementById("ccPriceUsd").innerText = ccJSON[0].price_usd;
}

