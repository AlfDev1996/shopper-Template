package model;

public class CategoriaBean {

	private int id_categoria,sconto;
	private String descrizione; 
	private boolean in_saldo;
	
	
	public CategoriaBean() {
		
		this.sconto=0;
		this.descrizione="";
		this.in_saldo=false;
	}
	
	
	public CategoriaBean(int id_categoria, int sconto, String descrizione, boolean in_saldo) {
		this.id_categoria = id_categoria;
		this.sconto = sconto;
		this.descrizione = descrizione;
		this.in_saldo = in_saldo;
	}
	
	


	public int getIdCategoria() {
		return id_categoria;
	}


	public void setIdCategoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}


	public int getSconto() {
		return sconto;
	}


	public void setSconto(int sconto) {
		this.sconto = sconto;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public boolean isInSaldo() {
		return in_saldo;
	}


	public void setInSaldo(boolean in_saldo) {
		this.in_saldo = in_saldo;
	}


	@Override
	public String toString() {
		return "categoria : [id_categoria=" + id_categoria + ", sconto=" + sconto + ", descrizione=" + descrizione
				+ ", in_saldo=" + in_saldo + "]";
	}







	
	
}
