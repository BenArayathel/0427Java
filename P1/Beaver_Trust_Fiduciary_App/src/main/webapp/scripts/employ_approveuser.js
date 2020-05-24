let submit6 = document.getElementById("approveButton");

submit6.addEventListener("click", approve);

function approve() {
    let user_id = document.getElementById("approveUser").value;

    // make call to api/servlet
    fetch("http://localhost:9999/Beaver_Trust_Fiduciary_App/approve", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
            'Accept': 'application/json',
        },
        body: JSON.stringify({
            user_id: user_id,
        })
    })
    // try getting back whatever it is sending...
    .then(response => response.text())
    .then(handleResponse)
}

function handleResponse() {
    // unless i need something from the servlet, just say it is approved

    // clear forms
    document.getElementById("approveUser").value = '';
    // create popup to notify them of the result
    alert("Account Approved. User will now be able to log in.");
    // location.reload();
}