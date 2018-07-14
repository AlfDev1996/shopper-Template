package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import model.ProdottoBean;
import model.ProdottoDAO;
import model.UtenteBean;
import model.UtenteDAO;

/**
 * Servlet implementation class ServletUtente
 */
@WebServlet("/ServletUtente")
public class ServletUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String operation = request.getParameter("operation");
		
		String mail= request.getParameter("email");
		if(mail != null) {
			HttpSession session = request.getSession();
			session.setAttribute("mailUser", mail);
		}
		
		if(operation.equalsIgnoreCase("load")) {
		System.out.println("ciao ");
		PrintWriter out = response.getWriter();
		
	
		UtenteDAO uDao = new UtenteDAO();
		UtenteBean user =uDao.doRetriveByEmail(mail);
		if(user.getId_utente()>0) {
			Gson gson= new Gson();
			String jsonArray = gson.toJson(user);
			out.append(jsonArray);
			
			System.out.println(jsonArray);
			
		}
		
		
		
	}
		else
			if(operation.equalsIgnoreCase("save"))
		{		
				HttpSession session = request.getSession();
				mail = (String) session.getAttribute("mailUser");
				JSONParser parser = new JSONParser();
				
				try {
					JSONObject json= (JSONObject) parser.parse(request.getParameter("utenteJs"));
					if(json!=null)
					{
						
						
						UtenteDAO udao = new UtenteDAO();
						System.out.println(mail);
						UtenteBean user=udao.doRetriveByEmail(mail);
						
						if(json.get("nazione")!=null)
							user.setNazione(json.get("nazione")+"");
						if(json.get("via")!=null)
							user.setIndirizzo_via(json.get("via")+"");
						
						
						if(json.get("citta")!=null)
							user.setIndirizzo_citta(json.get("citta")+"");
						
						
						if(json.get("cap")!=null)
							user.setIndirizzo_cap(Integer.parseInt(json.get("cap")+""));
						
						
						if(json.get("provincia")!=null)
							user.setIndirizzo_provincia(json.get("provincia")+"");
						
						
						if(json.get("civico")!=null)
							user.setIndirizzo_num_civico(Integer.parseInt(json.get("civico")+""));
						
						if(json.get("email")!=null)
							user.setEmail(json.get("email")+"");
						
						if(json.get("password")!=null)
							user.setPassword(json.get("password")+"");
						
						udao.doUpdate(user);
					
					  
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			
			
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
