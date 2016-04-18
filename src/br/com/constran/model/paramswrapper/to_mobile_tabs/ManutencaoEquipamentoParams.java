package br.com.constran.model.paramswrapper.to_mobile_tabs;

import br.com.constran.mobile.persistence.vo.ManutencaoEquipamentoVO;

public class ManutencaoEquipamentoParams {
	
	private String data = null;
	private String idEquipamento = null;
	private String descEquipamento = null;
	private String horaInicio = null;
	private String horaTermino = null;
	private String horimetro = null;
	private String hodometro = null;
	private String observacao = null;
	
	

	public ManutencaoEquipamentoParams(ManutencaoEquipamentoVO itemEquipamento) {
		
		setData(itemEquipamento.getData());
		setIdEquipamento(String.valueOf(itemEquipamento.getIdEquipamento()));
		setDescEquipamento(itemEquipamento.getDescricaoEquipamento());
		setHoraInicio(itemEquipamento.getHoraInicio());
		setHoraTermino(itemEquipamento.getHoraTermino());
		setHorimetro(itemEquipamento.getHorimetro());
		setHodometro(itemEquipamento.getHodometro());
		setObservacao(itemEquipamento.getObservacao());
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		
		if(data == null || data.length() == 0) return;
		
		String[] arrData = data.split("/");
		this.data = arrData[2]+"-"+arrData[1]+"-"+arrData[0];
	}


	public String getIdEquipamento() {
		return idEquipamento;
	}


	public void setIdEquipamento(String idEquipamento) {
		this.idEquipamento = idEquipamento;
	}


	public String getDescEquipamento() {
		return descEquipamento;
	}


	public void setDescEquipamento(String descEquipamento) {
		this.descEquipamento = descEquipamento;
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


	public String getHorimetro() {
		return horimetro;
	}


	public void setHorimetro(String horimetro) {
		this.horimetro = horimetro;
	}


	public String getHodometro() {
		return hodometro;
	}


	public void setHodometro(String hodometro) {
		this.hodometro = hodometro;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
	

}
