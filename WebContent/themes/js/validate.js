/**
 * 
 */


function validateMail(){
	var email = document.getElementById("mail");
	
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	if(email.value.match(re))
		return true;
	else{
		alert("Email Inserita non corretta");
		email.focus();
		return false;
	}
}


function validateName(){
	
	var nome = document.getElementById("nome");
	var re= /^[A-Za-z\s]+$/;
	
	if(nome.value.match(re))
		return true;
	else{
		alert("Nome non corretto")
		
		return false;
		
	}
	
}

function validateCognome(){
	
	var nome = document.getElementById("cognome");
	var re= /^[A-Za-z\s]+$/;
	
	if(nome.value.match(re))
		return true;
	else{
		alert("Cognome non corretto")
		
		return false;
		
	}
	
}