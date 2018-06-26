package model;

public class ProdottoBean {

	private int id_prodotto;
	private String nome, descrizione_breve, descrizione_estesa, tags, modello, sesso;
	private double prezzo ;
	private MarcaBean marca;
	
	public ProdottoBean() {
		id_prodotto=0;
		
		nome=descrizione_breve=descrizione_estesa=tags=modello=sesso="";
		prezzo=0.0;
		marca=new MarcaBean();
	}

	public ProdottoBean(int id_prodotto, String nome, String descrizione_breve, String descrizione_estesa, String tags,
			String modello, String sesso, double prezzo, MarcaBean marca) {
		
		this.id_prodotto = id_prodotto;
		this.nome = nome;
		this.descrizione_breve = descrizione_breve;
		this.descrizione_estesa = descrizione_estesa;
		this.tags = tags;
		this.modello = modello;
		this.sesso = sesso;
		this.prezzo = prezzo;
		this.marca = marca;
	}

	public int getId_prodotto() {
		return id_prodotto;
	}

	public void setId_prodotto(int id_prodotto) {
		this.id_prodotto = id_prodotto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione_breve() {
		return descrizione_breve;
	}

	public void setDescrizione_breve(String descrizione_breve) {
		this.descrizione_breve = descrizione_breve;
	}

	public String getDescrizione_estesa() {
		return descrizione_estesa;
	}

	public void setDescrizione_estesa(String descrizione_estesa) {
		this.descrizione_estesa = descrizione_estesa;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public MarcaBean getMarca() {
		return marca;
	}

	public void setMarca(MarcaBean marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "ProdottoBean [id_prodotto=" + id_prodotto + ", nome=" + nome + ", descrizione_breve="
				+ descrizione_breve + ", descrizione_estesa=" + descrizione_estesa + ", tags=" + tags + ", modello="
				+ modello + ", sesso=" + sesso + ", prezzo=" + prezzo + ", marca=" + marca + "]";
	}
	
	
	
}
