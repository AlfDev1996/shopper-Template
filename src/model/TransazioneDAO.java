package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class TransazioneDAO {

	public synchronized TransazioneBean doRetriveByKey(Integer id_ordine, Integer id_tipo_pagamento,
			Integer id_transazione) {

		Connection conn = null;
		PreparedStatement ps = null;
		TransazioneBean transazione = null;
		try {
			conn = (Connection) DriverManagerConnectionPool.getConnection();
			String sql = "SELECT * from transazione where ";
			int app = 0;
			if (id_transazione != null && id_transazione != 0) {
				sql += " id_transazione=" + id_transazione + " ";
				++app;
			}
			if (id_ordine != null && id_ordine != 0) {
				if (app > 0)
					sql += " and ";
				sql += " id_ordine=" + id_ordine + " ";
				++app;
			}
			if (id_tipo_pagamento != null && id_tipo_pagamento != 0) {
				if (app > 0)
					sql += " and ";
				sql += " id_tipo_pagamento=" + id_tipo_pagamento + " ";
			}
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				transazione = new TransazioneBean();
				transazione.setId_transazione(rs.getInt("id_transazione"));
				transazione.setImporto(rs.getDouble("importo"));
				transazione.setData(rs.getDate("data"));

				id_ordine = rs.getInt("id_ordine") != 0 ? rs.getInt("id_ordine") : 0;

				if (id_ordine != 0) {
					OrdineDAO ordineDao = new OrdineDAO();
					OrdineBean ordine = ordineDao.doRetriveByKey(id_ordine);
					if (ordine != null && ordine.getIdOrdine() > 0)
						transazione.setOrdine(ordine);
					else
						transazione.setOrdine(null);
				}
				id_tipo_pagamento = rs.getInt("id_tipo_pagamento") != 0 ? rs.getInt("id_tipo_pagamento") : 0;

				if (id_tipo_pagamento != 0) {
					TipoPagamentoDAO tipoPagamentoDao = new TipoPagamentoDAO();
					TipoPagamentoBean tipoPagamento = tipoPagamentoDao.doRetriveByKey(id_tipo_pagamento);
					if (tipoPagamento != null && tipoPagamento.getIdTipoPagamento() > 0)
						transazione.setTipoPagamento(tipoPagamento);
					else
						transazione.setTipoPagamento(null);
				}

				return transazione;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	
	public synchronized ArrayList<TransazioneBean> doRetrieveAll(String orderBy){
		
		ArrayList<TransazioneBean> transazioni =null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		TransazioneBean transazione = null;
		
		String sqlSelect = "select * from transazione";
		if(orderBy!=null && orderBy.equalsIgnoreCase("data")  )
			sqlSelect+="order by "+orderBy;
		
		try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);

		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			transazione= new TransazioneBean();
			
			int id_ordine = rs.getInt("id_ordine") != 0 ? rs.getInt("id_ordine") : 0;

			if (id_ordine != 0) {
				OrdineDAO ordineDao = new OrdineDAO();
				OrdineBean ordine = ordineDao.doRetriveByKey(id_ordine);
				if (ordine != null && ordine.getIdOrdine() > 0)
					transazione.setOrdine(ordine);
				else
					transazione.setOrdine(null);
			}
			int id_tipo_pagamento = rs.getInt("id_tipo_pagamento") != 0 ? rs.getInt("id_tipo_pagamento") : 0;

			if (id_tipo_pagamento != 0) {
				TipoPagamentoDAO tipoPagamentoDao = new TipoPagamentoDAO();
				TipoPagamentoBean tipoPagamento = tipoPagamentoDao.doRetriveByKey(id_tipo_pagamento);
				if (tipoPagamento != null && tipoPagamento.getIdTipoPagamento() > 0)
					transazione.setTipoPagamento(tipoPagamento);
				else
					transazione.setTipoPagamento(null);
			}
			
			transazioni.add(transazione);
			
			
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
		
		return transazioni;
	}
	
	public synchronized void doSave(TransazioneBean transazione) {
		if(transazione!=null && transazione.getOrdine()!=null && transazione.getOrdine().getIdOrdine()!=0 && transazione.getTipoPagamento()!=null && transazione.getTipoPagamento().getIdTipoPagamento()!=0) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String sqlInsert="insert into transazione (id_ordine,id_tipo_pagamento, importo,data) values(?,?,?,?)";
			int res=0;
			try {
			
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(sqlInsert);
			
			preparedStatement .setInt(1, transazione.getOrdine().getIdOrdine());
			preparedStatement .setInt(2, transazione.getTipoPagamento().getIdTipoPagamento());
			preparedStatement.setDouble(3, transazione.getImporto());
			preparedStatement.setDate(4, (Date) transazione.getData());
			
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
			
		
		}
		}
	
public synchronized boolean doDelete(int id_ordine) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;	
		String sqlDelete = "delete from transazione where id_transazione = ? ";
		int res=0;
		
		try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement=(PreparedStatement) connection.prepareStatement(sqlDelete);
		
		preparedStatement.setInt(1, id_ordine);
		res = preparedStatement.executeUpdate();
		
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

public synchronized boolean doUpdate(TransazioneBean transazione) {
	
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	int res=0;
	if(transazione!=null && transazione.getOrdine()!=null && transazione.getOrdine().getIdOrdine()!=0 && transazione.getTipoPagamento()!=null && transazione.getTipoPagamento().getIdTipoPagamento()!=0) {
		
		
	String sqlUpdate = "UPDATE transazione SET id_ordine = ? , id_tipo_pagamento = ? , importo = ? , data = ? where id_transazione=? ";
	try {
	connection = (Connection) DriverManagerConnectionPool.getConnection();
	preparedStatement=(PreparedStatement) connection.prepareStatement(sqlUpdate);
	
	preparedStatement .setInt(1, transazione.getOrdine().getIdOrdine());
	preparedStatement .setInt(2, transazione.getTipoPagamento().getIdTipoPagamento());
	preparedStatement.setDouble(3, transazione.getImporto());
	preparedStatement.setDate(4, (Date) transazione.getData());
	preparedStatement.setInt(5, transazione.getId_transazione());
	
	res = preparedStatement.executeUpdate();
	connection.commit();
	
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
	return false;
}
}
