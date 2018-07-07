/**
 * 
 */

function find(){
	
	deleteRowTable();
	
	var nome = document.getElementById("nome").value+"";
	if(nome!="")
    {
		console.log("qui");
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
					    td.appendChild(txt);
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
	var i=0;
	var table = document.getElementById("tableProdotti");
    var num = table.rows.length;
    if(num>1)
     for( i = 1 ; i < num ; ++i)
      table.deleteRow(i);
}

function createTableProdotti(){
	var p = document.getElementById("container");
	var table = document.createElement("TABLE");
	table.setAttribute("id", "tableProdotti");
	table.setAttribute("border", "1");
    p.appendChild(table);

    /*
     * 
     * var tr = document.createElement("TR");
    tr.setAttribute("id", "intestazione");
    table.appendChild(tr);

    var th = document.createElement("TH");
    
    var nome = document.createTextNode("Nome");
    
    th.appendChild(nome);
    tr.appendChild(th);
    var modello = document.createTextNode("Modello");
    th = document.createElement("TH");
    th.appendChild(modello);
    tr.appendChild(th);
    var marca = document.createTextNode("Marca");
    th = document.createElement("TH");
    th.appendChild(marca);
    tr.appendChild(th);
    var prezzo = document.createTextNode("Prezzo");
    th = document.createElement("TH");
    th.appendChild(prezzo);
    tr.appendChild(th);
     */
    
    
    
}