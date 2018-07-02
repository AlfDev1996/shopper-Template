package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class VoceOrdineDAO {

public synchronized VoceOrdineBean doRetriveByKey(int id_voce_ordine)  {
		
		Connection conn = null;
		PreparedStatement ps = null;
		VoceOrdineBean voceOrdine=null;
		try {
		conn = (Connection) DriverManagerConnectionPool.getConnection();
		ps=(PreparedStatement) conn.prepareStatement("SELECT * from voce_ordine where id_voce_ordine=?");
		ps.setInt(1, id_voce_ordine);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			voceOrdine = new VoceOrdineBean();
			
			int id_ordine= rs.getInt("id_ordine") !=0 ? rs.getInt("id_ordine"):0;
			int id_variante_prodotto= rs.getInt("id_variante_prodotto") !=0 ? rs.getInt("id_variante_prodotto"):0;
			if(id_ordine!=0 && id_variante_prodotto!=0) {
				
				voceOrdine.setId_voce_ordine(rs.getInt("id_voce_ordine"));
				
				//Inizializzo l'ordine a cui la voceOrdine appartiene
				OrdineDAO ordineDao= new OrdineDAO();
				OrdineBean ordine = ordineDao.doRetriveByKey(id_ordine);
				if(ordine!=null &&ordine.getIdOrdine()>0)
					voceOrdine.setOrdine(ordine);
				else
					voceOrdine.setOrdine(null);
				//Inizializzo la variante prodotto che fa parte dell'ordine
				VarianteProdottoDAO varianteDao= new VarianteProdottoDAO();
				VarianteProdottoBean variante = varianteDao.doRetriveByKey(id_variante_prodotto);
				if(variante!=null && variante.getId_variante_prodotto()>0)
					voceOrdine.setVarianteProdotto(variante);
				else
					voceOrdine.setVarianteProdotto(null);
				
			 voceOrdine.setQuantita(rs.getInt("quantita"));
			 voceOrdine.setPrezzo_unitario(rs.getDouble("prezzo_unitario"));
			 voceOrdine.setPrezzo_totale(rs.getDouble("prezzo_totale"));
			 voceOrdine.setValore_sconto(rs.getDouble("valore_sconto"));
				
			}
			
			return voceOrdine;
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

public synchronized ArrayList<VoceOrdineBean> doRetriveByOrdine(int id_ordine)  {
	Connection conn = null;
	PreparedStatement ps = null;
	VoceOrdineBean voceOrdine = null;
	ArrayList<VoceOrdineBean> vociOrdine = new ArrayList<>();
	try {
	conn = (Connection) DriverManagerConnectionPool.getConnection();
	ps=(PreparedStatement) conn.prepareStatement("SELECT * from voce_ordine where id_ordine = ? ");
	ps.setInt(1, id_ordine);
	
	ResultSet rs =ps.executeQuery();
	
	while(rs.next()) {
		voceOrdine= new VoceOrdineBean();
		id_ordine= rs.getInt("id_ordine") !=0 ? rs.getInt("id_ordine"):0;
		int id_variante_prodotto= rs.getInt("id_variante_prodotto") !=0 ? rs.getInt("id_variante_prodotto"):0;
		if(id_ordine!=0 && id_variante_prodotto!=0) {
			
			voceOrdine.setId_voce_ordine(rs.getInt("id_voce_ordine"));
			
			//Inizializzo l'ordine a cui la voceOrdine appartiene
			OrdineDAO ordineDao= new OrdineDAO();
			OrdineBean ordine = ordineDao.doRetriveByKey(id_ordine);
			if(ordine!=null &&ordine.getIdOrdine()>0)
				voceOrdine.setOrdine(ordine);
			else
				voceOrdine.setOrdine(null);
			//Inizializzo la variante prodotto che fa parte dell'ordine
			VarianteProdottoDAO varianteDao= new VarianteProdottoDAO();
			VarianteProdottoBean variante = varianteDao.doRetriveByKey(id_variante_prodotto);
			if(variante!=null && variante.getId_variante_prodotto()>0)
				voceOrdine.setVarianteProdotto(variante);
			else
				voceOrdine.setVarianteProdotto(null);
			
		 voceOrdine.setQuantita(rs.getInt("quantita"));
		 voceOrdine.setPrezzo_unitario(rs.getDouble("prezzo_unitario"));
		 voceOrdine.setPrezzo_totale(rs.getDouble("prezzo_totale"));
		 voceOrdine.setValore_sconto(rs.getDouble("valore_sconto"));
			
		}
		
		vociOrdine.add(voceOrdine);
		
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
	return vociOrdine;
	}

public synchronized ArrayList<VoceOrdineBean> doRetrieveAll(){
	
	ArrayList<VoceOrdineBean> vociOrdine =new ArrayList<>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	VoceOrdineBean voceOrdine = null;
	
	String sqlSelect = "select * from voce_ordine";
	
	try {
	connection = (Connection) DriverManagerConnectionPool.getConnection();
	preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);

	ResultSet rs = preparedStatement.executeQuery();
	
	while(rs.next()) {
		voceOrdine= new VoceOrdineBean();
		int id_ordine= rs.getInt("id_ordine") !=0 ? rs.getInt("id_ordine"):0;
		int id_variante_prodotto= rs.getInt("id_variante_prodotto") !=0 ? rs.getInt("id_variante_prodotto"):0;
		if(id_ordine!=0 && id_variante_prodotto!=0) {
			
			voceOrdine.setId_voce_ordine(rs.getInt("id_voce_ordine"));
			
			//Inizializzo l'ordine a cui la voceOrdine appartiene
			OrdineDAO ordineDao= new OrdineDAO();
			OrdineBean ordine = ordineDao.doRetriveByKey(id_ordine);
			if(ordine!=null &&ordine.getIdOrdine()>0)
				voceOrdine.setOrdine(ordine);
			else
				voceOrdine.setOrdine(null);
			//Inizializzo la variante prodotto che fa parte dell'ordine
			VarianteProdottoDAO varianteDao= new VarianteProdottoDAO();
			VarianteProdottoBean variante = varianteDao.doRetriveByKey(id_variante_prodotto);
			if(variante!=null && variante.getId_variante_prodotto()>0)
				voceOrdine.setVarianteProdotto(variante);
			else
				voceOrdine.setVarianteProdotto(null);
			
		 voceOrdine.setQuantita(rs.getInt("quantita"));
		 voceOrdine.setPrezzo_unitario(rs.getDouble("prezzo_unitario"));
		 voceOrdine.setPrezzo_totale(rs.getDouble("prezzo_totale"));
		 voceOrdine.setValore_sconto(rs.getDouble("valore_sconto"));
		
		 
		}
		vociOrdine.add(voceOrdine);
		
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
	
	return vociOrdine;
}

public synchronized void doSave(VoceOrdineBean voceOrdine) {
	if(voceOrdine!=null) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sqlInsert="insert into voce_ordine (id_ordine,id_variante_prodotto,quantita,prezzo_unitario,prezzo_totale,valore_sconto) values(?,?,?,?,?,?)";
		int res=0;
		try {
		
		connection = (Connection) DriverManagerConnectionPool.getConnection();
		preparedStatement = (PreparedStatement) connection.prepareStatement(sqlInsert);
		
		preparedStatement.setInt(1, voceOrdine.getOrdine().getIdOrdine());
		preparedStatement.setInt(2, voceOrdine.getVarianteProdotto().getId_variante_prodotto());
		preparedStatement.setInt(3, voceOrdine.getQuantita());
		preparedStatement.setDouble(4, voceOrdine.getPrezzo_unitario());
		preparedStatement.setDouble(5, voceOrdine.getPrezzo_totale());
		preparedStatement.setDouble(6, voceOrdine.getValore_sconto());
		
		
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

public synchronized boolean doDelete(int id_voce_ordine) {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;	
	String sqlDelete = "delete from voce_ordine where id_voce_ordine = ? ";
	int res=0;
	
	try {
	connection = (Connection) DriverManagerConnectionPool.getConnection();
	preparedStatement=(PreparedStatement) connection.prepareStatement(sqlDelete);
	
	preparedStatement.setInt(1, id_voce_ordine);
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

public synchronized boolean doUpdate(VoceOrdineBean voceOrdine) {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	int res=0;
	
	String sqlUpdate = "UPDATE voce_ordine SET id_ordine = ? , id_variante_prodotto = ? , quantita = ? , prezzo_unitario = ? , prezzo_totale = ? , valore_sconto= ? where id_voce_ordine=? ";
	try {
	connection = (Connection) DriverManagerConnectionPool.getConnection();
	preparedStatement=(PreparedStatement) connection.prepareStatement(sqlUpdate);
	
	preparedStatement.setInt(1, voceOrdine.getOrdine().getIdOrdine());
	preparedStatement.setInt(2, voceOrdine.getVarianteProdotto().getId_variante_prodotto());
	preparedStatement.setInt(3, voceOrdine.getQuantita());
	preparedStatement.setDouble(4, voceOrdine.getPrezzo_unitario());
	preparedStatement.setDouble(5, voceOrdine.getPrezzo_totale());
	preparedStatement.setDouble(6, voceOrdine.getValore_sconto());
	preparedStatement.setInt(7, voceOrdine.getId_voce_ordine());
	
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


}