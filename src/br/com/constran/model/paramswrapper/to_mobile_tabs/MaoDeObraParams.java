package br.com.constran.model.paramswrapper.to_mobile_tabs;

import br.com.constran.mobile.persistence.vo.aprop.ApropriacaoVO;
import br.com.constran.mobile.persistence.vo.aprop.maodeobra.ApropriacaoMaoObraVO;

public class MaoDeObraParams {
	
	
	private String idApropriacao=null;
	private String dataApontamento=null;
	private String horaApontamento=null;
	private String idEquipe=null;
	private String idPessoa=null;
	private String idServico=null;
	private String horaInicio=null;
	private String horaTermino=null;
	private String observacao=null;

	
	
	public MaoDeObraParams(){
		
	}
	

	public MaoDeObraParams(ApropriacaoVO itemApropriacao, ApropriacaoMaoObraVO itemApropMaoDeObra){
		
		
		if(itemApropMaoDeObra.getObservacoes() != null){
			setObservacao(itemApropMaoDeObra.getObservacoes());
		}
		
		setIdApropriacao(String.valueOf(itemApropriacao.getId()));
		setHoraApontamento(itemApropriacao.getDataHoraApontamento().split(" ")[1]);
		setDataApontamento(itemApropriacao.getDataHoraApontamento());
		setIdEquipe(String.valueOf(itemApropMaoDeObra.getEquipe().getId()));
		setIdPessoa(String.valueOf(itemApropMaoDeObra.getPessoa().getId()));
		setIdServico(String.valueOf(itemApropMaoDeObra.getServico().getId()));
		setHoraInicio(itemApropMaoDeObra.getHoraInicio());
		setHoraTermino(itemApropMaoDeObra.getHoraTermino());
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
		
		if(dataApontamento.length() == 0)return;
		
		String[] arrData = dataApontamento.split(" ")[0].split("/");
		this.dataApontamento = arrData[2]+"-"+arrData[1]+"-"+arrData[0]+" "+getHoraApontamento();
	}


	public String getHoraApontamento() {
		return horaApontamento;
	}


	public void setHoraApontamento(String horaApontamento) {
		
		if(horaApontamento.length() == 0)return;
		
		this.horaApontamento = horaApontamento;
	}


	public String getIdEquipe() {
		return idEquipe;
	}


	public void setIdEquipe(String idEquipe) {
		this.idEquipe = idEquipe;
	}


	public String getIdPessoa() {
		return idPessoa;
	}


	public void setIdPessoa(String idPessoa) {
		this.idPessoa = idPessoa;
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
		
		if(horaInicio != null && horaInicio.length() == 0)return;
		
		this.horaInicio = horaInicio;
	}


	public String getHoraTermino() {
		return horaTermino;
	}


	public void setHoraTermino(String horaTermino) {
		
		if(horaTermino != null && horaTermino.length() == 0)return;
		
		this.horaTermino = horaTermino;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		
		
		if(observacao != null && observacao.length() == 0)return;
		
		this.observacao = observacao;
	}
}
