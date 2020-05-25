window.onload = function() {
	let deleteButton = document.getElementById("deleteButton");
	let viewButton = document.getElementById("viewButton");
	let activateButton = document.getElementById("activateButton");
	let viewDiv = document.getElementById("viewDiv");
	let deleteDiv = document.getElementById("deleteDiv");
	let activateDiv = document.getElementById("activateDiv");


	deleteButton.addEventListener("click", function() {
		if(deleteDiv.classList.contains("hidden")) {
			deleteDiv.classList.remove("hidden");
			viewDiv.classList.add("hidden");
			activateDiv.classList.add("hidden");
		}
		
		else {
			deleteDiv.classList.add("hidden");
		}
		
	})
	viewButton.addEventListener("click", function() {
		if(viewDiv.classList.contains("hidden")) {
			deleteDiv.classList.add("hidden");
			viewDiv.classList.remove("hidden");
			activateDiv.classList.add("hidden");
		}
		
		else {
			viewDiv.classList.add("hidden");
		}
		
		
	})
	activateButton.addEventListener("click", function() {
		if(activateDiv.classList.contains("hidden")) {
			deleteDiv.classList.add("hidden");
			viewDiv.classList.add("hidden");
			activateDiv.classList.remove("hidden");
		}
		
		else {
			activateDiv.classList.add("hidden");
		}
		
	})
	
//	document.getElementById("MyElement").classList.add('MyClass');
//
//	document.getElementById("MyElement").classList.remove('MyClass');
}




