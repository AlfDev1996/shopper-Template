package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class OrdineDAO {

	public synchronized OrdineBean doRetriveByKey(int id)  {
		
		Connection conn = null;
		PreparedStatement ps = null;
		OrdineBean ordine=null;
		try {
		conn = (Connection) DriverManagerConnectionPool.getConnection();
		ps=(PreparedStatement) conn.prepareStatement("SELECT * from ordine where id_ordine=?");
		ps.setInt(1, id);
		
		
		
		ResultSet rs = ps.executeQuery();
		
		
		if(rs.next()) {
			ordine = new OrdineBean();
			
			ordine.setDataCreazione( rs.getDate("data_creazione") );
			ordine.setDataPrevistaPonsegna(rs.getDate("data_prevista_consegna"));
			ordine.setIdOrdine(rs.getInt("id_ordine"));
			ordine.setStato(rs.getString("stato"));
			ordine.setIndirizzo(rs.getString("indirizzo"));
			int id_utente= rs.getInt("id_utente") !=0 ? rs.getInt("id_utente"):0;
			
			if(id_utente!=0) {
				UtenteDAO utenteDao= new UtenteDAO();
				UtenteBean utente = utenteDao.doRetriveByKey(id_utente);
				if(utente!=null &&utente.getId_utente()>0)
					ordine.setUtente(utente);
				else
					ordine.setUtente(null);
				
			}
			
			return ordine;

			
		}
		
		
		}catch (SQLException e) {
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
	
	public synchronized ArrayList<OrdineBean> doRetriveByStato(String stato)  {
		Connection conn = null;
		PreparedStatement ps = null;
		OrdineBean ordine = null;
		ArrayList<OrdineBean> ordini = new ArrayList<>();
		try {
		conn = (Connection) DriverManagerConnectionPool.getConnection();
		ps=(PreparedStatement) conn.prepareStatement("SELECT * from ordine where stato = ? ");
		ps.setString(1, stato);
		
		ResultSet res =ps.executeQuery();
		
		while(res.next()) {
			ordine= new OrdineBean();
			ordine.setDataCreazione(res.getDate("data_creazione"));
			ordine.setDataPrevistaPonsegna(res.getDate("data_prevista_consegna"));
			ordine.setIndirizzo(res.getString("indirizzo"));
			ordine.setStato(res.getString("stato"));
			ordine.setTotale(res.getFloat("totale"));
			
			int id_utente= res.getInt("id_utente") !=0 ? res.getInt("id_utente"):0;
			
			if(id_utente!=0) {
				UtenteDAO utenteDao= new UtenteDAO();
				UtenteBean utente = utenteDao.doRetriveByKey(id_utente);
				if(utente!=null &&utente.getId_utente()>0)
					ordine.setUtente(utente);
				else
					ordine.setUtente(null);
				
			}
			ordini.add(ordine);
			
			
		}

		
		}catch (SQLException e) {
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
		return ordini;
		}
	
	public synchronized ArrayList<OrdineBean> doRetriveByDataCreazione(String dataCreazione)  {
		Connection conn = null;
		PreparedStatement ps = null;
		OrdineBean ordine = null;
		ArrayList<OrdineBean> ordini = new ArrayList<>();
		try {
		conn = (Connection) DriverManagerConnectionPool.getConnection();
		ps=(PreparedStatement) conn.prepareStatement("SELECT * from ordine where data_creazione = ? ");
		ps.setString(1, dataCreazione);
		
		ResultSet res =ps.executeQuery();
		
		while(res.next()) {
			ordine= new OrdineBean();
			ordine.setDataCreazione(res.getDate("data_creazione"));
			ordine.setDataPrevistaPonsegna(res.getDate("data_prevista_consegna"));
			ordine.setIndirizzo(res.getString("indirizzo"));
			ordine.setStato(res.getString("stato"));
			ordine.setTotale(res.getFloat("totale"));
			
			int id_utente= res.getInt("id_utente") !=0 ? res.getInt("id_utente"):0;
			
			if(id_utente!=0) {
				UtenteDAO utenteDao= new UtenteDAO();
				UtenteBean utente = utenteDao.doRetriveByKey(id_utente);
				if(utente!=null &&utente.getId_utente()>0)
					ordine.setUtente(utente);
				else
					ordine.setUtente(null);
				
			}
			ordini.add(ordine);
			
			
		}

		
		}catch (SQLException e) {
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
		return ordini;
		}
	
	
	
	public synchronized ArrayList<OrdineBean> doRetrieveAll(String orderBy){
		
		ArrayList<OrdineBean> ordini =null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		OrdineBean ordine = null;
		
		String sqlSelect = "select * from ordine";
		if(orderBy!=null && (orderBy.equalsIgnoreCase("id_ordine") || orderBy.equalsIgnoreCase("data_creazione") || orderBy.equalsIgnoreCase("data_prevista_consegna") || orderBy.equalsIgnoreCase("stato")|| orderBy.equalsIgnoreCase("indirizzo")|| orderBy.equalsIgnoreCase("totale")|| orderBy.equalsIgnoreCase("id_utente") ) )
			sqlSelect+="order by "+orderBy;
		
		try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);

		ResultSet res = preparedStatement.executeQuery();
		
		while(res.next()) {
			ordine= new OrdineBean();
			ordine.setDataCreazione(res.getDate("data_creazione"));
			ordine.setDataPrevistaPonsegna(res.getDate("data_prevista_consegna"));
			ordine.setStato(res.getString("stato"));
			ordine.setIndirizzo(res.getString("indirizzo"));
			ordine.setTotale(res.getFloat("totale"));
			
			int id_utente= res.getInt("id_utente") !=0 ? res.getInt("id_utente"):0;
			
			if(id_utente!=0) {
				UtenteDAO utenteDao= new UtenteDAO();
				UtenteBean utente = utenteDao.doRetriveByKey(id_utente);
				if(utente!=null &&utente.getId_utente()>0)
					ordine.setUtente(utente);
				else
					ordine.setUtente(null);
				
			}
			ordini.add(ordine);
			
			
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
		
		return ordini;
	}
	
	
	
	public synchronized ArrayList<OrdineBean> doRetrieveByProdotto(ProdottoBean prodotto){
		ArrayList<OrdineBean> ordini =null;
		if(prodotto!=null) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		OrdineBean ordine = null;
		String sqlSelect="select ord.* "
				+ "from ordine ord join voce_ordine voceord on voceord.id_ordine = ord.id_ordine "
				+ "join variante_prodotto varprod on voceord.id_variante_prodotto=varprod.id_variante_prodotto "
				+ "join prodotto prod on varprod.id_prodotto=prod.id where prod.id= ?";
		
		
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);
			preparedStatement.setInt(1, prodotto.getId_prodotto());
			
			ResultSet res = preparedStatement.executeQuery();
			
			while(res.next()) {
				ordine= new OrdineBean();
				ordine.setDataCreazione(res.getDate("data_creazione"));
				ordine.setDataPrevistaPonsegna(res.getDate("data_prevista_consegna"));
				ordine.setStato(res.getString("stato"));
				ordine.setIndirizzo(res.getString("indirizzo"));
				ordine.setTotale(res.getFloat("totale"));
				
				int id_utente= res.getInt("id_utente") !=0 ? res.getInt("id_utente"):0;
				
				if(id_utente!=0) {
					UtenteDAO utenteDao= new UtenteDAO();
					UtenteBean utente = utenteDao.doRetriveByKey(id_utente);
					if(utente!=null &&utente.getId_utente()>0)
						ordine.setUtente(utente);
					else
						ordine.setUtente(null);
					
				}
				ordini.add(ordine);
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
		return ordini;
	}
	
	public synchronized ArrayList<OrdineBean> doRetrieveByVarianteProdotto(VarianteProdottoBean varProdotto){
		ArrayList<OrdineBean> ordini =null;
		if(varProdotto!=null) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		OrdineBean ordine = null;
		String sqlSelect="“select ord.* "
				+ "from ordine ord join voce_ordine voceord on voceord.id_ordine = ord.id_ordine "
				+ "join variante_prodotto varprod on vord.id_variante_prodotto=varprod.id_variante_prodotto "
				+ "where varprod.id_variante_prodotto= ?";
		
		
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);
			preparedStatement.setInt(1, varProdotto.getId_variante_prodotto());
			
			ResultSet res = preparedStatement.executeQuery();
			
			while(res.next()) {
				ordine= new OrdineBean();
				ordine.setDataCreazione(res.getDate("data_creazione"));
				ordine.setDataPrevistaPonsegna(res.getDate("data_prevista_consegna"));
				ordine.setStato(res.getString("stato"));
				ordine.setIndirizzo(res.getString("indirizzo"));
				ordine.setTotale(res.getFloat("totale"));
				
				int id_utente= res.getInt("id_utente") !=0 ? res.getInt("id_utente"):0;
				
				if(id_utente!=0) {
					UtenteDAO utenteDao= new UtenteDAO();
					UtenteBean utente = utenteDao.doRetriveByKey(id_utente);
					if(utente!=null &&utente.getId_utente()>0)
						ordine.setUtente(utente);
					else
						ordine.setUtente(null);
					
				}
				ordini.add(ordine);
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
		return ordini;
	}
	
	
	
	public synchronized void doSave(OrdineBean ordine) {
		if(ordine!=null) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String sqlInsert="insert into ordine (data_creazione,data_prevista_consegna, stato,indirizzo,totale,id_utente) values(?,?,?,?,?,?)";
			int res=0;
			try {
			
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(sqlInsert);
			
			preparedStatement.setDate(1, (Date) ordine.getDataCreazione());
			preparedStatement.setDate(2, (Date) ordine.getDataPrevistaConsegna());
			preparedStatement.setString(3, ordine.getStato());
			preparedStatement.setString(4, ordine.getIndirizzo());
			preparedStatement.setFloat(5, ordine.getTotale());
			preparedStatement .setInt(6, ordine.getUtente().getId_utente());
			
			
			
			
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
			
		
		}
		}
	
	public synchronized boolean doDelete(int id_ordine) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;	
		String sqlDelete = "delete from ordine where id_ordine = ? ";
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
	
	public synchronized boolean doUpdate(OrdineBean ordine) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int res=0;
		
		String sqlUpdate = "UPDATE ordine SET data_creazione = ? , data_prevista_consegna = ? , stato = ? , indirizzo = ? , totale = ? , id_utente = ? where id_ordine=? ";
		try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement=(PreparedStatement) connection.prepareStatement(sqlUpdate);
		
		preparedStatement.setDate(1,(Date) ordine.getDataCreazione());
		preparedStatement.setDate(2, (Date)ordine.getDataPrevistaConsegna());
		preparedStatement.setString(3, ordine.getStato());
		preparedStatement.setString(4, ordine.getIndirizzo());
		preparedStatement.setFloat(5, ordine.getTotale());
		preparedStatement.setInt(6, ordine.getUtente().getId_utente());
		preparedStatement.setInt(7, ordine.getIdOrdine());
		
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
