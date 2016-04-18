package br.com.constran.model.carga;


public class Contrato {
	
	private String numero;
	private String ccObra;
	private String tipo;
	private Fornecedor fornecedor;
	private String ativo;
	private String objeto;
	private String tipoPeriodo;
	private Integer prazo;
	private String inicio;
	private String termino;
	private String observacao;
	private String formaPagamento;
	private String contato;
	private String dataDevolucao;
	private String valorGlobal;
	
	
	public Contrato(String numero){
		this.numero = numero;
	}
	
	public Contrato(){

	}
	
	
	public Contrato(String numero, String ccObra, String tipo,
			Fornecedor fornecedor, String objeto, String tipoPeriodo,
			Integer prazo, String inicio, String termino, String valorGlobal) {
		super();
		this.numero = numero;
		this.ccObra = ccObra;
		this.tipo = tipo;
		this.fornecedor = fornecedor;
		this.objeto = objeto;
		this.tipoPeriodo = tipoPeriodo;
		this.prazo = prazo;
		this.inicio = inicio;
		this.termino = termino;
		this.setValorGlobal(valorGlobal);
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCcObra() {
		return ccObra;
	}
	public void setCcObra(String ccObra) {
		this.ccObra = ccObra;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	public String getObjeto() {
		return objeto;
	}
	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
	public String getTipoPeriodo() {
		return tipoPeriodo;
	}
	public void setTipoPeriodo(String tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}
	public Integer getPrazo() {
		return prazo;
	}
	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}
	public String getInicio() {
		return inicio;
	}
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}
	public String getTermino() {
		return termino;
	}
	public void setTermino(String termino) {
		this.termino = termino;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	public String getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public String getValorGlobal() {
		return valorGlobal;
	}
	public void setValorGlobal(String valorGlobal) {
		this.valorGlobal = valorGlobal;
	}
	
	
}
