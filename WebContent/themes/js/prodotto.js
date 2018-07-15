/**
 * 
 */





function find(){
	
	deleteRowTable();
	
	var nome = document.getElementById("nome").value+"";
	if(nome!=null)
    {
		var xh= new XMLHttpRequest;
		xh.onreadystatechange=function(){
			
			if(xh.readyState==4 && xh.status==200){
				var response = xh.responseText;
				var arrProdottiJson = JSON.parse(response);
				//Prendo la tabella
				var table = document.getElementById("tableProdotti");
				if(arrProdottiJson.length>0)
			    {
					
					for( var i in arrProdottiJson)
					{
						var tr = document.createElement("TR");
						tr.setAttribute("id", i+"");
						tr.setAttribute("name",arrProdottiJson[i].nome);
						table.appendChild(tr);
						
						var td = document.createElement("TD");
						var check = document.createElement("INPUT");
						check.setAttribute("type","checkbox");
						check.setAttribute("id",arrProdottiJson[i].nome+"_"+arrProdottiJson[i].modello);
						td.append(check);
						tr.appendChild(td);
						
						td = document.createElement("TD");
					    var txt = document.createTextNode(arrProdottiJson[i].nome+"");
					    
					    td.appendChild(txt);
					    tr.appendChild(td);
					    
					    td = document.createElement("TD");
					    txt = document.createTextNode(arrProdottiJson[i].modello+"");
					   
					    td.appendChild(txt);
					    tr.appendChild(td);
					    
					    td = document.createElement("TD");
					    txt = document.createTextNode(arrProdottiJson[i].marca.nome+"");
					    
					    td.appendChild(txt);
					    tr.appendChild(td);
					    
					    td = document.createElement("TD");
					    txt = document.createTextNode(arrProdottiJson[i].prezzo+"");
					    td.setAttribute("id","prezzo");
					    td.appendChild(txt);
					    tr.appendChild(td);
					    
					    td = document.createElement("TD");
					    txt = document.createTextNode(arrProdottiJson[i].descrizione_breve+"");
					    td.setAttribute("id","descrizione_breve");
					    td.appendChild(txt);
					    tr.appendChild(td);
					    
					    td = document.createElement("TD");
					    txt = document.createTextNode(arrProdottiJson[i].descrizione_estesa+"");
					    td.setAttribute("id","descrizione_estesa");
					    td.appendChild(txt);
					    tr.appendChild(td);
					    
					    td = document.createElement("TD");
					    txt = document.createTextNode(arrProdottiJson[i].sesso+"");
					    td.setAttribute("id","sesso");
					    td.appendChild(txt);
					    tr.appendChild(td);
					    
					    td = document.createElement("TD");
						var btnModifica = document.createElement("INPUT");
						btnModifica.setAttribute("type","button");
						btnModifica.setAttribute("id","btnModifica");
						btnModifica.setAttribute("value","Modifica");
						btnModifica.setAttribute("class","btn btn");
						btnModifica.setAttribute("onclick", "modifica(this,'false')");
						td.append(btnModifica);
						tr.appendChild(td);
						
						td = document.createElement("TD");
						var btnSalva = document.createElement("INPUT");
						btnSalva.setAttribute("type","button");
						btnSalva.setAttribute("id","btnSalva");
						btnSalva.setAttribute("value","Salva");
						btnSalva.setAttribute("class","btn btn");
						btnSalva.setAttribute("style","display : none");
						btnSalva.setAttribute("onclick", "modifica(this,'true')");
						td.append(btnSalva);
						tr.appendChild(td);
					    
					}
			    }
				
			}
			
		}
		xh.open("GET","FindProdotti?nome="+nome,true);
		xh.send();
    }
}

function deleteRowTable(){
	
	var table = document.getElementById("tableProdotti");
    var num = table.rows.length;
   // alert(num);
    if(num>1)
     for(var i = 1 ; i < num ; --num)
     {
    	 table.deleteRow(i);
     }
}

