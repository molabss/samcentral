package br.com.constran.model.paramswrapper.to_import_tabs;

public class ApropMovimEquipParams {

	
	
	private String dataHoraApontamento = null;
	private String idEquipamento = null;
	private String idFrenteObra = null;
	private String idAtividade = null;
	private String tipoApropriacao = null;
	private String observacao = null;
	private String descricao = null;
	
	public ApropMovimEquipParams(){
		
	}
	
	
	//setters---------------------------------------------------------
	public void setDataHoraApontamento(String dataHoraApontamento) {
		this.dataHoraApontamento = dataHoraApontamento;
	}
	public void setIdEquipamento(String idEquipamento) {
		this.idEquipamento = idEquipamento;
	}
	public void setIdFrenteObra(String idFrenteObra) {
		this.idFrenteObra = idFrenteObra;
	}
	public void setIdAtividade(String idAtividade) {
		this.idAtividade = idAtividade;
	}

	public void setTipoApropriacao(String tipoApropriacao) {
		this.tipoApropriacao = tipoApropriacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	//------------------------------------------------------------


	//getters-----------------------------------------------------
	public String getDataHoraApontamento() {
		return dataHoraApontamento;
	}
	public String getIdEquipamento() {
		return idEquipamento;
	}
	public String getIdFrenteObra() {
		return idFrenteObra;
	}
	public String getIdAtividade() {
		return idAtividade;
	}

	public String getTipoApropriacao() {
		return tipoApropriacao;
	}
	public String getObservacao() {
		return observacao;
	}
	public String getDescricao() {
		return descricao;
	}
	//------------------------------------------------------------
	
	
	
}
