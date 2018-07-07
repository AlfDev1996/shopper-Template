package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ImmagineDAO {

	public synchronized ImmagineBean doRetriveByKey(int id)  {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ImmagineBean img =null;
		
		try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement=(PreparedStatement) connection.prepareStatement("SELECT * from immagine where id_immagine=?");
		preparedStatement.setInt(1, id);
		
		
		ResultSet rs = preparedStatement.executeQuery();
		
		
		while(rs.next()) {
			
			img.setIdImmagine(rs.getInt("id_immagine"));
			img.setDidascalia(rs.getString("didascalia"));
			img.setNomeFile(rs.getString("nome_file"));
			
			int id_prodotto= rs.getInt("id_prodotto") !=0 ? rs.getInt("id_utente"):0;
			
			if(id_prodotto!=0) {
				ProdottoDAO prodottoDAO = new ProdottoDAO();
				ProdottoBean prodotto = prodottoDAO.doRetriveByKey(id_prodotto);
				if(prodotto != null && prodotto.getId_prodotto()>0)
					img.setProdotto(prodotto);
				else
					img.setProdotto(null);
			
			}

			
			return img;
			
		}
		
		}catch(SQLException e) {
			
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
		return null;
	}
	
	
	public synchronized ArrayList<ImmagineBean> doRetrieveAll(String orderBy){
		ArrayList<ImmagineBean> immagini = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ImmagineBean img =null;
		
		String sqlSelect = "select * from immagine ";
		if(orderBy!=null &&(orderBy.equals("id_immagine")||orderBy.equals("didascalia")||orderBy.equals("nome_file")||orderBy.equals("id_utente"))) 
			sqlSelect+="order by "+orderBy;
			
		try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);
		
		ResultSet rs= preparedStatement.executeQuery();
		
		while(rs.next()) {
			img= new ImmagineBean();
			img.setIdImmagine(rs.getInt("id_immagine"));
			img.setDidascalia(rs.getString("didascalia"));
			img.setNomeFile(rs.getString("nome_file"));
			
			
			int id_prodotto= rs.getInt("id_prodotto") !=0 ? rs.getInt("id_utente"):0;
			
			if(id_prodotto!=0) {
				ProdottoDAO prodottoDAO = new ProdottoDAO();
				ProdottoBean prodotto = prodottoDAO.doRetriveByKey(id_prodotto);
				if(prodotto != null && prodotto.getId_prodotto()>0)
					img.setProdotto(prodotto);
				else
					img.setProdotto(null);
			
			}
			immagini.add(img);
			
		}
		
		}catch(SQLException e) {
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
		
		return immagini;
	}
	
	
	public synchronized void doSave(ImmagineBean immagine) {
		
		if(immagine!=null) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String sqlInsert= "insert into immagine(nome_file,didascalia,id_prodotto)  values(?,?,?)";
			
			int res=0;
			try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(sqlInsert);
			
			preparedStatement.setString(1, immagine.getNomeFile());
			preparedStatement.setString(2,immagine.getDidascalia());
			preparedStatement.setInt(3, immagine.getProdotto().getId_prodotto());
			
			
			 res= preparedStatement.executeUpdate();
			 connection.commit();
			
			}catch(SQLException e) {
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
	public synchronized boolean doDelete(int id_immagine) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sqlDelete="delete from immagine where id_immagine=?";
		
		
		int res=0;
		try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement=(PreparedStatement) connection.prepareStatement(sqlDelete);
		
		
		preparedStatement.setInt(1, id_immagine);
		res= preparedStatement.executeUpdate();
		
		}catch(SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				preparedStatement.close();
				connection.commit();
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return (res!=0);
		
	}
	
	public synchronized ArrayList<ImmagineBean> doRetriveByProdotto(ProdottoBean prodotto)  {
		ArrayList<ImmagineBean> immagini =new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ImmagineBean img=null;
		try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement=(PreparedStatement) connection.prepareStatement("SELECT * from immagine where id_prodotto = ? ");
		
		preparedStatement.setInt(1, prodotto.getId_prodotto());
		
		ResultSet res =preparedStatement.executeQuery();
		
		while(res.next()) {
			img= new  ImmagineBean();
			img.setDidascalia(res.getString("didascalia"));
			img.setNomeFile(res.getString("nome_file"));
			img.setIdImmagine(res.getInt("id_immagine"));
			
			int id_prodotto = res.getInt("id_prodotto");
			if(id_prodotto!=0) {
				ProdottoDAO prodotoDAO= new ProdottoDAO();
				prodotto = prodotoDAO.doRetriveByKey(id_prodotto);
				img.setProdotto(prodotto);
				
			}
			immagini.add(img);
			
		}
		
		}catch (SQLException e) {
			
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
		return immagini;
		}
	
	
	public synchronized boolean doUpdate(ImmagineBean immagine) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sqlUpdate="update immagine set didascalia=?, nome_file=?, id_prodotto=? where id_immagine=?";
		int res=0;
		try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement=(PreparedStatement) connection.prepareStatement(sqlUpdate);
		
		preparedStatement.setString(1, immagine.getDidascalia());
		preparedStatement.setString(2, immagine.getNomeFile());
		preparedStatement.setInt(3, immagine.getProdotto().getId_prodotto());
		preparedStatement.setInt(4, immagine.getIdImmagine());
		
		res=preparedStatement.executeUpdate();
		connection.commit();
		
		
		}catch(SQLException e) {
			
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