function modifica(object, salva){
	// 9 indice btnSalva
	// 8 indice btnModifica
	
	if(salva=="true"){
		object.setAttribute("style","display:none");
		var td = object.parentElement;
		var tr= td.parentElement;
		var c = tr.childNodes;
		var jsonObj= {
				"nome" : "",
				"modello" : "",
				"marca" : "",
				"prezzo" : 0,
				"descrizione_breve" : "",
				"descrizione_estesa" : "",
				"sesso" : ""
		};
		for(var i =0;i<c.length;++i)
		{
			if(c[i].childNodes[0]!=undefined && c[i].childNodes[0].type!="checkbox" && c[i].childNodes[0].type!="button")
			{
			 switch(i)
			 {
			  case 1 : jsonObj.nome= c[i].innerHTML;
			  		   break;
			  case 2 : jsonObj.modello = c[i].innerHTML;
			  		   break;
			  case 3 : jsonObj.marca = c[i].innerHTML;
	  		   		   break;
			  case 4 : jsonObj.prezzo = c[i].innerHTML;
		   		   	   break;
			  case 5 : jsonObj.descrizione_breve = c[i].innerHTML;
  		   	   			break;
			  case 6 : jsonObj.descrizione_estesa = c[i].innerHTML;
  		   	   			break;
			  case 7 : jsonObj.sesso = c[i].innerHTML;
  		   	   			break;
		   	  default : break;
			 } 
				 
			}
			//Rendo invisibile il bottone salva e visibile il bottone Modifica
			 if(c[i].childNodes[0].id=="btnModifica")
			 {
				 c[i].childNodes[0].style.display="inline";
			 }
		}
		
		
		var xh= new XMLHttpRequest;
		xh.onreadystatechange=function(){
			
			if(xh.readyState==4 && xh.status==200){
				var response = xh.responseText;
				if(response=="true")
					//alert("ok");
					;
			}
		}
		var x  = JSON.stringify(jsonObj);
		
		xh.open("GET","ModificaProdotto?prodotto="+encodeURIComponent(x),true);
		xh.send();
		
		
		//var btnModfica = document.getElementById("btnModifica");
		//btnModfica.setAttribute("style","display: inline");
		
	}else
	{
		object.setAttribute("style","display:none");
		//var btnSalva= document.getElementById("btnSalva");
		//btnSalva.setAttribute("style","display: inline");
		var td = object.parentElement;
		var tr= td.parentElement;
		var c = tr.childNodes;
		for(var i =0;i<c.length;++i)
			{
			//if(c[i].childNodes[0]!=undefined && c[i].childNodes[0].type!="checkbox" && c[i].childNodes[0].type!="button")
			if(c[i].id=="prezzo" || c[i].id=="descrizione_breve" || c[i].id=="descrizione_estesa" || c[i].id=="sesso")
				c[i].contentEditable = "true"
			else
			 //Rendo invisibile il bottone modifica e visibile il bottone salva
			 if(c[i].childNodes[0].id=="btnSalva")
		     {
				 c[i].childNodes[0].style="display:inline";
		     }
			
			}
	}
	
	
		
	
	
}

function deleteProducts(){
			//In values ci sono gli id delle checkbox , separati da ',' .
			//L'id della checkBox e' composto da chiave nomeProdotto + "_" + modello
			var values = $('input:checked').map(function() {
		        return this.id;
		    }).get();
			
			var xh= new XMLHttpRequest;
			xh.onreadystatechange=function(){
				if(xh.readyState==4 && xh.status==200){
					var response = xh.responseText;
				}
			}
			xh.open("GET","DeleteProdotti?stringaProdotti="+values,true);
			xh.send();
			
			
			//find();
			location.reload(true);
			
	
}

function test(prodotti){

	//var arrProdottiJson = JSON.parse(prodotti);
	if(prodotti.length>0)
    {

		//Prendo la lista html
		var ul = document.getElementById("listProdotti");
		for( var i in prodotti)
		{
			var li=document.createElement('li');
			li.setAttribute("class","span3");
			
			var div = document.createElement("div");
			div.setAttribute("class","product-box");
			
			var span = document.createElement("span");
			span.setAttribute("class","sale_tag");
			span.innerHTML="50%";
			div.appendChild(span);
			
			var immagini = prodotti[i].immagini;
			if(immagini!=null && immagini.length>0)
			{
				var aImg = document.createElement("a");
				aImg.setAttribute("href","prodotto_dettagli.jsp?nome="+prodotti[i].nome+"&modello="+prodotti[i].modello);
				var img = document.createElement("img");
				img.setAttribute("src","themes/images/prodotti/"+prodotti[i].immagini[0].nome_file+"");
				img.setAttribute("alt","");
				img.setAttribute("src","themes/images/prodotti/"+prodotti[i].immagini[0].nome_file+"");
				aImg.appendChild(img);
				div.appendChild(aImg);
			}
			
			
			var aNomeProdotto = document.createElement("a");
			aNomeProdotto.setAttribute("href","prodotto_dettagli.jsp?nome="+prodotti[i].nome+"&modello="+prodotti[i].modello);
			aNomeProdotto.setAttribute("class","title");
			aNomeProdotto.innerHTML= prodotti[i].nome;
			div.appendChild(aNomeProdotto);
			
			var pPrezzo = document.createElement("p");
			pPrezzo.setAttribute("class","price");
			pPrezzo.innerHTML= "&euro;"+prodotti[i].prezzo;
			div.appendChild(pPrezzo);
			//Aggiungo gli elementi al div
			
			
			
			
			
			li.appendChild(div);
			
		    ul.appendChild(li);
		}
    }
}

