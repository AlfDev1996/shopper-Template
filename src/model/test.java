package model;

import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		ProdottoDAO pDao= new ProdottoDAO();
		ArrayList<ProdottoBean> prodotti= pDao.doRetrieveAll(null);
		System.out.println(prodotti);
		
		pDao.doDelete(17);
		

		
		 pDao= new ProdottoDAO();
		prodotti= pDao.doRetrieveAll(null);
		System.out.println(prodotti);
		
	}

}
