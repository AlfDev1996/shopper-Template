package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.MarcaBean;
import model.MarcaDAO;

/**
 * Servlet implementation class ServletMarca
 */
@WebServlet("/ServletMarca")
public class ServletMarca extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMarca() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String tipoRicerca= request.getParameter("tipoRicerca");
		if(tipoRicerca!=null && tipoRicerca.equalsIgnoreCase("allBrands"))
		{
			MarcaDAO mdao=new MarcaDAO();
			ArrayList<MarcaBean> marche= mdao.doRetrieveAll(null);
			if(marche!=null && marche.size()>0)
			{
				Gson gson= new Gson();
				String jsonArray = gson.toJson(marche);
				response.getWriter().append(jsonArray);
				System.out.println(jsonArray);
			}
		}
		else
		{
			PrintWriter out = response.getWriter();
			System.out.println(request.getParameter("status"));
			if(request.getParameter("status").equalsIgnoreCase("ok")) {
			MarcaDAO dao = new MarcaDAO();
			ArrayList<MarcaBean> marche = new ArrayList<>();
			
			marche = dao.doRetrieveAll("id");
			
			for(MarcaBean s:marche) {
				out.append("<option "+"value ="+s.getNome()+">"+s.getNome()+"</option>");
				System.out.println(s.getNome());
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
