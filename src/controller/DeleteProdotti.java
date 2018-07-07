package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProdottoBean;
import model.ProdottoDAO;

/**
 * Servlet implementation class DeleteProdotti
 */
@WebServlet("/DeleteProdotti")
public class DeleteProdotti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProdotti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//stringaProdotti e' composta da + prodotti 
		// nomeProdotto1_modello1,nomeProdotto2_modello2....
		String prodotti = request.getParameter("stringaProdotti");
		String error ="";
		if(prodotti!=null)
		{
			ProdottoDAO prodottoDao = new ProdottoDAO();
			ProdottoBean prodotto = new ProdottoBean();
			
			String[] prods = prodotti.split(",");
			for(int i=0; i<prods.length; ++i)
			{
				String nome = "", modello = "";
			    nome = prods[i].substring(0, prods[i].indexOf("_"));
			    modello = prods[i].substring(prods[i].indexOf("_")+1, prods[i].length() );
			    if(!nome.equals("") && !modello.equals(""))
			    {
			    	prodotto = prodottoDao.doRetriveByNomeAndModello(nome, modello);
				    if(prodotto!=null && prodotto.getId_prodotto()!=0)
				    {
				    	System.out.println(prodotto.getId_prodotto()+"ID PRODOTTO _----------");
				    	boolean res = prodottoDao.doDelete(prodotto.getId_prodotto());
				    	if(!res)
				    		error+="Non e' stato possibile eliminare il prodotto "+prodotto.getNome();
				    }
				    else
				     error+="Prodotto "+nome+",  "+modello+" Non esistente";
				    prodotto = new ProdottoBean();	
			    }
			     
			    
			}
			
		}
		
		response.getWriter().append(error+"");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
