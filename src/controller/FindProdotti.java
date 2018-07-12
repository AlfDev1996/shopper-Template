package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

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
				if(prodottiFind!=null && prodottiFind.size()>0)
				{
					Gson gson= new Gson();
					String jsonArray = gson.toJson(prodottiFind);
					out.append(jsonArray);
					
					System.out.println(jsonArray);
				}
			}
		}
		else
			//Nel caso in cui non gli passo filtro e tipo filtro significa che mi trovo nella ricerca di ModificaProdotto
			//Lato Customizzatore
		{
			String nome = request.getParameter("nome");
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
