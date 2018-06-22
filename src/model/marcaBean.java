package model;

public class marcaBean {

	
	
	
	public marcaBean() {
		this.id_marca=0;
		this.descrizione="";
	}
	
	
	public marcaBean(int id_marca, String descrizione) {
		
		this.id_marca = id_marca;
		this.descrizione = descrizione;
	}
	
	
	
	public int getId_marca() {
		return id_marca;
	}


	public void setId_marca(int id_marca) {
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





	private int id_marca;
	private String descrizione;
	
}
