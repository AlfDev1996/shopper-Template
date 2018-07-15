/**
 * 
 */

function load(utente){
	
	var xh= new XMLHttpRequest;
	xh.onreadystatechange=function(){
		
		if(xh.readyState==4 && xh.status==200){
			
			var response = xh.responseText;
			
			var userJeson = JSON.parse(response);
			
			//Popolo i campi
			
			var x = document.getElementById("nazione");
			x.value= userJeson.nazione;
			
			x=document.getElementById("via");
			x.value= userJeson.indirizzo_via;
			
			
			x=document.getElementById("citta");
			x.value= userJeson.indirizzo_citta;
			
			
			x=document.getElementById("cap");
			x.value= userJeson.indirizzo_cap;
			
			
			x=document.getElementById("provincia");
			x.value= userJeson.indirizzo_provincia;
			
			x=document.getElementById("civico");
			x.value= userJeson.indirizzo_num_civico;
			
			x=document.getElementById("email");
			x.value= userJeson.email;
			
			x=document.getElementById("password");
			x.value= userJeson.password;
			
		}
	}
	
	xh.open("GET","ServletUtente?email="+utente+"&operation=load",true);
	
	xh.send();
	
	
}





function enableMod(btn){
	btn.setAttribute("style","display:none;");
	var save= document.getElementById("save");
	save.style.display="inline";
	var inputs = document.getElementsByTagName('input');

	for(var i = 0; i < inputs.length; i++) {
		if(inputs[i].type.toLowerCase() == 'text'|| inputs[i].type.toLowerCase() == 'password') {
	        inputs[i].removeAttribute("readonly");
	    }
	
}
}
	

function save(salva){
	
	salva.setAttribute("style","display:none;");
	var mod= document.getElementById("mod");
	mod.setAttribute("style","display:inline;");
	
	var jsonObj= {
			"nazione" : "",
			"via" : "",
			"citta" : "",
			"cap" : 0,
			"provincia" : "",
			"civico" : 0,
			"email" : "",
			"password":""
	};
	
	var inputs = document.getElementsByTagName('input');

	for(var i = 0; i < inputs.length; i++) {
		if(inputs[i].type.toLowerCase() == 'text' || inputs[i].type.toLowerCase() == 'password')
		{

				if(inputs[i].id=="nazione")
					jsonObj.nazione=inputs[i].value;
				if(inputs[i].id=="via")
					jsonObj.via=inputs[i].value;
				if(inputs[i].id=="citta")
					jsonObj.citta=inputs[i].value;
				if(inputs[i].id=="cap")
					jsonObj.cap=inputs[i].value;
				if(inputs[i].id=="provincia")
					jsonObj.provincia=inputs[i].value;
				if(inputs[i].id=="civico")
					jsonObj.civico=inputs[i].value;
				
				if(inputs[i].id=="email")
					jsonObj.email=inputs[i].value;
				
				if(inputs[i].id=="password")
					jsonObj.password=inputs[i].value;
	
}
	
	
	
	
}
	console.log(jsonObj);
	
	var xh= new XMLHttpRequest;
	xh.onreadystatechange=function(){
		
		if(xh.readyState==4 && xh.status==200){
			
				location.reload(true);
				
		}
	}
	var x  = JSON.stringify(jsonObj);
	
	xh.open("GET","ServletUtente?utenteJs="+encodeURIComponent(x)+"&operation=save",true);
	xh.send();
	

}
	
