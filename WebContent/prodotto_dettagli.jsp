
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		
		<script src="themes/js/prodotto.js"></script>


<title> <%= request.getParameter("nome") %></title>
</head>
<body onload='returnProductByNomeAndModello("<%=request.getParameter("nome")%>","<%=request.getParameter("modello")%>")' >

<%@ include file="header.jsp" %> 

<% UtenteBean user =(UtenteBean) session.getAttribute("utente"); %>
 <% //if( user==null|| !user.getRuolo().equalsIgnoreCase("admin")){ response.sendRedirect("index.jsp");}  %>  


 



			<%
				if (request.getParameter("errore") != null && request.getParameter("errore").contains("Ops")) {
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
				} %>
				
<section class="main-content">				
				<div class="row reg">						
					<div class="span12">
						<div class="row">
							<div class="span6">
								<a  class="thumbnail" data-fancybox-group="group1" title="Description 1"><img id="img1" alt="" src=""></a>												
								<ul class="thumbnails small">								
									<li class="span1">
										<a  class="thumbnail" data-fancybox-group="group1" title="Description 2"><img id="img2" alt=""></a>
									</li>								
									<li class="span1">
										<a   class="thumbnail" data-fancybox-group="group1" title="Description 3"><img id="img3" alt=""></a>
									</li>													
								</ul>
							</div>
							<div class="span6">
									<strong >Marca </strong> <span id="marca"></span><br>
									<strong >Disponibilita':</strong> <span id="disponibilita"></span><br>								
									<h4><strong>Prezzo: </strong> <span id="prezzo"></span></h4>
									<strong>Taglia : 
											<select id="selectTaglie">
												
											</select>
									</strong>
							</div>
							<div class="span5">
								<form class="form-inline">
									<p>&nbsp;</p>
									<label>Qty:</label>
									<input type="text" class="span1" placeholder="1">
									<button class="btn btn-inverse" type="submit">Add to cart</button>
								</form>
							</div>							
						</div>
						<div class="row">
							<div class="span10">
								<ul class="nav nav-tabs" id="myTab">
									<li class="active"><a href="#home">Description</a></li>
									
								</ul>							 
								<div class="tab-content">
								<div class="tab-pane active" id="descrizione_lunga"></div>
								<table class="table table-striped shop_attributes">	
									<tbody>
									<tr class="">
										<th>Taglie </th>
										<td id="taglie"></td>
									</tr>		
									</tbody>
								</table>
									<div class="tab-pane active" id="">
									
									</div>
								</div>							
							</div>						
							
						</div>
					</div>
					
				</div>
</section>		


<%@ include file="footer.jsp" %> 


</body>
</html>