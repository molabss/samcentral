package br.com.constran.model.paramswrapper.to_mobile_tabs;

import br.com.constran.mobile.persistence.vo.aprop.ApropriacaoVO;
import br.com.constran.mobile.persistence.vo.aprop.maodeobra.ApropriacaoServicoVO;

public class ServicoParams {
	
	
	private String idApropriacao = null;
	private String dataApontamento = null;
	private String horaApontamento = null;
	private String idEquipe = null;
	private String idServico = null;
	private String qtdProduzida = null;
	private String horaInicioServico = null;
	private String horaTerminoServico = null;
	private String observacao = null;
	
	
	public ServicoParams() {
		
	}
	
	
	public ServicoParams(ApropriacaoVO itemApropriacao, ApropriacaoServicoVO itemApropServico) {
		
		setIdApropriacao(String.valueOf(itemApropriacao.getId()));
		setHoraApontamento(itemApropriacao.getDataHoraApontamento());
		setDataApontamento(itemApropriacao.getDataHoraApontamento());

		
		if(itemApropServico.getQuantidadeProduzida() != null) {
			setQtdProduzida(String.valueOf(itemApropServico.getQuantidadeProduzida()));
		}
		
		if(itemApropServico.getObservacoes() != null && itemApropServico.getObservacoes().length() > 0) {
			setObservacao(itemApropServico.getObservacoes());
		}
		
		if(itemApropServico.getHoraFim() != null && itemApropServico.getHoraFim().length() > 0){
			setHoraTerminoServico(itemApropServico.getHoraFim());
		}
		
		
		setIdEquipe(String.valueOf(itemApropServico.getEquipe().getId()));
		
		setIdServico(String.valueOf(itemApropServico.getServico().getId()));
		
		
		if(itemApropServico.getHoraIni().length() > 0){
			
			setHoraInicioServico(itemApropServico.getHoraIni());	
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
		
		String[] arrData = {};
		
		try{
			
			arrData = dataApontamento.split(" ")[0].split("/");
			this.dataApontamento = arrData[2]+"-"+arrData[1]+"-"+arrData[0]+" "+getHoraApontamento();
			
		}catch (ArrayIndexOutOfBoundsException e){
			
			arrData = dataApontamento.split("/");
			this.dataApontamento = arrData[2]+"-"+arrData[1]+"-"+arrData[0];
		}
		
		//String[] arrData = dataApontamento.split(" ")[0].split("/");
		//this.dataApontamento = arrData[2]+"-"+arrData[1]+"-"+arrData[0]+" "+getHoraApontamento();
	}
	
	public String getHoraApontamento() {
		return horaApontamento;
	}
	public void setHoraApontamento(String dataHoraApontamento) {
		
		try{
			
			this.horaApontamento = dataHoraApontamento.split(" ")[1];
			
		}catch (ArrayIndexOutOfBoundsException e){
			
			this.horaApontamento ="";
		}
	}
	public String getIdEquipe() {
		return idEquipe;
	}
	public void setIdEquipe(String idEquipe) {
		this.idEquipe = idEquipe;
	}
	public String getIdServico() {
		return idServico;
	}
	public void setIdServico(String idServico) {
		this.idServico = idServico;
	}
	public String getQtdProduzida() {
		return qtdProduzida;
	}
	public void setQtdProduzida(String qtdProduzida) {
		this.qtdProduzida = qtdProduzida;
	}
	public String getHoraInicioServico() {
		return horaInicioServico;
	}
	public void setHoraInicioServico(String horaInicioServico) {
		this.horaInicioServico = horaInicioServico;
	}
	public String getHoraTerminoServico() {
		return horaTerminoServico;
	}
	public void setHoraTerminoServico(String horaTerminoServico) {
		this.horaTerminoServico = horaTerminoServico;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	
	
}
