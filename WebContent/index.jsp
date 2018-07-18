<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<head>
		<meta charset="utf-8">
		<title>e-shoes</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">  <!-- ViewPort Ã¨ l'aria visibile dall'utente, con questo meta tag introdotto in HTML5 diamo istruzioni al browser sul ridimensionamento  -->
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
		<script >
		
		$(document).ready(function(){
			//var quote = document.createElement("blockquote");
			 var txt2 = $("<blockquote></blockquote>").text("«Vediamo i nostri clienti come ospiti invitati a una festa, e noi siamo i padroni di casa. Il nostro lavoro è migliorare ogni giorno ogni aspetto della customer experience»");
		
			 
			 $("#motto").append(txt2);
			$("#motto").addClass("header_text");
			$("#motto").css("font-family","italic");
			
		});
		</script>
		
	</head>
    <body>	
    	<%@ include file="header.jsp" %>  
			
			<!-- Inizio section slider -->
			<section  class="homepage-slider" id="home-slider">  <!--  Slider, immagini che scorrono sulla home page, tutto gia implementato libreria indicata nell'header -->
				<div class="flexslider">
					<ul class="slides">
						<li>
							<img src="themes/images/carousel/banner-1.jpg" alt="" />
							<div class="intro">   <!-- Eventuale testo che va inserito sopra le immagini dello slider -->
								<h1>Esplora il </h1>
								<p><span>passato,presente e futuro</span></p>
								<p><span>della tecnologia delle scarpe con stile classico o sportivo</span></p>
							</div>
						</li>
						<li>
							<img src="themes/images/carousel/banner-2.jpg" alt="" />
							<div class="intro">   <!-- Eventuale testo che va inserito sopra le immagini dello slider -->
								<h1>Scopri</h1>
								<p><span>Tutta la collezione</span></p>
								<p><span>ADIDAS</span></p>
							</div>
						</li>
					
					<li>
							<img src="themes/images/carousel/banner-3.jpg" alt="" />
							<div class="intro">   <!-- Eventuale testo che va inserito sopra le immagini dello slider -->
								<h1>Vans Prototype </h1>
								<p><span>Tested</span></p>
								<p><span>Approved</span></p>
							</div>
						</li>
						</ul>
				</div>			
			</section>
			
			
			<!-- Fine section Slider -->
			
			<!-- testo principale della pagina , tipicamente riservato per il motto  -->
			<section  id="motto">
				
			</section>
			
			
			<!--Fine sezione testo  -->
			
						<!-- Inizio spazio dedicato ai servizi -->
						<div class="row feature_box">						
							<div class="span4">
								<div class="service">
									<div class="responsive">	
										<img src="themes/images/feature_img_2.png" alt="" />
										<h4>MODERN <strong>DESIGN</strong></h4>
										<p>Lorem Ipsum is simply dummy text of the printing and printing industry unknown printer.</p>									
									</div>
								</div>
							</div>
							<div class="span4">	
								<div class="service">
									<div class="customize">			
										<img src="themes/images/feature_img_1.png" alt="" />
										<h4>FREE <strong>SHIPPING</strong></h4>
										<p>Lorem Ipsum is simply dummy text of the printing and printing industry unknown printer.</p>
									</div>
								</div>
							</div>
							<div class="span4">
								<div class="service">
									<div class="support">	
										<img src="themes/images/feature_img_3.png" alt="" />
										<a href="javascript:;"
  										onClick="window.open('contact.jsp', 'titolo', 'width=850, height=625, resizable, status, scrollbars=1, location');">
										<h4>24/7 LIVE<strong>SUPPORT</strong></h4></a> 
										<p>Lorem Ipsum is simply dummy text of the printing and printing industry unknown printer.</p>
									</div>
								</div>
							</div>
							
							
								
						</div>
						
					<hr size="5" style="background-color:black;">	
						
			<section class="our_client">
				<h4 class="title"><span class="text">Manufactures</span></h4>
				<div class="row">					
					<div class="span2">
						<a href="#"><img alt="" src="themes/images/clients/logo-1.png"></a>
					</div>
					<div class="span2">
						<a href="#"><img alt="" src="themes/images/clients/logo-2.png"></a>
					</div>
					<div class="span2">
						<a href="#"><img alt="" src="themes/images/clients/logo-3.png"></a>
					</div>
					<div class="span2">
						<a href="#"><img alt="" src="themes/images/clients/logo-4.png"></a>
					</div>
					<div class="span2">
						<a href="#"><img alt="" src="themes/images/clients/logo-5.png"></a>
					</div>
					<div class="span2">
						<a href="#"><img alt="" src="themes/images/clients/logo-6.png"></a>
					</div>
					
				</div>
			</section>	
	
			<%@ include file="footer.jsp"%>	
					</div>	
								
				</div>
				<!-- Fine spazio dedicato ai prodotti "latest" -->
				
			</section>
			<!-- Fine main content -->
			
			
			<!-- Inizio area clienti  -->
				
			<!-- Fine area clienti -->
			
			
			<!-- Inizio footer che dovremmo generare dinamicamente -->
		
			
	
			<!-- Fine footer -->
		
		
		
		<script src="themes/js/common.js"></script>
		<script src="themes/js/jquery.flexslider-min.js"></script>
		<script type="text/javascript">
		//Funzione jquery che permette alle immagini dello slider di muoversi e scorrere.
			$(function() {
				$(document).ready(function() {
					$('.flexslider').flexslider({
						animation: "fade",
						slideshowSpeed: 4000,
						animationSpeed: 600,
						controlNav: false,  //Abilitandolo abiliti il controllo con il mouse
						directionNav: true,
						controlsContainer: ".flex-container" // container dello slider
					});
				});
			});
		</script>
    </body>
</html>