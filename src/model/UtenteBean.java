package model;

public class UtenteBean {

	private int id_utente, indirizzo_cap, indirizzo_num_civico;
	private String nome,cognome,email,password,ruolo,nazione,indirizzo_via,indirizzo_citta, indirizzo_provincia, indirizzo_nazione;
	
	public UtenteBean() {
		id_utente=0;
		indirizzo_cap=indirizzo_num_civico=0;
		nome=cognome=email=password=ruolo=nazione=indirizzo_via=indirizzo_citta=indirizzo_provincia=indirizzo_nazione="";
	}
	
	
	
	public UtenteBean(int id_utente, int indirizzo_cap, int indirizzo_num_civico, String nome, String cognome,
			String email, String password, String ruolo, String nazione, String indirizzo_via, String indirizzo_citta,
			String indirizzo_provincia, String indirizzo_nazione) {
		
		this.id_utente = id_utente;
		this.indirizzo_cap = indirizzo_cap;
		this.indirizzo_num_civico = indirizzo_num_civico;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.ruolo = ruolo;
		this.nazione = nazione;
		this.indirizzo_via = indirizzo_via;
		this.indirizzo_citta = indirizzo_citta;
		this.indirizzo_provincia = indirizzo_provincia;
		this.indirizzo_nazione = indirizzo_nazione;
	}

	public int getId_utente() {
		return id_utente;
	}
	public void setId_utente(int id_utente) {
		this.id_utente = id_utente;
	}
	public int getIndirizzo_cap() {
		return indirizzo_cap;
	}
	public void setIndirizzo_cap(int indirizzo_cap) {
		this.indirizzo_cap = indirizzo_cap;
	}
	public int getIndirizzo_num_civico() {
		return indirizzo_num_civico;
	}
	public void setIndirizzo_num_civico(int indirizzo_num_civico) {
		this.indirizzo_num_civico = indirizzo_num_civico;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public String getNazione() {
		return nazione;
	}
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	public String getIndirizzo_via() {
		return indirizzo_via;
	}
	public void setIndirizzo_via(String indirizzo_via) {
		this.indirizzo_via = indirizzo_via;
	}
	public String getIndirizzo_citta() {
		return indirizzo_citta;
	}
	public void setIndirizzo_citta(String indirizzo_citta) {
		this.indirizzo_citta = indirizzo_citta;
	}
	public String getIndirizzo_provincia() {
		return indirizzo_provincia;
	}
	public void setIndirizzo_provincia(String indirizzo_provincia) {
		this.indirizzo_provincia = indirizzo_provincia;
	}
	public String getIndirizzo_nazione() {
		return indirizzo_nazione;
	}
	public void setIndirizzo_nazione(String indirizzo_nazione) {
		this.indirizzo_nazione = indirizzo_nazione;
	}



	@Override
	public String toString() {
		return "UtenteBean [id_utente=" + id_utente + ", indirizzo_cap=" + indirizzo_cap + ", indirizzo_num_civico="
				+ indirizzo_num_civico + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", password="
				+ password + ", ruolo=" + ruolo + ", nazione=" + nazione + ", indirizzo_via=" + indirizzo_via
				+ ", indirizzo_citta=" + indirizzo_citta + ", indirizzo_provincia=" + indirizzo_provincia
				+ ", indirizzo_nazione=" + indirizzo_nazione + "]";
	}
	
	
	
}
