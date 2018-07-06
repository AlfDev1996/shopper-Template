package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MarcaBean;
import model.MarcaDAO;
import model.ProdottoBean;
import model.ProdottoDAO;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduct() {
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
		String nome = request.getParameter("Nome_Prodotto");
		String descrizione_b = request.getParameter("Descrizione_Breve");
		String descrizione = request.getParameter("Descrizione");
		String tag =request.getParameter("Tag");
		String modello = request.getParameter("Modello");
		Double prezzo = Double.parseDouble(request.getParameter("Prezzo"));
		String marca = request.getParameter("marca");
		String sesso = request.getParameter("sesso");
		
		ProdottoBean prodotto = new ProdottoBean();
		
		prodotto.setNome(nome);
		prodotto.setDescrizione_breve(descrizione_b);
		prodotto.setDescrizione_estesa(descrizione);
		prodotto.setTags(tag);
		prodotto.setModello(modello);
		prodotto.setPrezzo(prezzo);
		
		/*Marca*/
		MarcaBean mar=null;
		MarcaDAO mdao= new MarcaDAO();
		mar=mdao.doRetriveBynome(marca);
		prodotto.setMarca(mar);
		
		
		prodotto.setSesso(sesso);
		
		ProdottoDAO pDao = new ProdottoDAO();
		if(pDao.doSave(prodotto)) {
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AggiungiArticolo.jsp?errore= Complimenti, Hai aggiunto il prodotto con successo");
			dispatcher.forward(request, response);
			
		}
		else
		{
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AggiungiArticolo.jsp?errore= Ops , qualcosa è andato storto :( ");
			dispatcher.forward(request, response);
			
		}
		
		
	}

}
