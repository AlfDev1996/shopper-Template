package model;

public class TipoPagamentoBean {

	
	
	
	public TipoPagamentoBean() {
		this.id_tipo_pagamento=0;
		this.descrizione="";
		this.numero_carta=0;
		this.email="";
		this.nome_intestatario="";
		this.cognome_intestatario="";
		this.tipo="";
		
	}
	
	
	
	
	
	
	public TipoPagamentoBean(int id_tipo_pagamento, int numero_carta, String descrizione, String email,
			String nome_intestatario, String cognome_intestatario, String tipo) {
		
		this.id_tipo_pagamento = id_tipo_pagamento;
		this.numero_carta = numero_carta;
		this.descrizione = descrizione;
		this.email = email;
		this.nome_intestatario = nome_intestatario;
		this.cognome_intestatario = cognome_intestatario;
		this.tipo = tipo;
	}






	public int getIdTipoPagamento() {
		return id_tipo_pagamento;
	}
	public void setIdTipoPagamento(int id_tipo_pagamento) {
		this.id_tipo_pagamento = id_tipo_pagamento;
	}
	public int getNumeroCarta() {
		return numero_carta;
	}
	public void setNumeroCarta(int numero_carta) {
		this.numero_carta = numero_carta;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNomeIntestatario() {
		return nome_intestatario;
	}
	public void setNomeIntestatario(String nome_intestatario) {
		this.nome_intestatario = nome_intestatario;
	}
	public String getCognomeIntestatario() {
		return cognome_intestatario;
	}
	public void setCognomeIntestatario(String cognome_intestatario) {
		this.cognome_intestatario = cognome_intestatario;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	@Override
	public String toString() {
		return "TipoPagamentoBean [id_tipo_pagamento=" + id_tipo_pagamento + ", numero_carta=" + numero_carta
				+ ", descrizione=" + descrizione + ", email=" + email + ", nome_intestatario=" + nome_intestatario
				+ ", cognome_intestatario=" + cognome_intestatario + ", tipo=" + tipo + "]";
	}



	private int id_tipo_pagamento, numero_carta;
	private String descrizione, email,nome_intestatario,cognome_intestatario,tipo;
	
	
	
	
}
