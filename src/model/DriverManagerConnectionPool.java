package model;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class DriverManagerConnectionPool {
	//lista delle connessioni libere
		private static List<Connection> freeDBConnections;
	
	
	static{
		//instanzio la LinkedList
		freeDBConnections=new LinkedList<Connection>();
		//controllo se sono presenti i driver del BD
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO: stampo exception
			System.out.println("DB driver not found:"+ e.getMessage());
		}
	}
	
	/**
	 * Stabilisce la connessione con il server SQL inserendo le credenziali
	 * @return La nuova connessione con il DB
	 * @throws SQLException
	 */
	private static synchronized Connection createDBConnection() 
		/*	throws SQLException */{
		//nuova connessione 
		Connection newConnection = null;
		String ip = "localhost";
		String port = "3306";
		String db = "e-shoes";
		String username = "root";
		String password = "";

		try {
			newConnection = DriverManager.getConnection("jdbc:mysql://"+ ip+":"+ port+"/"+db+"?autoReconnect=true&useSSL=false", username, password);
			newConnection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connessione fallita"+ e.getMessage());
		}
		
		/**
		 * gli SQL statements sono raggruppati in transizioni quando
		 * AutoCommit è settato tu false (se fosse settato su true
		 * gli SQL statements sarebbero eseguiti come transizioni
		 * individuali)
		 */
		
//		newConnection.setAutoCommit(false);
		return newConnection;
	}
	
	/**
	 * Crea una connessione DB gestendo connessioni multithread
	 * se la lista di connessione libere non è vuota
	 * mi salvo la prima connessione disponibile( index 0) e 
	 * poi la rimuovo dalla lista. Altrimenti creo una 
	 * nuova connessione
	 * @return una connessione al server 
	 * @throws SQLException
	 */
	public static synchronized Connection getConnection() 
			throws SQLException {
		
		Connection connection;
		
		if (!freeDBConnections.isEmpty()) {
			connection = (Connection) freeDBConnections.get(0);
			freeDBConnections.remove(0);
			try {
				if (connection.isClosed())
					connection = getConnection();
			} catch (SQLException e) {
				// TODO: rilascio l'oggetto connessione e le 
				//risorse JDBC e apro una nuova connessione
				connection.close();
				connection = getConnection();
			}
		} else {
			connection = createDBConnection();		
		}

		return connection;
	}
	
	/**
	 * Rilascia una connessione aggiungendola alla
	 * lista di quelle libere
	 * @param connection connessione da rilasciare
	 * @throws SQLException
	 */
	public static synchronized void releaseConnection(Connection connection) 
			throws SQLException {
		
		if(connection != null) 
			freeDBConnections.add(connection);
	}
	
	
}
