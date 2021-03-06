package model;

import java.util.ArrayList;

public class ProdottoBean {

	private int id_prodotto, quantita;
	private String nome, descrizione_breve, descrizione_estesa, tags, modello, sesso , taglie;
	private double prezzo ;
	private MarcaBean marca;
	private ArrayList<ImmagineBean> immagini;
	private ArrayList<CategoriaBean> categorie;
	
	public ProdottoBean() {
		id_prodotto=0;
		nome=descrizione_breve=descrizione_estesa=tags=modello=sesso="";
		prezzo=0.0;
		marca=new MarcaBean();
		immagini= new ArrayList<>();
		taglie="";
		quantita=0;
		categorie=new ArrayList<>();
	}

	
	public ArrayList<CategoriaBean> getCategorie() {
		return categorie;
	}


	public void setCategorie(ArrayList<CategoriaBean> categorie) {
		this.categorie = categorie;
	}


	public int getQuantita() {
		return quantita;
	}


	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}


	public String getTaglie() {
		return taglie;
	}


	public void setTaglie(String taglie) {
		this.taglie = taglie;
	}


	public ProdottoBean(int id_prodotto, String nome, String descrizione_breve, String descrizione_estesa, String tags,
			String modello, String sesso, double prezzo, MarcaBean marca, ArrayList<ImmagineBean> immagini, String taglie, int quantita, ArrayList<CategoriaBean> categorie) {
		
		this.id_prodotto = id_prodotto;
		this.nome = nome;
		this.descrizione_breve = descrizione_breve;
		this.descrizione_estesa = descrizione_estesa;
		this.tags = tags;
		this.modello = modello;
		this.sesso = sesso;
		this.prezzo = prezzo;
		this.marca = marca;
		this.immagini=immagini;
		this.taglie=taglie;
		this.quantita=quantita;
		this.categorie=categorie;
	}

	public ArrayList<ImmagineBean> getImmagini() {
		return immagini;
	}

	public void setImmagini(ArrayList<ImmagineBean> immagini) {
		this.immagini = immagini;
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
		return "ProdottoBean [id_prodotto=" + id_prodotto + ", quantita=" + quantita + ", nome=" + nome
				+ ", descrizione_breve=" + descrizione_breve + ", descrizione_estesa=" + descrizione_estesa + ", tags="
				+ tags + ", modello=" + modello + ", sesso=" + sesso + ", taglie=" + taglie + ", prezzo=" + prezzo
				+ ", marca=" + marca  + ", immagini=" + immagini + "]";
	}


	
	
	
	
	public void inizializzaCategorie() {
		if(this!=null && this.id_prodotto!=0)
		{
			CategoriaProdottoDAO catProdDao=new CategoriaProdottoDAO();
			ArrayList<CategoriaProdottoBean> catProdBean = catProdDao.doRetriveByProdotto(this.id_prodotto);
			for (CategoriaProdottoBean categoriaProdottoBean : catProdBean) {
				this.getCategorie().add(categoriaProdottoBean.getCategoria());
				
				//prodotto.getCategorie().add(categoriaProdottoBean.getCategoria());
			}
		}
		
	}

	
	
	
	
}
