/**
 * 
 */

function loadMarca(){
		var check= document.createElement("select"); 
		check.setAttribute("name", "marca")
		var f=document.getElementById("container");
		
		var xh= new XMLHttpRequest;
		xh.onreadystatechange=function(){
			
			if(xh.readyState==4 && xh.status==200){
				console.log("inizio creazione text");
				check.innerHTML=xh.responseText;
				
			}
			
		}
		
		xh.open("GET","ServletMarca?status=ok",true);
		xh.send();
		
		f.appendChild(check);
		
	}
	
function loadTaglie(object){
	var div=document.getElementById("tg");
	 if(object.value=="F"){
		 div.innerHTML="";
		 for(i=35;i<42;++i){
		 var x = document.createElement("INPUT");
		 x.setAttribute("type", "checkbox");
		 x.setAttribute("name","taglia");
		 x.value=i;
		 
		 
		 var table = document.createElement("table");
		 var tr = document.createElement("tr");
		 var td = document.createElement("td");
		 td.setAttribute("style","display: -webkit-box;");
		 
		 
		 var lbl = document.createElement("label");
		 var taglie = document.createTextNode(""+i);
		 lbl.appendChild(taglie);
		 lbl.setAttribute("style","margin-right:22%;");
		 
		 td.append(lbl);
		 td.append(x);
		 tr.appendChild(td);
		 table.appendChild(tr);
		 div.appendChild(table);
	 } 
	 }else
		 if(object.value=="M"){
			 div.innerHTML="";
			 for(i=40;i<46;++i){
			 var x = document.createElement("INPUT");
			 x.setAttribute("type", "checkbox");
			 x.setAttribute("name","taglia");
			 x.value=i;
			 
			 
			 var table = document.createElement("table");
			 var tr = document.createElement("tr");
			 var td = document.createElement("td");
			 td.setAttribute("style","display: -webkit-box;");
			 
			 
			 var lbl = document.createElement("label");
			 var taglie = document.createTextNode(""+i);
			 lbl.appendChild(taglie);
			 lbl.setAttribute("style","margin-right:22%;");
			 
			 td.append(lbl);
			 td.append(x);
			 tr.appendChild(td);
			 table.appendChild(tr);
			 div.appendChild(table);
		 } 
		 }
}
	

	
	
	function crea(){
	var x = document.createElement("BR"); 
	var div=document.getElementById("container");
	div.classList.add("reg");
	var nome = document.createTextNode("Nome_Prodotto");
	var descrizione_b = document.createTextNode("Descrizione_Breve");
	var descrizione = document.createTextNode("Descrizione");
	var tag= document.createTextNode("Tag");
	var modello =document.createTextNode("Modello");
	var prezzo =document.createTextNode("Prezzo");
	var sesso = document.createTextNode("Sesso");
	
	var elements= [nome,descrizione_b,descrizione,tag,modello,prezzo];
	var i;
	for(i=0;i<elements.length;++i)
		{
			var p=document.createElement("p"); 
			var input = document.createElement("input");
			input.setAttribute("type","text");
			input.setAttribute("name",elements[i].nodeValue);
			input.required=true;
			
			var identify = i;
			p.setAttribute("id","par_"+identify);
			var label =document.createElement("label"); 
			label.appendChild(elements[i]);
			p.appendChild(label);
			p.appendChild(input);
			div.appendChild(p);
			
		
		}

		loadMarca();
		
		var  par= document.createElement("p"); 
		var check= document.createElement("select"); 
		check.onchange=function(){loadTaglie(this);}
		check.setAttribute("name", "sesso")
		check.innerHTML="<option value='M'> M</option> <option value ='F'> F</option>";
		par.appendChild(check);
		div.appendChild(par);
		
		var Tpar= document.createElement("p");
		Tpar.setAttribute("id","tg");
		div.appendChild(Tpar);
		
		var qPar= document.createElement("p");
		qPar.setAttribute("id","qta");
		var lblQuantita= document.createElement("label");
		var lblQtaTesto = document.createTextNode("Quantita'  disponibile");
		lblQuantita.appendChild(lblQtaTesto);
		div.appendChild(lblQuantita);
		var quantita= document.createElement("input");
		quantita.setAttribute("type","number");
		quantita.setAttribute("id","quantita");
		quantita.setAttribute("name","quantita");
		quantita.setAttribute("min","1");
		quantita.setAttribute("max","30");
		qPar.appendChild(quantita);
		div.appendChild(qPar);
		
		par = document.createElement("p");
		var file = document.createElement("input");
		file.setAttribute("type","file");
		file.setAttribute("name","file1");
		par.appendChild(file);
		div.appendChild(par);
		
		par = document.createElement("p");
		file = document.createElement("input");
		file.setAttribute("type","file");
		file.setAttribute("name","file2");
		par.appendChild(file);
		div.appendChild(par);
		
		par = document.createElement("p");
		file = document.createElement("input");
		file.setAttribute("type","file");
		file.setAttribute("name","file3");
		par.appendChild(file);
		div.appendChild(par);
		
		var invia = document.createElement("input");
		invia.setAttribute("type", "submit");
		invia.setAttribute("value", "Salva");
		//invia.classList.add("btn btn-primary");
		div.appendChild(x);
		div.appendChild(invia);
		
		
	}