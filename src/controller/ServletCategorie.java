package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.CategoriaBean;
import model.CategoriaDAO;

/**
 * Servlet implementation class ServletCategorie
 */
@WebServlet("/ServletCategorie")
public class ServletCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCategorie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operazione="";
		if(request.getParameter("operazione")!=null && request.getParameter("operazione")!="")
			operazione=request.getParameter("operazione");
		if(operazione.equalsIgnoreCase("getAllCategorie"))
		{
			CategoriaDAO catDao=new CategoriaDAO();
			ArrayList<CategoriaBean> categorie = catDao.doRetrieveAll("descrizione");
			Gson gson = new Gson();
			String categorieJsonArray = gson.toJson(categorie);
			response.getWriter().append(categorieJsonArray);
			System.out.println(categorieJsonArray);
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
