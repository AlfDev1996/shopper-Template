package controller;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import model.CartBean;
import model.ProdottoBean;
import model.ProdottoDAO;
import model.UtenteBean;

/**
 * Servlet implementation class ServletCart
 */
@WebServlet("/ServletCart")
public class ServletCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProdottoBean prodotto = new ProdottoBean();
		String error="";
		String operazione= request.getParameter("operazione");
		if(operazione.equalsIgnoreCase("aggiungi"))
		{
			if(request.getParameter("prodotto")!=null && request.getParameter("prodotto")!="")
			{
				JSONParser parser = new JSONParser();
				try {
					JSONObject prodottoJson= (JSONObject) parser.parse(request.getParameter("prodotto"));
					
					if(prodottoJson!=null)
					{
						ProdottoDAO pdao=new ProdottoDAO();
						prodotto = pdao.doRetriveByNomeAndModello(prodottoJson.get("nome")+"", prodottoJson.get("modello")+"");
						if(prodotto!=null)
						{
							prodotto.setTaglie(prodottoJson.get("taglia")+"");
							prodotto.setQuantita( Integer.parseInt(prodottoJson.get("quantita")+""));
							//Lo aggiungo al carrello
							HttpSession currentSession = request.getSession();
							CartBean carrello = new CartBean();
							UtenteBean utente = (UtenteBean) currentSession.getAttribute("utente");
							if(utente!=null)
								carrello.setUtente(utente);
							
							if(currentSession.getAttribute("carrello")!=null )
							{
								//Prendo il carrello dalla sessione
								carrello = (CartBean) currentSession.getAttribute("carrello");
								//Aggiungo il prodotto al carrello
								carrello.addProdotto(prodotto);
								//Aggiorno il carrello della sessione
								currentSession.setAttribute("carrello", carrello);
							}else
							{
								//Se il carrello non c'è nella sessione
								//Aggiungo il prodotto al carrello
								carrello.addProdotto(prodotto);
								//Aggiungo il carrello nella sessione
								currentSession.setAttribute("carrello", carrello);
							}
						}
					}
					
					error="Prodotto Aggiunto con successo";
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					error="Impossibile aggiungere il prodotto";
				}
				 
			}
		}
		else
		 if(operazione.equalsIgnoreCase("elimina"))
		 {
			 if(request.getParameter("prodottiDaRimuovere")!= null && !request.getParameter("prodottiDaRimuovere").equals(""))
			 {
			  String appId= request.getParameter("prodottiDaRimuovere");
			  String[] ids = appId.split(",");
			  
			  HttpSession currentSession = request.getSession();
				CartBean carrello = new CartBean();
				
				if(currentSession.getAttribute("carrello")!=null )
				 carrello = (CartBean) currentSession.getAttribute("carrello");
				if(ids.length>0)
					carrello.rimuoviProdotti(ids);
				
			 }
		 }
		 else
		  if(operazione.equals("modifica"))
		  {
			  if(request.getParameter("arrayProdottiModificaJson")!= null && !request.getParameter("arrayProdottiModificaJson").equals(""))
				 {
				  JSONParser parser = new JSONParser();
				  try {
					HttpSession currentSession = request.getSession();
					CartBean carrello = new CartBean();
						
					if(currentSession.getAttribute("carrello")!=null )
					{
						carrello = (CartBean) currentSession.getAttribute("carrello");
						
						JSONArray prodottiJson= (JSONArray) parser.parse(request.getParameter("arrayProdottiModificaJson"));
						for(Object o: prodottiJson){
						    if ( o instanceof JSONObject ) {
						        JSONObject obj = ((JSONObject)o);
						        
						        carrello.updateProdotto((obj.get("id")+""), Integer.parseInt(obj.get("quantita")+""));
						    }
						}	
					}
					
				  } catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				  
				 }   
		  }
		
		
		response.getWriter().append(error+"");
		request.setAttribute("error", error);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		
	}

}
