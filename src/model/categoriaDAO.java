package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class categoriaDAO {
	
	static PreparedStatement ps=null;
	public synchronized categoriaBean doRetriveByKey(int id)  {
		try {
		Connection conn=null;
		
		
		
		categoriaBean cat = new categoriaBean();
		conn = (Connection) driverManagerConnectionPool.getConnection();
		ps=(PreparedStatement) conn.prepareStatement("SELECT * from categoria where id_categoria=?");
		
		ps.setInt(1, id);
		
		ResultSet res =ps.executeQuery();
		
		//Prendo il risultato dalla query
		
		if(res.next()) {
			cat.setDescrizione(res.getString("descrizione"));
			cat.setIn_saldo(res.getBoolean("in_saldo"));
			cat.setSconto(res.getInt("sconto"));
			
			
		}
		
		return cat;
	}catch(SQLException e) {
		e.printStackTrace();
		
		
		
	}
		
		finally {
		
		}
		
		return null;
	}
}
