package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.CartBean;
import model.OrdineBean;
import model.OrdineDAO;
import model.ProdottoBean;
import model.UtenteBean;
import model.VoceOrdineBean;
import model.VoceOrdineDAO;

/**
 * Servlet implementation class ServletOrdine
 */
@WebServlet("/ServletOrdine")
public class ServletOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletOrdine() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession currentSession = request.getSession();
		UtenteBean utente = (UtenteBean) currentSession.getAttribute("utente");
		String error ="";
		String operazione = request.getParameter("operazione");
		if(operazione!=null && operazione.equalsIgnoreCase("getOrdiniUtente"))
		{
			if(utente!=null && utente.getId_utente()!=0)
			{
				ArrayList<OrdineBean> ordini = new ArrayList<>();
				OrdineDAO ordineDao=new OrdineDAO();
				ordini = ordineDao.doRetrieveByUtente(utente);
				
				request.setAttribute("ordini", ordini);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/lista_ordini.jsp?error="+error);
				dispatcher.forward(request, response);
			}
			else
				{
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
				}
		}
		else
			if(operazione!=null && operazione.equalsIgnoreCase("getAll"))
			{
				if(utente!=null && utente.getId_utente()!=0)
				{
					ArrayList<OrdineBean> ordini = new ArrayList<>();
					OrdineDAO ordineDao=new OrdineDAO();
					ordini = ordineDao.doRetrieveAll(null);
				//	ordini = ordineDao.doRetrieveByUtente(utente);
					
					request.setAttribute("ordini", ordini);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/lista_ordini.jsp?error="+error);
					dispatcher.forward(request, response);
				}
				else
					{
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
					}
			}
	    else
		if(operazione!=null && operazione.equalsIgnoreCase("getOrdine"))
		{
			String id_ordine = request.getParameter("id_ordine");
			System.out.println(id_ordine+"<----");
			int id= Integer.parseInt(id_ordine);
			OrdineDAO ordineDao=new OrdineDAO();
			OrdineBean ordine = ordineDao.doRetriveByKey(id);
			
			if(ordine!=null)
			{
					ordine.inizializzaVociOrdine();
				request.setAttribute("ordine", ordine);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/riepilogo_ordine.jsp");
				dispatcher.forward(request, response);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession currentSession = request.getSession();
		UtenteBean utente = (UtenteBean) currentSession.getAttribute("utente");
		String error ="";
		
			
		
		CartBean carrello = (CartBean) currentSession.getAttribute("carrello");
		
		OrdineBean ordine= new OrdineBean();
		
		if(carrello!=null && carrello.getProdotti()!=null && carrello.getProdotti().size()>0) {
			if ( utente!=null && utente.getId_utente()!=0 && utente.getEmail()!=null)
			{
				String indirizzoVia = request.getParameter("txtIndirizzoVia")!= null ? request.getParameter("txtIndirizzoVia") : "";
				String indirizzoNumCivico = request.getParameter("txtIndirizzoNumCivico")!= null ? request.getParameter("txtIndirizzoNumCivico") : "";
				String indirizzoCitta = request.getParameter("txtIndirizzoCitta")!= null ? request.getParameter("txtIndirizzoCitta") : "";
				String indirizzoCap = request.getParameter("txtIndirizzoCap")!= null ? request.getParameter("txtIndirizzoCap") : "";
				String indirizzoNazione = request.getParameter("txtIndirizzoNazione")!= null ? request.getParameter("txtIndirizzoNazione") : "";
				
				
				Calendar currenttime = Calendar.getInstance();
			    Date sqldate = new Date((currenttime.getTime()).getTime());
				ordine.setDataCreazione(sqldate);
				ordine.setStato("Creato");
				ordine.setTotale(Float.parseFloat(carrello.getPrezzoTotale()+""));
				ordine.setUtente(utente);
				String indirizzo = indirizzoVia + " " +indirizzoNumCivico + " " + indirizzoCitta + " " + indirizzoCap + " , " + indirizzoNazione.toUpperCase() + " "; 
				ordine.setIndirizzo(indirizzo);
				
				OrdineDAO ordineDao=new OrdineDAO();
				int id_ordine = ordineDao.doSave(ordine);
				if(id_ordine!=-1)
				{
					ordine.setIdOrdine(id_ordine);
					ArrayList<VoceOrdineBean> vociOrdine = new ArrayList<>();
					VoceOrdineDAO voceOrdineDao=new VoceOrdineDAO();
					for (ProdottoBean prodotto : carrello.getProdotti()) {
						VoceOrdineBean voceOrdine = new VoceOrdineBean();
						voceOrdine.setOrdine(ordine);
						voceOrdine.setProdotto(prodotto);
						voceOrdine.setPrezzo_unitario(prodotto.getPrezzo());
						voceOrdine.setPrezzo_totale(prodotto.getPrezzo()*prodotto.getQuantita());
						voceOrdine.setQuantita(prodotto.getQuantita());
						voceOrdine.setTagliaOrdinata(prodotto.getTaglie());
						voceOrdineDao.doSave(voceOrdine);
						ordine.getVoceOrdine().add(voceOrdine);
						error="Grazie, il tuo ordine e' stato ricevuto ed e' in fase di lavorazione";
					}
					
					
				}
				
			}
			else
				error+="errore utente ";
		}
		else
			error+="Non ci sono articoli nel carrello";
		if(carrello!=null && carrello.getProdotti()!=null && carrello.getProdotti().size()>0){
			carrello.getProdotti().clear();
		
		request.setAttribute("ordine", ordine);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/riepilogo_ordine.jsp?error="+error);
		dispatcher.forward(request, response);
	}
		}
	
}
