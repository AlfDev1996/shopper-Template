package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class OrdineBean {

	
	
	
	
	public OrdineBean() {
		this.id_ordine=0;
		this.data_creazione = new Date();
		this.stato="";
		this.indirizzo="";
		this.totale=0;
		this.utente=new UtenteBean();
		vociOrdine= new ArrayList<>();
	}
	
	
	public OrdineBean(int id_ordine, Date data_creazione,
			String stato, String indirizzo, float totale, UtenteBean utente, ArrayList<VoceOrdineBean> voceOrdine) {
		
		this.id_ordine = id_ordine;
		this.data_creazione = data_creazione;
		this.stato = stato;
		this.indirizzo = indirizzo;
		this.totale = totale;
		this.utente = utente;
		this.vociOrdine=voceOrdine;
	}
	public int getIdOrdine() {
		return id_ordine;
	}
	public void setIdOrdine(int id_ordine) {
		this.id_ordine = id_ordine;
	}
	public Date getDataCreazione() {
		return data_creazione;
	}
	public void setDataCreazione(Date data_creazione) {
		this.data_creazione = data_creazione;
	}
	
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public float getTotale() {
		return totale;
	}
	public void setTotale(float totale) {
		this.totale = totale;
	}
	public UtenteBean getUtente() {
		return utente;
	}
	public void setUtente(UtenteBean utente) {
		this.utente = utente;
	}
	
	public ArrayList<VoceOrdineBean> getVoceOrdine() {
		return vociOrdine;
	}


	public void setVociOrdine(ArrayList<VoceOrdineBean> voceOrdine) {
		this.vociOrdine = voceOrdine;
	}
	
	public void inizializzaVociOrdine() {
		
		if(this!=null && this.id_ordine!=0)
		{
			VoceOrdineDAO voceDao=new VoceOrdineDAO();
			this.vociOrdine.addAll(voceDao.doRetriveByOrdine(this.id_ordine));
		}
			
		
	}
	
	
	public int getQuantitaTotale() {
		int totale = 0;
		if(this.vociOrdine!=null && this.vociOrdine.size()>0)
			for (VoceOrdineBean voceOrdineBean : vociOrdine) {
				totale+=voceOrdineBean.getQuantita();
			}
		return totale;
	}
	
	
	private int id_ordine;
	private Date data_creazione;
	private String stato,indirizzo;
	private float totale;
	private ArrayList<VoceOrdineBean> vociOrdine;
	


	private UtenteBean utente;
	
	
}
