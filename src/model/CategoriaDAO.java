package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class CategoriaDAO {
	
	
	public synchronized CategoriaBean doRetriveByKey(int id)  {
		
		Connection conn = null;
		PreparedStatement ps = null;
		CategoriaBean cat = null;
		try {
			conn = (Connection) DriverManagerConnectionPool.getConnection();
			ps=(PreparedStatement) conn.prepareStatement("SELECT * from categoria where id_categoria=?");
			ps.setInt(1, id);
			
			ResultSet res =ps.executeQuery();
			//Prendo il risultato dalla query
			
			if(res.next()) {
				cat = new CategoriaBean();
				cat.setDescrizione(res.getString("descrizione"));
				cat.setInSaldo(res.getBoolean("in_saldo"));
				cat.setSconto(res.getInt("sconto"));
				cat.setIdCategoria(res.getInt("id_categoria"));
				return cat;
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
	
	public synchronized CategoriaBean doRetriveByDescrizione(String descrizione)  {
		
		Connection conn = null;
		PreparedStatement ps = null;
		CategoriaBean cat = null;
		try {
			conn = (Connection) DriverManagerConnectionPool.getConnection();
			ps=(PreparedStatement) conn.prepareStatement("SELECT * from categoria where descrizione=?");
			ps.setString(1, descrizione);
			
			ResultSet res =ps.executeQuery();
			//Prendo il risultato dalla query
			
			if(res.next()) {
				cat = new CategoriaBean();
				cat.setDescrizione(res.getString("descrizione"));
				cat.setInSaldo(res.getBoolean("in_saldo"));
				cat.setSconto(res.getInt("sconto"));
				cat.setIdCategoria(res.getInt("id_categoria"));
				return cat;
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
	
	public synchronized void doSave(CategoriaBean categoria) {
		if(categoria!=null && categoria.getDescrizione()!=null && !categoria.getDescrizione().equals(""))
		{
			Connection connection = null;
			PreparedStatement preparedStatement = null;	
			String sqlInsert = "Insert into categoria (descrizione,in_saldo,sconto) values (?,?,?) ";
			
			try {
				
				connection = (Connection) DriverManagerConnectionPool.getConnection();
				preparedStatement = (PreparedStatement) connection.prepareStatement(sqlInsert);
				preparedStatement.setString(1, categoria.getDescrizione());
				preparedStatement.setBoolean(2, categoria.isInSaldo());
				preparedStatement.setInt(1, categoria.getSconto());
				
				preparedStatement.executeUpdate();
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
		}
		
	}
	
	public synchronized boolean doDelete(int id_categoria) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;	
		String sqlDelete = "delete from categoria where id_categoria = ? ";
		int res=0;
		
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement=(PreparedStatement) connection.prepareStatement(sqlDelete);
			preparedStatement.setInt(1, id_categoria);
			
			res = preparedStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	public synchronized ArrayList<CategoriaBean> doRetrieveAll(String orderBy){
		ArrayList<CategoriaBean> categorie = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		CategoriaBean tempCategoria = null ;
		
		String sqlSelect = "select * from categoria ";
		if(orderBy!=null && (orderBy.equalsIgnoreCase("id_categoria") || orderBy.equalsIgnoreCase("descrizione") || orderBy.equalsIgnoreCase("in_saldo") || orderBy.equalsIgnoreCase("sconto") ) )
			sqlSelect+="order by "+orderBy;
		
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				tempCategoria=new CategoriaBean();
				tempCategoria.setIdCategoria(rs.getInt("id_categoria"));
				tempCategoria.setDescrizione(rs.getString("descrizione"));
				tempCategoria.setInSaldo(rs.getBoolean("in_saldo"));
				tempCategoria.setSconto(rs.getInt("sconto"));
				
				categorie.add(tempCategoria);
				
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
		
		return categorie;
	}
	
	
	public synchronized boolean doUpdate(CategoriaBean categoria) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int res=0;
		String sqlUpdate = "UPDATE categoria SET descrizione = ? , in_saldo = ? , sconto = ? WHERE id_categoria = ?";
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement=(PreparedStatement) connection.prepareStatement(sqlUpdate);
			
			preparedStatement.setString(1, categoria.getDescrizione());
			preparedStatement.setBoolean(2, categoria.isInSaldo());
			preparedStatement.setInt(3, categoria.getSconto());
			preparedStatement.setInt(4, categoria.getIdCategoria());
			
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
