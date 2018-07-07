<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifica Prodotti</title>

<link href="themes/css/flexslider.css" rel="stylesheet"/>
		<link href="themes/css/main.css" rel="stylesheet"/>

		<!-- scripts -->
		<script src="themes/js/jquery-1.7.2.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>				
		<script src="themes/js/superfish.js"></script>	
		<script src="themes/js/jquery.scrolltotop.js"></script>
		<script  src="themes/js/validate.js"></script>
		<!--  Includo il file js relativo al prodotto -->
		<script src="themes/js/prodotto.js"></script>
	
</head>
<body onload="createTableProdotti()">
	<%@ include file="header.jsp" %>
<div>
	<input type="text" id="nome" class="inputMargin" >
	
	<input type="button" value="Cerca Prodotto" onclick="find()" class="btn btn" style="height: 100%"> 
	
	<input type="button" value="Elimina" onclick="deleteProducts()" class="btn btn" style="height: 100%">
	
</div>

	 
	<div id="container">
		<table id="tableProdotti" class="table table-striped" >
		 <tr id="intestazione">
		 	<th> &nbsp; </th>
		 	<th> Nome </th>
		 	<th> Modello </th>
		 	<th> Marca </th>
		 	<th> Prezzo </th>
		 </tr>
		</table>
	</div>


	<%@ include file="footer.jsp" %>
</body>
</html>