package br.com.constran.model.paramswrapper.to_mobile_tabs;

import br.com.constran.mobile.persistence.vo.ManutencaoEquipamentoServicosVO;

public class ManutencaoServicosParams {
	
	private String data = null;
	private String idServicoCategoriaEquipamento = null;
	private String idEquipamento = null;
	private String descricaoEquipamento = null;
	private String descricaoServico = null;
	private String horaInicio = null;
	private String horaTermino = null;
	private String observacao = null;
	
	
	public ManutencaoServicosParams(ManutencaoEquipamentoServicosVO itemServico) {
		
		setData(itemServico.getData());
		setIdServicoCategoriaEquipamento(String.valueOf(itemServico.getIdServicoCategoriaEquipamento()));
		setIdEquipamento(String.valueOf(itemServico.getIdEquipamento()));
		setDescricaoEquipamento(itemServico.getDescricaoEquipamento());
		setDescricaoServico(itemServico.getDescricaoServico());
		setHoraInicio(itemServico.getHoraInicio());
		setHoraTermino(itemServico.getHoraTermino());
		setObservacao(itemServico.getObservacao());
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		if(data == null || data.length() == 0) return;
		
		String[] arrData = data.split("/");
		this.data = arrData[2]+"-"+arrData[1]+"-"+arrData[0];
	}


	public String getIdServicoCategoriaEquipamento() {
		return idServicoCategoriaEquipamento;
	}


	public void setIdServicoCategoriaEquipamento(
			String idServicoCategoriaEquipamento) {
		this.idServicoCategoriaEquipamento = idServicoCategoriaEquipamento;
	}


	public String getIdEquipamento() {
		return idEquipamento;
	}


	public void setIdEquipamento(String idEquipamento) {
		this.idEquipamento = idEquipamento;
	}


	public String getDescricaoEquipamento() {
		return descricaoEquipamento;
	}


	public void setDescricaoEquipamento(String descricaoEquipamento) {
		this.descricaoEquipamento = descricaoEquipamento;
	}


	public String getDescricaoServico() {
		return descricaoServico;
	}


	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}


	public String getHoraInicio() {
		return horaInicio;
	}


	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}


	public String getHoraTermino() {
		return horaTermino;
	}


	public void setHoraTermino(String horaTermino) {
		this.horaTermino = horaTermino;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
	
	
	

}
