package br.com.constran.model.paramswrapper.to_mobile_tabs;

import br.com.constran.mobile.persistence.vo.aprop.ApropriacaoVO;
import br.com.constran.mobile.persistence.vo.aprop.eqp.ApropriacaoEquipamentoVO;

public class ParteDiariaEquipamentoParams {

	private String idApropriacao=null;
	private String dataApontamento=null;
	private String horaApontamento=null;
	private String idEquipamento=null;
	private String horimetroInicial=null;
	private String horimetroFinal=null;
	private String percProducao=null;
	private String operador1 = null;
	private String operador2 = null;
	private String observacoes = null;

	
	public ParteDiariaEquipamentoParams() {
		
	}
	
	
	public ParteDiariaEquipamentoParams(ApropriacaoEquipamentoVO itemEqpParteDiaria, ApropriacaoVO itemApropriacao){

		if(itemEqpParteDiaria.getHorimetroIni() != null && itemEqpParteDiaria.getHorimetroIni().length() > 0){
			setHorimetroInicial(itemEqpParteDiaria.getHorimetroIni());
		}
		
		if(itemEqpParteDiaria.getHorimetroFim() != null && itemEqpParteDiaria.getHorimetroFim().length() > 0){
			setHorimetroFinal(itemEqpParteDiaria.getHorimetroFim());
		}
		
		if(itemEqpParteDiaria.getProducao() != null){
			setPercProducao(itemEqpParteDiaria.getProducao());
		}
		
		if(itemEqpParteDiaria.getOperador1() != null && itemEqpParteDiaria.getOperador1().length() > 0){
			setOperador1(itemEqpParteDiaria.getOperador1());
		}
		
		if(itemEqpParteDiaria.getOperador2() != null && itemEqpParteDiaria.getOperador2().length() > 0){
			setOperador2(itemEqpParteDiaria.getOperador2());
		}
		
		if(itemEqpParteDiaria.getObservacoes() != null && itemEqpParteDiaria.getObservacoes().length() > 0){
			setObservacoes(itemEqpParteDiaria.getObservacoes());
		}
		
		setIdApropriacao(String.valueOf(itemApropriacao.getId()));
		
		setHoraApontamento(itemApropriacao.getDataHoraApontamento().split(" ")[1]);
		
		setDataApontamento(itemApropriacao.getDataHoraApontamento());
		
		
		
		
		if(itemEqpParteDiaria.getEquipamento() != null){
			setIdEquipamento(String.valueOf(itemEqpParteDiaria.getEquipamento().getId()));
		}
		
		
	}
	
	
	
	
	
	public String getIdApropriacao() {
		return idApropriacao;
	}
	public void setIdApropriacao(String idApropriacao) {
		this.idApropriacao = idApropriacao;
	}
	public String getDataApontamento() {
		return dataApontamento;
	}
	
	public void setDataApontamento(String dataApontamento) {
		
		if(dataApontamento == null || dataApontamento.length() == 0) return;
		
		
		//System.out.println(dataApontamento);
		
		String[] arrData = dataApontamento.split(" ")[0].split("/");
		this.dataApontamento = arrData[2]+"-"+arrData[1]+"-"+arrData[0]+ " "+getHoraApontamento();
	}
	
	public String getHoraApontamento() {
		return horaApontamento;
	}
	
	public void setHoraApontamento(String horaApontamento) {
		this.horaApontamento = horaApontamento;
	}
	
	public String getIdEquipamento() {
		return idEquipamento;
	}
	
	public void setIdEquipamento(String idEquipamento) {
		this.idEquipamento = idEquipamento;
	}
	
	public String getHorimetroInicial() {
		return horimetroInicial;
	}
	
	public void setHorimetroInicial(String horimetroInicial) {
		this.horimetroInicial = horimetroInicial;
	}
	
	public String getHorimetroFinal() {
		return horimetroFinal;
	}
	
	public void setHorimetroFinal(String horimetroFinal) {
		this.horimetroFinal = horimetroFinal;
	}
	
	public String getPercProducao() {
		return percProducao;
	}
	
	public void setPercProducao(String percProducao) {
		this.percProducao = percProducao;
	}
	
	public String getOperador1() {
		return operador1;
	}
	
	public void setOperador1(String operador1) {
		this.operador1 = operador1;
	}
	
	public String getOperador2() {
		return operador2;
	}
	
	public void setOperador2(String operador2) {
		this.operador2 = operador2;
	}
	
	public String getObservacoes() {
		return observacoes;
	}
	
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	
	
	
	
	
}
