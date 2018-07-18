package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class CategoriaProdottoDAO {

	public synchronized CategoriaProdottoBean doRetriveByKey(int id_categoria_prodotto)  {
		
		Connection conn = null;
		PreparedStatement ps = null;
		CategoriaProdottoBean categoriaProdotto =new CategoriaProdottoBean();
		try {
		conn = (Connection) DriverManagerConnectionPool.getConnection();
		ps=(PreparedStatement) conn.prepareStatement("SELECT * from categoria_prodotto where id_categoria_prodotto=?");
		ps.setInt(1, id_categoria_prodotto);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			
			categoriaProdotto= new CategoriaProdottoBean();
			
			int id_categoria= rs.getInt("id_categoria") !=0 ? rs.getInt("id_categoria"):0;
			int id_prodotto= rs.getInt("id_prodotto") !=0 ? rs.getInt("id_prodotto"):0;
			if(id_categoria!=0 && id_prodotto!=0) {
				
				categoriaProdotto.setIdCategoriaProdotto(rs.getInt("id_categoria_prodotto"));
			//Inizializzo la categoria a cui appartiene
				CategoriaDAO catDAO = new CategoriaDAO();
				CategoriaBean cat = catDAO.doRetriveByKey(rs.getInt("id_categoria"));
				if(cat !=null && cat.getIdCategoria()>0)
					categoriaProdotto.setCategoria(cat);
				else
					categoriaProdotto.setCategoria(null);
				
				
				ProdottoDAO pDAO = new ProdottoDAO();
				ProdottoBean prod = pDAO.doRetriveByKey(rs.getInt("id_prodotto"));
				if(prod!=null && prod.getId_prodotto()>0)
					categoriaProdotto.setProdotto(prod);
				else
					categoriaProdotto.setProdotto(null);
		}
			return categoriaProdotto;
		
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
		return null;
	}
	
	public synchronized ArrayList<CategoriaProdottoBean> doRetriveByCategoria(int id_categoria)  {
		Connection conn = null;
		PreparedStatement ps = null;
		CategoriaProdottoBean categoriaProdotto =null;
		ArrayList<CategoriaProdottoBean> categorieProdotto= new ArrayList<>();
		try {
		conn = (Connection) DriverManagerConnectionPool.getConnection();
		ps=(PreparedStatement) conn.prepareStatement("SELECT * from categoria_prodotto where id_categoria = ? ");
		ps.setInt(1, id_categoria);
		
		ResultSet rs =ps.executeQuery();
		
		while(rs.next()) {
			
			categoriaProdotto= new CategoriaProdottoBean();
			
			id_categoria= rs.getInt("id_categoria")  !=0 ? rs.getInt("id_categoria"):0;
			int id_prodotto = rs.getInt("id_prodotto") !=0 ? rs.getInt("id_prodotto"):0;
			if(id_categoria!=0 && id_prodotto!=0) {
				
				categoriaProdotto.setIdCategoriaProdotto(rs.getInt("id_categoria_prodotto"));
			//Inizializzo la categoria a cui appartiene
				CategoriaDAO catDAO = new CategoriaDAO();
				CategoriaBean cat = catDAO.doRetriveByKey(rs.getInt("id_categoria"));
				if(cat !=null && cat.getIdCategoria()>0)
					categoriaProdotto.setCategoria(cat);
				else
					categoriaProdotto.setCategoria(null);
				
				
				ProdottoDAO pDAO = new ProdottoDAO();
				ProdottoBean prod = pDAO.doRetriveByKey(rs.getInt("id_prodotto"));
				if(prod!=null && prod.getId_prodotto()>0)
					categoriaProdotto.setProdotto(prod);
				else
					categoriaProdotto.setProdotto(null);
		}
			categorieProdotto.add(categoriaProdotto);
			
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
return categorieProdotto;
}
	
	
	public synchronized ArrayList<CategoriaProdottoBean> doRetriveByProdotto(int id_prodotto)  {
		Connection conn = null;
		PreparedStatement ps = null;
		CategoriaProdottoBean categoriaProdotto =new CategoriaProdottoBean();
		ArrayList<CategoriaProdottoBean> categorieProdotto= new ArrayList<>();
		try {
		conn = (Connection) DriverManagerConnectionPool.getConnection();
		ps=(PreparedStatement) conn.prepareStatement("SELECT * from categoria_prodotto where id_prodotto = ? ");
		ps.setInt(1, id_prodotto);
		
		ResultSet rs =ps.executeQuery();
		
		while(rs.next()) {
			
			categoriaProdotto= new CategoriaProdottoBean();
			
			id_prodotto= rs.getInt("id_prodotto")  !=0 ? rs.getInt("id_prodotto"):0;
			int id_categoria = rs.getInt("id_categoria") !=0 ? rs.getInt("id_categoria"):0;
			if(id_categoria!=0 && id_prodotto!=0) {
				
				categoriaProdotto.setIdCategoriaProdotto(rs.getInt("id_categoria_prodotto"));
			//Inizializzo la categoria a cui appartiene
				CategoriaDAO catDAO = new CategoriaDAO();
				CategoriaBean cat = catDAO.doRetriveByKey(rs.getInt("id_categoria"));
				if(cat !=null && cat.getIdCategoria()>0)
					categoriaProdotto.setCategoria(cat);
				else
					categoriaProdotto.setCategoria(null);
				
				
				ProdottoDAO pDAO = new ProdottoDAO();
				ProdottoBean prod = pDAO.doRetriveByKey(id_prodotto);
				if(prod!=null && prod.getId_prodotto()>0)
					categoriaProdotto.setProdotto(prod);
				else
					categoriaProdotto.setProdotto(null);
		}
			categorieProdotto.add(categoriaProdotto);
			
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
return categorieProdotto;
}
	
	
	public synchronized ArrayList<CategoriaProdottoBean> doRetrieveAll(){
		ArrayList<CategoriaProdottoBean> categorieProdotto =null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		CategoriaProdottoBean categoriaProdotto = null;
		
		
		String sqlSelect = "select * from categoria_prodotto";
		try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);

		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			categoriaProdotto = new CategoriaProdottoBean();
			int id_categoria= rs.getInt("id_categoria") !=0 ? rs.getInt("id_categoria"):0;
			int id_prodotto= rs.getInt("id_prodotto") !=0 ? rs.getInt("id_prodotto"):0;
			if(id_categoria!=0 && id_prodotto!=0) {
				
				categoriaProdotto.setIdCategoriaProdotto(rs.getInt("id_categoria_prodotto"));
			//Inizializzo la categoria a cui appartiene
				CategoriaDAO catDAO = new CategoriaDAO();
				CategoriaBean cat = catDAO.doRetriveByKey(rs.getInt("id_categoria"));
				if(cat !=null && cat.getIdCategoria()>0)
					categoriaProdotto.setCategoria(cat);
				else
					categoriaProdotto.setCategoria(null);
				
				
				ProdottoDAO pDAO = new ProdottoDAO();
				ProdottoBean prod = pDAO.doRetriveByKey(rs.getInt("id_prodotto"));
				if(prod!=null && prod.getId_prodotto()>0)
					categoriaProdotto.setProdotto(prod);
				else
					categoriaProdotto.setProdotto(null);
		}
			categorieProdotto.add(categoriaProdotto);
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
	
	
	public synchronized void doSave(CategoriaProdottoBean categoriaProdotto) {
		if(categoriaProdotto!=null) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String sqlInsert="insert into categoria_prodotto (id_categoria,id_prodotto) values(?,?)";
			int res=0;
			try {
				if(categoriaProdotto!=null)
				{
					if( categoriaProdotto.getProdotto().getId_prodotto()!=0)
						;
					else
					 if( categoriaProdotto.getProdotto().getNome()!=null && categoriaProdotto.getProdotto().getModello()!=null)
					{
						ProdottoDAO pdao=new ProdottoDAO();
						categoriaProdotto.setProdotto(pdao.doRetriveByNomeAndModello(categoriaProdotto.getProdotto().getNome(), categoriaProdotto.getProdotto().getModello()));
					}
					if( categoriaProdotto.getCategoria()!=null && categoriaProdotto.getCategoria().getIdCategoria()!=0)
						;
					else
					 if(categoriaProdotto.getCategoria()!=null && categoriaProdotto.getCategoria().getDescrizione()!=null)
					{
						CategoriaDAO cdao=new CategoriaDAO();
						categoriaProdotto.setCategoria(cdao.doRetriveByDescrizione(categoriaProdotto.getCategoria().getDescrizione()));
					}
					if( categoriaProdotto.getCategoria()!=null && categoriaProdotto.getProdotto()!=null)
					{
						connection = (Connection) DriverManagerConnectionPool.getConnection();
						preparedStatement = (PreparedStatement) connection.prepareStatement(sqlInsert);
						
						preparedStatement.setInt(1, categoriaProdotto.getCategoria().getIdCategoria());
						preparedStatement.setInt(2, categoriaProdotto.getProdotto().getId_prodotto());
						
						res = preparedStatement.executeUpdate();
						
					}
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
			
		
		}
		}
	
	public synchronized boolean doDelete(int id_categoria_prodotto) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;	
		String sqlDelete = "delete from categoria_prodotto where id_categoria_prodotto = ? ";
		int res=0;
		
		try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement=(PreparedStatement) connection.prepareStatement(sqlDelete);
		
		preparedStatement.setInt(1,  id_categoria_prodotto);
		
		res= preparedStatement.executeUpdate();
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
	
	public synchronized boolean doUpdate(CategoriaProdottoBean categoriaProdotto) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int res=0;
		
		String sqlUpdate = "UPDATE categoria_prodotto SET id_categoria = ? , id_prodotto = ? WHERE id_categoria_prodotto=?";
		
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement=(PreparedStatement) connection.prepareStatement(sqlUpdate);
			preparedStatement.setInt(1, categoriaProdotto.getCategoria().getIdCategoria());
			preparedStatement.setInt(2, categoriaProdotto.getProdotto().getId_prodotto());
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


		}