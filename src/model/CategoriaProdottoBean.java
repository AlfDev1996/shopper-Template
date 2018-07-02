package model;

public class CategoriaProdottoBean {

	
	
	public CategoriaProdottoBean() {
		this.id_categoria_prodotto=0;
		this.categoria= new CategoriaBean();
		this.prodotto= new ProdottoBean();
	}
	
	
	
	public CategoriaProdottoBean(int id,CategoriaBean categoria, ProdottoBean prodotto) {
		this.id_categoria_prodotto=id;
		this.categoria = categoria;
		this.prodotto = prodotto;
	}


	
	

	public int getIdCategoriaProdotto() {
		return id_categoria_prodotto;
	}



	public void setIdCategoriaProdotto(int id_categoria_prodotto) {
		this.id_categoria_prodotto = id_categoria_prodotto;
	}



	public CategoriaBean getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaBean categoria) {
		this.categoria = categoria;
	}
	public ProdottoBean getProdotto() {
		return prodotto;
	}
	public void setProdotto(ProdottoBean prodotto) {
		this.prodotto = prodotto;
	}
	
	
	



	@Override
	public String toString() {
		return "CategoriaProdottoBean [id_categoria_prodotto=" + id_categoria_prodotto + ", categoria=" + categoria
				+ ", prodotto=" + prodotto + "]";
	}






	private int id_categoria_prodotto;
	private CategoriaBean categoria;
	private ProdottoBean prodotto;
	
}
