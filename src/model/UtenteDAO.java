package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class UtenteDAO {

public synchronized UtenteBean doRetriveByKey(int id)  {
		
		Connection conn = null;
		PreparedStatement ps = null;
		UtenteBean utente = null;
		try {
			conn = (Connection) DriverManagerConnectionPool.getConnection();
			ps=(PreparedStatement) conn.prepareStatement("SELECT * from utente where id_utente=?");
			ps.setInt(1, id);
			
			ResultSet res =ps.executeQuery();
			//Prendo il risultato dalla query
			
			if(res.next()) {
				utente=new UtenteBean();
				utente.setId_utente(res.getInt("id_utente"));
				utente.setNome(res.getString("nome"));
				utente.setCognome(res.getString("cognome"));
				utente.setEmail(res.getString("email"));
				utente.setPassword(res.getString("password"));
				utente.setRuolo(res.getString("ruolo"));
				utente.setNazione(res.getString("nazione"));
				utente.setIndirizzo_via(res.getString("indirizzo_via"));
				utente.setIndirizzo_citta(res.getString("indirizzo_citta"));
				utente.setIndirizzo_cap(res.getInt("indirizzo_cap"));
				utente.setIndirizzo_provincia(res.getString("indirizzo_provincia"));
				utente.setIndirizzo_nazione("indirizzo_nazione");
				utente.setIndirizzo_num_civico(res.getInt("indirizzo_num_civico"));
				
				return utente;
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
	
public synchronized UtenteBean doRetriveByEmailAndPassword(String email,String password)  {
	
	Connection conn = null;
	PreparedStatement ps = null;
	UtenteBean utente =null;
	try {
		conn = (Connection) DriverManagerConnectionPool.getConnection();
		ps=(PreparedStatement) conn.prepareStatement("SELECT * from utente where email = ? and password = ?");
		ps.setString(1, email);
		ps.setString(2, password);
		
		ResultSet res =ps.executeQuery();

		//Prendo il risultato dalla query
		
		if(res.next()) {
			utente = new UtenteBean();
			utente.setId_utente(res.getInt("id_utente"));
			utente.setNome(res.getString("nome"));
			utente.setCognome(res.getString("cognome"));
			utente.setEmail(res.getString("email"));
			utente.setPassword(res.getString("password"));
			utente.setRuolo(res.getString("ruolo"));
			utente.setNazione(res.getString("nazione"));
			utente.setIndirizzo_via(res.getString("indirizzo_via"));
			utente.setIndirizzo_citta(res.getString("indirizzo_citta"));
			utente.setIndirizzo_cap(res.getInt("indirizzo_cap"));
			utente.setIndirizzo_provincia(res.getString("indirizzo_provincia"));
			utente.setIndirizzo_nazione("indirizzo_nazione");
		//	utente.setIndirizzo_num_civico(res.getInt("indirizzo_num_civico"));
			return utente;
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


public synchronized UtenteBean doRetriveByEmail(String email)  {
	
	Connection conn = null;
	PreparedStatement ps = null;
	UtenteBean utente =null;
	try {
		conn = (Connection) DriverManagerConnectionPool.getConnection();
		ps=(PreparedStatement) conn.prepareStatement("SELECT * from utente where email = ?");
		ps.setString(1, email);
		
		
		ResultSet res =ps.executeQuery();

		//Prendo il risultato dalla query
		
		if(res.next()) {
			utente = new UtenteBean();
			utente.setId_utente(res.getInt("id_utente"));
			utente.setNome(res.getString("nome"));
			utente.setCognome(res.getString("cognome"));
			utente.setEmail(res.getString("email"));
			utente.setPassword(res.getString("password"));
			utente.setRuolo(res.getString("ruolo"));
			utente.setNazione(res.getString("nazione"));
			utente.setIndirizzo_via(res.getString("indirizzo_via"));
			utente.setIndirizzo_citta(res.getString("indirizzo_citta"));
			utente.setIndirizzo_cap(res.getInt("indirizzo_cap"));
			utente.setIndirizzo_provincia(res.getString("indirizzo_provincia"));
			utente.setIndirizzo_nazione("indirizzo_nazione");
			utente.setIndirizzo_num_civico(res.getInt("indirizzo_num_civico"));
			return utente;
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

public synchronized ArrayList<UtenteBean> doRetrieveAll(String orderBy){
	ArrayList<UtenteBean> utenti = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	UtenteBean utente = null ;
	
	String sqlSelect = "select * from utente ";
	if(orderBy!=null && (orderBy.equalsIgnoreCase("id_utente") || orderBy.equalsIgnoreCase("nome") || orderBy.equalsIgnoreCase("cognome") || orderBy.equalsIgnoreCase("email") ) )
		sqlSelect+="order by "+orderBy;
	
	try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);

		ResultSet res = preparedStatement.executeQuery();
		
		while(res.next()) {
			
			utente=new UtenteBean();
			utente.setId_utente(res.getInt("id_utente"));
			utente.setNome(res.getString("nome"));
			utente.setCognome(res.getString("cognome"));
			utente.setEmail(res.getString("email"));
			utente.setPassword(res.getString("password"));
			utente.setRuolo(res.getString("ruolo"));
			utente.setNazione(res.getString("nazione"));
			utente.setIndirizzo_via(res.getString("indirizzo_via"));
			utente.setIndirizzo_citta(res.getString("indirizzo_citta"));
			utente.setIndirizzo_cap(res.getInt("indirizzo_cap"));
			utente.setIndirizzo_provincia(res.getString("indirizzo_provincia"));
			utente.setIndirizzo_nazione("indirizzo_nazione");
			utente.setIndirizzo_num_civico(res.getInt("indirizzo_num_civico"));
			utenti.add(utente);
			
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
	
	return utenti;
}

public synchronized void doSave(UtenteBean utente) {
	if(utente!=null)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;	
		String sqlInsert = "Insert into utente (nome,cognome,email,password,ruolo,nazione,indirizzo_via,indirizzo_citta,indirizzo_cap,indirizzo_provincia,indirizzo_nazione,indirizzo_num_civico) values (?,?,?,?,?,?,?,?,?,?,?,?) ";
		
		try {
			
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(sqlInsert);
			preparedStatement.setString(1, utente.getNome());
			preparedStatement.setString(2, utente.getCognome());
			preparedStatement.setString(3, utente.getEmail());
			preparedStatement.setString(4, utente.getPassword());
			preparedStatement.setString(5, utente.getRuolo());
			preparedStatement.setString(6, utente.getNazione());
			preparedStatement.setString(7, utente.getIndirizzo_via());
			preparedStatement.setString(8, utente.getIndirizzo_citta());
			preparedStatement.setInt(9, utente.getIndirizzo_cap());
			preparedStatement.setString(10, utente.getIndirizzo_provincia());
			preparedStatement.setString(11, utente.getIndirizzo_nazione());
			preparedStatement.setInt(12, utente.getIndirizzo_num_civico());
			
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

public synchronized boolean doDelete(int id_utente) {
	Connection connection = null;
	PreparedStatement preparedStatement = null;	
	String sqlDelete = "delete from utente where id_utente = ? ";
	int res=0;
	
	try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement=(PreparedStatement) connection.prepareStatement(sqlDelete);
		preparedStatement.setInt(1, id_utente);
		
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

public synchronized boolean doUpdate(UtenteBean utente) {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	int res=0;
	String sqlUpdate = "UPDATE utente SET nome = ? , cognome = ? , email = ? , password = ? , ruolo = ? , nazione = ? , indirizzo_via = ? , indirizzo_citta = ? , indirizzo_cap = ? , indirizzo_provincia = ? , indirizzo_nazione = ? , indirizzo_num_civico = ? where id_utente = ?";
	try {
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement=(PreparedStatement) connection.prepareStatement(sqlUpdate);
		
		preparedStatement.setString(1, utente.getNome());
		preparedStatement.setString(2, utente.getCognome());
		preparedStatement.setString(3, utente.getEmail());
		preparedStatement.setString(4, utente.getPassword());
		preparedStatement.setString(5, utente.getRuolo());
		preparedStatement.setString(6, utente.getNazione());
		preparedStatement.setString(7, utente.getIndirizzo_via());
		preparedStatement.setString(8, utente.getIndirizzo_citta());
		preparedStatement.setInt(9, utente.getIndirizzo_cap());
		preparedStatement.setString(10, utente.getIndirizzo_provincia());
		preparedStatement.setString(11, utente.getIndirizzo_nazione());
		preparedStatement.setInt(12, utente.getIndirizzo_num_civico());
		preparedStatement.setInt(13, utente.getId_utente());
		
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
