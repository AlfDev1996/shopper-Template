<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ProdottoBean"%>
<%@page import="model.CartBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Carrello</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
		<!-- bootstrap -->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">       
		<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">		
		<link href="themes/css/bootstrappage.css" rel="stylesheet"/>
		
		<!-- global styles -->
		<link href="themes/css/flexslider.css" rel="stylesheet"/> 
		<link href="themes/css/main.css" rel="stylesheet"/>

		<!-- scripts -->
		<script src="themes/js/jquery-1.7.2.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>				
		<script src="themes/js/superfish.js"></script>	
		<script src="themes/js/jquery.scrolltotop.js"></script>
	
		<script src="themes/js/prodotto.js"></script>
</head>
<body>
<body>		

<%@ include file="header.jsp" %> 

		
		
			</section>				
			<section class="header_text sub">
			<img class="pageBanner" src="themes/images/pageBanner.png" alt="New products" >
				<h4><span>Shopping Cart</span></h4>
			</section>
			<section class="main-content">				
				<div class="row">
					<div class="span12">					
						<h4 class="title"><span class="text"><strong>Your</strong> Cart</span></h4>
						<div class="table-responsive">
						<table class="table table-striped" id="tableProdotti">
							<thead>
								<tr>
									<th>Remove</th>
									<th>Immagine</th>
									<th>Nome Prodotto</th>
									<th>Quantita'</th>
									<th>Taglia</th>
									<th>Prezzo Unitario</th>
									<th>Prezzo Totale</th>
								</tr>
							</thead>
							<tbody id="listProdotti">
							<% CartBean carrello = (CartBean) session.getAttribute("carrello"); 
							if(carrello!=null)
							{
								if(carrello.getProdotti()!=null && carrello.getProdotti().size()>0)
								{
									for(ProdottoBean prodotto : carrello.getProdotti())
									{
										
							%>
								<tr id="prod<%=prodotto.getNome()%>">
									<td id="Alfonso"><input type="checkbox" name="prodotti[]" id='<%= prodotto.getId_prodotto()%>'></td>
									<% if( prodotto.getImmagini()!=null && prodotto.getImmagini().size()>0) { %>
									<td style="width:25%"><a href=""><img class="imgCarrello" alt="" src='themes/images/prodotti/<%=prodotto.getImmagini().get(0).getNomeFile() %>'></a></td>
									<% } else {  %>
									<td><a href=""><img alt="" src="themes/images/non-disponibile.png"></a></td>
									<% } %>
									<td><%= prodotto.getNome() %></td>
									<td><input type="number" min="1" class="input-mini" name="qtaProdotti[]" id="<%=prodotto.getId_prodotto() %>_<%=prodotto.getTaglie() %>" value="<%= prodotto.getQuantita() %>"> </td>
									<td><%= prodotto.getTaglie() %></td>
									<td><%= prodotto.getPrezzo() %></td>
									<td><%= prodotto.getPrezzo()*prodotto.getQuantita() %></td>
								</tr>			  
							<%
							
									}
								
								%>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td><strong><%= carrello.getPrezzoTotale() %></strong></td>
								</tr>
								<%
									
								}
										} //End for %>
							
										  
							</tbody>
						</table>
						</div>
						<hr>
						
						<% if(carrello!=null && carrello.getProdotti()!=null && carrello.getProdotti().size()>0) {
							DecimalFormat twoDForm = new DecimalFormat("#.##");
							
							
        					%>
							<p class="cart-total right">
							<strong>Sub-Totale</strong>:<%=Math.floor((carrello.getPrezzoTotale()/1.22) * 100.0) / 100.0%><br>
							<strong>Iva (22%)</strong>: <%=Math.floor((carrello.getPrezzoTotale() - (carrello.getPrezzoTotale()/1.22)) * 100.0) / 100.0%><br>
							<strong>Totale</strong>: <%=Math.floor((carrello.getPrezzoTotale()) * 100.0) / 100.0 %><br>
							</p>
						<hr/>
						<% } %> 
						
						<p class="buttons center">		
								
							<button class="btn" type="button" onclick="updateProductsFromCart()">Update</button>
							<a href="checkout.jsp"> <button class="btn btn-inverse" id="checkout">Checkout</button> </a>
						
						</p>					
					</div>
					
				</div>
			</section>		
						
			<section id="footer-bar">
				<div class="row">
					<div class="span3">
						<h4>Navigation</h4>
						<ul class="nav">
							<li><a href="./index.html">Homepage</a></li>  
							<li><a href="./about.html">About Us</a></li>
							<li><a href="./contact.html">Contac Us</a></li>
							<li><a href="./cart.html">Your Cart</a></li>
							<li><a href="./register.html">Login</a></li>							
						</ul>					
					</div>
					<div class="span4">
						<h4>My Account</h4>
						<ul class="nav">
							<li><a href="#">My Account</a></li>
							<li><a href="#">Order History</a></li>
							<li><a href="#">Wish List</a></li>
							<li><a href="#">Newsletter</a></li>
						</ul>
					</div>
					<div class="span5">
						<p class="logo"><img src="themes/images/logo.png" class="site_logo" alt=""></p>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. the  Lorem Ipsum has been the industry's standard dummy text ever since the you.</p>
						<br/>
						<span class="social_icons">
							<a class="facebook" href="#">Facebook</a>
							<a class="twitter" href="#">Twitter</a>
							<a class="skype" href="#">Skype</a>
							<a class="vimeo" href="#">Vimeo</a>
						</span>
					</div>					
				</div>	
			</section>
			<section id="copyright">
				<span>Copyright 2013 bootstrappage template  All right reserved.</span>
			</section>
		</div>
		<script src="themes/js/common.js"></script>
		<script>
			$(document).ready(function() {
				$('#checkout').click(function (e) {
					document.location.href = "checkout.html";
				})
			});
		</script>		
   
<%@ include file="footer.jsp" %> 

</body>
</html>