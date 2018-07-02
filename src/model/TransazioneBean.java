package model;

import java.util.Date;

public class TransazioneBean {

	
	
	public TransazioneBean() {
		this.ordine= new OrdineBean();
		this.tipoPagamento= new TipoPagamentoBean();
		this.importo=0;
		this.data= new Date();
		this.id_transazione=0;
	}
	public int getId_transazione() {
		return id_transazione;
	}
	public void setId_transazione(int id_transazione) {
		this.id_transazione = id_transazione;
	}
	public TransazioneBean(int id_transazione,OrdineBean ordine, TipoPagamentoBean tipoPagamento, double importo, Date data) {
		this.id_transazione = id_transazione;
		this.ordine = ordine;
		this.tipoPagamento = tipoPagamento;
		this.importo = importo;
		this.data = data;
	}
	public OrdineBean getOrdine() {
		return ordine;
	}
	public void setOrdine(OrdineBean ordine) {
		this.ordine = ordine;
	}
	public TipoPagamentoBean getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(TipoPagamentoBean tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public double getImporto() {
		return importo;
	}
	public void setImporto(double importo) {
		this.importo = importo;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	
	
	@Override
	public String toString() {
		return "TransazioneBean [ordine=" + ordine + ", tipoPagamento=" + tipoPagamento + ", importo=" + importo
				+ ", data=" + data + "]";
	}



	private OrdineBean ordine;
	private TipoPagamentoBean tipoPagamento;
	private double importo;
	private Date data;
	private int id_transazione;
	
}
