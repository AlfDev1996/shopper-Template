package model;

public class categoriaBean {

	
	
	
	public categoriaBean() {
		this.id_categoria =0;
		this.sconto=0;
		this.descrizione="";
		this.in_saldo=false;
	}
	
	
	public categoriaBean(int id_categoria, int sconto, String descrizione, boolean in_saldo) {
		this.id_categoria = id_categoria;
		this.sconto = sconto;
		this.descrizione = descrizione;
		this.in_saldo = in_saldo;
	}
	
	


	public int getId_categoria() {
		return id_categoria;
	}


	public void setId_categoria(int id_categoria) {
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


	public boolean isIn_saldo() {
		return in_saldo;
	}


	public void setIn_saldo(boolean in_saldo) {
		this.in_saldo = in_saldo;
	}
	
	
	




	@Override
	public String toString() {
		return "categoria : [id_categoria=" + id_categoria + ", sconto=" + sconto + ", descrizione=" + descrizione
				+ ", in_saldo=" + in_saldo + "]";
	}







	private int id_categoria,sconto;
	private String descrizione; 
	private boolean in_saldo;
	
}
