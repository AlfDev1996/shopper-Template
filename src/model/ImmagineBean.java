package model;

public class ImmagineBean {

	
	
	
	
	public ImmagineBean() {
		this.id_immagine=0;
		this.didascalia="";
		this.nome_file="";
		this.prodotto= new ProdottoBean();
	}
	
	
	public ImmagineBean(int id_immagine, String nome_file, String didascalia, ProdottoBean prodotto) {
		
		this.id_immagine = id_immagine;
		this.nome_file = nome_file;
		this.didascalia = didascalia;
		this.prodotto = prodotto;
	}
	
	
	
	
	public int getIdImmagine() {
		return id_immagine;
	}


	public void setIdImmagine(int id_immagine) {
		this.id_immagine = id_immagine;
	}


	public String getNomeFile() {
		return nome_file;
	}


	public void setNomeFile(String nome_file) {
		this.nome_file = nome_file;
	}


	public String getDidascalia() {
		return didascalia;
	}


	public void setDidascalia(String didascalia) {
		this.didascalia = didascalia;
	}


	public ProdottoBean getProdotto() {
		return prodotto;
	}


	public void setProdotto(ProdottoBean prodotto) {
		this.prodotto = prodotto;
	}




	private int id_immagine;
	private String nome_file, didascalia;
	private ProdottoBean prodotto;
	
}
