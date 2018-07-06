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
		ProdottoBean prodotto = null;
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
				
				//Mi inizializzo l'arraylist di variantiProdotto cercando da variantiProdottoDAO tutte le varianti dello specifico prodotto
				ArrayList<VarianteProdottoBean> varianti = new ArrayList<>();
				VarianteProdottoDAO varianteDAO=new VarianteProdottoDAO();
				varianti = varianteDAO.doRetriveByProdotto(prodotto);
				
				prodotto.setVariantiProdotto(varianti);
				
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
	ProdottoBean prodotto = null;
	ArrayList<ProdottoBean> prodotti = new ArrayList<>();
	try {
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
			
			//Mi inizializzo l'arraylist di variantiProdotto cercando da variantiProdottoDAO tutte le varianti dello specifico prodotto
			ArrayList<VarianteProdottoBean> varianti = new ArrayList<>();
			VarianteProdottoDAO varianteDAO=new VarianteProdottoDAO();
			varianti = varianteDAO.doRetriveByProdotto(prodotto);
			
			prodotto.setVariantiProdotto(varianti);
			
			prodotti.add(prodotto);
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
	return prodotti;
	}

public synchronized ArrayList<ProdottoBean> doRetriveByMarca(MarcaBean marca)  {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ProdottoBean prodotto = null;
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
			
			//Mi inizializzo l'arraylist di variantiProdotto cercando da variantiProdottoDAO tutte le varianti dello specifico prodotto
			ArrayList<VarianteProdottoBean> varianti = new ArrayList<>();
			VarianteProdottoDAO varianteDAO=new VarianteProdottoDAO();
			varianti = varianteDAO.doRetriveByProdotto(prodotto);
			
			prodotto.setVariantiProdotto(varianti);
			
			prodotti.add(prodotto);
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
	return prodotti;
	}


public synchronized ArrayList<ProdottoBean> doRetrieveAll(String orderBy){
	ArrayList<ProdottoBean> prodotti = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ProdottoBean prodotto = null ;
	
	String sqlSelect = "select * from prodotto ";
	if(orderBy!=null && (orderBy.equalsIgnoreCase("id_prodotto") || orderBy.equalsIgnoreCase("id_marca") || orderBy.equalsIgnoreCase("nome") || orderBy.equalsIgnoreCase("prezzo") ) )
		sqlSelect+="order by "+orderBy;
	
	try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);

		ResultSet res = preparedStatement.executeQuery();
		
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
			
			//Mi inizializzo l'arraylist di variantiProdotto cercando da variantiProdottoDAO tutte le varianti dello specifico prodotto
			ArrayList<VarianteProdottoBean> varianti = new ArrayList<>();
			VarianteProdottoDAO varianteDAO=new VarianteProdottoDAO();
			varianti = varianteDAO.doRetriveByProdotto(prodotto);
			
			prodotto.setVariantiProdotto(varianti);
			
			prodotti.add(prodotto);
			
		}
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
		String sqlInsert = "Insert into prodotto (nome,descrizione_breve,descrizione_estesa,tags,modello,prezzo,sesso,id_marca) values (?,?,?,?,?,?,?,?) ";
		
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
			
			preparedStatement.executeUpdate();
			connection.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			try {
				preparedStatement.close();
				DriverManagerConnectionPool.releaseConnection(connection);
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
	String sqlDelete = "delete from prodotto where id_prodotto = ? ";
	int res=0;
	
	try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement=(PreparedStatement) connection.prepareStatement(sqlDelete);
		preparedStatement.setInt(1, id_prodotto);
		
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
	String sqlUpdate = "UPDATE prodotto SET nome = ? , descrizione_breve = ? , descrizione_estesa = ? , tags = ? , modello = ? , prezzo = ? , sesso = ? , id_marca = ?  where id_prodotto = ?";
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
		preparedStatement.setInt(9, prodotto.getId_prodotto());
		
		res = preparedStatement.executeUpdate();
		connection.commit();
		
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

