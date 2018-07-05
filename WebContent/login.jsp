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
		<script  src="themes/js/validate.js"></script>
	
		
</head>
<body>

<%@ include file="header.jsp" %>



<form method="post" action="accedi"  onsubmit="return validateMail()">


<div class="reg">


			<%
				if (request.getParameter("errore") != null && !request.getParameter("errore").contains("Complimenti") && !request.getParameter("errore").contains("Ops")) {
			%>
			<div class="alert alert-danger" role="alert">
				<strong><%=request.getParameter("errore")%></strong>
			</div>
			<%
				} else if (request.getParameter("errore") != null && request.getParameter("errore").contains("Complimenti")){
			%>
			<%
				
			%>
			<div class="alert alert-success" role="alert">
				<strong><%=request.getParameter("errore")%></strong>
			</div>
			<%
				} else if(request.getParameter("errore") != null && request.getParameter("errore").contains("Ops")){
			%>
		
			<div class="alert alert-warning" role="alert">
				<strong><%=request.getParameter("errore")%></strong>
			</div>
			<% } %>

			<label>Email: <br><input type="text" value="" name="mail" id="mail" maxlength="30" style="" required/> <br />
<label>Password: <br><input type="password" value="" name="password" id="password" maxlength="20" required /><br/>

<input type="submit" class="btn btn " value="Login" name="loginSubmit" /></label>


</div>

<%@ include file="footer.jsp" %>


</body>
</html>