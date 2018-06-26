package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class MarcaDAO {

	public synchronized MarcaBean doRetriveByKey(int id) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		MarcaBean mar = null;
		try {
		conn =(Connection) DriverManagerConnectionPool.getConnection();
		ps=(PreparedStatement) conn.prepareStatement("SELECT * FROM marca WHERE id_marca=?");
		ps.setInt(1,id);
		
		
		ResultSet rs= ps.executeQuery();
		
		if(rs.next()) {
			mar.setIdMarca(rs.getInt("id_marca"));
			mar.setDescrizione(rs.getString("descrizione"));
			return mar;
		}
		
		
	}catch(SQLException e) {
		
		e.printStackTrace();
	}finally{
		try {
			ps.close();
			DriverManagerConnectionPool.releaseConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
return mar;	
}
	
	
	public synchronized MarcaBean doRetriveByDescrizione(String descrizione) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		MarcaBean mar = null;
		try {
		
		connection= (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement= (PreparedStatement) connection.prepareStatement("SELECT * FROM marca WHERE descrizione =?");
		preparedStatement.setString(1, descrizione);
		
		ResultSet rs= preparedStatement.executeQuery();
		
		
		if(rs.next()) {
			mar.setIdMarca(rs.getInt("id_marca"));
			mar.setDescrizione(rs.getString("descrizione"));
		return mar;	
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
		
		return mar;
	}
	
	
	
	public synchronized void doSave(MarcaBean marca) {
		
		if(marca!=null&& marca.getDescrizione()!=null && !marca.getDescrizione().equals("")) {
			Connection connection=null;
			PreparedStatement preparedStatement=null;
			String sqlInsert="Insert into marca (descrizione) values (?)";
			
			
			try {
				
				connection=(Connection) DriverManagerConnectionPool.getConnection();
				preparedStatement = (PreparedStatement) connection.prepareStatement("");
				preparedStatement.setString(1, marca.getDescrizione());
				preparedStatement.executeUpdate();
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
	
	
	public synchronized boolean doDelete(int id_marca) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;	
		String sqlDelete = "delete from marca where id_marca = ? ";
		int res=0;
		
		try {
		
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement=(PreparedStatement) connection.prepareStatement(sqlDelete);
		preparedStatement.setInt(1, id_marca);
		
		res = preparedStatement.executeUpdate();
		
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
	return (res!=0);
	}
	
	
	public synchronized ArrayList<MarcaBean> doRetrieveAll(String orderBy){
		
		ArrayList<MarcaBean> marche=null;
		Connection connection = null;
		PreparedStatement preparedStatement =null;
		MarcaBean tmpMarca = null;
		
		String sqlSelect ="select * from marca";
		if(orderBy!=null && (orderBy.equals("id_marca")||orderBy.equals("descrizione"))) 
			sqlSelect+="order by "+orderBy;
			try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);
			
			
			ResultSet rs = preparedStatement.executeQuery();
			
			
			while(rs.next()) {
				tmpMarca = new MarcaBean();
				tmpMarca.setIdMarca(rs.getInt("id_marca"));
				tmpMarca.setDescrizione(rs.getString("descrizione"));
				marche.add(tmpMarca);
				
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
			
			return marche;
		
		
	}
	
	
	public synchronized boolean doUpdate(MarcaBean marca) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int res=0;
		
		String sqlUpdate = "UPDATE marca SET descrizione = ?  WHERE id_marca = ?";
		
		try {
		
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement=(PreparedStatement) connection.prepareStatement(sqlUpdate);
		preparedStatement.setString(1, marca.getDescrizione());
		preparedStatement.setInt(2, marca.getIdMarca());
		
		
		res = preparedStatement.executeUpdate();
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
