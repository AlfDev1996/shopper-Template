package model;

import com.mysql.jdbc.Connection;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		MarcaBean marca = new MarcaBean();
		marca.setNome("Adidas");
		MarcaDAO marcaDAO=new MarcaDAO();
		marcaDAO.doSave(marca);
	}

}
