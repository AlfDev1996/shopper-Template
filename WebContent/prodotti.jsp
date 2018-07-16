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
<!-- ViewPort � l'aria visibile dall'utente, con questo meta tag introdotto in HTML5 diamo istruzioni al browser sul ridimensionamento  -->
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

<!--   <body onload='returnProductsByFilter("<%=request.getParameter("filtro")%>")' > -->

<body onload='returnProducts(<%=request.getAttribute("prodotti")%>,true)'>
<%@ include file="header.jsp"%>
	


	

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


	<section class="header_text sub"> <img class="pageBanner"
		src="themes/images/pageBanner.png" alt="New products">
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
					<li> <button type="button" class="btn btn-primary btn-md" onclick='filterByPrice(<%=request.getAttribute("prodotti")%>)'>Applica</button> </li>
				</ul>
				
				
			</div>
			<div class="block">
				<br />
				<ul class="nav nav-list below" id="listBrands">
					<li class="nav-header" >Brands</li>
					
					<li id="btnApplicaFiltriBrands"><button type="button" class="btn btn-primary btn-md" onclick='filterByBrands(<%=request.getAttribute("prodotti")%>)' >Applica</button></li>
				</ul>
			</div>

			<div class="block">
				<h4 class="title">
					<strong>Best</strong> Seller
				</h4>
				<ul class="small-product">
					<li><a href="#" title="Praesent tempor sem sodales"> <img
							src="themes/images/ladies/3.jpg"
							alt="Praesent tempor sem sodales">
					</a> <a href="#">Praesent tempor sem</a></li>
					<li><a href="#" title="Luctus quam ultrices rutrum"> <img
							src="themes/images/ladies/4.jpg"
							alt="Luctus quam ultrices rutrum">
					</a> <a href="#">Luctus quam ultrices rutrum</a></li>
					<li><a href="#" title="Fusce id molestie massa"> <img
							src="themes/images/ladies/5.jpg" alt="Fusce id molestie massa">
					</a> <a href="#">Fusce id molestie massa</a></li>
				</ul>
			</div>
		</div>
	</div>
	</section>


	<%@ include file="footer.jsp"%>
</body>
</html>