package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ProdottoDAO {

public synchronized ProdottoBean doRetriveByKey(int id)  {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ProdottoBean prodotto = new ProdottoBean();
		try {
			
			conn = (Connection) DriverManagerConnectionPool.getConnection();
			ps=(PreparedStatement) conn.prepareStatement("SELECT * from prodotto where id_prodotto=?");
			ps.setInt(1, id);
			
			ResultSet res =ps.executeQuery();
			//Prendo il risultato dalla query
			
			if(res.next()) {
				prodotto=new ProdottoBean();
				prodotto.setId_prodotto(res.getInt("id_prodotto"));
				prodotto.setNome(res.getString("nome"));
				prodotto.setDescrizione_breve(res.getString("descrizione_breve"));
				prodotto.setDescrizione_estesa(res.getString("descrizione_estesa"));
				prodotto.setTags(res.getString("tags"));
				prodotto.setModello(res.getString("modello"));
				prodotto.setPrezzo(res.getDouble("prezzo"));
				prodotto.setSesso(res.getString("sesso"));
				prodotto.setTaglie(res.getString("taglie"));
				prodotto.setQuantita(res.getInt("quantita"));
				
				int id_marca = res.getInt("id_marca") != 0 ? res.getInt("id_marca") : 0;
				if(id_marca!=0)
				{
					MarcaDAO marcaDao= new MarcaDAO();
					MarcaBean marca = marcaDao.doRetriveByKey(id_marca);
					if(marca!=null && marca.getIdMarca()>0)
					 prodotto.setMarca(marca);
					else
					 prodotto.setMarca(null);
				}
				
				
				
				//Mi inizializzo l'araylist di categorie
				
				/*
				CategoriaProdottoDAO catProdDao=new CategoriaProdottoDAO();
				System.out.println(prodotto.getId_prodotto());
				ArrayList<CategoriaProdottoBean> catProdBean = catProdDao.doRetriveByProdotto(prodotto.getId_prodotto());
				if(catProdBean!=null && catProdBean.size()>0)
				for (CategoriaProdottoBean categoriaProdottoBean : catProdBean) {
					prodotto.getCategorie().add(categoriaProdottoBean.getCategoria());
				}
				*/
				//Mi inizializzo l'arraylist di variantiProdotto cercando da variantiProdottoDAO tutte le varianti dello specifico prodotto
				//ArrayList<VarianteProdottoBean> varianti = new ArrayList<>();
				//VarianteProdottoDAO varianteDAO=new VarianteProdottoDAO();
				//varianti = varianteDAO.doRetriveByProdotto(prodotto);
				
				//prodotto.setVariantiProdotto(varianti);
				//Mi inizializzo la lista di immagini
				
				 ArrayList<ImmagineBean> immagini = new ArrayList<>();
				ImmagineDAO immagineDAO=new ImmagineDAO();
				immagini = immagineDAO.doRetriveByProdotto(prodotto);
				
				prodotto.setImmagini(immagini);
				
				
				res.close();
				return prodotto;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally{
			try {
				ps.close();
				
				DriverManagerConnectionPool.releaseConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

public synchronized ArrayList<ProdottoBean> doRetriveBySesso(String sesso)  {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ProdottoBean prodotto =  new ProdottoBean();
	ArrayList<ProdottoBean> prodotti = new ArrayList<>();
	try {
		//DriverManagerConnectionPool.getConnection().commit();
	
		conn = (Connection) DriverManagerConnectionPool.getConnection();
		ps=(PreparedStatement) conn.prepareStatement("SELECT * from prodotto where sesso = ? ");
		ps.setString(1, sesso);
		
		ResultSet res =ps.executeQuery();

		//Prendo il risultato dalla query
		while(res.next()) {
			prodotto=new ProdottoBean();
			prodotto.setId_prodotto(res.getInt("id_prodotto"));
			prodotto.setNome(res.getString("nome"));
			prodotto.setDescrizione_breve(res.getString("descrizione_breve"));
			prodotto.setDescrizione_estesa(res.getString("descrizione_estesa"));
			prodotto.setTags(res.getString("tags"));
			prodotto.setModello(res.getString("modello"));
			prodotto.setPrezzo(res.getDouble("prezzo"));
			prodotto.setSesso(res.getString("sesso"));
			prodotto.setTaglie(res.getString("taglie"));
			prodotto.setQuantita(res.getInt("quantita"));
			int id_marca = res.getInt("id_marca") != 0 ? res.getInt("id_marca") : 0;
			if(id_marca!=0)
			{
				MarcaDAO marcaDao= new MarcaDAO();
				MarcaBean marca = marcaDao.doRetriveByKey(id_marca);
				if(marca!=null && marca.getIdMarca()>0)
				 prodotto.setMarca(marca);
				else
				 prodotto.setMarca(null);
			}
			
			//Mi inizializzo l'araylist di categorie
			
			
			
			//Mi inizializzo l'arraylist di variantiProdotto cercando da variantiProdottoDAO tutte le varianti dello specifico prodotto
			//ArrayList<VarianteProdottoBean> varianti = new ArrayList<>();
			//VarianteProdottoDAO varianteDAO=new VarianteProdottoDAO();
			//varianti = varianteDAO.doRetriveByProdotto(prodotto);
			
			//prodotto.setVariantiProdotto(varianti);
			
			//Mi inizializzo la lista di immagini
			ArrayList<ImmagineBean> immagini = new ArrayList<>();
			ImmagineDAO immagineDAO=new ImmagineDAO();
			immagini = immagineDAO.doRetriveByProdotto(prodotto);
			
			prodotto.setImmagini(immagini);
			prodotti.add(prodotto);
		}

		res.close();
		
	}catch(SQLException ex) {
		ex.printStackTrace();
	}finally{
		try {
			
			ps.close();
			
			DriverManagerConnectionPool.releaseConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return prodotti;
	}

public synchronized ArrayList<ProdottoBean> doRetriveByNome(String nome)  {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ProdottoBean prodotto =  new ProdottoBean();
	ArrayList<ProdottoBean> prodotti = new ArrayList<>();
	try {
		
		conn = (Connection) DriverManagerConnectionPool.getConnection();
		ps=(PreparedStatement) conn.prepareStatement("SELECT * from prodotto where nome like  ?  ");
		ps.setString(1, "%"+nome+"%");
		
		ResultSet res =ps.executeQuery();

		//Prendo il risultato dalla query
		while(res.next()) {
			prodotto=new ProdottoBean();
			prodotto.setId_prodotto(res.getInt("id_prodotto"));
			prodotto.setNome(res.getString("nome"));
			prodotto.setDescrizione_breve(res.getString("descrizione_breve"));
			prodotto.setDescrizione_estesa(res.getString("descrizione_estesa"));
			prodotto.setTags(res.getString("tags"));
			prodotto.setModello(res.getString("modello"));
			prodotto.setPrezzo(res.getDouble("prezzo"));
			prodotto.setSesso(res.getString("sesso"));
			prodotto.setTaglie(res.getString("taglie"));
			prodotto.setQuantita(res.getInt("quantita"));
			int id_marca = res.getInt("id_marca") != 0 ? res.getInt("id_marca") : 0;
			if(id_marca!=0)
			{
				MarcaDAO marcaDao= new MarcaDAO();
				MarcaBean marca = marcaDao.doRetriveByKey(id_marca);
				if(marca!=null && marca.getIdMarca()>0)
				 prodotto.setMarca(marca);
				else
				 prodotto.setMarca(null);
			}
			
			//Mi inizializzo l'araylist di categorie
			
			/*
			CategoriaProdottoDAO catProdDao=new CategoriaProdottoDAO();
			System.out.println(prodotto.getId_prodotto());
			ArrayList<CategoriaProdottoBean> catProdBean = catProdDao.doRetriveByProdotto(prodotto.getId_prodotto());
			if(catProdBean!=null && catProdBean.size()>0)
			for (CategoriaProdottoBean categoriaProdottoBean : catProdBean) {
				prodotto.getCategorie().add(categoriaProdottoBean.getCategoria());
			}
			*/
			
			//Mi inizializzo l'arraylist di variantiProdotto cercando da variantiProdottoDAO tutte le varianti dello specifico prodotto
			//ArrayList<VarianteProdottoBean> varianti = new ArrayList<>();
			//VarianteProdottoDAO varianteDAO=new VarianteProdottoDAO();
			//varianti = varianteDAO.doRetriveByProdotto(prodotto);
			
			//prodotto.setVariantiProdotto(varianti);
			
			//Mi inizializzo la lista di immagini
			ArrayList<ImmagineBean> immagini = new ArrayList<>();
			ImmagineDAO immagineDAO=new ImmagineDAO();
			immagini = immagineDAO.doRetriveByProdotto(prodotto);
			
			prodotto.setImmagini(immagini);
			prodotti.add(prodotto);
		}

		res.close();
	}catch(SQLException ex) {
		ex.printStackTrace();
	}finally{
		try {
			ps.close();
			conn.close();
			DriverManagerConnectionPool.releaseConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	return prodotti;
	}

public synchronized ProdottoBean doRetriveByNomeAndModello(String nome, String modello)  {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ProdottoBean prodotto =  new ProdottoBean();
	try {
		
		conn = (Connection) DriverManagerConnectionPool.getConnection();
		ps=(PreparedStatement) conn.prepareStatement("SELECT * from prodotto where nome like  ?  and modello like ?");
		ps.setString(1, nome);
		ps.setString(2, modello);
		
		ResultSet res =ps.executeQuery();

		//Prendo il risultato dalla query
		if(res.next()) {
			prodotto=new ProdottoBean();
			prodotto.setId_prodotto(res.getInt("id_prodotto"));
			prodotto.setNome(res.getString("nome"));
			prodotto.setDescrizione_breve(res.getString("descrizione_breve"));
			prodotto.setDescrizione_estesa(res.getString("descrizione_estesa"));
			prodotto.setTags(res.getString("tags"));
			prodotto.setModello(res.getString("modello"));
			prodotto.setPrezzo(res.getDouble("prezzo"));
			prodotto.setSesso(res.getString("sesso"));
			prodotto.setTaglie(res.getString("taglie"));
			prodotto.setQuantita(res.getInt("quantita"));
			int id_marca = res.getInt("id_marca") != 0 ? res.getInt("id_marca") : 0;
			if(id_marca!=0)
			{
				MarcaDAO marcaDao= new MarcaDAO();
				MarcaBean marca = marcaDao.doRetriveByKey(id_marca);
				if(marca!=null && marca.getIdMarca()>0)
				 prodotto.setMarca(marca);
				else
				 prodotto.setMarca(null);
			}
			
			//Mi inizializzo l'araylist di categorie
			
			/*
			CategoriaProdottoDAO catProdDao=new CategoriaProdottoDAO();
			System.out.println(prodotto.getId_prodotto());
			ArrayList<CategoriaProdottoBean> catProdBean = catProdDao.doRetriveByProdotto(prodotto.getId_prodotto());
			if(catProdBean!=null && catProdBean.size()>0)
			for (CategoriaProdottoBean categoriaProdottoBean : catProdBean) {
				prodotto.getCategorie().add(categoriaProdottoBean.getCategoria());
			}
			*/
			
			//Mi inizializzo l'arraylist di variantiProdotto cercando da variantiProdottoDAO tutte le varianti dello specifico prodotto
			//ArrayList<VarianteProdottoBean> varianti = new ArrayList<>();
			//VarianteProdottoDAO varianteDAO=new VarianteProdottoDAO();
			//varianti = varianteDAO.doRetriveByProdotto(prodotto);
			
			//prodotto.setVariantiProdotto(varianti);
			
			
			//Mi inizializzo la lista di immagini
			ArrayList<ImmagineBean> immagini = new ArrayList<>();
			ImmagineDAO immagineDAO=new ImmagineDAO();
			immagini = immagineDAO.doRetriveByProdotto(prodotto);
			
			prodotto.setImmagini(immagini);
			res.close();
			return prodotto;
		}
	}catch(SQLException ex) {
		ex.printStackTrace();
	}finally{
		try {
			ps.close();
			
			DriverManagerConnectionPool.releaseConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return null;
	}


public synchronized ArrayList<ProdottoBean> doRetriveByMarca(MarcaBean marca)  {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ProdottoBean prodotto =  new ProdottoBean();
	ArrayList<ProdottoBean> prodotti = new ArrayList<>();
	try {
		
		conn = (Connection) DriverManagerConnectionPool.getConnection();
		ps=(PreparedStatement) conn.prepareStatement("SELECT * from prodotto where id_marca = ? ");
		ps.setInt(1, marca.getIdMarca());
		
		ResultSet res =ps.executeQuery();

		//Prendo il risultato dalla query
		while(res.next()) {
			prodotto=new ProdottoBean();
			prodotto.setId_prodotto(res.getInt("id_prodotto"));
			prodotto.setNome(res.getString("nome"));
			prodotto.setDescrizione_breve(res.getString("descrizione_breve"));
			prodotto.setDescrizione_estesa(res.getString("descrizione_estesa"));
			prodotto.setTags(res.getString("tags"));
			prodotto.setModello(res.getString("modello"));
			prodotto.setPrezzo(res.getDouble("prezzo"));
			prodotto.setSesso(res.getString("sesso"));
			prodotto.setTaglie(res.getString("taglie"));
			prodotto.setQuantita(res.getInt("quantita"));
			int id_marca = res.getInt("id_marca") != 0 ? res.getInt("id_marca") : 0;
			if(id_marca!=0)
			{
				MarcaDAO marcaDao= new MarcaDAO();
				marca = marcaDao.doRetriveByKey(id_marca);
				if(marca!=null && marca.getIdMarca()>0)
				 prodotto.setMarca(marca);
				else
				 prodotto.setMarca(null);
			}
			
			//Mi inizializzo l'araylist di categorie
			
			/*
			CategoriaProdottoDAO catProdDao=new CategoriaProdottoDAO();
			System.out.println(prodotto.getId_prodotto());
			ArrayList<CategoriaProdottoBean> catProdBean = catProdDao.doRetriveByProdotto(prodotto.getId_prodotto());
			if(catProdBean!=null && catProdBean.size()>0)
			for (CategoriaProdottoBean categoriaProdottoBean : catProdBean) {
				prodotto.getCategorie().add(categoriaProdottoBean.getCategoria());
			}
			*/
			
			//Mi inizializzo l'arraylist di variantiProdotto cercando da variantiProdottoDAO tutte le varianti dello specifico prodotto
			//ArrayList<VarianteProdottoBean> varianti = new ArrayList<>();
			//VarianteProdottoDAO varianteDAO=new VarianteProdottoDAO();
			//varianti = varianteDAO.doRetriveByProdotto(prodotto);
			
			//prodotto.setVariantiProdotto(varianti);
			
			//Mi inizializzo la lista di immagini
			ArrayList<ImmagineBean> immagini = new ArrayList<>();
			ImmagineDAO immagineDAO=new ImmagineDAO();
			immagini = immagineDAO.doRetriveByProdotto(prodotto);
			
			prodotto.setImmagini(immagini);
			
			prodotti.add(prodotto);
		}

		res.close();
	}catch(SQLException ex) {
		ex.printStackTrace();
	}finally{
		try {
			ps.close();
			
			DriverManagerConnectionPool.releaseConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return prodotti;
	}

public synchronized ArrayList<ProdottoBean> doRetriveByCategoria(CategoriaBean categoria)  {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ProdottoBean prodotto =  new ProdottoBean();
	ArrayList<ProdottoBean> prodotti = new ArrayList<>();
	try {
		
		conn = (Connection) DriverManagerConnectionPool.getConnection();
		//1 se ho id categoria, 2 se ho non ho id e ho descrizione categoria
		int flag = 0;
		String sql = " SELECT p.* from prodotto p join categoria_prodotto cp on cp.id_prodotto=p.id_prodotto ";
		if(categoria!=null && categoria.getIdCategoria()!=0)
		{
			sql+=" where cp.id_categoria= ? ";
			flag=1;
		}
		else
		 if(categoria!=null && categoria.getDescrizione()!=null && !categoria.getDescrizione().isEmpty() && !categoria.getDescrizione().equalsIgnoreCase(""))
		 {
			 sql+=" join categoria c on cp.id_categoria=c.id_categoria where c.descrizione = ? ";
			 flag=2;
		 }
		
		ps=(PreparedStatement) conn.prepareStatement(sql);
		if(flag==1)
			ps.setInt(1, categoria.getIdCategoria());
		else
		 if(flag==2)
			 ps.setString(1, categoria.getDescrizione());
		
		ResultSet res =ps.executeQuery();

		//Prendo il risultato dalla query
		while(res.next()) {
			prodotto=new ProdottoBean();
			prodotto.setId_prodotto(res.getInt("id_prodotto"));
			prodotto.setNome(res.getString("nome"));
			prodotto.setDescrizione_breve(res.getString("descrizione_breve"));
			prodotto.setDescrizione_estesa(res.getString("descrizione_estesa"));
			prodotto.setTags(res.getString("tags"));
			prodotto.setModello(res.getString("modello"));
			prodotto.setPrezzo(res.getDouble("prezzo"));
			prodotto.setSesso(res.getString("sesso"));
			prodotto.setTaglie(res.getString("taglie"));
			prodotto.setQuantita(res.getInt("quantita"));
			int id_marca = res.getInt("id_marca") != 0 ? res.getInt("id_marca") : 0;
			if(id_marca!=0)
			{
				MarcaDAO marcaDao= new MarcaDAO();
				MarcaBean marca = marcaDao.doRetriveByKey(id_marca);
				if(marca!=null && marca.getIdMarca()>0)
				 prodotto.setMarca(marca);
				else
				 prodotto.setMarca(null);
			}
			
			//Mi inizializzo l'araylist di categorie
			
			/*
			CategoriaProdottoDAO catProdDao=new CategoriaProdottoDAO();
			System.out.println(prodotto.getId_prodotto());
			ArrayList<CategoriaProdottoBean> catProdBean = catProdDao.doRetriveByProdotto(prodotto.getId_prodotto());
			if(catProdBean!=null && catProdBean.size()>0)
			for (CategoriaProdottoBean categoriaProdottoBean : catProdBean) {
				prodotto.getCategorie().add(categoriaProdottoBean.getCategoria());
			}
			*/
			
			//Mi inizializzo l'arraylist di variantiProdotto cercando da variantiProdottoDAO tutte le varianti dello specifico prodotto
			//ArrayList<VarianteProdottoBean> varianti = new ArrayList<>();
			//VarianteProdottoDAO varianteDAO=new VarianteProdottoDAO();
			//varianti = varianteDAO.doRetriveByProdotto(prodotto);
			
			//prodotto.setVariantiProdotto(varianti);
			
			//Mi inizializzo la lista di immagini
			ArrayList<ImmagineBean> immagini = new ArrayList<>();
			ImmagineDAO immagineDAO=new ImmagineDAO();
			immagini = immagineDAO.doRetriveByProdotto(prodotto);
			
			prodotto.setImmagini(immagini);
			
			prodotti.add(prodotto);
		}

		res.close();
	}catch(SQLException ex) {
		ex.printStackTrace();
	}finally{
		try {
			ps.close();
			
			DriverManagerConnectionPool.releaseConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return prodotti;
	}

public synchronized ArrayList<ProdottoBean> doRetriveByBestSeller()  {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ProdottoBean prodotto =  new ProdottoBean();
	ArrayList<ProdottoBean> prodotti = new ArrayList<>();
	try {
		
		conn = (Connection) DriverManagerConnectionPool.getConnection();
		String sql = "select p.* , sum(voceOrd.quantita) as qtaVenduta from prodotto p join voce_ordine voceOrd on voceOrd.id_prodotto=p.id_prodotto GROUP BY p.id_prodotto order by qtaVenduta DESC ";
		
		ps=(PreparedStatement) conn.prepareStatement(sql);
		
		ResultSet res =ps.executeQuery();

		//Prendo il risultato dalla query
		while(res.next()) {
			prodotto=new ProdottoBean();
			prodotto.setId_prodotto(res.getInt("id_prodotto"));
			prodotto.setNome(res.getString("nome"));
			prodotto.setDescrizione_breve(res.getString("descrizione_breve"));
			prodotto.setDescrizione_estesa(res.getString("descrizione_estesa"));
			prodotto.setTags(res.getString("tags"));
			prodotto.setModello(res.getString("modello"));
			prodotto.setPrezzo(res.getDouble("prezzo"));
			prodotto.setSesso(res.getString("sesso"));
			prodotto.setTaglie(res.getString("taglie"));
			prodotto.setQuantita(res.getInt("quantita"));
			int id_marca = res.getInt("id_marca") != 0 ? res.getInt("id_marca") : 0;
			if(id_marca!=0)
			{
				MarcaDAO marcaDao= new MarcaDAO();
				MarcaBean marca = marcaDao.doRetriveByKey(id_marca);
				if(marca!=null && marca.getIdMarca()>0)
				 prodotto.setMarca(marca);
				else
				 prodotto.setMarca(null);
			}
			
			
			//Mi inizializzo la lista di immagini
			ArrayList<ImmagineBean> immagini = new ArrayList<>();
			ImmagineDAO immagineDAO=new ImmagineDAO();
			immagini = immagineDAO.doRetriveByProdotto(prodotto);
			
			prodotto.setImmagini(immagini);
			
			prodotti.add(prodotto);
		}

		res.close();
	}catch(SQLException ex) {
		ex.printStackTrace();
	}finally{
		try {
			ps.close();
			
			DriverManagerConnectionPool.releaseConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return prodotti;
	}


public synchronized ArrayList<ProdottoBean> doRetrieveAll(String orderBy){
	ArrayList<ProdottoBean> prodotti = new ArrayList<>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ProdottoBean prodotto = new ProdottoBean();
	
	String sqlSelect = "select * from prodotto ";
	if(orderBy!=null && (orderBy.equalsIgnoreCase("id_prodotto") || orderBy.equalsIgnoreCase("id_marca") || orderBy.equalsIgnoreCase("nome") || orderBy.equalsIgnoreCase("prezzo") ) )
		sqlSelect+="order by "+orderBy;
	
	try {
		
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		
		preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);

		ResultSet res = preparedStatement.executeQuery();
		
		while(res.next()) {
			
			prodotto = new ProdottoBean();
			prodotto.setId_prodotto(res.getInt("id_prodotto"));
			prodotto.setNome(res.getString("nome"));
			prodotto.setDescrizione_breve(res.getString("descrizione_breve"));
			prodotto.setDescrizione_estesa(res.getString("descrizione_estesa"));
			prodotto.setTags(res.getString("tags"));
			prodotto.setModello(res.getString("modello"));
			prodotto.setPrezzo(res.getDouble("prezzo"));
			prodotto.setSesso(res.getString("sesso"));
			prodotto.setTaglie(res.getString("taglie"));
			prodotto.setQuantita(res.getInt("quantita"));
			int id_marca = res.getInt("id_marca") != 0 ? res.getInt("id_marca") : 0;
			if(id_marca!=0)
			{
				MarcaDAO marcaDao= new MarcaDAO();
				MarcaBean marca = marcaDao.doRetriveByKey(id_marca);
				if(marca!=null && marca.getIdMarca()>0)
				 prodotto.setMarca(marca);
				else
				 prodotto.setMarca(null);
			}
			
			//Mi inizializzo l'araylist di categorie
			
			//prodotto.setVariantiProdotto(varianti);
			//Mi inizializzo la lista di immagini
			
			ArrayList<ImmagineBean> immagini = new ArrayList<>();
			ImmagineDAO immagineDAO=new ImmagineDAO();
			immagini = immagineDAO.doRetriveByProdotto(prodotto);
			
			prodotto.setImmagini(immagini);
			 
			
			
			
			
			prodotti.add(prodotto);
			
			
		}

		res.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			preparedStatement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	return prodotti;
}

public synchronized boolean doSave(ProdottoBean prodotto) {
	if(prodotto!=null)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;	
		String sqlInsert = "Insert into prodotto (nome,descrizione_breve,descrizione_estesa,tags,modello,prezzo,sesso,id_marca,taglie,quantita) values (?,?,?,?,?,?,?,?,?,?) ";
		int res=0;
		try {
			
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			
			preparedStatement = (PreparedStatement) connection.prepareStatement(sqlInsert);
			preparedStatement.setString(1, prodotto.getNome());
			preparedStatement.setString(2, prodotto.getDescrizione_breve());
			preparedStatement.setString(3, prodotto.getDescrizione_estesa());
			preparedStatement.setString(4, prodotto.getTags());
			preparedStatement.setString(5, prodotto.getModello());
			preparedStatement.setDouble(6, prodotto.getPrezzo());
			preparedStatement.setString(7, prodotto.getSesso());
			preparedStatement.setInt(8, prodotto.getMarca().getIdMarca());
			preparedStatement.setString(9, prodotto.getTaglie());
			preparedStatement.setInt(10, prodotto.getQuantita());
			
			res = preparedStatement.executeUpdate();
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			try {
				preparedStatement.close();
				DriverManagerConnectionPool.releaseConnection(connection);
				/*
				 
				 * if(res>0)
				 {
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
				}
				 */
				
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	return true;
}

public synchronized boolean doDelete(int id_prodotto) {

	Connection connection = null;
	PreparedStatement preparedStatement = null;	
	String sqlDelete = "DELETE FROM Prodotto WHERE id_prodotto=?";
	int res=0;
	
	try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		
		preparedStatement=(PreparedStatement) connection.prepareStatement(sqlDelete);
		preparedStatement.setInt(1, id_prodotto);
		System.out.println(sqlDelete);
		res = preparedStatement.executeUpdate();
		
		
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			preparedStatement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	return (res!=0);
	
}

public synchronized boolean doUpdate(ProdottoBean prodotto) {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	int res=0;
	String sqlUpdate = "UPDATE prodotto SET nome = ? , descrizione_breve = ? , descrizione_estesa = ? , tags = ? , modello = ? , prezzo = ? , sesso = ? , id_marca = ? , taglie = ? , quantita = ? where id_prodotto = ?";
	try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement=(PreparedStatement) connection.prepareStatement(sqlUpdate);
		
		preparedStatement.setString(1, prodotto.getNome());
		preparedStatement.setString(2, prodotto.getDescrizione_breve());
		preparedStatement.setString(3, prodotto.getDescrizione_estesa());
		preparedStatement.setString(4, prodotto.getTags());
		preparedStatement.setString(5, prodotto.getModello());
		preparedStatement.setDouble(6, prodotto.getPrezzo());
		preparedStatement.setString(7, prodotto.getSesso());
		preparedStatement.setInt(8, prodotto.getMarca().getIdMarca());
		preparedStatement.setString(9, prodotto.getTaglie());
		preparedStatement.setInt(10, prodotto.getQuantita());
		preparedStatement.setInt(11, prodotto.getId_prodotto());
		
		res = preparedStatement.executeUpdate();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			preparedStatement.close();
			
			DriverManagerConnectionPool.releaseConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	return (res!=0);
}
}

