<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

	<link href="themes/css/flexslider.css" rel="stylesheet"/>
		<link href="themes/css/main.css" rel="stylesheet"/>

		<!-- scripts -->
		<script src="themes/js/jquery-1.7.2.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>				
		<script src="themes/js/superfish.js"></script>	
		<script src="themes/js/jquery.scrolltotop.js"></script>
</head>
<body>

<%@ include file="header.jsp" %>



<form method="post" action="accedi">


<div class="reg">

<span class="errore container">
	<%if(request.getParameter("errore")!=null){ %>
		<h4 ><%=request.getParameter("errore") %></h4>
	<%} %>
					</span>
<label>Email: <br><input type="text" value="" name="mail" maxlength="30" style=""/> <br />
<label>Password: <br><input type="password" value="" name="password" maxlength="20" /><br/>

<input type="submit" value="login" name="loginSubmit" /></label>


</div>

<%@ include file="footer.jsp" %>


</body>
</html>