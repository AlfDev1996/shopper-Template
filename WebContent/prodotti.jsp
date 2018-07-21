<%@page import="model.ProdottoBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="org.json.simple.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>




<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prodotti</title>
<title>e-shoes</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- ViewPort è l'aria visibile dall'utente, con questo meta tag introdotto in HTML5 diamo istruzioni al browser sul ridimensionamento  -->
<meta name="description" content="">
<!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
<!-- bootstrap -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Libreria bootsrao (min) utilizzata dagli sviluppatori durante la fase di debugging o di sito in lavorazione, possiede meno proprieta del bootstrap.css  -->
<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
<!-- Libreria responsive -->

<link href="themes/css/bootstrappage.css" rel="stylesheet" />

<!-- global styles -->
<link href="themes/css/flexslider.css" rel="stylesheet" />
<!-- classe jquery per realizzare immagini, gallerie di immagini responsive !! -->
<link href="themes/css/main.css" rel="stylesheet" />

<!-- scripts -->
 <script src="themes/js/jquery-1.7.2.min.js"></script>
<!-- Libreeia jQuery: preferisco linkarla da google -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<!-- Funzioni bootstrap per eventuali animazioni  -->
<script src="themes/js/superfish.js"></script>
<!-- Classe javascript per implementare il menu, sembra non utilizzata da una prima analisi -->
<script src="themes/js/jquery.scrolltotop.js"></script>
<!-- http://www.dynamicdrive.com libreria di questo sito che permette di swipppare al top della pagina o in una posizione precisa -->
<!--[if lt IE 9]>			
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
			<script src="js/respond.min.js"></script>
		<![endif]-->

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


<script src="themes/js/prodotto.js">  </script>


<script>

function getElementCookie(){
	var container=document.getElementById("listaCookie");
	var xh= new XMLHttpRequest;
	xh.onreadystatechange=function(){
		
		if(xh.readyState==4 && xh.status==200){
			var obj = JSON.parse(xh.responseText);
			
			
			for(var i in obj){
				var li = document.createElement("li");
			var title=document.createElement("a");
				title.setAttribute("title",""+obj[i].nome);
				
				
				var img = document.createElement("img");
				
			
			if(obj[i].immagini.length>0)
			{
				img.setAttribute("src","themes/images/prodotti/"+obj[i].immagini[0].nome_file);
				img.setAttribute("onerror","this.onerror=null;this.src='themes/images/defaultImages/"+obj[i].immagini[0].nome_file+"'");
				
			}
			else
				img.setAttribute("src","themes/images/images.png");
			
			var link=document.createElement("a");
			link.innerHTML=obj[i].marca.nome+"  "+obj[i].modello;
			link.setAttribute("href","prodotto_dettagli.jsp?nome="+obj[i].nome+"&modello="+obj[i].modello);
			li.appendChild(title);
			li.appendChild(img);
			li.appendChild(link);
			container.appendChild(li);
				
				
	}
			
			
	}
		
		
	}
	
	xh.open("GET","FindProdotti?operazione=getCookie",true);
	xh.send();

	


}
</script>

</head>

<%
	String filtro = "";
	String tipoFiltro = "";
	if (request.getParameter("sesso") != null) {
		filtro = request.getParameter("sesso");
		tipoFiltro = "sesso";
	} else if (request.getParameter("categoria") != null) {
		filtro = request.getParameter("categoria");
		tipoFiltro = "categoria";
	}
%>

<body onload='returnProducts(<%=request.getAttribute("prodotti")%>,true);getElementCookie()'>
<%@ include file="header.jsp"%>

</section>	

	

	<%
		UtenteBean user = (UtenteBean) session.getAttribute("utente");
	%>

	<%
		if (request.getParameter("errore") != null && request.getParameter("errore").contains("Ops")) {
	%>
	<div class="alert alert-danger" role="alert">
		<strong><%=request.getParameter("errore")%></strong>
	</div>
	<%
		} else if (request.getParameter("errore") != null
				&& request.getParameter("errore").contains("Complimenti")) {
	%>
	<%
		
	%>
	<div class="alert alert-success" role="alert">
		<strong><%=request.getParameter("errore")%></strong>
	</div>
	<%
		}
	%>






	<section class="header_text sub"> 
	<%if(request.getParameter("filtro")!=null && !request.getParameter("filtro").isEmpty() &&  request.getParameter("filtro").equalsIgnoreCase("Adidas")){ %>
	<img class="pageBanner" src="themes/images/banner.jpg" alt="New products">
	<% } else if(request.getParameter("filtro")!=null && !request.getParameter("filtro").isEmpty() && request.getParameter("filtro").equalsIgnoreCase("nike")){ %>
		<img class="pageBanner" src="themes/images/banner-nike.jpg" alt="New products">
		
	
	<%} else {%>
	<img class="pageBanner" src="themes/images/banner-default.jpg" alt="New products">
	<%} %>
	<h4>
		<span id="title"></span>
	</h4>
	</section>
	<section class="main-content">

	

	<div class="row">
		<div class="span9">
			<ul class="thumbnails listing-products" id="listProdotti">

			</ul>
			<hr>
			<div class="pagination pagination-small pagination-centered">
				<ul>
					<li><a href="#">Prev</a></li>
					<li id="liPage1"><a href="#" onclick='returnProducts(<%=request.getAttribute("prodotti")%>,false,1)'>1</a></li>
					<li><a href="#" onclick='returnProducts(<%=request.getAttribute("prodotti")%>,false,2)' >2</a></li>
					<li><a href="#" onclick='returnProducts(<%=request.getAttribute("prodotti")%>,false,3)'>3</a></li>
					<li><a href="#" onclick='returnProducts(<%=request.getAttribute("prodotti")%>,false,4)'>4</a></li>
				</ul>
			</div>
		</div>
		
		
		
		<div class="span3 col">
			<div class="block">
				<ul class="nav nav-list below">
					<li class="nav-header">Prezzo</li>
					<li>Min<input type="text" id="txtPrezzoMin" style="width: 30px; height: 20px;">
						 -- <input type="text" id="txtPrezzoMax" style="width: 30px;height: 20px;">
						Max</li>
					<li><center> <button type="button" class="btn btn" onclick='filterByPrice(<%=request.getAttribute("prodotti")%>)'>Applica</button> </center></li>
				</ul>
				
				
			</div>
			<div class="block">
				<br />
				<ul class="nav nav-list below" id="listBrands">
					<li class="nav-header" >Brands</li>
					
					<li id="btnApplicaFiltriBrands"> <center> <button type="button" class="btn btn" onclick='filterByBrands(<%=request.getAttribute("prodotti")%>)' >Applica</button> </center></li>  
				</ul>
			</div>
			<div class="block">
				<h4 class="title">
					<strong>Consigliati per te </strong> 
				</h4>
				
			<ul class="small-product" id ="listaCookie">
					
					
				</ul>
				
				
			</div>
		
		</div>
	</div>
	</section>


	<%@ include file="footer.jsp"%>
</body>
</html>