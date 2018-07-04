<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrati</title>

<link href="themes/css/flexslider.css" rel="stylesheet"/>
		<link href="themes/css/main.css" rel="stylesheet"/>

		<!-- scripts -->
		<script src="themes/js/jquery-1.7.2.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>				
		<script src="themes/js/superfish.js"></script>	
		<script src="themes/js/jquery.scrolltotop.js"></script>
		
		
	<script>
	function loadCitta(){
		var nazione=document.getElementById("nazione").value;
		var xh= new XMLHttpRequest;
		xh.onreadystatechange=function(){
			if(xh.readyState==4 && xh.status==200){
				document.getElementById("provincia").innerHTML=xh.responseText;
				
			}
			
		}
		
		xh.open("GET","loadProv?nazione="+nazione,true);
		xh.send();
	}

		
	
	</script>	
		
</head>
<body>
<%@ include file="header.jsp" %>


<span class="errore container">
	<%if(request.getParameter("errore")!=null){ %>
		<h4 ><%=request.getParameter("errore") %></h4>
	<%} %>
					</span>
<div class ="reg">
<form method="POST" action ="register">
	Nome : <br>
	<input type="text" name="nome" required/>
	<br>
	Cognome :<br>
	<input type="text" name="cognome" required/>
	<br>
	Email : <br>
	<input type="text" name="email" required/>
	<br>
	Password: <br>
	<input type="password" name="password" required />
	<br>
	Nazione : <br>
	<input type="text" id="nazione" name="nazione" onfocusout="loadCitta()"required / >
	<br>
	Provincia : <br>
	<select id="provincia" name="provincia" required>
	
	</select>
	
	<br>
	
	Citta: <br>
	<input type="text" name="citta" required />
	<br>
	Via : <br>
	<input type="text" name="via" required />
	<br>
	
	CAP: <br>
	<input type="text" name="cap" required />
	<br>
	<input type="submit" value="Registrati"> 
	
	Civico: <br>
	<input type="text" name="civico" required />
	<br>
</form>


</div>
<%@ include file="footer.jsp" %>
</body>
</html>