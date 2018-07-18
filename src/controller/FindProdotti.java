package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import model.CategoriaProdottoBean;
import model.CategoriaProdottoDAO;
import model.MarcaBean;
import model.MarcaDAO;
import model.ProdottoBean;
import model.ProdottoDAO;

/**
 * Servlet implementation class FindProdotti
 */
@WebServlet("/FindProdotti")
public class FindProdotti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindProdotti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		
		String tipoFiltro=null, filtro=null;
		if(request.getParameter("tipoFiltro")!=null)
			tipoFiltro=request.getParameter("tipoFiltro");
		if(request.getParameter("filtro")!=null)
			filtro=request.getParameter("filtro");
		
		if(tipoFiltro!=null && filtro!=null)
		{
			ArrayList<ProdottoBean> prodottiFind=new ArrayList<>();
			ProdottoDAO proDAO=new ProdottoDAO();
			if(tipoFiltro.equalsIgnoreCase("sesso"))
			{
				prodottiFind= proDAO.doRetriveBySesso(filtro);
				if(prodottiFind!=null )
				{
					
					
					if( prodottiFind.size()>0) {
						
						for (ProdottoBean prodottoBean : prodottiFind) {
							prodottoBean.inizializzaCategorie();
						}
						
						Gson gson= new Gson();
						String jsonArray = gson.toJson(prodottiFind);
						request.setAttribute("prodotti", jsonArray);	
					}
					
					
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("./prodotti.jsp");
					requestDispatcher.forward(request, response);
					
					//out.append(jsonArray);
					
					//System.out.println(jsonArray);
				}
			}else
			 if(tipoFiltro.equalsIgnoreCase("marca"))
			 {
				 MarcaDAO mDao=new MarcaDAO();
				 MarcaBean marca = mDao.doRetriveBynome(filtro);
				 if(marca!=null && marca.getIdMarca()!=0)
				 {
					 prodottiFind= proDAO.doRetriveByMarca(marca);
					 if(prodottiFind!=null )
						{
						 if( prodottiFind.size()>0) {
							 for (ProdottoBean prodottoBean : prodottiFind) {
									prodottoBean.inizializzaCategorie();
								}
								Gson gson= new Gson();
								String jsonArray = gson.toJson(prodottiFind);
								request.setAttribute("prodotti", jsonArray);	
							}
						 
							RequestDispatcher requestDispatcher = request.getRequestDispatcher("./prodotti.jsp");
							requestDispatcher.forward(request, response);
							

						} 
				 }
				  
			 }
			 
		}
		else
			//Nel caso in cui non gli passo filtro e tipo filtro significa che mi trovo nella ricerca di ModificaProdotto
			//Lato Customizzatore
		{
			String nome = request.getParameter("nome");
			String modello = request.getParameter("modello");
			if(modello!=null && nome!=null)
			{
				ProdottoDAO prodottoDao =  new ProdottoDAO();
				ProdottoBean prodotto = prodottoDao.doRetriveByNomeAndModello(nome, modello);
				if(prodotto!=null)
				{
					
					prodotto.inizializzaCategorie();
					
					Gson gson = new Gson();
					String json = gson.toJson(prodotto);
					out.append(json);
					System.out.println(json);
				}
				
			}else
			if(nome!=null )
			{
				
				
				
				
				ProdottoDAO prodottoDao =  new ProdottoDAO();
				ArrayList<ProdottoBean> prodotti = new ArrayList<>();
				if(nome.equals(""))
				 prodotti = prodottoDao.doRetrieveAll(null);
				else
				 prodotti = prodottoDao.doRetriveByNome(nome);
				if(prodotti!=null && prodotti.size()>0)
				{
					for (ProdottoBean prodottoBean : prodotti) {
						prodottoBean.inizializzaCategorie();
					}
					
					Gson gson= new Gson();
					String jsonArray = gson.toJson(prodotti);
					out.append(jsonArray);
					
					System.out.println(jsonArray);
				}
				
				
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
