function validateRegisterForm() {
	// Validates Username
	let u = document.forms["registerForm"]["usernameInput"].value;
	if (!u === /[a-zA-Z0-9]{3,15}/) {
		alert("Invalid, username must be between [3-15] alphanumeric characters.");
		return false;
	}

	// Validates Password
	let p = document.forms["registerForm"]["passwordInput"].value;
	if (!p === /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,}/) {
		alert("Invalid, password must be a minimum of 8 characters including 1 uppercase, 1 lowercase, 1 digit, and 1 special character [!@#$%^&*_].");
		return false;
	}

	// Validates Deposit Amount
	let d = document.forms["registerForm"]["depositInput"].value;
	if (!d == /^\\d{1,5}/) {
		alert("Invalid, amount must be whole numbers between 1-5 digits long.");
		return false;
	}
}

function validateLoginForm() {
	// Validates Username

	// Validates Password
}