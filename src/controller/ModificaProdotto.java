package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.ProdottoBean;
import model.ProdottoDAO;

/**
 * Servlet implementation class ModificaProdotto
 */
@WebServlet("/ModificaProdotto")
public class ModificaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Sei nella servlet");
		JSONParser parser = new JSONParser();
		boolean res = false;
		try {
			JSONObject json= (JSONObject) parser.parse(request.getParameter("prodotto"));
			if(json!=null)
			{
			 ProdottoBean prodotto = new ProdottoBean();
			 ProdottoDAO proDAO= new ProdottoDAO();
			 prodotto = proDAO.doRetriveByNomeAndModello(json.get("nome")+"" , json.get("modello")+"");
			 if(json.get("prezzo")!=null)
				 prodotto.setPrezzo(Double.parseDouble(json.get("prezzo")+""));
			 if(json.get("descrizione_breve")!=null)
				 prodotto.setDescrizione_breve(json.get("descrizione_breve")+"");
			 if(json.get("descrizione_estesa")!=null)
				 prodotto.setDescrizione_estesa(json.get("descrizione_estesa")+"");
			 if(json.get("sesso")!=null)
				 prodotto.setSesso(json.get("sesso")+"");
			 
			  res = proDAO.doUpdate(prodotto);
			  
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.getWriter().append(res+"");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		doGet(request, response);
	}

}
