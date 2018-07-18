package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class VarianteProdottoDAO {

	
public synchronized VarianteProdottoBean doRetriveByKey(int id)  {
		
		Connection conn = null;
		PreparedStatement ps = null;
		VarianteProdottoBean variante = null;
		try {
			conn = (Connection) DriverManagerConnectionPool.getConnection();
			ps=(PreparedStatement) conn.prepareStatement("SELECT * from variante_prodotto where id_variante_prodotto=?");
			ps.setInt(1, id);
			
			ResultSet res =ps.executeQuery();
			//Prendo il risultato dalla query
			
			if(res.next()) {
				variante=new VarianteProdottoBean();
				variante.setId_variante_prodotto(res.getInt("id_variante_prodotto"));
				variante.setTaglia(res.getString("taglia"));
				variante.setQuantita_disponibile(res.getInt("quantita_disponibile"));
				int id_prodotto = res.getInt("id_prodotto");
				if(id_prodotto!=0)
				{
					ProdottoDAO prodottoDAO = new ProdottoDAO();
					ProdottoBean prodotto = prodottoDAO.doRetriveByKey(id_prodotto);
					variante.setProdotto(prodotto);
				}
				return variante;
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

public synchronized ArrayList<VarianteProdottoBean> doRetriveByProdotto(ProdottoBean prodotto)  {
	
	Connection conn = null;
	PreparedStatement ps = null;
	VarianteProdottoBean variante = null;
	ArrayList<VarianteProdottoBean> varianti = new ArrayList<>();
	try {
		conn = (Connection) DriverManagerConnectionPool.getConnection();
		ps=(PreparedStatement) conn.prepareStatement("SELECT * from variante_prodotto where id_prodotto = ? ");
		ps.setInt(1, prodotto.getId_prodotto());
		
		ResultSet res =ps.executeQuery();

		//Prendo il risultato dalla query
		while(res.next()) {
			variante=new VarianteProdottoBean();
			variante.setId_variante_prodotto(res.getInt("id_variante_prodotto"));
			variante.setTaglia(res.getString("taglia"));
			variante.setQuantita_disponibile(res.getInt("quantita_disponibile"));
			int id_prodotto = res.getInt("id_prodotto");
			if(id_prodotto!=0)
			{
				prodotto=new ProdottoBean();
				ProdottoDAO prodottoDAO = new ProdottoDAO();
				prodotto = prodottoDAO.doRetriveByKey(id_prodotto);
				variante.setProdotto(prodotto);
			}
			varianti.add(variante);
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
	return varianti;
	}

public synchronized ArrayList<VarianteProdottoBean> doRetriveByTaglia(String taglia)  {
	
	Connection conn = null;
	PreparedStatement ps = null;
	VarianteProdottoBean variante = null;
	ArrayList<VarianteProdottoBean> varianti = new ArrayList<>();
	try {
		conn = (Connection) DriverManagerConnectionPool.getConnection();
		ps=(PreparedStatement) conn.prepareStatement("SELECT * from variante_prodotto where taglia = ? ");
		ps.setString(1, taglia);
		
		ResultSet res =ps.executeQuery();

		//Prendo il risultato dalla query
		while(res.next()) {
			variante=new VarianteProdottoBean();
			variante.setId_variante_prodotto(res.getInt("id_variante_prodotto"));
			variante.setTaglia(res.getString("taglia"));
			variante.setQuantita_disponibile(res.getInt("quantita_disponibile"));
			int id_prodotto = res.getInt("id_prodotto");
			if(id_prodotto!=0)
			{
				ProdottoBean prodotto=new ProdottoBean();
				ProdottoDAO prodottoDAO = new ProdottoDAO();
				prodotto = prodottoDAO.doRetriveByKey(id_prodotto);
				variante.setProdotto(prodotto);
			}
			varianti.add(variante);
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
	return varianti;
	}

public synchronized void doSave(VarianteProdottoBean variante) {
	if(variante!=null)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;	
		String sqlInsert = "Insert into variante_prodotto (id_variante_prodotto,taglia,quantita_disponibile,id_prodotto) values (?,?,?,?) ";
		
		try {
			
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(sqlInsert);
			preparedStatement.setInt(1, variante.getId_variante_prodotto());
			preparedStatement.setString(2, variante.getTaglia());
			preparedStatement.setInt(3, variante.getQuantita_disponibile());
			preparedStatement.setInt(4, variante.getProdotto().getId_prodotto());
			
			preparedStatement.executeUpdate();
			
			
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
	}
	
}

public synchronized boolean doDelete(int id_variante_prodotto) {

	Connection connection = null;
	PreparedStatement preparedStatement = null;	
	String sqlDelete = "delete from variante_prodotto where id_variante_prodotto = ? ";
	int res=0;
	
	try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement=(PreparedStatement) connection.prepareStatement(sqlDelete);
		preparedStatement.setInt(1, id_variante_prodotto);
		
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

public synchronized boolean doUpdate(VarianteProdottoBean variante) {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	int res=0;
	String sqlUpdate = "UPDATE variante_prodotto SET id_variante_prodotto = ? , taglia = ? , quantita_disponibile = ? , id_prodotto = ? where id_variante_prodotto = ?";
	try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement=(PreparedStatement) connection.prepareStatement(sqlUpdate);
		
		preparedStatement.setInt(1, variante.getId_variante_prodotto());
		preparedStatement.setString(2, variante.getTaglia());
		preparedStatement.setInt(3, variante.getQuantita_disponibile());
		preparedStatement.setInt(4, variante.getProdotto().getId_prodotto());
		
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
