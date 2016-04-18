package br.com.constran.model.paramswrapper.to_mobile_tabs;

import br.com.constran.mobile.persistence.vo.aprop.ApropriacaoVO;

public class ApropriacaoParams {


	private String dataApontamento = null;
	private String idFrenteObra = null;
	private String idAtividade = null;
	private String idApropriacao = null;
	private String tipoApropriacao = null;
	private String observacao = null;
	
	
	
	public ApropriacaoParams(){
		
	}
	
	
	public ApropriacaoParams(ApropriacaoVO itemApropriacao){
		
		setDataApontamento(itemApropriacao.getDataHoraApontamento());
		setIdFrenteObra(String.valueOf(itemApropriacao.getAtividade().getFrenteObra().getId()));
		setIdAtividade(String.valueOf(itemApropriacao.getAtividade().getIdAtividade()));
		setIdApropriacao(String.valueOf(itemApropriacao.getId()));
		setTipoApropriacao(itemApropriacao.getTipoApropriacao());
		setObservacao(itemApropriacao.getObservacoes());
	}


	public String getDataApontamento() {
		return dataApontamento;
	}


	public void setDataApontamento(String dataApontamento) {
		
		String[] arrData = {};
		
		//28/01/2016 00:00:000
		
		try{ //TEM HORA ?
			
			arrData = dataApontamento.split(" ")[0].split("/");
			
			                       //data                                      //hora
			this.dataApontamento = arrData[2]+"-"+arrData[1]+"-"+arrData[0]+" "+dataApontamento.split(" ")[1];
		
		}catch (ArrayIndexOutOfBoundsException e){// ENTAO POE SO A DATA !
			
			arrData = dataApontamento.split("/");
			
			this.dataApontamento = arrData[2]+"-"+arrData[1]+"-"+arrData[0];
		}
		
		
		/*
		if(dataApontamento.split(" ")[1].length() > 0) { //TEM HORA ?
			this.dataApontamento = arrData[2]+"-"+arrData[1]+"-"+arrData[0]+" "+dataApontamento.split(" ")[1];
		} else { // ENTAO POE SO A DATA !
			this.dataApontamento = arrData[2]+"-"+arrData[1]+"-"+arrData[0];
		}
		*/		
	}


	public String getIdFrenteObra() {
		return idFrenteObra;
	}


	public void setIdFrenteObra(String idFrenteObra) {
		this.idFrenteObra = idFrenteObra;
	}


	public String getIdAtividade() {
		return idAtividade;
	}


	public void setIdAtividade(String idAtividade) {
		this.idAtividade = idAtividade;
	}


	public String getIdApropriacao() {
		return idApropriacao;
	}


	public void setIdApropriacao(String idApropriacao) {
		this.idApropriacao = idApropriacao;
	}


	public String getTipoApropriacao() {
		return tipoApropriacao;
	}


	public void setTipoApropriacao(String tipoApropriacao) {
		this.tipoApropriacao = tipoApropriacao;
	}


	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		
		if(observacao == null || observacao.length() == 0)return;
		
		this.observacao = observacao;
	}
	
}
