package model;

public class MarcaBean {

	private int id_marca;
	private String descrizione;
	
	public MarcaBean() {
		this.descrizione="";
		this.id_marca=0;
	}
	
	
	public MarcaBean(int id_marca, String descrizione) {
		this.id_marca = id_marca;
		this.descrizione = descrizione;
	}
	
	
	
	public int getIdMarca() {
		return id_marca;
	}


	public void setIdMarca(int id_marca) {
		this.id_marca = id_marca;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	
	


	@Override
	public String toString() {
		return "marca : [id_marca=" + id_marca + ", descrizione=" + descrizione + "]";
	}

	
}
