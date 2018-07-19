<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<%@page import="model.VoceOrdineBean"%>
<%@page import="model.OrdineBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<% String error = request.getParameter("error");
	   if(error==null)
		   error="";
	%>
	
	<% OrdineBean ordine = (OrdineBean) request.getAttribute("ordine"); 
	   if(ordine!=null)
	   {
		if(!error.equals("")) {
	%>
	<div class="alert alert-success">
 		 <strong><%= error %></strong> 
	</div>
	<% } %>
	<div class="row">
	
	<div class="span6">
	<table class="table borderless">
  <tbody>
    <tr>
      <th scope="row">Num. Ordine</th>
      <td><%= ordine.getIdOrdine() %></td>
    </tr>
    <tr>
      <th scope="row">Stato</th>
      <td><%= ordine.getStato() %></td>
    </tr>
    <tr>
      <th scope="row">Totale Pezzi </th>
      <td> <%= ordine.getQuantitaTotale() %> </td>
    </tr>
    <tr>
      <th scope="row">Totale </th>
      <td> <%= ordine.getTotale() %> </td>
    </tr>
  </tbody>
</table>
	
	</div>
	<div class="span6">
	 <div class="table-responsive">
		<table class="table borderless">
  <tbody>
  	<tr>
      <th scope="row">Data Ordine</th>
      <td><%= ordine.getDataCreazione() %></td>
    </tr>
    <tr>
      <th scope="row">Indirizzo</th>
      <td><%= ordine.getIndirizzo() %></td>
    </tr>
    
    <tr>
      <th scope="row">Corriere </th>
      <td> DHL </td>
    </tr>
  </tbody>
</table>
</div>
	</div>
	<br>
	<br>
	<br>
	</div>
	<!--  Articoli -->
	
	<div class="row">
	
	<div class="span12">
	<table class="table table-hover">
  <tbody>
  <thead>
  <tr>
  	<th>Prodotto </th>
  	<th>Modello </th>
  	<th>Marca </th>
  	<th>Taglia </th>
  	<th>Pr. Unitario </th>
  	<th>Quantita </th>
  </tr>
  </thead>
  <% 	if(ordine!=null)
  		for(VoceOrdineBean v : ordine.getVoceOrdine()){  %>
    <tr> 
	<td><%= v.getProdotto().getNome() %>  </td>
		<td> <%= v.getProdotto().getModello() %></td>
		<td> <%= v.getProdotto().getMarca().getNome() %></td>
		<td> <%= v.getTagliaOrdinata() %></td>
		<td> <%= v.getPrezzo_unitario() %> </td>
		<td> <%= v.getQuantita() %></td>
	 </tr>
    <%} %>
  	
  </tbody>
</table>
	
	</div>
	
	</div>
	
	<% }  else { 
		 String redirectURL = "index.jsp";
		    response.sendRedirect(redirectURL);
	}%>
	
	
	


</body>
</html>