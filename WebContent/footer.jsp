<%@page import="model.UtenteBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta charset="utf-8">
		<title>e-shoes</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">  <!-- ViewPort è l'aria visibile dall'utente, con questo meta tag introdotto in HTML5 diamo istruzioni al browser sul ridimensionamento  -->
		<meta name="description" content="">
		<!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
		<!-- bootstrap -->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">    <!-- Libreria bootsrao (min) utilizzata dagli sviluppatori durante la fase di debugging o di sito in lavorazione, possiede meno proprieta del bootstrap.css  -->   
		<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet"> <!-- Libreria responsive -->
		
		<link href="themes/css/bootstrappage.css" rel="stylesheet"/>
		
		<!-- global styles -->
		<link href="themes/css/flexslider.css" rel="stylesheet"/> <!-- classe jquery per realizzare immagini, gallerie di immagini responsive !! -->
		<link href="themes/css/main.css" rel="stylesheet"/>

		<!-- scripts -->
		  <script src="themes/js/jquery-1.7.2.min.js"></script> <!-- Libreeia jQuery: preferisco linkarla da google -->
		<script src="bootstrap/js/bootstrap.min.js"></script> <!-- Funzioni bootstrap per eventuali animazioni  -->				
		<script src="themes/js/superfish.js"></script> 	<!-- Classe javascript per implementare il menu, sembra non utilizzata da una prima analisi -->
		<script src="themes/js/jquery.scrolltotop.js"></script> <!-- http://www.dynamicdrive.com libreria di questo sito che permette di swipppare al top della pagina o in una posizione precisa -->
		<!--[if lt IE 9]>			
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
			<script src="js/respond.min.js"></script>
		<![endif]-->

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>

<section id="footer-bar">
				<div class="row">
					<div class="span3">
						<h4>Navigation</h4>
						<ul class="nav">
							<li><a href="index.jsp">Homepage</a></li>  
							<li><a href="contact.jsp">Contattaci</a></li>
							<li><a href="cart.jsp">Carrello</a></li>
							<li><a href="login.jsp">Login</a></li>							
						</ul>					
					</div>
					<div class="span4">
						<h4>Account</h4>
						<ul class="nav">
						<% if(us!=null) { %>
							<li><a href="myAccount.jsp">Il mio account</a></li>
							<% if(us.getRuolo().equalsIgnoreCase("admin")) { %>
							<li><a href="ServletOrdine?operazione=getAll">Ordini</a></li>
							<% }else { %>
							<li><a href="ServletOrdine?operazione=getOrdiniUtente">ordini</a></li>
							<% } %>
						<% } else { %> 
							<li><a href="login.jsp">Il mio account</a></li>
							<li><a href="login.jsp">I miei ordini</a></li>
						<% } %>
						</ul>
					</div>
					<div class="span5">
						<p class="logo"><img src="themes/images/logo.PNG" class="site_logo" alt=""></p>
						<p>
						<strong> e-shoes e' più social che mai, seguici !</strong><br>
						<span id="social">
						<a href="http://www.facebook.com" target="_blank" class="fa fa-facebook"></a>
						<a href="http://www.twitter.com" target="_blank" class="fa fa-twitter"></a>
						<a href="http://www.youtube.com" target="_blank" class="fa fa-youtube"></a>
						<a href="http://www.instagram.com" target="_blank" class="fa fa-instagram"></a>
						<a href="http://www.linkedin.com" target="_blank" class="fa fa-linkedin"></a>
						</span>
						
						
						</p>
						<br/>
						
					</div>					
				</div>	
			</section>
			<section id="copyright">
				<span>Copyright 2018 Rianna Alfonso && Raffaele Dragone. Tutti i diritti riservati.</span>
			</section>
		</div>

</body>
</html>