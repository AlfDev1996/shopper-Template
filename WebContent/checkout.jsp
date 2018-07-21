<%@page import="model.ProdottoBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check-Out</title>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prodotti</title>
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
<script  src="themes/js/validate.js"></script>

		<script>

function accesso(){
	var radios = document.getElementsByName('account');

	for (var i = 0, length = radios.length; i < length; i++)
	{
	 if (radios[i].checked)
	 {
	  // do whatever you want with the checked radio
	  if(radios[i].value=="register")
		  window.location.replace("login.jsp");
	  else
		  if(radios[i].value=="guest")
			  window.location.replace("registrazione.jsp");

	  
	  break;
	 }
	}
}

</script>


</head>
<body>	
<% UtenteBean user =(UtenteBean) session.getAttribute("utente"); %>
	
	
		<%@include file="header.jsp" %>
		
		<section class="main-content">
				<div class="row">
					<div class="span12">
						<div class="accordion" id="accordion2">
						<% if(user==null) {%>
							<div class="accordion-group">
								<div class="accordion-heading">
									<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">Checkout Options</a>
								</div>
								<div id="collapseOne" class="accordion-body in collapse">
									<div class="accordion-inner">
										<div class="row-fluid">
											<div class="span6">
												<h4>New Customer</h4>
												<p>By creating an account you will be able to shop faster, be up to date on an order's status, and keep track of the orders you have previously made.</p>
												<form action="#" method="post">
													<fieldset>
														<label class="radio" for="register">
															<input type="radio" name="account" id="registrato" value="register"  checked="checked">Utente Registrato
														</label>
														<label class="radio" for="guest">
															<input type="radio" name="account" value="guest" id="nonRegistrato">Utente non Registrato
														</label>
														<br>
														<button class="btn btn-inverse" data-toggle="collapse" data-parent="#collapse2" onclick="accesso()" >Continue</button>
													</fieldset>
												</form>
											 </div>
											 <div class="span6">
												<h4>Returning Customer</h4>
												<p>I am a returning customer</p>
												<form method="post" action="accedi"  onsubmit="return validateMail()">
													<fieldset>
														<div class="control-group">
															<label class="control-label">Email</label>
															<div class="controls">
																<input type="text" placeholder="Enter your username" type="text" value="" name="mail" id="mail" maxlength="30" required class="input-xlarge">
															</div>
														</div>
														<div class="control-group">
															<label class="control-label">Password</label>
															<div class="controls">
															<input type="password" placeholder="Enter your password" name="password" id="password" maxlength="20" required class="input-xlarge">
															</div>
														</div>
														<button class="btn btn-inverse">Continue</button>
													</fieldset>
												</form>
											</div>
										</div>
									</div>
								</div>
							</div>
							<% } else {%>
							<form method="POST" action="ServletOrdine">
							
							<div class="accordion-group">
								<div class="accordion-heading">
									<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">Account &amp; Billing Details</a>
								</div>
								<div id="collapseTwo" class="accordion-body collapse">
									<div class="accordion-inner">
										<div class="row-fluid">
											<div class="span6">
												<h4>Your Personal Details</h4>
												<div class="control-group">
													<label class="control-label">First Name</label>
													<div class="controls">
														<input type="text" placeholder="" value="<%=user.getNome()%>"class="input-xlarge">
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >Last Name</label>
													<div class="controls">
														<input type="text" placeholder="" value="<%=user.getCognome()%>" class="input-xlarge">
													</div>
												</div>					  
												<div class="control-group">
													<label class="control-label">Email Address</label>
													<div class="controls">
														<input type="text" value="<%=user.getEmail()%>" placeholder="" class="input-xlarge">
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Telephone</label>
													<div class="controls">
														<input type="text" placeholder=""  class="input-xlarge">
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Fax</label>
													<div class="controls">
														<input type="text" placeholder="" class="input-xlarge">
													</div>
												</div>
											</div>
											<div class="span6">
												<h4>Your Address</h4>
												<div class="control-group">
													<label class="control-label">Company</label>
													<div class="controls">
														<input type="text" placeholder="" class="input-xlarge">
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Company ID:</label>
													<div class="controls">
														<input type="text" placeholder="" class="input-xlarge">
													</div>
												</div>					  
												<div class="control-group">
													<label class="control-label"><span class="required">*</span> Via</label>
													<div class="controls">
														<input type="text" placeholder="" name="txtIndirizzoVia" value="<%=user.getIndirizzo_via()%>" class="input-xlarge">
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Civico</label>
													<div class="controls">
														<input type="text" placeholder=""  name="txtIndirizzoNumCivico" value="<%=user.getIndirizzo_num_civico()%>" class="input-xlarge">
													</div>
												</div>
												<div class="control-group">
													<label class="control-label"><span class="required">*</span> Città</label>
													<div class="controls">
														<input type="text" placeholder="" name="txtIndirizzoCitta" value="<%=user.getIndirizzo_citta()%>" class="input-xlarge">
													</div>
												</div>
												<div class="control-group">
													<label class="control-label"><span class="required">*</span> CAP</label>
													<div class="controls">
														<input type="text" placeholder="" name="txtIndirizzoCap" value="<%=user.getIndirizzo_cap()%>" class="input-xlarge">
													</div>
												</div>
												<div class="control-group">
													<label class="control-label"><span class="required">*</span> Nazione</label>
													<div class="controls">
													<input type="text" placeholder="" name="txtIndirizzoNazione" value="<%=user.getIndirizzo_nazione()%>" class="input-xlarge">
													</div>
												</div>
												
											</div>
										</div>
									</div>
								</div>
							</div>
							<%} %>
							<div class="accordion-group">
								<div class="accordion-heading">
									<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseThree">Confirm Order</a>
								</div>
								<div id="collapseThree" class="accordion-body collapse">
									<div class="accordion-inner">
										<div class="row-fluid">
											<div class="control-group">
												<table class="table table-striped" id="tableProdotti">
							<thead>
								<tr>
									<th>Immaginee</th>
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
									<!--  <td id="Alfonso"><input type="checkbox" name="prodotti[]" id='<%= prodotto.getId_prodotto()%>'></td> -->
									<% if( prodotto.getImmagini()!=null && prodotto.getImmagini().size()>0) { %>
									<td style="width:25%"><a href=""><img class="imgCarrello" alt="" src='themes/images/prodotti/' onerror='this.onerror=null;this.src="themes/images/defaultImages/<%=prodotto.getImmagini().get(0).getNomeFile() %>"'></a></td>
									<% } else {  %>
									<td style="width:10%"><a href=""><img alt="" src="themes/images/non-disponibile.png"></a></td>
									<% } %>
									<td><%= prodotto.getNome() %></td>
									<td><%= prodotto.getQuantita() %> </td>
									<td><%= prodotto.getTaglie() %></td>
									<td><%= prodotto.getPrezzo() %></td>
									<td><%= prodotto.getPrezzo()*prodotto.getQuantita() %></td>
								</tr>			  
							<%
							
									}
								}
								%>
								<tr>
								
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td><strong><%= carrello.getPrezzoTotale() %></strong></td>
								<%		} //End for %>
							
								
								</tr>		  
							</tbody>
						</table>
											</div>	
											<input type="submit" class="btn btn-inverse pull-right" value="Simula Pagamento"></button>	
			</form>							
			<form action="https://www.paypal.com/cgi-bin/webscr" method="post" >
			<div class=text-right>
			  <!-- Identify your business so that you can collect the payments. -->
			  <input type="hidden" name="business" value="raffaeledragonepay3@gmail.com">
			
			  <!-- Specify a Buy Now button. -->
			  <input type="hidden" name="cmd" value="_xclick">
			
			  <!-- Specify details about the item that buyers will purchase. -->
			  <input type="hidden" name="item_name" value="">
			  <input type="hidden" name="amount" value='<%=carrello.getPrezzoTotale() %>'>
			  <input type="hidden" name="currency_code" value="EUR">
			
			  <!-- Display the payment button. -->
			  <input class="pull-right" type="image" name="submit" border="0"
			  src="https://www.paypalobjects.com/en_US/i/btn/btn_buynow_LG.gif"
			  alt="Buy Now">
			  <img alt="" border="0" width="1" height="1"
			  src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" >
			</div>
			</form>	
											<!--  <input type="submit" class="btn btn-inverse pull-right">Confirm order</button> -->
										</div>
									</div>
								</div>
							</div>
							
						</div>				
					</div>
				</div>
			</section>
		
		
		
		<%@include file="footer.jsp" %>
		

    </body>
</html>