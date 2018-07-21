package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.message.EmptyMessageImpl;

import model.UtenteBean;
import model.UtenteDAO;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String error="";
	UtenteDAO dao = new UtenteDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
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
		// TODO Auto-generated method stub
		
		String mailUtente = request.getParameter("email");
		
		String dominio = mailUtente.substring(mailUtente.indexOf("@"), mailUtente.length());
		
		
		
		
		UtenteBean user = new UtenteBean();
		user.setCognome(request.getParameter("cognome"));
		user.setNome(request.getParameter("nome"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		
		if(dominio.equalsIgnoreCase("@eshoes.it"))
			user.setRuolo("Admin");
		else
			user.setRuolo("User");
		
		user.setNazione(request.getParameter("nazione"));
		user.setIndirizzo_nazione(request.getParameter("nazione"));
		user.setIndirizzo_provincia(request.getParameter("provincia"));
		user.setIndirizzo_citta(request.getParameter("citta"));
		user.setIndirizzo_via(request.getParameter("via"));
		user.setIndirizzo_cap(Integer.parseInt(request.getParameter("cap")));
		user.setIndirizzo_num_civico(Integer.parseInt(request.getParameter("civico")));
		
		
		System.out.println(user.toString());
		
		if(verifica(request.getParameter("email"))) {
			
			dao.doSave(user);
			String success = "Complimenti "+user.getNome()+", ti sei registrato con successo. Accedi per iniziare ad acquistare";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp?errore="+success);
			dispatcher.forward(request, response);
			
		}
		else
		{
			
			String success = "Ops, la mail da te inserita : "+mailUtente+" ci risulta gia registrata, prova ad accedere.";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp?errore="+success);
			dispatcher.forward(request, response);
			
		}
		
		
	}
	
	protected boolean verifica(String mail) {
		UtenteBean tmp = null;
		
		tmp = dao.doRetriveByEmail(mail);
		if(tmp==null) return true;
		else{
			error+= "Utente gia presente";
			
			return false;}
	}
	
	
}
