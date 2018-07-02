package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class OrdineBean {

	
	
	
	
	public OrdineBean() {
		this.id_ordine=0;
		this.data_creazione = new Date();
		
		this.data_prevista_consegna=new Date();
		this.stato="";
		this.indirizzo="";
		this.totale=0;
		this.utente=new UtenteBean();
		voceOrdine= new ArrayList<>();
	}
	
	
	public OrdineBean(int id_ordine, Date data_creazione, Date data_prevista_consegna,
			String stato, String indirizzo, float totale, UtenteBean utente, ArrayList<VoceOrdineBean> voceOrdine) {
		
		this.id_ordine = id_ordine;
		this.data_creazione = data_creazione;
		this.data_prevista_consegna = data_prevista_consegna;
		this.stato = stato;
		this.indirizzo = indirizzo;
		this.totale = totale;
		this.utente = utente;
		this.voceOrdine=voceOrdine;
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
	public Date getDataPrevistaConsegna() {
		return data_prevista_consegna;
	}
	public void setDataPrevistaPonsegna(Date data_prevista_consegna) {
		this.data_prevista_consegna = data_prevista_consegna;
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
		return voceOrdine;
	}


	public void setVoceOrdine(ArrayList<VoceOrdineBean> voceOrdine) {
		this.voceOrdine = voceOrdine;
	}
	
	
	private int id_ordine;
	private Date data_creazione, data_prevista_consegna;
	private String stato,indirizzo;
	private float totale;
	private ArrayList<VoceOrdineBean> voceOrdine;
	


	private UtenteBean utente;
	
	
}
