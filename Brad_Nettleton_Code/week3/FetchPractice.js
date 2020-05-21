window.onload = function() {
    this.document.getElementById('ccSubmit').addEventListener('click', getCC);
};

function getCC() {
    let ccId = document.getElementById("ccId").value;

    fetch("https://api.coinlore.net/api/ticker/?id="+ccId)
    .then((respons) => {
        return respons.json();
    }).then(function(respons){
        console.log(respons);
        DOMManipulation(respons);
        return respons.mass;
    }).then(respons => {
        console.log(respons);
    }).catch(
        function(respons){
            console.log(respons);
            console.log("error");
        }
    )
}

function DOMManipulation(ccJSON) {
    document.getElementById("ccName").innerText = ccJSON[0].name;
    document.getElementById("ccSymbol").innerText = ccJSON[0].symbol;
    document.getElementById("ccPriceUsd").innerText = ccJSON[0].price_usd;
}