package model;

import java.util.ArrayList;
import java.util.Iterator;

public class CartBean {

	
	
	public CartBean(UtenteBean utente, ArrayList<ProdottoBean> prodotti) {
		this.utente= utente;
		this.prodotti = prodotti;
	}
	
	

	public CartBean() {
		prodotti = new ArrayList<>();
		utente=new UtenteBean();
	}
	
	public void rimuoviProdotto(ProdottoBean prodotto) {
		
		for (Iterator it = prodotti.iterator(); it.hasNext();) {
			ProdottoBean p = (ProdottoBean) it.next();
			if(p.getNome().equalsIgnoreCase(prodotto.getNome()) && p.getModello().equalsIgnoreCase(prodotto.getModello()) && p.getTaglie().equalsIgnoreCase(prodotto.getTaglie()))
				it.remove();
		}
	}
	
	public void rimuoviProdotti(String[] ids) {
		for( int i=0; i<ids.length; ++i)
			for (Iterator it = prodotti.iterator(); it.hasNext();) {
				ProdottoBean prodotto = (ProdottoBean) it.next();
				if(prodotto.getId_prodotto()==(Integer.parseInt(ids[i])))
					it.remove();
			}
	}
	
	public void updateProdotto(String idTaglia, int quantita) {
		for (ProdottoBean prodottoBean : prodotti) {
			String[] str = idTaglia.split("_");
			int id = Integer.parseInt(str[0]);
			String taglia = str[1];
			if(prodottoBean.getId_prodotto()==id)
			 if( prodottoBean.getTaglie().equals(taglia))
				 prodottoBean.setQuantita(quantita);
		}
	}
	
	
	public void removeAll () {
		prodotti.clear();
		
	}
	
	public void addProdotto(ProdottoBean prodotto) {
		if(prodotto!=null)
		{
			for( ProdottoBean p : this.prodotti)
				if(p.getNome().equalsIgnoreCase(prodotto.getNome()) && p.getModello().equalsIgnoreCase(prodotto.getModello()) && p.getTaglie().equalsIgnoreCase(prodotto.getTaglie()))
					{
						p.setQuantita(p.getQuantita()+prodotto.getQuantita());
						return ;
					}
		}
		prodotti.add(prodotto);
		
	}
	
	
	public UtenteBean getUtente() {
		return utente;
	}



	public void setUtente(UtenteBean utente) {
		this.utente = utente;
	}



	public ArrayList<ProdottoBean> getProdotti() {
		return prodotti;
	}
	public void setProdotti(ArrayList<ProdottoBean> prodotti) {
		this.prodotti = prodotti;
	}
	
	
	
	public double getPrezzoTotale() {
		double prezzo = 0.0;
		for (ProdottoBean prodottoBean : prodotti) {
			prezzo+=(prodottoBean.getPrezzo()*prodottoBean.getQuantita());
		}
		return prezzo;
	}



	public void setPrezzoTotale(double prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}



	@Override
	public String toString() {
		return "CartBean [utente=" + utente + ", prodotti=" + prodotti + "]";
	}



	private UtenteBean utente;// utenteBean
	ArrayList<ProdottoBean> prodotti =null;
	private double prezzoTotale;
	
}
