<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Account</title>

<link href="themes/css/flexslider.css" rel="stylesheet" />
<link href="themes/css/main.css" rel="stylesheet" />

<!-- scripts -->
<script src="themes/js/jquery-1.7.2.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="themes/js/superfish.js"></script>
<script src="themes/js/jquery.scrolltotop.js"></script>
<script src="themes/js/user.js"></script>

<% UtenteBean user =(UtenteBean) session.getAttribute("utente"); %>
<% if( user==null|| !user.getRuolo().equalsIgnoreCase("admin")){ response.sendRedirect("index.jsp");}  %>

</head>
<body onload='load("<%=user.getEmail()%>")' >




	<%@ include file="header.jsp"%>
	


	
	
	<div id="container" class="container reg">
		<div id="row">
			<div class="span6">
				<p  class="float-left"> Nazione </p>
			
			
				<input type="text" readonly="readonly" id="nazione"/>
			</div>
		</div>
		<div id="row">
			<div class="span6">
				<p  class="float-left"> Via </p>
			
			
				<input type="text" readonly="readonly" id="via"/>
			</div>
		</div>
			<div id="row">
			<div class="span6">
				<p class="float-left"> Citta </p>
			
			
				<input type="text" readonly="readonly"  id="citta" />
			</div>
		</div>
			<div id="row">
			<div class="span6">
				<p  class="float-left"> CAP </p>
			
			
				<input type="text" readonly="readonly" id="cap"/>
			</div>
		</div>
			<div id="row">
			<div class="span6">
				<p  class="float-left"> Provincia </p>
			
			
				<input type="text" readonly="readonly" id="provincia" />
			</div>
		</div>
			<div id="row">
			<div class="span6">
				<p  class="float-left"> Civico </p>
			
			
				<input type="text" readonly="readonly" id="civico"/>
			</div>
		</div>
		<div id="row">
			<div class="span6">
				<p  class="float-left"> Email </p>
			
			
				<input type="text" readonly="readonly" id="email"/>
			</div>
			<div id="row">
			<div class="span6">
				<p  class="float-left"> Password </p>
			
			
				<input type="password" readonly="readonly" id="password"/>
			</div>
		</div>
		</div>
		<input type="button" class="btn btn" value="Modifica" id="mod" onclick="enableMod(this)">
		<input type="button" class="btn btn" value="Salva" id="save" onclick="save(this)" style="display:none;">
	
	</div>




<%@ include file="footer.jsp"%>
</body>
</html>