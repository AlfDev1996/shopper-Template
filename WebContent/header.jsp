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

<script>
function submit(){
	   document.getElementById("myForm").submit();
	}
</script>



</head>
<body>
	<%UtenteBean us=(UtenteBean)session.getAttribute("utente");%>
	<div id="top-bar" class="container">
		<!--Contrainer superiore   -->
		<div class="row">
			<!-- Crea una riga in alto con bootstrap -->
			<div class="span4">
				<!-- classe span 4 predefinita bootstrap, le colonne possono essere max 12, quindi qui ne abbiamo una da 4 e quindi ne restano 8 -->
				<form method="POST" class="search_form">
					<!-- Form per la ricerca   -->
					<input type="text" class="input-block-level search-query"
						Placeholder="eg. T-sirt">
				</form>
			</div>
			<div class="span8">
				<!-- Colonna da 8 mancante -->
				<div class="account pull-right">
					<!-- Div che contiene il menu  -->
					<ul class="user-menu">

						<%		
								if(us!=null){
							%>

						<li><a href="myAccount.jsp">My Account</a></li>
						<li><a href="cart.html">Your Cart</a></li>
						<li><a href="checkout.html">Checkout</a></li>


						<li><a href="#">Ciao, <%= us.getNome() %></a></li>
						
						
						<%if(us.getRuolo().equalsIgnoreCase("admin")){ %>
							<nav id="menu" class="pull-right">
								<ul>
								<li>Funzioni Admin</a>
								<ul>
								<li><a href="modificaArticolo.jsp">Modifica Articolo</a></li>
								<li><a href="AggiungiArticolo.jsp">Aggiungi Articolo</a></li>
								</ul></li>
								</ul>
						
						<%} %>
						
						
						<span style="float: right;">
							<form method="post" action="logout" id="myForm">
								<li onclick="submit();"><a href="#">Logout</a></li>
							</form>
						</span>
						<%	}else {%>
						<li><a href="login.jsp">My Account</a></li>
						<li><a href="cart.html">Your Cart</a></li>
						<li><a href="checkout.html">Checkout</a></li>
						<li><a href="login.jsp">Login</a></li>
						<li><a href="registrazione.jsp">Registrati</a></li>

						<%} %>
					</ul>
				</div>
			</div>
		</div>
	</div>





	<div id="wrapper" class="container">
		<!-- Qui inizia il menu principale  -->
		<section class="navbar main-menu"> <!--  Inizio section menu -->
		<div class="navbar-inner main-menu">
			<a href="index.jsp" class="logo pull-left"><img src="themes/images/logo.PNG" alt=""
				style="width: 150px; height: 45px;"></a>
			<!-- logo del sito  con posizionamento a sinistra tramite la classe pull-left -->
			<nav id="menu" class="pull-right"> <!-- Il menu invece si posiziona a destra -->
			<ul>
				<li><a href="./prodotti.jsp?filtro=sesso_F">Woman</a> <!-- categoria menu  --></li>
				<li><a href="./prodotti.jsp?filtro=sesso_M">Man</a></li>
				<li><a href="#">Brand</a>
					<ul>
						<li><a href="./prodotti.jsp?filtro=marca_Adidas">Adidas</a>
						<li><a href="./prodotti.jsp?filtro=marca_Nike">Nike</a>
					</ul></li>
				<li><a href="./products.html">Piu' Venduti</a></li>
			</ul>
			</nav>
		</div>
		</section>
</body>
</html>