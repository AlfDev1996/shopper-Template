package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class TipoPagamentoDAO {

	public synchronized TipoPagamentoBean doRetriveByKey(int id)  {
		
		Connection conn = null;
		PreparedStatement ps = null;
		TipoPagamentoBean pagamento=null;
		try {
		conn = (Connection) DriverManagerConnectionPool.getConnection();
		ps=(PreparedStatement) conn.prepareStatement("SELECT * from tipo_pagamento where id_tipo_pagamento=?");
		
		ResultSet res =ps.executeQuery();
		
		while(res.next()) {
			pagamento.setIdTipoPagamento(res.getInt("id_tipo_pagamento"));
			pagamento.setDescrizione(res.getString("descrizione"));
			pagamento.setNumeroCarta(res.getInt("numero_carta"));
			pagamento.setEmail(res.getString("email"));
			pagamento.setNomeIntestatario("nome_intestatario");
			pagamento.setCognomeIntestatario("cognome_intestatario");
			pagamento.setTipo(res.getString("tipo"));
			return pagamento;
			
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
	
	public synchronized ArrayList<TipoPagamentoBean> doRetriveByDescrizione(String descrizione)  {
		
		Connection conn = null;
		PreparedStatement ps = null;
		TipoPagamentoBean pagamento=null;
		ArrayList<TipoPagamentoBean> pagamenti = new ArrayList<>();
		
		try {
		conn = (Connection) DriverManagerConnectionPool.getConnection();
		ps=(PreparedStatement) conn.prepareStatement("SELECT * from tipo_pagamento where descrizione = ? ");
		ps.setString(1, descrizione);
		
		ResultSet res =ps.executeQuery();
		while(res.next()) {
			pagamento.setIdTipoPagamento(res.getInt("id_tipo_pagamento"));
			pagamento.setDescrizione(res.getString("descrizione"));
			pagamento.setNumeroCarta(res.getInt("numero_carta"));
			pagamento.setEmail(res.getString("email"));
			pagamento.setNomeIntestatario("nome_intestatario");
			pagamento.setCognomeIntestatario("cognome_intestatario");
			pagamento.setTipo(res.getString("tipo"));
			
			pagamenti.add(pagamento);
			
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
		return pagamenti;
		}
	
	public synchronized ArrayList<TipoPagamentoBean> doRetriveByTipo(String tipo)  {
		
		Connection conn = null;
		PreparedStatement ps = null;
		TipoPagamentoBean pagamento=null;
		ArrayList<TipoPagamentoBean> pagamenti = new ArrayList<>();
		
		try {
		conn = (Connection) DriverManagerConnectionPool.getConnection();
		ps=(PreparedStatement) conn.prepareStatement("SELECT * from tipo_pagamento where tipo = ? ");
		ps.setString(1, tipo);
		
		ResultSet res =ps.executeQuery();
		while(res.next()) {
			pagamento.setIdTipoPagamento(res.getInt("id_tipo_pagamento"));
			pagamento.setDescrizione(res.getString("descrizione"));
			pagamento.setNumeroCarta(res.getInt("numero_carta"));
			pagamento.setEmail(res.getString("email"));
			pagamento.setNomeIntestatario("nome_intestatario");
			pagamento.setCognomeIntestatario("cognome_intestatario");
			pagamento.setTipo(res.getString("tipo"));
			
			pagamenti.add(pagamento);
			
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
		return pagamenti;
		}
	
	public synchronized ArrayList<TipoPagamentoBean> doRetrieveAll(String orderBy){
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		TipoPagamentoBean pagamento=null;
		ArrayList<TipoPagamentoBean> pagamenti = new ArrayList<>();
		
		String sqlSelect = "select * from tipo_pagamento ";
		if(orderBy!=null && (orderBy.equals("id_tipo_pagamento")||orderBy.equals("descrizione")||orderBy.equals("numero_carta")||orderBy.equals("email")||orderBy.equals("nome_intestatario")||orderBy.equals("cognome_intestatario")||orderBy.equals("tipo")))
			sqlSelect+="order by "+orderBy;
		try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);

		ResultSet res = preparedStatement.executeQuery();
		
		
		while(res.next()) {
			pagamento= new TipoPagamentoBean();
			pagamento.setIdTipoPagamento(res.getInt("id_tipo_pagamento"));
			pagamento.setDescrizione(res.getString("descrizione"));
			pagamento.setNumeroCarta(res.getInt("numero_carta"));
			pagamento.setEmail(res.getString("email"));
			pagamento.setNomeIntestatario("nome_intestatario");
			pagamento.setCognomeIntestatario("cognome_intestatario");
			pagamento.setTipo(res.getString("tipo"));
			
			pagamenti.add(pagamento);
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
		
		return pagamenti;
	}
	
	public synchronized boolean doUpdate(TipoPagamentoBean pagamento) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int res=0;
		
		String sqlUpdate="update tipo_pagamento  set descrizione=? , numero_carta=? , email=? , nome_intestatario=?, cognome_intestatario=?, tipo=? WHERE id_tipo_pagamento =?";
		try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement=(PreparedStatement) connection.prepareStatement(sqlUpdate);
		
		preparedStatement.setString(1, pagamento.getDescrizione());
		preparedStatement.setInt(2, pagamento.getNumeroCarta());
		preparedStatement.setString(3, pagamento.getEmail());
		preparedStatement.setString(4, pagamento.getNomeIntestatario());
		preparedStatement.setString(5, pagamento.getCognomeIntestatario());
		preparedStatement.setString(6, pagamento.getTipo());
		preparedStatement.setInt(7,  pagamento.getIdTipoPagamento());
		
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
	
	
	public synchronized void doSave(TipoPagamentoBean pagamento) {
		
		if(pagamento!=null && !pagamento.getDescrizione().equals("") && !pagamento.getEmail().equals("") && !pagamento.getNomeIntestatario().equals("")&& !pagamento.getCognomeIntestatario().equals("")&& !pagamento.getTipo().equals("")&& pagamento.getNumeroCarta()>0){
			Connection connection = null;
			PreparedStatement preparedStatement = null;	
			String sqlInsert = "Insert into tipo_pagamento (descrizione,numero_carta,email,nome_intestatario,cognome_intestatario,tipo) values (?,?,?,?,?,?) ";
			
			try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(sqlInsert);
			
			
			preparedStatement.setString(1, pagamento.getDescrizione());
			preparedStatement.setInt(2, pagamento.getNumeroCarta());
			preparedStatement.setString(3, pagamento.getEmail());
			preparedStatement.setString(4, pagamento.getNomeIntestatario());
			preparedStatement.setString(5, pagamento.getCognomeIntestatario());
			preparedStatement.setString(6, pagamento.getTipo());
			
			
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
	
	public synchronized boolean doDelete(int id_tipo_pagamento) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;	
		String sqlDelete = "delete from tipo_pagamento where id_tipo_pagamento = ? ";
		int res=0;
		
		try {
		connection= (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement= (PreparedStatement) connection.prepareStatement(sqlDelete);
		preparedStatement.setInt(1, id_tipo_pagamento);
		
		res = preparedStatement.executeUpdate();
		
		
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
	
}
