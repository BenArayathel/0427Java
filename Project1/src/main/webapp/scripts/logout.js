function logoutUser(event) {
    fetch('http://localhost:8090/Project1/app/Logout')
        .then(response => {
        	console.log(response)
        	window.location.href = response.url;
        })
        .catch(error => console.error(error));
}