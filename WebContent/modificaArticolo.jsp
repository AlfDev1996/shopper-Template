<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifica Articolo</title>




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
<script>

</script>	
	
</head>
<body >
<% UtenteBean user =(UtenteBean) session.getAttribute("utente"); %>
<% if( user==null|| !user.getRuolo().equalsIgnoreCase("admin")){ response.sendRedirect("index.jsp");}  %>
	
	<%@ include file="header.jsp" %>
	
	<form action="">
	
	<div>
	<input type="text" id="nome" class="inputMargin" >
	
	<input type="button" id="btnCerca" value="Cerca Prodotto" onclick="find()" class="btn btn" style="height: 100%"> 
	
	<input type="button" id="btnElimina" name="btnElimina" value="Elimina" onclick="deleteProducts()" class="btn btn" style="height: 100%">
	
	</div>
 
	</form>

	 
	<div id="container">
		<table id="tableProdotti" class="table table-striped" >
		 <tr id="intestazione">
		 	<th> &nbsp; </th>
		 	<th> Immagine </th>
		 	<th> Nome </th>
		 	<th> Modello </th>
		 	<th> Marca </th>
		 	<th> Prezzo </th>
		 	<th> Descr. Breve </th>
		 	<th> Descr. Estesa </th>
		 	<th> Sesso </th>
		 	<th> Quantita </th>
		 	<th> &nbsp; </th>
		 	<th> &nbsp; </th>
		 </tr>
		 
		</table>
	</div>

	<%@ include file="footer.jsp" %>
</body>
</html>