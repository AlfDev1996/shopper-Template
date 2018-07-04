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
</head>
<body>

	<%@ include file="header.jsp"%>
<form method="post" action="ModificaAccount">
	<div class="row">
		<div class="span6">
			<hr style="border: solid;">
			<h5> Informazioni personali</h5>
			<hr style="border: solid;">
		</div>
		<div class="span6">
			<hr style="border: solid;">
			<h5> Informazioni di accesso</h5>
			<hr style="border: solid;">
		</div>
	</div>

	<div class="row">
	<!--  Tabella per informazioni Personali -->
		<div class="span6">
		 <table style="float: left; width: 100%">
		 <tr>
		 	<td colspan="3"> Nome : </td>
		 	<td  colspan="3" align="right">
		 	 <input type=""> 
		 	</td>
		 </tr>
		 <tr>
		 	<td colspan="3"> Cognome : </td>
		 	<td colspan="3" align="right">  </td>
		 </tr>
		 <tr>
		 	<td colspan="3"> Indirizzo : </td>
		 	<td colspan="3" align="right">  </td>
		 </tr>
		 <tr>
		 	<td colspan="3"> Codice Postale : </td>
		 	<td colspan="3" align="right">  </td>
		 </tr>
		 <tr>
		 	<td colspan="3"> Citta' : </td>
		 	<td colspan="3" align="right">  </td>
		 </tr>
		 <tr>
		 	<td colspan="3"> Provincia : </td>
		 	<td colspan="3" align="right">  </td>
		 </tr>
		</table>
		
		</div>
	<!--  Tabella per informazioni di accesso -->
		<div class="span6">
		<table style="float: left; width: 100%">
		 <tr>
		 	<td colspan="3"> Email : </td>
		 	<td colspan="3" align="right">  </td>
		 </tr>
		 <tr>
		 	<td colspan="3"> Password : </td>
		 	<td colspan="3" align="right">  </td>
		 </tr>
		 </table>
		</div>
	</div>
	<div class="row">
		<div class="span6" align="right">
			<input type="button" class="btn btn" value="Modifica">
		</div>
		<div class="span6" align="right">
			<input type="button" class="btn btn" value="Modifica">
		</div>
	</div>
 



<br>
<br>
<br>

		<%@ include file="footer.jsp"%>
</body>
</html>