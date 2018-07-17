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
						
						td=document.createElement("TD");
						td.setAttribute("style","width:10%;height:10%;");
						var aImg = document.createElement("a");
						aImg.setAttribute("href","#");
						var img = document.createElement("img");
						img.setAttribute("alt","");
						aImg.appendChild(img);
						
						var immagini = arrProdottiJson[i].immagini;
						if(immagini!=null && immagini.length>0)
						{
							img.setAttribute("src","themes/images/prodotti/"+arrProdottiJson[i].immagini[0].nome_file+"");
						}
						td.appendChild(aImg);
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
					    txt = document.createTextNode(arrProdottiJson[i].quantita+"");
					    td.setAttribute("id","quantita");
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
				"sesso" : "",
				"quantita" : 0
		};
		for(var i =0;i<c.length;++i)
		{
			if(c[i].childNodes[0]!=undefined && c[i].childNodes[0].type!="checkbox" && c[i].childNodes[0].type!="button")
			{
			 switch(i)
			 {
			  case 2 : jsonObj.nome= c[i].innerHTML;
			  		   break;
			  case 3 : jsonObj.modello = c[i].innerHTML;
			  		   break;
			  case 4 : jsonObj.marca = c[i].innerHTML;
	  		   		   break;
			  case 5 : jsonObj.prezzo = c[i].innerHTML;
		   		   	   break;
			  case 6 : jsonObj.descrizione_breve = c[i].innerHTML;
  		   	   			break;
			  case 7 : jsonObj.descrizione_estesa = c[i].innerHTML;
  		   	   			break;
			  case 8 : jsonObj.sesso = c[i].innerHTML;
  		   	   			break;
			  case 9 : jsonObj.quantita = c[i].innerHTML;
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
			if(c[i].id=="prezzo" || c[i].id=="descrizione_breve" || c[i].id=="descrizione_estesa" || c[i].id=="sesso" || c[i].id=="quantita")
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

function returnProducts(prodotti,refresh){
	alert("return prod senza numPage");
	returnProducts(prodotti,refresh,1);
	
}

function returnProducts(prodotti,refresh,numPage){
	
	
	
	if(numPage==undefined)
		numPage=1;
	
	//var arrProdottiJson = JSON.parse(prodotti);
	if(prodotti.length>0)
    {

		//Prendo la lista html
		var ul = document.getElementById("listProdotti");
		//Svuoto la lista
		//JQUERY
		$(ul).empty();
		
		var maxProducts = numPage*9;
		
		//Riempio la lista
		//for( var i in prodotti)
		for(var i = maxProducts-9 ; (i < prodotti.length && i < maxProducts) ; ++i)
		{
			var li=document.createElement('li');
			li.setAttribute("class","span3");
			
			var div = document.createElement("div");
			div.setAttribute("class","product-box");
			
			
			var span = document.createElement("span");
			span.setAttribute("class","sale_tag");
			span.innerHTML="";
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
	if(refresh==true)
	 inizializzaFiltri();
}

function inizializzaFiltri(){
	
	//Popolo la lista Marche
	popolaBrand();
	//Inserisco 3 articoli + Venduti
	popolaBestSeller();
	
}

function popolaBestSeller(){
	
}

function popolaBrand(){
	
	var xh= new XMLHttpRequest;
	xh.onreadystatechange=function(){
		if(xh.readyState==4 && xh.status==200){
			var response = xh.responseText;
			
			var arrMarcheJson = JSON.parse(response);
			
			
			if(arrMarcheJson.length>0)
		    {
				//Prendo la lista html marche
				var liBrands= document.getElementById("listBrands");

				var liBtnApplica = document.getElementById("btnApplicaFiltriBrands");
				for( var i in arrMarcheJson)
				{
					
					var liInterno= document.createElement("li");
					liInterno.setAttribute("name","liBrands[]");
					
					var divCheck= document.createElement("div");
					divCheck.setAttribute("class","checkbox");
					
					var checkBox= document.createElement("input");
					checkBox.setAttribute("type","checkbox");
					
					checkBox.setAttribute("id",arrMarcheJson[i].id_marca);
					//checkBox.setAttribute("name",arrMarcheJson[i].nome);
					checkBox.setAttribute("name","checkBrands[]");
					
					divCheck.innerHTML=arrMarcheJson[i].nome;
					
					divCheck.appendChild(checkBox);
					liInterno.appendChild(divCheck);
					
					
					liBrands.insertBefore(liInterno,liBtnApplica);
				}
				
				/*
				//Creo il bottone Applica
				var li = document.createElement("li");
				var btn = document.createElement("button");
				btn.setAttribute("type","button");
				btn.setAttribute("class","btn btn-primary btn-md");
				btn.innerHTML="Applica";
				btn.setAttribute("onclick","filterByBrands()");
				
				li.appendChild(btn);
				liBrands.appendChild(li);*/
		    }
		}
	}
	xh.open("GET","ServletMarca?tipoRicerca=allBrands",true);
	xh.send();
	
}

function returnProductsByFilter(parametro){
	
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

function filterByBrands(prodotti){
	
	var newProdotti = new Array();
	//JQUERY
	//Mi prendo tutti i brand checked
	var brands = $("input[name='checkBrands[]']:checked").map(function() {
		this.checked=false;
        return this.id;
    }).get();
	
	if(prodotti.length>0 && brands.length>0)
	{
		for( var i in prodotti)
		{
			//jQuery.inArray controlla se il primo parametro si trova nell array inviato come secondo
			//parametro, restituisce l'indice se esiste tale elemento nell'array, -1 altrimenti
			if(jQuery.inArray(prodotti[i].marca.id_marca+"",brands)!=-1)
			 newProdotti.push(prodotti[i]);
		}
	}
	else
	 if(prodotti.length>0 && brands.length==0)
		 returnProducts(prodotti,false);
	
	returnProducts(newProdotti,false);
}

function filterByPrice(prodotti){
	var newProdotti = new Array();
	var prezzoMin= document.getElementById("txtPrezzoMin").value;
	var prezzoMax= document.getElementById("txtPrezzoMax").value;
	
	if(prodotti.length>0 )
	{
		if(prezzoMin=="")
			prezzoMin=0;
		if(prezzoMax=="")
			prezzoMax=9999;
		for( var i in prodotti)
		{
			if(prodotti[i].prezzo>=prezzoMin && prodotti[i].prezzo<=prezzoMax)
				newProdotti.push(prodotti[i]);
		}
	}
	else
	 if(prodotti.length>0)
		 returnProducts(prodotti,false);
	
	returnProducts(newProdotti,false);
}

function addToCart(nome,modello){
	
	var prodottoJson = {
			"nome" : "",
			"modello" : "",
			"quantita" : 0,
			"taglia" : ""
	};
	
	var taglia = $("#selectTaglie").val();
	
	var quantita = document.getElementById("txtQuantita").value;
	if(quantita == "")
		quantita=1;
	
	
	
	if(prezzo=="")
		prezzo=0;
	if(quantita!="" && taglia!="" && nome!="" && modello!="" && prezzo!="")
	{
		prodottoJson.nome=nome+"";
		prodottoJson.modello=modello+"";
		prodottoJson.quantita=quantita+"";
		prodottoJson.taglia=taglia+"";
		
		
		var xh= new XMLHttpRequest;
		xh.onreadystatechange=function(){
			if(xh.readyState==4 && xh.status==200){
				var response = xh.responseText;
				location.reload(true);
			}
			
		}
		var jsonStr= JSON.stringify(prodottoJson);
		xh.open("GET","ServletCart?operazione=aggiungi&prodotto="+encodeURIComponent(jsonStr),true);
		//xh.setRequestHeader("Content-type", "application/json");
		//xh.send("prodotto="+jsonStr);
		xh.send();
	}
	
	
}

function updateProductsFromCart(){
		//Se la checkBox elimina e' settata, bisogna eliminare i prodotti dal carrello.
		var eliminaProdottiChecked = $("input[name='prodotti[]']:checked").map(function() {
			return this.id;
	    }).get();
		//Per eliminare prodotti dal carrello, passo semplicemente gli id dei prodotti.
		if(eliminaProdottiChecked.length>0)
		{
			var xh= new XMLHttpRequest;
			xh.onreadystatechange=function(){
				if(xh.readyState==4 && xh.status==200){
					var response = xh.responseText;
					location.reload(true);
				}
				
			}
			xh.open("GET","ServletCart?operazione=elimina&prodottiDaRimuovere="+(eliminaProdottiChecked+""),true);
			xh.send();
		}else{
			//UPDATE
			var arrJson=[];
			$("input[type='number'][name='qtaProdotti[]']").each(function(){
				
				arrJson.push({
					"id":$(this).attr('id'),
					"quantita" : $(this).attr('value')
					});
			});
			
			var xh= new XMLHttpRequest;
			xh.onreadystatechange=function(){
				if(xh.readyState==4 && xh.status==200){
					var response = xh.responseText;
					location.reload(true);
				}
				
			}
			var json= JSON.stringify(arrJson);
			xh.open("GET","ServletCart?operazione=modifica&arrayProdottiModificaJson="+encodeURIComponent(json),true);
			xh.send();
			
		}
		
}