function returnProductsByFilter(parametro){
	
	var s="<%=sss%>";
	alert(s);
	var tipoFiltro = parametro.substring(0,parametro.indexOf("_"));
	var filtro = parametro.substring(parametro.indexOf("_")+1,parametro.length);
	
	if(filtro=="M"){
		var x = document.getElementById("title");
		x.innerHTML="Scarpe Uomo";
		
	}
	
	var xh= new XMLHttpRequest;
	xh.onreadystatechange=function(){
		
		if(xh.readyState==4 && xh.status==200){
			var response = xh.responseText;
			var arrProdottiJson = JSON.parse(response);
			
			
			if(arrProdottiJson.length>0)
		    {
				//Prendo la lista html
				var ul = document.getElementById("listProdotti");
				for( var i in arrProdottiJson)
				{
					var li=document.createElement('li');
					li.setAttribute("class","span3");
					
					var div = document.createElement("div");
					div.setAttribute("class","product-box");
					
					var span = document.createElement("span");
					span.setAttribute("class","sale_tag");
					span.innerHTML="50%";
					div.appendChild(span);
					
					var immagini = arrProdottiJson[i].immagini;
					if(immagini!=null && immagini.length>0)
					{
						var aImg = document.createElement("a");
						aImg.setAttribute("href","prodotto_dettagli.jsp?nome="+arrProdottiJson[i].nome+"&modello="+arrProdottiJson[i].modello);
						var img = document.createElement("img");
						img.setAttribute("src","themes/images/prodotti/"+arrProdottiJson[i].immagini[0].nome_file+"");
						img.setAttribute("alt","");
						img.setAttribute("src","themes/images/prodotti/"+arrProdottiJson[i].immagini[0].nome_file+"");
						aImg.appendChild(img);
						div.appendChild(aImg);
					}
					
					
					var aNomeProdotto = document.createElement("a");
					aNomeProdotto.setAttribute("href","prodotto_dettagli.jsp?nome="+arrProdottiJson[i].nome+"&modello="+arrProdottiJson[i].modello);
					aNomeProdotto.setAttribute("class","title");
					aNomeProdotto.innerHTML= arrProdottiJson[i].nome;
					div.appendChild(aNomeProdotto);
					
					var pPrezzo = document.createElement("p");
					pPrezzo.setAttribute("class","price");
					pPrezzo.innerHTML= "&euro;"+arrProdottiJson[i].prezzo;
					div.appendChild(pPrezzo);
					//Aggiungo gli elementi al div
					
					
					
					
					
					li.appendChild(div);
					
				    ul.appendChild(li);
				}
		    }
		}
	}
	
	xh.open("GET","FindProdotti?tipoFiltro="+tipoFiltro+"&filtro="+filtro,true);
	xh.send();
	
}

function returnProductByNomeAndModello(nome,modello){
	
	var xh= new XMLHttpRequest;
	xh.onreadystatechange=function(){
		
		if(xh.readyState==4 && xh.status==200){
			var response = xh.responseText;
			var prodotto = JSON.parse(response);
			
			
			if(prodotto!=null)
		    {
				var marca = document.getElementById("marca");
				var disponibilita = document.getElementById("disponibilita");
				var prezzo = document.getElementById("prezzo");
				marca.innerHTML= prodotto.marca.nome;
				disponibilita.innerHTML = prodotto.quantita > 0 ? "Disponibile" : "Esaurito" ;
				prezzo.innerHTML = "&euro;"+prodotto.prezzo;
				
				var img1=document.getElementById("img1");
				var img2=document.getElementById("img2");
				var img3=document.getElementById("img3");
				if(prodotto.immagini!=null && prodotto.immagini.length>0)
				{
					if(prodotto.immagini[0]!=null && prodotto.immagini[0].nome_file!="")
						img1.setAttribute("src","themes/images/prodotti/"+prodotto.immagini[0].nome_file);
					
					else
						img1.parentElement.setAttribute("style","dysplay:none;");
					
					if(prodotto.immagini[1]!=null && prodotto.immagini[1].nome_file!="")
						img2.setAttribute("src","themes/images/prodotti/"+prodotto.immagini[1].nome_file);
					
					else
						img2.parentElement.setAttribute("style","dysplay:none;");
					
					if(prodotto.immagini[2]!=null && prodotto.immagini[2].nome_file!="")
						img3.setAttribute("src","themes/images/prodotti/"+prodotto.immagini[2].nome_file);
					else
						img3.parentElement.setAttribute("style","dysplay:none;");
				}else
				{
					img1.parentElement.setAttribute("style","dysplay:none;");
					img2.parentElement.setAttribute("style","dysplay:none;");
					img3.parentElement.setAttribute("style","dysplay:none;");
				}
				
				if(prodotto.taglie!=null && prodotto.taglie.length>0){
					var taglie = document.getElementById("selectTaglie");
					var res= prodotto.taglie.split(",");
					for (i=0; i<res.length; ++i)
						{	var option = document.createElement("option");
							option.text = res[i]+"";
							taglie.add(option);
						
						
						}
					
					taglie= document.getElementById("taglie");
					taglie.innerHTML= prodotto.taglie;
					
					
				}
				
			if(prodotto.descrizione_estesa!=null){
				var descrizione_lunga= document.getElementById("descrizione_lunga");
				descrizione_lunga.innerHTML = prodotto.descrizione_estesa;
				
			}
			
		    }
		}
	}
	
	xh.open("GET","FindProdotti?nome="+nome+"&modello="+modello,true);
	xh.send();
	
}
