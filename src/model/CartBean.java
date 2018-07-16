package model;

import java.util.ArrayList;

public class CartBean {

	
	
	public CartBean(String cliente, ArrayList<ProdottoBean> prodotti) {
		this.cliente = cliente;
		this.prodotti = prodotti;
	}
	
	

	public CartBean() {
		prodotti = new ArrayList<>();
		cliente=null;
	}
	
	public void rimuoviProdotto(ProdottoBean prodotto) {
		for (ProdottoBean p : prodotti) {
			if(p.getNome().equalsIgnoreCase(prodotto.getNome()) && p.getMarca().getNome().equalsIgnoreCase(prodotto.getMarca().getNome()))
				prodotti.remove(p);
			
		}
		
	}
	
	
	public void removeAll () {
		prodotti.clear();
		
	}
	
	public void addProdotto(ProdottoBean prodotto) {
		prodotti.add(prodotto);
		
	}
	
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public ArrayList<ProdottoBean> getProdotti() {
		return prodotti;
	}
	public void setProdotti(ArrayList<ProdottoBean> prodotti) {
		this.prodotti = prodotti;
	}
	private String cliente;// utenteBean
	ArrayList<ProdottoBean> prodotti =null;
	
}
