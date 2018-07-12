package model;

public class ImmagineBean {

	
	
	
	
	public ImmagineBean() {
		this.id_immagine=0;
		this.didascalia="";
		this.nome_file="";
	}
	
	
	public ImmagineBean(int id_immagine, String nome_file, String didascalia, ProdottoBean prodotto) {
		
		this.id_immagine = id_immagine;
		this.nome_file = nome_file;
		this.didascalia = didascalia;
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








	@Override
	public String toString() {
		return "ImmagineBean [id_immagine=" + id_immagine + ", nome_file=" + nome_file + ", didascalia=" + didascalia
				+ "]";
	}




	private int id_immagine;
	private String nome_file, didascalia;
	
}
