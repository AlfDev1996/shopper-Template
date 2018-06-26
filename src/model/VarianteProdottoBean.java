package model;

public class VarianteProdottoBean {

	private int id_variante_prodotto;
	private String taglia;
	private int quantita_disponibile;
	private ProdottoBean prodotto;
	
	public VarianteProdottoBean() {
		id_variante_prodotto=0;
		taglia="";
		quantita_disponibile=0;
		prodotto=new ProdottoBean();
	}

	public VarianteProdottoBean(int id_variante_prodotto, String taglia, int quantita_disponibile, ProdottoBean prodotto) {
		this.id_variante_prodotto = id_variante_prodotto;
		this.taglia = taglia;
		this.quantita_disponibile = quantita_disponibile;
		this.prodotto=prodotto;
	}

	public int getId_variante_prodotto() {
		return id_variante_prodotto;
	}

	public void setId_variante_prodotto(int id_variante_prodotto) {
		this.id_variante_prodotto = id_variante_prodotto;
	}

	public String getTaglia() {
		return taglia;
	}

	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}

	public int getQuantita_disponibile() {
		return quantita_disponibile;
	}

	public void setQuantita_disponibile(int quantita_disponibile) {
		this.quantita_disponibile = quantita_disponibile;
	}

	public ProdottoBean getProdotto() {
		return prodotto;
	}

	public void setProdotto(ProdottoBean prodotto) {
		this.prodotto = prodotto;
	}

	@Override
	public String toString() {
		return "VarianteProdottoBean [id_variante_prodotto=" + id_variante_prodotto + ", taglia=" + taglia
				+ ", quantita_disponibile=" + quantita_disponibile + ", prodotto=" + prodotto + "]";
	}
	
	
	
	
}
