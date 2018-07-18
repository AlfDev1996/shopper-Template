package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession currentSession = request.getSession();
		CartBean carrello = (CartBean) currentSession.getAttribute("carrello");
		UtenteBean utente = (UtenteBean) currentSession.getAttribute("utente");
		String error ="";
		if(carrello!=null && carrello.getProdotti()!=null && carrello.getProdotti().size()>0)
			if ( utente!=null && utente.getId_utente()!=0 && utente.getEmail()!=null)
			{
				String indirizzoVia = request.getParameter("indirizzoVia")!= null ? request.getParameter("indirizzoVia") : "";
				String indirizzoNumCivico = request.getParameter("indirizzoNumCivico")!= null ? request.getParameter("indirizzoNumCivico") : "";
				String indirizzoCitta = request.getParameter("indirizzoCitta")!= null ? request.getParameter("indirizzoCitta") : "";
				String indirizzoCap = request.getParameter("indirizzoCap")!= null ? request.getParameter("indirizzoCap") : "";
				String indirizzoNazione = request.getParameter("indirizzoNazione")!= null ? request.getParameter("indirizzoNazione") : "";
				
				OrdineBean ordine= new OrdineBean();
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
						voceOrdineDao.doSave(voceOrdine);
						ordine.getVoceOrdine().add(voceOrdine);
					}
				}
				
			}
			else
				error+="errore utente ";
		else
			error+="Non ci sono articoli nel carrello";
	}

}
