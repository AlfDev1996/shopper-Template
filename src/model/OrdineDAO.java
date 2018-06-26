package model;

import java.sql.ResultSet;
import java.sql.SQLException;
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
			
			
		}
		
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
