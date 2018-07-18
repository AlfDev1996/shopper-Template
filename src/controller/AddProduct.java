package controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.CategoriaBean;
import model.CategoriaProdottoBean;
import model.CategoriaProdottoDAO;
import model.ImmagineBean;
import model.ImmagineDAO;
import model.MarcaBean;
import model.MarcaDAO;
import model.ProdottoBean;
import model.ProdottoDAO;

/**
 * Servlet implementation class AddProduct
 */
@MultipartConfig
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
		String taglie="";
		int quantita=Integer.parseInt(request.getParameter("quantita"));
		
		String[] checked = request.getParameterValues("taglia");
		
		
		for(int i=0;i<checked.length;++i) {
			taglie+=checked[i]+",";
			
		}
		
		String[] categorieChecked = request.getParameterValues("categorie");
		
		
		
		ArrayList<CategoriaBean> categorie=new ArrayList<>();
		for(int i=0;i<categorieChecked.length;++i) {
			CategoriaBean categoria=new CategoriaBean();
			categoria.setIdCategoria(Integer.parseInt(categorieChecked[i]+""));
			categorie.add(categoria);
		}
		
		System.out.println(taglie);
		
		
		ProdottoBean prodotto = new ProdottoBean();
		
		prodotto.setNome(nome);
		prodotto.setDescrizione_breve(descrizione_b);
		prodotto.setDescrizione_estesa(descrizione);
		prodotto.setTags(tag);
		prodotto.setModello(modello);
		prodotto.setPrezzo(prezzo);
		prodotto.setTaglie(taglie);
		prodotto.setQuantita(quantita);
		prodotto.setCategorie(categorie);
		System.out.println(getServletContext().getRealPath("/themes/images/prodotti"));
		/*Marca*/
		MarcaBean mar=null;
		MarcaDAO mdao= new MarcaDAO();
		mar=mdao.doRetriveBynome(marca);
		prodotto.setMarca(mar);
		
		
		prodotto.setSesso(sesso);
		System.out.println(getServletContext().getContextPath());
		System.out.println(prodotto.toString()+"<---------");
		//Immagini
		
		
		
		
		ProdottoDAO pDao = new ProdottoDAO();
		if(pDao.doSave(prodotto)) {
			
			prodotto = pDao.doRetriveByNomeAndModello(prodotto.getNome(), prodotto.getModello());
			prodotto.setCategorie(categorie);
			if(prodotto!=null)
			{
				//Salvo le categorie Nella tabella ponte fra prodotto e categoria
				if(prodotto.getCategorie()!=null && prodotto.getCategorie().size()>0)
				{
					
					CategoriaProdottoDAO catDao=new CategoriaProdottoDAO();
					for (CategoriaBean categoria : prodotto.getCategorie()) {
						CategoriaProdottoBean catProdBean=new CategoriaProdottoBean();
						catProdBean.setProdotto(prodotto);
						catProdBean.setCategoria(categoria);
						catDao.doSave(catProdBean);
					}
				}
				
				
				Part part= null;
				String name_image=null;
				ArrayList<ImmagineBean> immagini = new ArrayList<>();
				ImmagineBean img=null;
				ImmagineDAO imgDao=new ImmagineDAO();
				
				if(request.getPart("file1")!=null && request.getPart("file1").getSize()>0)
				{
					img= new ImmagineBean();
					part = request.getPart("file1");
					name_image=extractFileName(part);
					//part.write(getServletContext().getRealPath("/themes/images/prodotti")+"/"+name_image);img.setNomeFile(name_image);
					//img.setProdotto(prodotto);
					img.setNomeFile(name_image);
					img.setId_prodotto(prodotto.getId_prodotto());
					imgDao.doSave(img);
					part.write(getServletContext().getRealPath("/themes/images/prodotti")+"/"+name_image);
					
					immagini.add(img);
				}
				
				if(request.getPart("file2")!=null && request.getPart("file2").getSize()>0)
				{
					img= new ImmagineBean();
					part = request.getPart("file2");
					name_image=extractFileName(part);
					//part.write(getServletContext().getRealPath("/themes/images/prodotti")+"/"+name_image);
					
					img.setNomeFile(name_image);
					//img.setProdotto(prodotto);
					img.setId_prodotto(prodotto.getId_prodotto());
					imgDao.doSave(img);
					part.write(getServletContext().getRealPath("/themes/images/prodotti")+"/"+name_image);
					
					immagini.add(img);
				}
				
				if(request.getPart("file3")!=null && request.getPart("file3").getSize()>0)
				{
					img= new ImmagineBean();
					part = request.getPart("file3");
					name_image=extractFileName(part);
					//img.setProdotto(prodotto);
					img.setNomeFile(name_image);
					img.setId_prodotto(prodotto.getId_prodotto());
					imgDao.doSave(img);
					part.write(getServletContext().getRealPath("/themes/images/prodotti")+"/"+name_image);
					immagini.add(img);
				}
			}
			
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AggiungiArticolo.jsp?errore= Complimenti, Hai aggiunto il prodotto con successo");
			dispatcher.forward(request, response);
			
		}
		else
		{
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AggiungiArticolo.jsp?errore= Ops , qualcosa è andato storto :( ");
			dispatcher.forward(request, response);
			
		}
		
		
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
	}

}
