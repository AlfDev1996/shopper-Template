package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import model.UtenteBean;
import model.UtenteDAO;

/**
 * Servlet implementation class accedi
 */
@WebServlet("/accedi")
public class accedi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public accedi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// Recupero il bean della sessione <---- verificare se è nullo quando arriva dalla jsp
		String error = "";
		UtenteBean utenteSession  = (UtenteBean) request.getSession().getAttribute("utente");

	
	if(utenteSession==null) {
	// ottengo i parametri inseriti nel form di login
	String emailParameter = request.getParameter("mail");
	System.out.println(emailParameter);
	
	String passwordParameter = request.getParameter("password");
	System.out.println(passwordParameter);
	
	// stringa per l'errore
	
	
	// dichiaro un bean per il DB
	UtenteBean utenteBean =null;
	utenteSession= new UtenteBean();
	// oggetto DAO
	UtenteDAO utenteDAO = new UtenteDAO();

	utenteBean = utenteDAO.doRetriveByEmailAndPassword(emailParameter, passwordParameter);
	//System.out.println(utenteBean.getNome());
	
	/**
	 *  verifica se l'utente con email = emailParameter
	 *  
	 *  Se l'utente esiste e la psw è corretta si completa il bean di tutti i dati dell'utente
	 *  e si effettua una forward alla home
	 *  
	 *  Se l'utente non esiste o la psw non è corretta si effettua una forward con 
	 *  l'errore relativo
	 */
//	if (utenteDb==null) 
//		//l'utente non esiste
//		error += "Non sei ancora iscritto, compila i campi che trovi in questa pagina per iniziare subito ad acquistare";
//	else{
//		if(!utenteDb.getPassword().equals(passwordParameter))
//			error += "Password errata";
//		else {
//			//i dati iseriti nel form sono corretti
//			utenteSession.setMail(utenteDb.getMail());
//			utenteSession.setNome(utenteDb.getNome());
//			utenteSession.setCognome(utenteDb.getCognome());
//			utenteSession.setPassword(utenteDb.getPassword());
//			utenteSession.setTipo(utenteDb.getTipo());
//			utenteSession.setPaese(utenteDb.getPaese());
//			utenteSession.setProvincia(utenteDb.getProvincia());
//			utenteSession.setCitta(utenteDb.getCitta());
//			utenteSession.setVia(utenteDb.getVia());
//			utenteSession.setCap(utenteDb.getCap());
//			utenteSession.setCivico(utenteDb.getCivico());
//			utenteSession.setNumerCarta(utenteDb.getNumerCarta());
//			// il bean di sessione ha tutti i dati dell'utente
//			}
//		}
	
	if(utenteBean == null){
		System.out.println("Problemi al DB");
		error+="Utente Non trovato";
	}else if (utenteBean.getEmail() == null) {
		error += "Non sei ancora iscritto, compila i campi che trovi in questa pagina per iniziare subito ad acquistare";
	}else {
		if (!utenteBean.getPassword().equals(passwordParameter)) {
			error += "Password errata";
		}
	}
	
	// se non ci sono stati errori -> forward alla home del sito
	//se ci sono stati errori -> forward alla pagina di login/registrazione del sito
	
	System.out.println(error+"ss");
	if (error.equals("")) {
		
		HttpSession session = request.getSession();
		session.setAttribute("utente", utenteBean);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	} else 
		
	{
		// ci sono stati errori durante il login
		// l'errore è accodato all'url come query  
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp?errore="+error);
		dispatcher.forward(request, response);
	}
	}
	else
	{
		System.out.println("sono entrato con la session");
	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
	dispatcher.forward(request, response);}
}
	

}
