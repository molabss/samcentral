package br.com.constran.model.paramswrapper.to_mobile_tabs;

import br.com.constran.mobile.persistence.vo.aprop.ApropriacaoVO;
import br.com.constran.mobile.persistence.vo.aprop.maodeobra.ParalisacaoEquipeVO;

public class ParalisacaoEquipeParams {
	
	private String idApropriacao =null;
	private String dataApontamento = null;
	private String horaApontamento = null;
	private String idEquipe = null;
	private String codParalisacao = null;
	private String idServico = null;
	private String horaInicio = null;
	private String horaTermino = null;
	private String observacao = null;

	
	public ParalisacaoEquipeParams(){
		
	}
	
	public ParalisacaoEquipeParams(ApropriacaoVO itemApropriacao, ParalisacaoEquipeVO itemParalisacaoEquipe){
		
		setIdApropriacao(String.valueOf(itemApropriacao.getId()));
		
		setDataApontamento(itemApropriacao.getDataHoraApontamento());
		
		setHoraApontamento(itemApropriacao.getDataHoraApontamento());
		
		
		if(itemParalisacaoEquipe.getEquipe() != null){
			setIdEquipe(String.valueOf(itemParalisacaoEquipe.getEquipe().getId()));
		}
		
		if(itemParalisacaoEquipe.getParalisacao() != null){
			setCodParalisacao(itemParalisacaoEquipe.getParalisacao().getCodigo());
		}
		
		if(itemParalisacaoEquipe.getServico() != null){
			setIdServico(String.valueOf(itemParalisacaoEquipe.getServico().getId()));
		}
		
		if(itemParalisacaoEquipe.getHoraInicio() != null && itemParalisacaoEquipe.getHoraInicio().length() > 0){
			setHoraInicio(itemParalisacaoEquipe.getHoraInicio());
		}

		
		if(itemParalisacaoEquipe.getHoraTermino() != null && itemParalisacaoEquipe.getHoraTermino().length() > 0){
			setHoraTermino(itemParalisacaoEquipe.getHoraTermino());
		}
		
		if(itemParalisacaoEquipe.getObservacoes() != null && itemParalisacaoEquipe.getObservacoes().length() > 0){
			setObservacao(itemParalisacaoEquipe.getObservacoes());
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
		
		//String[] arrData = dataApontamento.split(" ")[0].split("/");
		//this.dataApontamento = arrData[2]+"-"+arrData[1]+"-"+arrData[0];
		
		String[] arrData = {};
		
		
		try{
			
			arrData = dataApontamento.split(" ")[0].split("/");
			this.dataApontamento = arrData[2]+"-"+arrData[1]+"-"+arrData[0]+" "+dataApontamento.split(" ")[1];
			
		}catch (ArrayIndexOutOfBoundsException e){
			
			arrData = dataApontamento.split("/");
			this.dataApontamento = arrData[2]+"-"+arrData[1]+"-"+arrData[0];
		}
		
		/*
		String[] arrData = dataApontamento.split(" ")[0].split("/");
		
		if(dataApontamento.split(" ")[1].length() > 0) { //TEM HORA ?
			
			this.dataApontamento = arrData[2]+"-"+arrData[1]+"-"+arrData[0]+" "+dataApontamento.split(" ")[1];
		
		} else { // ENTAO POE SO A DATA !
			
			this.dataApontamento = arrData[2]+"-"+arrData[1]+"-"+arrData[0];
		}		
		*/
		
	}

	public String getHoraApontamento() {
		return horaApontamento;
	}

	public void setHoraApontamento(String dataHoraApontamento) {
		
		try{
			
			this.horaApontamento = dataHoraApontamento.split(" ")[1];
			
		}catch(ArrayIndexOutOfBoundsException e){
			
			this.horaApontamento = "";
		}
	}

	public String getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(String idEquipe) {
		this.idEquipe = idEquipe;
	}

	public String getCodParalisacao() {
		return codParalisacao;
	}

	public void setCodParalisacao(String codParalisacao) {
		this.codParalisacao = codParalisacao;
	}

	public String getIdServico() {
		return idServico;
	}

	public void setIdServico(String idServico) {
		this.idServico = idServico;
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
